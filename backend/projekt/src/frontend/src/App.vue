<template>
  <div id="app">
  <Navigation></Navigation>
  <HelloWorld/>
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import Navigation from "@/components/Navigation";
export default {
  name: 'App',
  components: {
    HelloWorld,
    Navigation
  }
}
</script>

<style>

</style>
