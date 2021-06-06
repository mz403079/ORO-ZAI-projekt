<template>
  <PostViewer v-bind:postsToDisplay="this.posts"></PostViewer>
</template>

<script>
import PostViewer from "@/components/PostViewer";
import instance from "@/server";
import setLikes from "@/setLikes";

export default {
  name: "PostsWithTag",
  components: {PostViewer},
  data() {
    return {
      posts: [],
    }
  },
  created() {
    this.getPostsWithTag();
  },
  methods: {
    getPostsWithTag() {
      instance.get("/api/tag/" + this.$route.params.id)
      .then((response) => {
        this.posts = response.data;
        setLikes(this.posts);
      })
    }
  },
  watch:{
    $route (){
      this.getPosts();
    }
  }
}
</script>

<style scoped>

</style>