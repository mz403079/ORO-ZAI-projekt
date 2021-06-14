<template>
  <div>
    <div class="user" v-if="user">
      <h2>
        <div class="items">
          <div>
            <b-avatar size="10rem" v-if="user.profileImage != null">
              <img class="avatar-icon"
                   :src="`data:image/png;base64, ${user.profileImage.imageData}`"
                   alt="${item.title}">
            </b-avatar>
            {{ user.username }}
          </div>
          <div v-if="userIsAdmin">
            <router-link :to="{ path: '#'}">
              <b-icon color="red" icon="trash" @click="deleteUser()"></b-icon>
            </router-link>
          </div>
        </div>
      </h2>
      <div v-if="checkId">
      <form id="profilePicForm"
            @submit="checkForm">
          <!-- Styled -->
          <b-form-file
              v-model="selectedFile"
              :state="Boolean(selectedFile)"
              placeholder="Choose a file or drop it here..."
              drop-placeholder="Drop file here..."
          ></b-form-file>
          <div class="mt-3">Selected file: {{ selectedFile ? selectedFile.name : '' }}</div>

        <b-button class="custom-button" type="submit" variant="primary">Submit</b-button>
      </form>
      </div>
    </div>
    <PostViewer v-bind:postsToDisplay="posts"></PostViewer>
    <Pager v-bind:path="this.$router.currentRoute.path" v-bind:pages="this.pages"
           v-bind:current-page="currentPage"></Pager>
  </div>
</template>

<script>
import PostViewer from "@/components/PostViewer";
import instance from "@/server";
import setLikes from "@/setLikes";
import Pager from "@/components/Pager";
import isUserAdmin from "@/isUserAdmin";
import authHeader from "@/authHeader";
import multipartHeaders from "@/multipartHeader";

export default {
  name: 'UserPosts',
  components: {PostViewer, Pager},
  data() {
    return {
      posts: [],
      pages: 0,
      currentPage: 1,
      user: null,
      picName: "",
      selectedFile: null,
      formData: null,
    }
  },
  created() {
    this.$on('ChangeView', section => {
      if(section === "refresh")
        this.getPosts();
    });
    this.getPosts();
  },
  computed: {
    userIsAdmin() {
      return isUserAdmin();
    },
    checkId() {
      let jsonUser = JSON.parse(localStorage.user);
      return this.user.userId === jsonUser.id;
    }
  },
  methods: {
    fileSelected(event) {
      this.selectedFile = event.target.files[0];
    },
    checkForm() {
      event.preventDefault();
      this.formData = new FormData();
      this.formData.append("file", this.selectedFile);
      this.formData.append("userId", this.user.id);
      console.log(this.formData);
      instance.post("/api/profile/" + this.user.userId + "/pic", this.formData,
          {headers: multipartHeaders})
    },
    getPosts() {
      instance.get(
          "/api/getUserPosts/" + this.$route.params.username + '/' + this.$route.query.page)
      .then((response) => {
        this.pages = response.data.pages;
        this.posts = response.data.posts;
        this.user = this.posts[0].author;
        console.log(this.user)
        setLikes(this.posts);
      })
    },
    deleteUser() {
      console.log(this.user)
      instance.delete("/api/profile/deleteUser/"+this.user.userId, {headers: authHeader()}).then(() => {
       console.log("deleted user");
      });
    }
  },
  watch: {
    $route() {
      this.currentPage = parseInt(this.$route.query.page, 10);
      this.getPosts();
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.avatar-icon {
  width: 100%;
  height: 100%;
}

.items {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.user {
  background-color: #FBF1FF;
  padding: 20px;
  margin-bottom: 15px;
}
.custom-button {
  margin: 10px 0 10px;
  background-color: #A42CD6;
  border-color: #A42CD6;
}

.custom-button:hover {
  background-color: #C44EF6;
  border-color: #C44EF6;
}
</style>