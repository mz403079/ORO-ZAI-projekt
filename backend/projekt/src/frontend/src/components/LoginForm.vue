<template>
  <div class="formWrapper">
    <form id="commentForm"
          @submit="checkForm">
      <p>
        <label>Username
          <input type="text" v-model="username">
        </label>
      </p>
      <p>
        <label>Password
          <input type="text" v-model="password">
        </label>
      </p>
      <p>
        <input type="submit" value="Loguj">
      </p>
    </form>
  </div>
</template>

<script>
import instance from "@/server";
import headers from "@/headers";

export default {
  name: "Login",

  data () {
    return {
      username : "",
      password : "",
    }
  },

  methods : {
    checkForm() {
      event.preventDefault();
      let json = JSON.stringify({
        "username": this.username,
        "plainPassword": this.password,
      })
      console.log(json);
      instance.post("/api/auth/signin",json, {headers : headers}).then((response) => {
        console.log(response);
      })
    }
  }
}

</script>

<style scoped>

</style>