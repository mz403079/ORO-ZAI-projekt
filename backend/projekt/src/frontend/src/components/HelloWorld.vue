<template>
  <div id="app">
    <div class="b-container contentWrapper">
      <b-row>
        <b-col md="2">
          <div>
            <SideNavigation></SideNavigation>
          </div>
        </b-col>
        <b-col md="5" offset-md="1">
          <div id="post-viewer-wrapper">
            <PostViewer v-bind:postsToDisplay="this.posts"></PostViewer>
          </div>
        </b-col>
        <b-col offset-md="1">
          <b-row>
            <PopularTags></PopularTags>
          </b-row>
          <b-row>
            <PopularTags></PopularTags>
          </b-row>
        </b-col>
      </b-row>
    </div>
  </div>
</template>

<script>
import PopularTags from "@/components/PopularTags";
import SideNavigation from "@/components/SideNavigation";
import PostViewer from "@/components/PostViewer";
import instance from "@/server";

export default {
  name: 'HelloWorld',
  components: {PopularTags, SideNavigation, PostViewer},
  data() {
    return {
      posts: [],
    }
  },
  created() {
    this.getPosts();
  },
  methods: {
    getPosts() {
      instance.get("/api/getPosts")
          .then((response) => {
            this.posts = response.data;
          })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

#post-viewer-wrapper {
  margin-top: 5px;
  border: solid #273E47 1px;
}

.contentWrapper {
  width: 85%;
  margin: 0 auto;
}
</style>
