<template>
  <div id="app">
    <div id="margin-top">
      <Navigation></Navigation>
    </div>
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
  background-color: white;
}

#margin-top {
  margin-top: 100px;
}

</style>
