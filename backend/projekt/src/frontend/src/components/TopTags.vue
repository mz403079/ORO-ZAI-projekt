<template>
  <div>essa</div>
</template>

<script>
import instance from "@/server";

export default {
  name: "TopTags",
  data() {
    return {
      tags : null,
    }
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

</style>