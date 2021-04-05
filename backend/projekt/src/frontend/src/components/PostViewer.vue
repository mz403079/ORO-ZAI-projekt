<template>
  <div>
    <div class="b-container post" v-for="item in postsToDisplay " :key="item.postId">
      <b-row>
        <b-col>
          <h3>{{ item.title }}
            <b-badge>Hot</b-badge>
            <b-badge :href="'/tag/'+tag.tagId" v-for="tag in item.tags" v-bind:key="tag.tagId" class="tagBadge">
              {{ tag.tagName }}
            </b-badge>
          </h3>
        </b-col>
      </b-row>
      <b-row>
        <h5>
          <b-avatar>JW</b-avatar>
          {{ item.author.username }} posted {{ item.timePosted }}
        </h5>
      </b-row>
      <b-row v-if="item.imageData != null">
        <img :src="`data:image/png;base64, ${item.imageData}`">
      </b-row>
      <b-row v-else-if="item.content != null">
        {{ item.content }}
      </b-row>
    </div>
  </div>
</template>

<script>

export default {
  name: "PostViewer",
  props: {
    postsToDisplay:Array,
  },

}

</script>

<style scoped>
.post {
  background-color: white;
  padding: 25px;
  margin: 25px 10px 10px;
}
.tagBadge {
  margin-left: 5px;
}
</style>