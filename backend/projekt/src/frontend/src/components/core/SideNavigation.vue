<template>
  <div id="app">
    <div id="nav-wrapper">
      <nav>
        <div class="wrapper">
          <router-link to="/home">
            <div id="/home" class="link-wrapper">
              <div class="nav-icon">
                <b-icon icon="house-door-fill"></b-icon>
              </div>
              <div class="nav-text d-none d-lg-block">
                <span>Home</span>
              </div>
            </div>
          </router-link>
          <router-link to="/my-wall">
            <div id="/my-wall" class="link-wrapper">
              <div class="nav-icon">
                <b-icon icon="person-circle"></b-icon>
              </div>
              <div class="nav-text d-none d-lg-block">
                <span>Profile</span>
              </div>
            </div>
          </router-link>
          <b-link v-b-modal.newPostModal class="link">
            <div class="link-wrapper">
              <div class="nav-icon">
                <b-icon icon="plus-circle"></b-icon>
              </div>
              <div class="nav-text d-none d-lg-block">
                <span>New post</span>
              </div>
            </div>
          </b-link>
          <router-link to="/top-tags">
            <div id="/top-tags" class="link-wrapper">
              <div class="nav-icon">
                <b-icon icon="hash"></b-icon>
              </div>
              <div class="nav-text d-none d-lg-block">
                <span>Top 100</span>
              </div>
            </div>
          </router-link>
          <router-link to="/trending-posts">
            <div id="/trending-posts" class="link-wrapper">
              <div class="nav-icon">
                <b-icon icon="arrow-up-right-square"></b-icon>
              </div>
              <div class="nav-text d-none d-lg-block">
                <span>Trending posts</span>
              </div>
            </div>
          </router-link>
          <router-link to="/help">
            <div id="/help" class="link-wrapper">
              <div class="nav-icon">
                <b-icon icon="question-circle"></b-icon>
              </div>
              <div class="nav-text d-none d-lg-block">
                <span>Help</span>
              </div>
            </div>
          </router-link>
        </div>
      </nav>
    </div>
    <b-modal hide-footer id="newPostModal" title="Say something!">
      <NewPostForm></NewPostForm>
    </b-modal>
  </div>
</template>

<script>
import NewPostForm from "@/components/forms/PostForm";

export default {
  name: "SideNavigation",
  components: {
    NewPostForm,
  },
  data() {
    return {
      user: null,
      pageElement: null,
    }
  },
  created() {
    // this.getUser();
  },
  methods: {
    getUser() {
      this.user = JSON.parse(localStorage.user);
    },
    toggleCurrentPage() {
      if (this.pageElement !== null) {
        this.pageElement.classList.remove("current");
      }
      let currentPage = this.$router.currentRoute;
      this.pageElement = document.getElementById(currentPage.path);
      if(this.pageElement !== null)
        this.pageElement.classList.add("current");
    }
  },
  watch: {
    $route() {
      this.toggleCurrentPage();
    }
  }
}
</script>

<style scoped>

.link-wrapper {
  display: flex;
}

.nav-icon {
  width: 15%;
}

.nav-text {
  width: 75%;
}

a, a:hover {
  text-decoration: none;
}

.link-wrapper {
  color: black;
  font-size: 20px;
  font-weight: 700;
  padding: 12px 0 10px;
  border-radius: 9999px;
}

.current {
  color: #A42CD6;
}

.link-wrapper:hover {
  color: #A42CD6;
}

@media only screen and (min-width: 600px) {
  .link-wrapper:hover {
    padding-left: 20px;
    background-color: #FBF1FF;
  }
}
</style>