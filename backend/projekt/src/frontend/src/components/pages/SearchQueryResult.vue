<template>
  <PostViewer v-bind:postsToDisplay="this.posts"></PostViewer>
</template>

<script>
import instance from "@/server";
import PostViewer from "@/components/PostViewer";

export default {
  name: "SearchQueryResult",
  components: {PostViewer},
  props: {
    search: String,
  },
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
      instance.get("/api/search/" + this.search)
          .then((response) => {
            this.posts = response.data;
            console.log("refng");
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