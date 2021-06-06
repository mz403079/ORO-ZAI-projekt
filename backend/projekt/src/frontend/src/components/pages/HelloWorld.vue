<template>
    <PostViewer v-bind:postsToDisplay="this.posts"></PostViewer>
</template>

<script>
import PostViewer from "@/components/PostViewer";
import instance from "@/server";
import setLikes from "@/setLikes"

export default {
  name: 'HelloWorld',
  components: { PostViewer},
  data() {
    return {
      posts: [],
      posty: [],
    }
  },
  created() {
    this.getPosts();
    this.getPostsy();
  },
  methods: {
    getPosts() {
      instance.get("/api/getPosts")
      .then((response) => {
        this.posts = response.data;
        setLikes(this.posts);
      })
    },
    //TESTING QUERY SEARCH
    getPostsy() {
      instance.get("/api/search/1234")
      .then((response) => {
        this.posty = response.data;
        console.log(this.posty);
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

#post-viewer-wrapper {
  margin-top: 5px;
}

@media only screen and (min-width: 900px) {
  .contentWrapper {
    width: 85%;
    margin: 0 auto;
  }
}
</style>
