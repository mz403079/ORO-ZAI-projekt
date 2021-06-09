<template>
  <div id="app">
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
              <h5 class="post-header">
                <div>{{ item.title }}
                  <b-badge v-for="tag in item.tags" v-bind:key="tag.tagId"
                           class="tagBadge">
                    <router-link class="tag-badge-link"
                                 :to="{ path: '/tag/'+tag.tagId+'/', query: { page: 1 }}">
                      {{ tag.tagName }}
                    </router-link>
                    <router-view/>
                  </b-badge>
                </div>
                <div v-if="userIsAdmin">
                  <router-link :to="{ path: '#'}">
                  <b-icon color="red" icon="trash" @click="deletePost(item.postId)"></b-icon>
                  </router-link>
                </div>
              </h5>
              <b-row>
                <div>
                  <router-link class="tag-badge-link"
                               :to="{ path: '/user/'+item.author.username+'/', query: { page: 1 }}">
                    <b-avatar v-if="item.author.profileImage != null">
                      <img class="avatar-icon"
                           :src="`data:image/png;base64, ${item.author.profileImage.imageData}`"
                           alt="${item.title}">
                    </b-avatar>
                    <b-avatar v-else>R</b-avatar>

                    <span class="username">  {{ item.author.username }} </span>
                  </router-link>
                  · {{ timeFromNow(item.timePosted) }}
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
            <b-button class="icon-button" style="border-bottom-left-radius: 8px;"
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

            <b-button class="icon-button" style="border-bottom-right-radius: 8px;"
                      @click="toggleVisibility($event)">
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
import isUserAdmin from "@/isUserAdmin";
import {formatDistanceToNow} from 'date-fns'
import authHeader from "@/authHeader";

export default {
  name: "PostViewer",
  props: {
    postsToDisplay: Array,
  },
  data() {
    return {
      fullWidthImage: false,
      userIsAdmin: false,
    }
  },
  created() {
    this.userIsAdmin = isUserAdmin();

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
    timeFromNow(time) {
      return formatDistanceToNow(
          new Date(time), {addSuffix: true}
      )
    },
    likePost(postId) {
      let user = JSON.parse(localStorage.user);
      let json = JSON.stringify({
        "userId": user.id,
        "postId": postId,
      })
      instance.post("/api/likePost", json, {headers: headers})
    },
    deletePost(id) {
      instance.delete("/api/deletePost/"+id, {headers: authHeader()}).then(() => {
        this.$parent.$emit('ChangeView', "refresh");
      });
    }
  }

}

</script>

<style scoped>
#app {
  margin: 0;
  padding: 0;
}

.post-wrapper {
  margin-bottom: 15px;
  border-radius: 10px;
  border: solid #FFE8FF 1px;
}

.post {
  background-color: #FBF1FF;
  border-radius: 8px 8px 0 0;
  padding: 15px 0 10px 25px;

}

.post:hover {
  background-color: #FFE8FF;
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

.post-header {
  display: flex;
  font-weight: 600;
  justify-content: space-between;
}

.buttons-wrapper > * {
  flex-basis: 100%;
  padding-bottom: 10px;
}

.tagBadge {
  margin-left: 5px;
  background-color: #A42CD6;
}

.tagBadge a {
  color: white;
}

.tagBadge a:hover {
  text-decoration: underline;
}

a {
  color: black;
  text-decoration: none;
}


.icon-button {
  background-color: #FBF1FF;
  border: 0;
  color: black;
  width: 100%;
}

.icon-button:hover {
  color: #A42CD6;
  background-color: #FFE8FF;
}

.likeso {
  font-size: 1rem;
  color: hsl(317, 100%, 54%);

  filter: drop-shadow(5px 5px 4px #A42CD6);
  text-shadow: 0 0 0.5em hsl(317, 100%, 54%);
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