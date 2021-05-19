<template>
  <div>
    <div class="b-container comment" v-for="comment in comments" :key="comment.commentId">
      {{ comment.commentAuthor.username }} said {{ comment.content }}
    </div>
  </div>
</template>

<script>
export default {
  name: "CommentViewer",
  props: {
    comments: Array,
  }
}
</script>

<style scoped>

</style>