<template>
  <div id="app">
    <div id="nav-wrapper">
      <nav>
        <div>
          <router-link to="/home">
            <div class="link-wrapper">
              <div class="nav-icon">
                <b-icon icon="house-door-fill"></b-icon>
              </div>
              <div class="nav-text d-none d-lg-block">
                <span>Home</span>
              </div>
            </div>
          </router-link>

          <router-link v-bind:to="'/my-wall/'">
            <div class="link-wrapper">
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
          <router-link to="/topTags">
            <div class="link-wrapper">
              <div class="nav-icon">
                <b-icon icon="hash"></b-icon>
              </div>
              <div class="nav-text d-none d-lg-block">
                <span>Top 100</span>
              </div>
            </div>
          </router-link>

          <router-link to="/trending-posts">
            <div class="link-wrapper">
              <div class="nav-icon">
                <b-icon icon="arrow-up-right-square"></b-icon>
              </div>
              <div class="nav-text d-none d-lg-block">
                <span>Trending posts</span>
              </div>
            </div>
          </router-link>
          <router-link to="Help">
            <div class="link-wrapper">
              <div class="nav-icon">
                <b-icon icon="question-circle"></b-icon>
              </div>
              <div class="nav-text d-none d-lg-block">
                <span>Help</span>
              </div>
            </div>
          </router-link>
          <router-view/>
        </div>
      </nav>
    </div>
    <b-modal id="newPostModal" title="BootstrapVue">
      <NewPostForm></NewPostForm>
    </b-modal>
  </div>
</template>

<script>
import NewPostForm from "@/components/NewPostForm";

export default {
  name: "SideNavigation",
  components: {
    NewPostForm,
  },
  data() {
    return {
      user: null,
    }
  },
  created() {
    this.getUser();
  },
  methods: {
    getUser() {
      this.user = JSON.parse(localStorage.user);
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

a {
  text-decoration: none;
}

.link-wrapper {
  color: black;
  font-size: 20px;
  font-weight: 700;
  padding: 12px 0 12px;
  border-radius: 9999px;
}

.link-wrapper:hover {
  padding-left: 20px;
  color: #A42CD6;
  background-color: #FBF1FF;
}
</style>