<template>
  <b-nav-form>
    <b-form-input v-model="query" size="sm" class="mr-sm-2" placeholder="Search"></b-form-input>
    <router-link :to="{ path: '/result', query: { search: query }}">
    <b-button size="sm" class="my-2 my-sm-0" type="submit">Search</b-button>
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
