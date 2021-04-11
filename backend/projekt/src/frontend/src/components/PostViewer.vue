<template>
  <div>
    <div class="b-container post" v-for="item in postsToDisplay" :key="item.postId">
      <b-row>
        <b-col md="1">
          <div v-if="item.imageData != null">
            <img class="post-icon" :src="`data:image/png;base64, ${item.imageData}`"
                 alt="${item.title}">
          </div>
          <div v-else>
            <b-icon icon="card-text" font-scale="5"></b-icon>

          </div>

        </b-col>
        <b-col md="9" >
          <b-link :href="'/post/'+item.postId" class="link">
            <h3> {{ item.title }}
              <b-badge>Hot</b-badge>
              <b-badge :href="'/tag/'+tag.tagId" v-for="tag in item.tags" v-bind:key="tag.tagId"
                       class="tagBadge">
                {{ tag.tagName }}
              </b-badge>
            </h3>
          </b-link>


          <b-row>
            <h5>
              <b-avatar>JW</b-avatar>
              {{ item.author.username }} posted {{ item.timePosted }}
            </h5>
          </b-row>
        </b-col>
      </b-row>
      <b-row>
        <div>
          <b-button @click="toggleVisibility($event)">Toggle Collapse</b-button>
          <div class="post-content-hidden">
            <b-row v-if="item.imageData != null">
              <img :src="`data:image/png;base64, ${item.imageData}`" alt="${item.title}">
            </b-row>
            <b-row v-else-if="item.content != null">
              {{ item.content }}
            </b-row>
          </div>
        </div>
      </b-row>
      <b-row>
        <b-link :href="'/post/'+item.postId" style="font-size: 1.1rem;"><a @load="countComments(item.comments)">komentarze </a>
          <b-icon icon="chat-left-text"></b-icon>
        </b-link>
      </b-row>
    </div>

  </div>
</template>

<script>

export default {
  name: "PostViewer",
  props: {
    postsToDisplay: Array,
  },
  data() {
    return {
      "fullWidthImage": false,
    }
  },
  methods: {
    toggleVisibility(e) {
      let el = e.target.nextSibling;
      console.log(el);
      if (el.classList.contains("post-content-visible")) {
        el.classList.remove("post-content-visible");
        el.classList.add("post-content-hidden");
      } else {
        el.classList.remove("post-content-hidden");
        el.classList.add("post-content-visible");
      }
    },

    countComments(comments) {
      console.log(comments);
    }
  }

}

</script>

<style scoped>
.post {
  background-color: white;
  padding: 25px 25px 5px;
  margin: 25px 10px 10px;
}

.tagBadge {
  margin-left: 5px;
}

.link {
  text-decoration: none;
  color: black;
}

.post-icon {
  width: 55px;
  height: 70px;
  border-radius: 2px;
  box-shadow: 1px 1px 3px 1px rgba(0, 0, 0, 0.5);
  transition: width 1s;
}

.post-content-hidden {
  height: 10px;
  visibility: hidden;
}

.post-content-visible {
  width: 100%;
  height: auto;
  visibility: visible;
}
</style>