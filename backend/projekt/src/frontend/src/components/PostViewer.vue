<template>
  <div>
    <div class="b-container post" v-for="item in postsToDisplay" :key="item.postId">
      <b-row>
        <b-col md="2">
          <div v-if="item.postImage != null">
            <img class="post-icon" :src="`data:image/png;base64, ${item.postImage.imageData}`"
                 alt="${item.title}">
          </div>
          <div v-else>
            <b-icon icon="card-text" font-scale="4"></b-icon>

          </div>

        </b-col>
        <b-col md="9">
          <b-link :href="'/post/'+item.postId" class="link">
            <h4 style="font-weight: 600;"> {{ item.title }}
              <b-badge>Hot</b-badge>

              <b-badge v-for="tag in item.tags" v-bind:key="tag.tagId"
                       class="tagBadge">
                <router-link v-bind:to="'/tag/'+tag.tagId">{{ tag.tagName }}</router-link>
                <router-view/>

              </b-badge>
            </h4>
          </b-link>
          <b-row>
            <div>
              <b-avatar v-if="item.author.profileImage != null">
                <img class="avatar-icon"
                     :src="`data:image/png;base64, ${item.author.profileImage.imageData}`"
                     alt="${item.title}">
              </b-avatar>
              <b-avatar v-else>R</b-avatar>

              {{ item.author.username }} at {{ item.timePosted }}
            </div>
          </b-row>
        </b-col>
      </b-row>
      <b-row>
        <div>
          <b-button class="icon-button" @click="likePost(item.postId)">
            {{ item.score }}
            <b-icon icon="heart"></b-icon>
          </b-button>
          <b-button class="icon-button">
            <b-link :href="'/post/'+item.postId" style="text-decoration: none; color:black;">
              <a style="font-weight: 600;"> {{ item.numOfComments }} </a>
              <b-icon icon="chat-left-text"></b-icon>
            </b-link>
          </b-button>
          <b-button class="icon-button" @click="toggleVisibility($event)">
            <b-icon icon="arrows-angle-expand"></b-icon>
          </b-button>
          <div v-if="item.postImage != null" class="post-content-hidden">

            <img style="width:90%;" :src="`data:image/png;base64, ${item.postImage.imageData}`"
                 alt="${item.title}">
          </div>
          <div v-else-if="item.content != null">
            {{ item.content }}
          </div>
        </div>
      </b-row>
    </div>

  </div>
</template>

<script>

import instance from "@/server";
import headers from "@/headers";

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
      console.log(e.target);
      let el = e.target.parentElement.nextSibling;
      console.log(el);
      if (el.classList.contains("post-content-visible")) {
        el.classList.remove("post-content-visible");
        el.classList.add("post-content-hidden");
      } else {
        el.classList.remove("post-content-hidden");
        el.classList.add("post-content-visible");
      }
    },
    likePost(postId) {
    let user = JSON.parse(localStorage.user);
      let json = JSON.stringify({
        "userId": user.id,
        "postId": postId,
      })
       instance.post("/api/likePost",json, {headers : headers})
    }
  }

}

</script>

<style scoped>
.post {
  background-color: white;
  padding: 0px 25px 0px 25px;
  margin: 10px 0 5px;
  border-bottom: solid #273E47 1px;
}

.tagBadge {
  margin-left: 5px;
}

.icon-button {
  background-color: white;
  border: 0;
  border-radius: 100%;
  color: black;
}

.icon-button:hover {
  font-size: 1.1rem;
  border-radius: 100%;
  color: #A42CD6;
  background-color: #FBF1FF;
}

.link {
  text-decoration: none;
  color: black;
}

.post-icon {
  width: 65px;
  height: 60px;
  border-radius: 2px;
  box-shadow: 1px 1px 3px 1px rgba(0, 0, 0, 0.5);
  transition: width 1s;
}

.avatar-icon {
  width: 40px;
  height: 40px;
}

.post-content-hidden {
  height: 0px;
  visibility: hidden;
}

.post-content-visible {
  width: 100%;
  text-align: center;
  height: auto;
  margin-bottom: 10px;
}
</style>