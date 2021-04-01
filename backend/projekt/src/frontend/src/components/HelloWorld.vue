<template>
  <div class="b-container">
    <div class="b-container post" v-for="item in msg " :key="item.postId">
      <b-row>
        <b-col>
          <h1>Tytuł: {{ item.title }}</h1>
        </b-col>
        <b-col>
          <h1>Nick autora: {{ item.author.username }}</h1>
        </b-col>
      </b-row>
      <b-row>
        <img :src="`data:image/png;base64, ${item.imageData}`" width="300" height="300">

      </b-row>
      <b-row>
        <div v-for="tag in item.tags" v-bind:key="tag.tagId" class="tagButton">
          <b-button pill variant="secondary"># {{ tag.tagName }}
          </b-button>
        </div>
      </b-row>
    </div>
    <div class="formWrapper">
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
          <input type="file" value="Wybierz plik" @change="fileSelected">
        </label>
      </p>
      <p>
        <input type="submit" value="Dodaj">
      </p>
    </form>
    <div>
      <b-row>
        <div v-for="tag in tags" v-bind:key="tag.tagId" class="tagButton">
          <b-button pill variant="secondary" v-on:click="addTag($event,tag)"> {{ tag.tagName }}
          </b-button>
        </div>
      </b-row>
    </div>
    {{ selected }}
    </div>
  </div>
</template>

<script>
import instance from "@/server";
import multipartHeaders from "@/multipartHeader";

export default {
  name: 'HelloWorld',
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
      this.formData.append("tags", this.selected);
      this.formData.append("authorId", 1);
      console.log(this.formData);
      instance.post("/api/addPost", this.formData, {headers: multipartHeaders})
    },
    addTag(e, tag) {
      let el = e.target;
      for (let i = 0; i < this.selected.length; i++) {
        if (this.selected[i] === tag.tagId) {
          el.classList.remove("btn-primary");
          el.classList.add("btn-secondary");
          this.selected.splice(i, 1);
          return;
        }
      }
      el.classList.remove("btn-secondary");
      el.classList.add("btn-primary");
      this.selected.push(tag.tagId);
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
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.post,.formWrapper {
  padding: 100px;
  margin: 100px;
  border: black solid 1px;
}

.tagButton {
  padding: 10px;
}

</style>
