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
        <label>Email
          <input type="text" v-model="email">
        </label>
      </p>
      <p>
        <label>Password
          <input type="text" v-model="password">
        </label>
      </p>
      <p>
        <input type="submit" value="Sign-up">
      </p>
    </form>
  </div>
</template>

<script>
import instance from "@/server";
import headers from "@/headers";

export default {
  name: "Register",
  data () {
    return {
      username : "",
      password : "",
      email : "",
    }
  },

  methods : {
    checkForm() {
      event.preventDefault();
      let json = JSON.stringify({
        "username": this.username,
        "plainPassword": this.password,
        "email" : this.email,
      })
      console.log(json);
      instance.post("/api/auth/signup",json, {headers : headers}).then((response) => {
        console.log(response);
      })
    }
  }
}
</script>

<style scoped>

</style>