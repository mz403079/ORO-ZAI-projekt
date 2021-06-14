<template>
  <div>
    <b-form @submit.stop.prevent="checkForm">
      <label for="username">Username:</label>
      <b-form-input type="text"
                    id="username"
                    v-model="form.username"
                    placeholder="Enter username"
                    required
      ></b-form-input>
      <b-form-invalid-feedback :state="validateUsername">
        Username must be minimum 5 characters long.
      </b-form-invalid-feedback>
      <label for="email">Email:</label>
      <b-form-input type="text"
                    id="email"
                    v-model="form.email"
                    placeholder="Enter email"
                    required
      ></b-form-input>
      <b-form-invalid-feedback :state="validateEmail">
        That's incorrect e-mail address.
      </b-form-invalid-feedback>
      <label for="text-password">Password:</label>
      <b-form-input type="password"
                    id="text-password"
                    v-model="form.password"
                    required
                    placeholder="Enter password"
                    aria-describedby="password-help-block"></b-form-input>
      <b-form-invalid-feedback :state="validatePassword">
        Incorrect password.
      </b-form-invalid-feedback>
      <b-form-valid-feedback :state="validatePassword">
        Correct password.
      </b-form-valid-feedback>
      <b-form-text id="password-help-block">
        Your password must be 5-20 characters long and contain minimum one number.
      </b-form-text>
      <label for="text-password2">Confirm password:</label>
      <b-form-input type="password"
                    id="text-password2"
                    v-model="form.password2"
                    required
                    placeholder="Enter password"
      ></b-form-input>
      <b-form-invalid-feedback :state="passwordMatch">
        Passwords do not match.
      </b-form-invalid-feedback>
      <b-form-valid-feedback :state="passwordMatch">
        Passwords are the same.
      </b-form-valid-feedback>
      <b-button class="sign-up-button" type="submit" variant="primary">Sign-up</b-button>
    </b-form>
  </div>
</template>

<script>
import instance from "@/server";
import headers from "@/headers";

export default {
  name: "Register",
  data() {
    return {
      form: {
        username: '',
        password: '',
        password2: '',
        email: '',
      },
    }
  },
  computed: {
    passwordMatch() {
      if (this.form.password2.length < 1) {
        return null;
      }
      return this.form.password === this.form.password2;
    },
    validateEmail() {
      if (this.form.email.length < 1) {
        return null;
      }
      var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(this.form.email);
    },
    validatePassword() {
      if (this.form.password.length < 1) {
        return null;
      }
      if (this.form.password.length < 5 || this.form.password.length > 20) {
        return false;
      }
      return /\d/.test(this.form.password);
    },
    validateUsername() {
      if (this.form.username.length < 1) {
        return null;
      }
      return this.form.username.length > 4;
    }
  },

  methods: {
    checkForm() {
      if (this.passwordMatch && this.validateEmail && this.validateUsername
          && this.validatePassword) {
        this.registerUser();
      }
    },

    registerUser() {
      let json = JSON.stringify({
        "username": this.form.username,
        "plainPassword": this.form.password,
        "email": this.form.email,
      })
      instance.post("/api/auth/signup", json, {headers: headers}).then((response) => {
        console.log(response);
        this.$toast.success(
            '\nSuccessfully created account.', {
              position: 'top'
            })
      }).catch(error => {
        let message =
            (error.response &&
                error.response.data &&
                error.response.data.message) ||
            error.message ||
            error.toString();
        this.$toast.error(
            '\n' + message, {
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