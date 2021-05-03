<template>
  <div v-if="post" id="app">
    <div class="b-container contentWrapper">
      <b-row>
        <b-col>
          <h3> {{ post.title }}
            <b-badge>Hot</b-badge>
            <b-badge :href="'/tag/'+tag.tagId" v-for="tag in post.tags" v-bind:key="tag.tagId" class="tagBadge">
              {{ tag.tagName }}
            </b-badge>
          </h3>
        </b-col>
      </b-row>
      <b-row>
        <h5>
          <b-avatar>JW</b-avatar>
          {{ post.author.username }} posted {{ post.timePosted }}
        </h5>
      </b-row>
      <b-row v-if="post.imageData != null">
        <img :src="`data:image/png;base64, ${post.imageData}`">
      </b-row>
      <CommentViewer v-bind:comments="post.comments"></CommentViewer>
      <NewCommentForm v-bind:post-id="post.postId"></NewCommentForm>
    </div>

  </div>
</template>

<script>
import instance from "@/server";
import NewCommentForm from "@/components/NewCommentForm";
import CommentViewer from "@/components/CommentViewer";
import authHeader from "@/authHeader";

export default {
  name: "SinglePostAdvanced",
  components: {CommentViewer, NewCommentForm},
  data() {
    return {
      post: null,
    }
  },
  created() {
    this.getPost();
  },
  methods: {
    getPost() {
      instance.get("/api/post/" + this.$route.params.id, {headers: authHeader()})
          .then((response) => {
            this.post = response.data;
          })
    }
  }
}
</script>

<style scoped>
.tagBadge {
  margin-left: 5px;
}
</style>