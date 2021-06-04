import Vue from 'vue';
import Router from 'vue-router';
import HelloWorld from "@/components/HelloWorld";
import PostsWithTag from "@/components/PostsWithTag";
import SinglePostAdvanced from "@/components/SinglePostAdvanced";
import AdminBoard from "@/components/AdminBoard";
import Profile from "@/components/Profile";
import TopTags from "@/components/SideNavbarComponents/TopTags";
import TrendingPosts from "@/components/SideNavbarComponents/TrendingPosts";
import UserPosts from "@/components/UserPosts";
import UserWall from "@/components/SideNavbarComponents/UserWall";
import SearchQueryResult from "@/components/SearchQueryResult";

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: HelloWorld,
    },
    {
      path: '/tag/:id',
      component: PostsWithTag,
    },
    {
      path: '/post/:id',
      component: SinglePostAdvanced,
    },
    {
      path: '/admin-panel',
      component: AdminBoard,
    },
    {
      path: '/profile',
      component: Profile,
    },
    {
      path: '/home',
      component: HelloWorld,
    },
    {
      path: '/topTags',
      component: TopTags,
    },
    {
      path: '/trending-posts',
      component: TrendingPosts,
    },
    {
      path: '/user/:username',
      component: UserPosts,
    },
    {
      path: '/my-wall',
      component: UserWall,
    },
    {
      path: '/result',
      component: SearchQueryResult,
      props(route) {
        return {  search: route.query.search }
      }
    }
  ]
})
