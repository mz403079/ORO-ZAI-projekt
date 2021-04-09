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
  <NewPostForm></NewPostForm>
    </div>
  </div>
</template>

<script>
import PostViewer from "@/components/PostViewer";
import PopularTags from "@/components/PopularTags";
import NewPostForm from "@/components/NewPostForm";
import instance from "@/server";

export default {
  name: 'HelloWorld',
  components: {NewPostForm, PopularTags, PostViewer},
  data() {
    return {
      msg: [],
    }
  },
  created() {
    instance.get("/api/getPosts")
        .then((response) => {
          console.log(response.data);
          this.msg = response.data;
        })
    instance.get("/api/getPostTag")
    .then((response) => {
      console.log(response.data);
    })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>



.contentWrapper {
  width: 70%;
  margin: 0 auto;
}
</style>
