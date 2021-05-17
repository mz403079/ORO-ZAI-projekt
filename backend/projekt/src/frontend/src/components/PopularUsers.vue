<template>
  <div class="wrapper">
    <h4>Top Ledditors</h4>
    <b-row v-for="userInfo in users" v-bind:key="userInfo.user.userId">
      <b-col>
        <b-avatar size="3rem" v-if="userInfo.user.profileImage != null">
          <img class="avatar-icon"
               :src="`data:image/png;base64, ${userInfo.user.profileImage.imageData}`" alt="essa">
        </b-avatar>
        <b-avatar size="3rem" v-else>R</b-avatar>
      </b-col>
      <b-col>
        <b-link :href="'/user/'+userInfo.user.username">{{ userInfo.user.username }}</b-link>
        <br>
        {{ userInfo.count }} posts
      </b-col>
    </b-row>
  </div>
</template>

<script>
import instance from "@/server";

export default {
  name: "PopularTags",
  data() {
    return {
      users: null,
    }
  },
  created() {
    this.getPopularTags();
  },
  methods: {
    getPopularTags() {
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
.wrapper {
  width: 250px;
  background-color: greenyellow;
  margin-top: 25px;
  border-radius: 5px;
  padding: 0 15px 0;
}


.avatar-icon {
  width: 100%;
  height: 100%;
}
</style>