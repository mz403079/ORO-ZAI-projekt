<template>
  <div id="app">
    <div class="b-container contentWrapper">
          <div id="tag-viewer-wrapper">
            <div v-for="tagInfo in tags" :key="tagInfo.tagId">
              <router-link class="tag-badge-link" v-bind:to="'/tag/'+tagInfo.tag.tagId">
                <div class="tag-wrapper">
                  <div><span class="tag-name">#{{ tagInfo.tag.tagName }}</span></div>
                  <div><span>{{ tagInfo.count }} posts</span></div>
                </div>
              </router-link>
            </div>
          </div>
  </div>
  </div>
</template>

<script>
import instance from "@/server";

export default {
  name: "TopTags",
  data() {
    return {
      tags: null,
    }
  },

  created() {
    this.getTopTags();
  },
  methods: {
    getTopTags() {
      instance.get("/api/getTopTags")
      .then((response) => {
        this.tags = response.data;
      })
    }
  }
}
</script>

<style scoped>
#tag-viewer-wrapper {
  margin-top: 5px;
  border: solid #273E47 1px;
}

.tag-name {
  font-weight: 700;
}

a {
  color: black;
  text-decoration: none;
}

.tag-wrapper {
  padding: 10px;
  border-bottom: solid #273E47 1px;
}

.tag-wrapper:hover {
  background-color: #FBF1FF;
}

.contentWrapper {
  width: 85%;
  margin: 0 auto;
}
</style>