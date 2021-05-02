<template>
  <div id="app">
    <div class="b-container contentWrapper">
      <b-row>
        <b-col md="2">
          <div>
            <SideNavigation></SideNavigation>
          </div>
        </b-col>
        <b-col md="5" offset-md="1">
          <div id="post-viewer-wrapper">
            <component v-bind:is="componentName"></component>
          </div>
        </b-col>
        <b-col offset-md="1">
          <b-row>
            <PopularTags></PopularTags>
          </b-row>
          <b-row>
            {{ componentName }}
            <PopularTags></PopularTags>
          </b-row>
        </b-col>
      </b-row>
    </div>
  </div>
</template>

<script>
import PopularTags from "@/components/PopularTags";
import SideNavigation from "@/components/SideNavigation";
import Home from "@/components/Home"
import TrendingPosts from "@/components/TrendingPosts"
import TopTags from "@/components/TopTags"
import instance from "@/server";

export default {
  name: 'HelloWorld',
  components: {PopularTags, SideNavigation, Home, TrendingPosts, TopTags},
  data() {
    return {
      componentName: "Home",
    }
  },
  created() {

    instance.get("/api/getPostTag")
        .then((response) => {
          console.log(response.data);
        })
    instance.get("/api/getTopTags")
        .then((response) => {
          console.log(response.data);
        })
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

#post-viewer-wrapper {
  margin-top: 5px;
  border: solid #273E47 1px;
}

.contentWrapper {
  width: 85%;
  margin: 0 auto;
}
</style>
