<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="info">
      <b-navbar-brand href="#">NavBar</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <div v-if="currentUser">
            <b-button @click="logOut()" href="/">Wyloguj</b-button>
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
          <b-nav-form>
            <b-form-input size="sm" class="mr-sm-2" placeholder="Search"></b-form-input>
            <b-button size="sm" class="my-2 my-sm-0" type="submit">Search</b-button>
          </b-nav-form>

          <b-nav-item-dropdown text="Lang" right>
            <b-dropdown-item href="#">EN</b-dropdown-item>
            <b-dropdown-item href="#">ES</b-dropdown-item>
            <b-dropdown-item href="#">RU</b-dropdown-item>
            <b-dropdown-item href="#">FA</b-dropdown-item>
          </b-nav-item-dropdown>

          <b-nav-item-dropdown right>
            <!-- Using 'button-content' slot -->
            <template #button-content>
              <em>User</em>
            </template>
            <b-dropdown-item href="#">Profile</b-dropdown-item>
            <b-dropdown-item href="#">Sign Out</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <router-view/>
  </div>
</template>

<script>
import LoginForm from "@/components/LoginForm";
import RegisterForm from "@/components/RegisterForm";
import isUserAdmin from "@/isUserAdmin";
export default {
  name: "Navigation",
  components : {
    LoginForm, RegisterForm
  },
  computed: {
    currentUser() {
      return localStorage.user;
    },
    userIsAdmin() {
      console.log(isUserAdmin());
      return isUserAdmin();
    }
  },
  methods : {
    logOut() {
      localStorage.clear();
    }
  }
}
</script>

<style scoped>

</style>