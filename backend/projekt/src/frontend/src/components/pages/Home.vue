<template>
  <div>
    <PostViewer v-bind:postsToDisplay="this.posts"></PostViewer>
    <Pager v-bind:path="this.$router.currentRoute.path" v-bind:pages="this.pages"
           v-bind:current-page="this.currentPage"></Pager>
  </div>
</template>

<script>
import PostViewer from "@/components/PostViewer";
import instance from "@/server";
import setLikes from "@/setLikes"
import Pager from "@/components/Pager";

export default {
  name: 'Home',
  components: {Pager, PostViewer},
  data() {
    return {
      posts: [],
      pages: 0,
      currentPage: 1,
    }
  },
  created() {
    this.$on('ChangeView', section => {
      if(section === "refresh")
        this.getPosts();
    });
    if (!this.$route.query.page) {
      this.currentPage = 1;
    } else {
      this.currentPage = parseInt(this.$route.query.page);
    }
    this.getPosts();
  },
  methods: {
    getPosts() {
      let query = "";
      if (!this.$route.query.page) {
        query = "/api/getPosts/1"
      } else {
        query = "/api/getPosts/" + this.$route.query.page
      }
      instance.get(query)
      .then((response) => {
        this.pages = response.data.pages;
        this.posts = response.data.posts;
        setLikes(this.posts);
      })
    },
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
.disabled {
  pointer-events: none;
  cursor: default;

}
</style>
