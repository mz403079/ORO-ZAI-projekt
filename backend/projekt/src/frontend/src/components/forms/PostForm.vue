<template>
  <div class="formWrapper">
    <form id="postForm"
          @submit="checkForm">
      <p>
        <label>Title:
          <input type="text" v-model="title">
        </label>
      </p>
      <p>
        <label>Description:
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
  </div>
</template>

<script>
import instance from "@/server";
import multipartHeaders from "@/multipartHeader";

export default {
  name: "NewPostForm",

  data() {
    return {
      selected: [],
      title: "",
      content: "",
      tags: null,
      selectedFile: null,
      formData: null,
    }
  },
  methods: {
    fileSelected(event) {
      this.selectedFile = event.target.files[0];
    },
    checkForm() {
      event.preventDefault();

      let json = JSON.parse(localStorage.user);
      console.log(json);
      this.formData = new FormData();
      this.formData.append("title", this.title);
      this.formData.append("content", this.content);
      this.formData.append("file", this.selectedFile);
      this.formData.append("tags", this.selected);
      this.formData.append("authorId", parseInt(json.id));
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
    instance.get("/api/getTags")
    .then((response) => {
      this.tags = response.data;
    })
  }
}
</script>

<style scoped>
.tagButton {
  padding: 10px;
}

.formWrapper {
  background-color: white;
  padding: 25px;
  margin: 25px 10px 10px;
}
</style>