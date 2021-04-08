<template>
  <div id="app">
    <div class="b-container contentWrapper">
      <b-row>
        <b-col md="8">
          <PostViewer v-bind:postsToDisplay="this.msg"></PostViewer>
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
import PostViewer from "@/components/PostViewer";
import PopularTags from "@/components/PopularTags";
import instance from "@/server";
export default {
  name: "PostsWithTag",
  components: {PostViewer, PopularTags},
  data() {
    return {
      msg:null,
    }
  },
  created() {
    instance.get("/api/tag/"+this.$route.params.id)
    .then((response) => {
      this.msg = response.data;
    })
  }
}
</script>

<style scoped>

</style>