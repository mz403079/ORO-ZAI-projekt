<template>
  <div id="app">
    <div id="essa">
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
.contentWrapper {
  margin-left: 20px;
}
#essa {
  margin-top: 100px;
}

</style>
