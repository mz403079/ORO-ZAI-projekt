<template>
  <nav aria-label="Page navigation example">
    <ul class="pagination">
      <router-link :to="{ path: path, query: { page: currentPage-1 }}"
                   v-bind:class="{disabled : currentPage === 1}">
        <li class="page-item" v-bind:class="{disabled : currentPage === 1}"><a class="page-link">Previous</a>
        </li>
      </router-link>
      <router-link v-for="i in pages " v-bind:key="i" class="tag-badge-link"
                   :to="{ path: path, query: { page: i }}">
        <li class="page-item"><a class="page-link">{{ i }}</a></li>
      </router-link>
      <router-link :to="{ path: path, query: { page: currentPage+1 }}"
                   v-bind:class="{disabled : currentPage === pages}">
        <li class="page-item" v-bind:class="{disabled : currentPage === pages}"><a
            class="page-link">Next</a></li>
      </router-link>
    </ul>
  </nav>
</template>

<script>
export default {
  name: "Pager",
  props: {
    currentPage: Number,
    pages: Number,
    path: String
  },
}
</script>

<style scoped>

</style>