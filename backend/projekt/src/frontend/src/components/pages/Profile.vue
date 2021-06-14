<template>
  <div>
    {{ user.username }} - {{ user.email }}
    <div v-if="user.profileImage != null">
      <img class="post-icon" :src="`data:image/png;base64, ${user.profileImage.imageData}`"
           alt="essa">
    </div>
    <form id="profilePicForm"
          @submit="checkForm">
      <p>
        <label>
          <input type="file" value="Wybierz plik" @change="fileSelected">
        </label>
      </p>
      <p>
        <input type="submit" value="Dodaj">
      </p>
    </form>
  </div>
</template>

<script>
import instance from "@/server";
import multipartHeaders from "@/multipartHeader";

export default {
  name: "Profile",
  data() {
    return {
      user: null,
      picName: "",
      selectedFile: null,
      formData: null,
      posts: [],
    }
  },
  created() {
    this.getUserData();
  },
  methods: {
    getUserData() {
      this.user = JSON.parse(localStorage.user);
      instance.get("api/profile/" + this.user.id).then(response => {
        console.log(response)
        this.user = response.data;
        console.log(this.user);
      })
    },
    fileSelected(event) {
      this.selectedFile = event.target.files[0];
    },
    checkForm() {
      event.preventDefault();
      this.formData = new FormData();
      this.formData.append("file", this.selectedFile);
      this.formData.append("userId", this.user.id);
      console.log(this.formData);
      instance.post("/api/profile/" + this.user.userId + "/pic", this.formData,
          {headers: multipartHeaders})
    },
  }
}
</script>

<style scoped>
.post-icon {
  width: 100px;
  height: 100px;
  border-radius: 2px;
  box-shadow: 1px 1px 3px 1px rgba(0, 0, 0, 0.5);
  transition: width 1s;
}
</style>