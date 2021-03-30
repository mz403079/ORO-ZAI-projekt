<template>
  <div class="container">
    <div v-for="item in msg " :key="item.postId">
      <h1>Tytuł: {{ item.title }}</h1>
      <h2>Nick autora: {{ item.author.username }}</h2>
      <p>{{ item.content }}</p>
      tagi:
      <a v-for="tag in item.tags" :key="tag.tagId"> #{{ tag.tagName }}</a>
      <img :src="`data:image/png;base64, ${item.imageData}`">
    </div>
    <form id="postForm"
          @submit="checkForm">
      <p>
        <label>Tytul
          <input type="text" v-model="title">
        </label>
      </p>
      <p>
        <label>Tresc
          <input type="text" v-model="content">
        </label>
      </p>
      <p>
        <label>
          <input type="file" @change="fileSelected">
        </label>
      </p>
      <p>
        <input type="submit" value="Dodaj">
      </p>
    </form>
    <div>
        <div v-for="tag in tags" v-bind:key="tag.tagId" class="tagButton">
          <b-button pill variant="secondary" v-on:click="addTag($event,tag)"> {{tag.tagName}} </b-button>
        </div>
    </div>
    {{ selected }}
  </div>
</template>

<script>
import instance from "@/server";
import multipartHeaders from "@/multipartHeader";

export default {
  name: 'HelloWorld',
  props: ['img'],
  data() {
    return {
      selected: [],
      msg: [],
      title: "",
      content: "",
      authorId: 0,
      picName: "",
      tags: null,
      selectedFile: null,
      formData: null,
    }
  },
  methods: {
    fileSelected(event) {
      this.selectedFile = event.target.files[0];
      this.getTags();
      console.log(event);
    },
    checkForm() {
      event.preventDefault();
      console.log(this.selectedFile);
      this.formData = new FormData();
      this.formData.append("title", this.title);
      this.formData.append("content", this.content);
      this.formData.append("file", this.selectedFile);
      this.formData.append("tags",this.selected);
      this.formData.append("authorId",1);
      console.log(this.formData);
      instance.post("/api/addPost", this.formData, {headers: multipartHeaders})
    },
    addTag(e,tag) {
      let el = e.target;
      console.log(el.classList);
      for(let i = 0; i < this.selected.length; i++)
        if(this.selected[i] === tag.tagId) {
          el.classList.remove("success");
          el.classList.add("btn-secondary");
          this.selected.splice(i, 1);
          return;
        }
      el.classList.remove("btn-secondary");
      el.classList.add("success");
      this.selected.push(tag.tagId);
      console.log(this.selected);
    },
  },
  created() {
    instance.get("/api/getPosts")
    .then((response) => {
      console.log(response.data);
      this.msg = response.data;
    })
    instance.get("/api/getTags")
    .then((response) => {
      console.log(response.data);
      this.tags = response.data;
    })
  },
  computed: {
    myImage() {
      return `data:image/png;base64, ${this.msg[0].imageData}`;
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.container {
  padding-top:100px;
}
.tagButton {
  padding: 10px;
}
.success {
  border-color: green;
  background-color: green;
}
</style>
