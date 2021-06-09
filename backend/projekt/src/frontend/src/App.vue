<template>
  <div id="app">
    <div id="margin-top">
      <Navigation></Navigation>
    </div>
    <b-container fluid>
      <b-row>
        <b-col cols="1" md="1" lg="2" offset-lg="1">
          <SideNavigation></SideNavigation>
        </b-col>
        <b-col cols="11" md="7" lg="5">
          <router-view></router-view>
        </b-col>
        <b-col cols="12" md="2" offset-lg="1">
          <PopularTags></PopularTags>
          <PopularUsers></PopularUsers>
        </b-col>
      </b-row>
    </b-container>
<!--    <div>-->
<!--      <b-navbar class="navbar" fixed="bottom" sticky="false">-->
<!--        <b-navbar-brand class="text-white" href="#">Leddit</b-navbar-brand>-->
<!--        <b-navbar-toggle id="nav-button" target="nav-collapse"></b-navbar-toggle>-->
<!--        <b-collapse id="nav-collapse" is-nav>-->

<!--          <b-navbar-nav class="ml-auto">-->
<!--          </b-navbar-nav>-->
<!--        </b-collapse>-->
<!--      </b-navbar>-->
<!--    </div>-->
  </div>
</template>

<script>
import Navigation from "@/components/core/Navigation";
import PopularTags from "@/components/core/PopularTags";
import SideNavigation from "@/components/core/SideNavigation";
import PopularUsers from "@/components/core/PopularUsers";

export default {
  name: 'App',
  components: {
    Navigation, PopularTags, SideNavigation, PopularUsers

  }
}
</script>

<style>
#app {
  background-color: white;
}

#margin-top {
  margin-top: 100px;
}

a {
  color: red;
}

</style>
