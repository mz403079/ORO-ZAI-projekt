<template>
  <b-nav-form>
    <b-form-input v-model="query" size="sm" class="mr-sm-2" placeholder="Search"></b-form-input>
    <router-link :to="{ path: '/result', query: { search: query }}">
    <b-button size="sm" class="my-2 my-sm-0 custom-button" type="submit">Search</b-button>
    </router-link>
  </b-nav-form>
</template>
<script>
export default {
  name: 'SearchBar',
  data() {
    return {
      query: "",
    }
  }
}
</script>
<style scoped>
.form-control:focus {
  color: #495057;
  background-color: #fff;
  border-color: #A42CD6;
  outline: 0;
  box-shadow: 0 0 0 0.2rem rgba(164, 44, 214, 0.25);
}
.custom-button {
  background-color: #A42CD6;
  border-color: #A42CD6;
}
.btn:hover {
  background-color: #C44EF6;
  border-color: #C44EF6;
}
</style>