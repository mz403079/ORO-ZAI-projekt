<template>
  <div>
    <b-navbar  class="navbar" fixed="top" toggleable="md">
      <b-navbar-brand class="text-white" href="#">Leddit</b-navbar-brand>
      <b-navbar-toggle id="mm" target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav >
          <div v-if="currentUser">
            <b-button id="log-out-btn" @click="logOut()" href="/">Logout</b-button>
          </div>
          <div v-else>
            <b-button id="sign-btn" v-b-modal.modal-1 >Sign-in / Sign-up</b-button>
            <b-modal hide-footer hide-header content-class="shadow" id="modal-1" title="Hello!">
              <b-tabs fill content-class="mt-3"
              >
                <b-tab title="Sign-up" >
                  <RegisterForm></RegisterForm>
                </b-tab>
                <b-tab title="Sign-in">
                  <LoginForm></LoginForm>
                </b-tab>
              </b-tabs>
            </b-modal>
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

<style>

.nav-button {
  background-color: #343a40;
  border: 0;
  border-radius: 9999px;
}
 ul > li > a{
  color: #A42CD6;
}
ul > li > a:hover{
  color:#C44EF6;
}
#log-out-btn, #sign-btn {
  background-color: #A42CD6;
  border-color: #A42CD6;
}
#log-out-btn:hover, #sign-btn:hover {
  background-color: #C44EF6;
  border-color: #C44EF6;
}
.navbar {
  background-color: black;
}
#mm {
  background-color: #A42CD6;

}
</style>