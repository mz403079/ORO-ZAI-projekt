<template>
  <div class="formWrapper">
    <form id="commentForm"
          @submit="checkForm">
      <p>
        <label>Komentarz
          <input type="text" v-model="comment">
        </label>
      </p>
      <p>
        <input type="submit" value="Dodaj">
      </p>
    </form>
  </div>
</template>

<script>
import headers from "@/headers";
import instance from "@/server";
export default {
  name: "NewCommentForm",
  props: {
    postId: Number,
  },
  data () {
    return {
      comment:"",
    }
  },

  methods : {
    checkForm() {
      event.preventDefault();
      let json = JSON.stringify({
            "comment": this.comment,
            "postId": this.postId,
            "authorId": 1,
          })
      console.log(json);
      instance.post("/api/addComment",json, {headers : headers})
    }
  }
}
</script>

<style scoped>

</style>