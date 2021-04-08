<template>
  <div id="app">
  <Navigation></Navigation>
  </div>
</template>

<script>
import Navigation from "@/components/Navigation";

export default {
  name: 'App',
  components: {
    Navigation,

  }
}
</script>

<style>
#app {
  background-color: #ABD6D6;
}
.contentWrapper {
  margin-left: 20px;
}

</style>
