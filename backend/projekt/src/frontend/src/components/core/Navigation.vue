<template>
  <div>
    <b-navbar fixed="top" toggleable="lg" type="dark" variant="dark">
      <b-navbar-brand href="#">Leddit</b-navbar-brand>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <div v-if="currentUser">
            <b-button class="nav-button" @click="logOut()" href="/">Wyloguj</b-button>
          </div>
          <div v-else>
            <b-button v-b-modal.modal-1>Zaloguj / Zarejestruj</b-button>
            <b-modal id="modal-1" title="BootstrapVue">
              <RegisterForm></RegisterForm>
              <LoginForm></LoginForm>
            </b-modal>
          </div>
          <div v-if="userIsAdmin">
            <b-button href="/admin-panel">Admin</b-button>
          </div>
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto">
         <SearchBar/>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import LoginForm from "@/components/forms/LoginForm";
import RegisterForm from "@/components/forms/RegisterForm";
import isUserAdmin from "@/isUserAdmin";
import SearchBar from "@/components/SearchBar";

export default {
  name: "Navigation",
  components: {
    SearchBar,
    LoginForm, RegisterForm
  },
  computed: {
    currentUser() {
      return localStorage.user;
    },
    userIsAdmin() {
      return isUserAdmin();
    }
  },
  methods: {
    logOut() {
      localStorage.clear();
    }
  }
}
</script>

<style scoped>
.nav-button {
  background-color: #343a40;
  border: 0;
  border-radius: 9999px;
}

.nav-button:hover {
  background-color: #c1c1c1;
  color: gold;
}
</style>