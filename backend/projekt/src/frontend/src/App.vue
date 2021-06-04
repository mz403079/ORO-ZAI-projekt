<template>
  <div id="app">
    <div id="margin-top">
      <Navigation></Navigation>
    </div>
    <b-row>
      <b-col cols="2" md="2" offset-md="1">
        <SideNavigation></SideNavigation>
      </b-col>
      <b-col cols="9" sm="10" md="5">
        <router-view></router-view>
      </b-col>
      <b-col cols="2" sm="12" md="2" offset-md="1">
        <PopularTags></PopularTags>
        <PopularUsers></PopularUsers>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import Navigation from "@/components/Navigation";
import PopularTags from "@/components/PopularTags";
import SideNavigation from "@/components/SideNavigation";
import PopularUsers from "@/components/PopularUsers";

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
  /*font-family: "Poppins", sans-serif;*/
}

#margin-top {
  margin-top: 100px;
}
</style>
