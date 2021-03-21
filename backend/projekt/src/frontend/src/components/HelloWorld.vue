<template>
  <h1>{{msg}}</h1>
</template>

<script>
import instance from "@/server";
export default {
  name: 'HelloWorld',
  data(){
    return {
      msg: ''
    }
  },
  created() {
    // fetch("/api/subjects").then((response)=>response.text())
    // .then((data)=> {
    //   this.msg = data;
    // })
      instance.get("/api/subjects")
        .then((response) =>{
          this.msg = response.data;
        })
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
