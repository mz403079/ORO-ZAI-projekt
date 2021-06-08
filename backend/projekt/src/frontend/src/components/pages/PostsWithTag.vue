<template>
  <div>
  <PostViewer v-bind:postsToDisplay="this.posts"></PostViewer>
  <Pager v-bind:path="this.$router.currentRoute.path" v-bind:pages="this.pages" v-bind:current-page="currentPage"></Pager>
  </div>
</template>

<script>
import PostViewer from "@/components/PostViewer";
import instance from "@/server";
import setLikes from "@/setLikes";
import Pager from "@/components/Pager";
export default {
  name: "PostsWithTag",
  components: {PostViewer, Pager},
  data() {
    return {
      posts: [],
      pages: 0,
      currentPage: 1,
    }
  },
  created() {
    this.getPosts();
  },
  methods: {
    getPosts() {
      instance.get("/api/tag/" + this.$route.params.id + '/' + this.$route.query.page)
      .then((response) => {
        this.pages = response.data.pages;
        this.posts = response.data.posts;
        setLikes(this.posts);
      })
    }
  },
  watch:{
    $route (){
      this.currentPage = parseInt(this.$route.query.page, 10);
      this.getPosts();
    }
  }
}
</script>

<style scoped>

</style>