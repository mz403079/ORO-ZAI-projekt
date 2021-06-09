<template>
  <div>
    <b-form @submit.stop.prevent="checkForm">
      <label for="username-l">Username:</label>
      <b-form-input type="text"
                    id="username-l"
                    v-model="form.username"
                    placeholder="Enter username"
                    required
      ></b-form-input>
      <label for="text-password-l">Password:</label>
      <b-form-input type="password"
                    id="text-password-l"
                    v-model="form.password"
                    required
                    placeholder="Enter password"
                    aria-describedby="password-help-block"></b-form-input>
      <b-button class="sign-up-button" type="submit" variant="primary">Sign-in</b-button>
    </b-form>
  </div>
</template>

<script>
import instance from "@/server";
import headers from "@/headers";

export default {
  name: "Login",

  data() {
    return {
      form: {
        username: '',
        password: '',
      },
    }
  },

  methods: {
    checkForm() {
      event.preventDefault();
      let json = JSON.stringify({
        "username": this.form.username,
        "plainPassword": this.form.password,
      })
      instance.post("/api/auth/signin", json, {headers: headers}).then(response => {
        localStorage.setItem('user', JSON.stringify(response.data));
        let r = this.$router.resolve({
          name: this.$route.name, // put your route information in
        });
        window.location.assign(r.href)
        // eslint-disable-next-line no-unused-vars
      }).catch(error => {
        this.$toast.error(
            '\nWrong credentials.', {
              position: 'top'
            })
      })
    }
  }
}

</script>

<style scoped>
.form-control {
  margin: 20px 0;

}

.form-control:focus {
  color: #495057;
  background-color: #fff;
  border-color: #A42CD6;
  outline: 0;
  box-shadow: 0 0 0 0.2rem rgba(164, 44, 214, 0.25);
}

.sign-up-button {
  margin: 10px 0 10px;
  background-color: #A42CD6;
  border-color: #A42CD6;
}

.sign-up-button:hover {
  background-color: #C44EF6;
  border-color: #C44EF6;
}

.form-control label {
  display: block;
}
</style>