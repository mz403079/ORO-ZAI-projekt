<template>
<div>
  no
  {{response}}
</div>
</template>

<script>
import instance from "@/server";
export default {
  name: "AdminBoard",
  data() {
    return {
      response : null,
    }
  },
  created() {
      instance.get("api/getAdminBoard").then(response => {
      console.log(response.data)
      })
  }
}
</script>

<style scoped>

</style>