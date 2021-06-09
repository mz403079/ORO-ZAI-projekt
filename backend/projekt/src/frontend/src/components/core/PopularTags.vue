<template>
  <div class="wrapper">
    <h3>Popular tags</h3>
    <h4>
      <b-badge v-for="tag in tags" v-bind:key="tag.tag.tagId" class="tag-badge">
        <router-link class="tag-badge-link"
                     :to="{ path: '/tag/'+tag.tag.tagId+'/', query: { page: 1 }}">
          {{ tag.tag.tagName }}
        </router-link>
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
      tags: [],
    }
  },
  created() {
    this.getPopularTags();
  },
  methods: {
    getPopularTags() {
      instance.get("/api/getTopTags")
      .then((response) => {
        this.tags = response.data;
      })
    }
  }
}
</script>

<style scoped>
h3 {
  font-weight: 600;
}

.wrapper {
  width: 250px;
  padding: 0 5px 0;
  border-radius: 5px;

}

a, a:hover {
  font-weight: 500;
  color: white;
  text-decoration: none;
}

.tag-badge {
  margin-right: 5px;
  background-color: #A42CD6;
}

.tag-badge a:hover {
  text-decoration: underline;
}
</style>