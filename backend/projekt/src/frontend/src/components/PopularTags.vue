<template>
  <div class="gog">
    <h4>Popular tags<br>
      <b-badge v-for="tag in tags" v-bind:key="tag.tagId" class="tagBadge">
        {{ tag.tagName }}
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
      instance.get("/api/getTags")
      .then((response) => {
        this.tags = response.data;
      })
    }
  }
}
</script>

<style scoped>
.gog {
  width: 250px;
  background-color: white;
  margin-top: 25px;
  border-radius: 5px;
  padding: 0 15px 0;
}

.tagBadge {
  margin-left: 5px;
}
</style>