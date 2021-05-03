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
              <div>essa!</div>
            </div>
          </b-col>
          <b-col offset-md="1">
            <b-row>
              <PopularTags></PopularTags>
            </b-row>
            <b-row>
              <PopularTags></PopularTags>
            </b-row>
          </b-col>
        </b-row>
      </div>
    </div>
</template>

<script>
import instance from "@/server";
import SideNavigation from "@/components/SideNavigation";
import PopularTags from "@/components/PopularTags";
export default {
  name: "TopTags",
  data() {
    return {
      tags : null,
    }
  },
  components : {
    SideNavigation,PopularTags
  },
  created() {
    this.getTopTags();
  },
  methods : {
    getTopTags() {
      instance.get("/api/getTopTags")
          .then((response) => {
            console.log(response.data);
            this.tags = response.data;
          })
    }
  }
}
</script>

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