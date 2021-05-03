<template>
  <PostViewer v-bind:postsToDisplay="this.posts"></PostViewer>
</template>

<script>
import PostViewer from "@/components/PostViewer";
import instance from "@/server";

export default {
  name: "Home",
  components: {PostViewer},
  data() {
    return {
      posts: null,
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

<style scoped>

</style>