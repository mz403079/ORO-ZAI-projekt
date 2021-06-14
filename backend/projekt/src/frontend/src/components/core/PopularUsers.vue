<template>
  <div class="wrapper">
    <h3>Top Ledditors</h3>
    <b-row v-for="userInfo in users" v-bind:key="userInfo.user.userId">
      <b-col>
        <router-link class="tag-badge-link" v-bind:to="'/user/'+userInfo.user.username">
          <b-avatar size="3rem" v-if="userInfo.user.profileImage != null">
            <img class="avatar-icon"
                 :src="`data:image/png;base64, ${userInfo.user.profileImage.imageData}`" alt="essa">
          </b-avatar>
          <b-avatar size="3rem" v-else>R</b-avatar>
        </router-link>
      </b-col>
      <b-col>
        <router-link class="tag-badge-link" v-bind:to="'/user/'+userInfo.user.username">
          {{ userInfo.user.username }}
        </router-link>
        <br>
        {{ userInfo.count }} posts
      </b-col>
    </b-row>
  </div>
</template>

<script>
import instance from "@/server";

export default {
  name: "PopularUsers",
  data() {
    return {
      users: null,
    }
  },
  created() {
    this.getPopularUsers();
  },
  methods: {
    getPopularUsers() {
      instance.get("/api/getTopUsers")
      .then((response) => {
        this.users = response.data;
        console.log(this.users);
      })
    }
  }
}
</script>

<style scoped>

h3 {
  font-weight: 600;
}

.wrapper {
  background-color: #FBF1FF;
  width: 250px;
  margin-top: 25px;
  border-radius: 5px;
  padding: 0 15px 0;
}

.avatar-icon {
  width: 100%;
  height: 100%;
}
</style>