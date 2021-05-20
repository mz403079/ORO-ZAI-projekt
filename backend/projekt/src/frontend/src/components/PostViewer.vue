<template>
  <div>
    <div class="post-wrapper" v-for="item in postsToDisplay" :key="item.postId">
      <div class="post">
        <router-link class="tag-badge-link" v-bind:to="'/post/'+item.postId">
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

              <h4 style="font-weight: 600;"> {{ item.title }}
                <b-badge v-for="tag in item.tags" v-bind:key="tag.tagId"
                         class="tagBadge">
                  <router-link class="tag-badge-link" v-bind:to="'/tag/'+tag.tagId">{{
                      tag.tagName
                    }}
                  </router-link>
                  <router-view/>

                </b-badge>
              </h4>

              <b-row>
                <div>
                  <router-link class="tag-badge-link" v-bind:to="'/user/'+item.author.username">

                    <b-avatar v-if="item.author.profileImage != null">
                      <img class="avatar-icon"
                           :src="`data:image/png;base64, ${item.author.profileImage.imageData}`"
                           alt="${item.title}">
                    </b-avatar>
                    <b-avatar v-else>R</b-avatar>

                    <span class="username">  {{ item.author.username }} </span>
                  </router-link>

                  at {{ item.timePosted }}
                </div>
              </b-row>
            </b-col>
          </b-row>
        </router-link>
        <router-view/>
      </div>
      <div>


        <div class="omega">
          <div class="buttons-wrapper">
            <b-button class="icon-button"
                      @click="likePost(item.postId); item.likes = !item.likes">
              {{ item.score }}
              <b-icon v-if="item.likes === true" icon="heart-fill"
                      v-bind:class="{ likeso: item.likes }"></b-icon>
              <b-icon v-else icon="heart"></b-icon>
            </b-button>

            <b-button class="icon-button">
              <router-link id="comment-link" v-bind:to="'/post/'+item.postId">
                {{ item.numOfComments }}
                <b-icon icon="chat-left-text"></b-icon>
              </router-link>
            </b-button>

            <b-button class="icon-button" @click="toggleVisibility($event)">
              <b-icon icon="arrows-angle-expand"></b-icon>
            </b-button>
          </div>
          <div v-if="item.postImage != null" class="post-content-hidden">

            <img style="width:90%;" :src="`data:image/png;base64, ${item.postImage.imageData}`"
                 alt="${item.title}">
          </div>
          <div v-else-if="item.content != null" class="post-content-hidden">
            {{ item.content }}
          </div>
        </div>

      </div>

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
      let el = e.target.parentElement.nextSibling;
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
      instance.post("/api/likePost", json, {headers: headers})
    },
  }

}

</script>

<style scoped>
.post-wrapper {
  border-bottom: solid #273E47 1px;
}

.post {
  background-color: white;
  padding: 15px 25px 10px 25px;

}

.post:hover {
  background-color: #FBF1FF;
}

#comment-link {
  width: 100%;
}

.omega {
  width: 100%;
}

.buttons-wrapper {
  display: flex;
}

.buttons-wrapper > * {
  flex-basis: 100%;
  padding-bottom: 10px;
}

.tagBadge {
  margin-left: 5px;
  background-color: white;
  text-decoration: underline;
}

.tagBadge a {
  color: #A42CD6;
}

a {
  color: black;
  text-decoration: none;
}


.icon-button {
  background-color: white;
  border: 0;
  color: black;
  width: 100%;
}

.likeso {
  font-size: 1rem;
  color: hsl(317, 100%, 54%);

  filter: drop-shadow(5px 5px 4px #A42CD6);
  text-shadow: 0 0 0.5em hsl(317, 100%, 54%);
}

.icon-button:hover {
  /*font-size: 1.1rem;*/
  color: #A42CD6;
  background-color: #FBF1FF;
}

.username:hover {
  text-decoration: underline;
}

.post-icon {
  width: 65px;
  height: 60px;
  border-radius: 2px;
  box-shadow: 1px 1px 3px 1px rgba(0, 0, 0, 0.5);
  transition: width 1s;
}

.avatar-icon {
  width: 100%;
  height: 100%;
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