<template>

</template>

<script>
import instance from "@/server";

export default {
  name: "SearchQueryResult",

  methods: {
    getPosts() {
      instance.get("/api/search/1234")
          .then((response) => {
            this.posty = response.data;
            console.log(this.posty);
          })
    }
  }
}
</script>

<style scoped>

</style>