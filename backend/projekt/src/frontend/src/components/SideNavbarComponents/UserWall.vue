<template>
  <PostViewer v-bind:postsToDisplay="this.posts"></PostViewer>
</template>

<script>
import PostViewer from "@/components/PostViewer";
import instance from "@/server";
import setLikes from "@/setLikes";

export default {
  name: 'UserWall',
  components: {PostViewer},
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
      let user = JSON.parse(localStorage.user);
      instance.get("/api/getProfilePosts/" + user.id)
          .then((response) => {
            this.posts = response.data;
            setLikes(this.posts);
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

@media only screen and (min-width: 900px) {
  .contentWrapper {
    width: 85%;
    margin: 0 auto;
  }
}
</style>