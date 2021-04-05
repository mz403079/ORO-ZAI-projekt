<template>
  <div id="app">
    <div v-if="msg" class="b-container contentWrapper">
      <b-row>
        <b-col>
          <h3> {{ msg.title }}
            <b-badge>Hot</b-badge>
            <b-badge :href="'/tag/'+tag.tagId" v-for="tag in msg.tags" v-bind:key="tag.tagId" class="tagBadge">
              {{ tag.tagName }}
            </b-badge>
          </h3>
        </b-col>
      </b-row>
      <b-row>
        <h5>
          <b-avatar>JW</b-avatar>
          {{ msg.author.username }} posted {{ msg.timePosted }}
        </h5>
      </b-row>
      <b-row v-if="msg.imageData != null">
        <img :src="`data:image/png;base64, ${msg.imageData}`">
      </b-row>
      <b-row v-else-if="postToDisplay.content != null">
        {{ msg.content }}
      </b-row>
    </div>
  </div>
</template>

<script>
import instance from "@/server";
export default {
  name: "SinglePostAdvanced",
  data() {
    return {
      msg:null,
    }
  },
  created() {
    instance.get("/api/post/1")
    .then((response) => {
      this.msg = response.data;
    })

  }
}
</script>

<style scoped>

</style>