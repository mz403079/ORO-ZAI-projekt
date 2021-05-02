<template>
  <div id="app">
    <div id="nav-wrapper">
      <nav>
        <div>
          <b-link v-on:click="$parent.componentName='Home'" class="link">
            <div class="link-wrapper">
              <div class="nav-icon">
                <b-icon icon="house-door-fill"></b-icon>
              </div>
              <div class="nav-text">
                <span>Home</span>
              </div>
            </div>
          </b-link>
          <b-link v-on:click="$parent.componentName='UserHome'" class="link">
            <div class="link-wrapper">
              <div class="nav-icon">
                <b-icon icon="person-circle"></b-icon>
              </div>
              <div class="nav-text">
                <span>Profile</span>
              </div>
            </div>
          </b-link>
          <b-link v-b-modal.newPostModal class="link">
            <div class="link-wrapper">
              <div class="nav-icon">
                <b-icon icon="plus-circle"></b-icon>
              </div>
              <div class="nav-text">
                <span>New post</span>
              </div>
            </div>
          </b-link>
          <b-link v-on:click="$parent.componentName='TopTags'" class="link">
            <div class="link-wrapper">
              <div class="nav-icon">
                <b-icon icon="hash"></b-icon>
              </div>
              <div class="nav-text">
                <span>Top 100</span>
              </div>
            </div>
          </b-link>
          <b-link v-on:click="$parent.componentName='TrendingPosts'" class="link">
            <div class="link-wrapper">
              <div class="nav-icon">
                <b-icon icon="arrow-up-right-square"></b-icon>
              </div>
              <div class="nav-text">
                <span>Trending posts</span>
              </div>
            </div>
          </b-link>
          <b-link class="link">
            <div class="link-wrapper">
              <div class="nav-icon">
                <b-icon icon="question-circle"></b-icon>
              </div>
              <div class="nav-text">
                <span>Help</span>
              </div>
            </div>
          </b-link>
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
  components : {
    NewPostForm,
  },
}
</script>

<style scoped>

.link-wrapper {
  display: flex;
}

.nav-icon {
  width: 20%;
}

.nav-text {
  width: 80%;
}
.link {
  text-decoration: none;
}
.link-wrapper {
  color: black;
  font-size: 20px;
  font-weight: 700;
  padding: 12px;
  border-radius: 9999px;
}
.link-wrapper:hover {
  color: #A42CD6;
  background-color: #FBF1FF;
}
</style>