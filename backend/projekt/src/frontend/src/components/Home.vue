<template>
  <PostViewer v-bind:postsToDisplay="this.msg"></PostViewer>
</template>

<script>
import PostViewer from "@/components/PostViewer";
import instance from "@/server";

export default {
  name: "Home",
  components: {PostViewer},
  data() {
    return {
      msg: null,
    }
  },
  created() {
    instance.get("/api/getPosts")
        .then((response) => {
          this.msg = response.data;
        })
  }
}
</script>

<style scoped>

</style>