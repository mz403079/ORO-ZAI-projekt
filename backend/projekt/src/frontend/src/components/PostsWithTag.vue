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
  }
}
</script>

<style scoped>
#post-viewer-wrapper {
  margin-top: 5px;
  border: solid #273E47 1px;
}

@media only screen and (min-width: 900px) {
  .contentWrapper {
    width: 85%;
    margin: 0 auto;
  }
}
</style>