<template>
  <div class="wrapper">
    <h3>Popular tags</h3>
    <h4>
      <b-badge v-for="tag in tags" v-bind:key="tag.tag.tagId" class="tag-badge">
        <router-link class="tag-badge-link" v-bind:to="'/tag/'+tag.tagId"> {{ tag.tag.tagName }}
        </router-link>
        <router-view/>
      </b-badge>
    </h4>
  </div>
</template>

<script>
import instance from "@/server";

export default {
  name: "PopularTags",
  data() {
    return {
      tags: null,
    }
  },
  created() {
    this.getPopularTags();
  },
  methods: {
    getPopularTags() {
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
h3 {
  font-weight: 700;
}

.wrapper {
  width: 250px;
  background-color: #FBF1FF;
  padding: 0 5px 0;
  border-radius: 5px;

}

a {
  color: #A42CD6;

}

.tag-badge {
  margin-right: 5px;
  background-color: white;
}
</style>