<template>
  <div id="app">
    <div class="b-container contentWrapper">
      <b-row>
        <b-col md="8">
      <div class="b-container post" v-for="item in msg " :key="item.postId">
        <b-row>
          <b-col>
            <h3>{{ item.title }}
              <b-badge>Hot</b-badge>
              <b-badge v-for="tag in item.tags" v-bind:key="tag.tagId" class="tagBadge">
                {{ tag.tagName }}
              </b-badge>
            </h3>
          </b-col>
        </b-row>
        <b-row>
          <h5>
            <b-avatar>JW</b-avatar>
            {{ item.author.username }} posted {{ item.timePosted }}
          </h5>
        </b-row>
        <b-row v-if="item.imageData != null">
          <img :src="`data:image/png;base64, ${item.imageData}`">
        </b-row>
        <b-row v-else-if="item.content != null">
          {{ item.content }}
        </b-row>
        <!--      <b-row>-->

        <!--      </b-row>-->
      </div>
        </b-col>
        <b-col offset-md="1">
          <b-row>
          <div class="gog">
            <h4>Popular tags</h4>
            <div v-for="tag in tags" v-bind:key="tag.tagId" class="tagButton">
              <b-button pill variant="secondary" v-on:click="addTag($event,tag)"> {{ tag.tagName }}
              </b-button>
            </div>
          </div>
          </b-row>
          <b-row>
          <div class="gog">
            <h4>Top contributors</h4>
            <div v-for="tag in tags" v-bind:key="tag.tagId" class="tagButton">
              <b-button pill variant="secondary" v-on:click="addTag($event,tag)"> {{ tag.tagName }}
              </b-button>
            </div>
          </div>
          </b-row>
        </b-col>
      </b-row>
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
#app {
  background-color: #ABD6D6;
}

.post, .formWrapper {
  background-color: white;
  padding: 25px;
  margin: 25px 10px 10px;
  border: black solid 1px;
}

.gog {
  width:250px;
  background-color: white;
  margin-top: 25px;
  border: black solid 1px;
  padding: 0 15px 0;
}

.tagButton {
  padding: 10px;
}

.tagBadge {
  margin-left: 5px;
}

.contentWrapper {
  width: 70%;
  margin: 0 auto;
}
</style>
