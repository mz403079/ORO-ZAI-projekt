<template>
  <div v-for ="item in msg " :key="item.postId">
    <h1 >Tytuł: {{item.title}}</h1>
    <h2>Autor Id: {{item.authorId}}</h2>
    <p>{{item.content}}</p>
    tagi:
    <a v-for ="tag in item.tags" :key="tag.tagId"> #{{tag.tagName}}</a>
  </div>
</template>

<script>
import instance from "@/server";
export default {
  name: 'HelloWorld',
  data(){
    return {
      msg: []
    }
  },
  created() {
      instance.get("/api/tag/2")
        .then((response) =>{
          console.log(response.data);
          this.msg = response.data;
        })
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
