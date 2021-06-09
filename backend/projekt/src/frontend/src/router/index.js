import Vue from 'vue';
import Router from 'vue-router';
import Home from "@/components/pages/Home";
import PostsWithTag from "@/components/pages/PostsWithTag";
import SinglePostAdvanced from "@/components/pages/SinglePostAdvanced";
import AdminBoard from "@/components/AdminBoard";
import Profile from "@/components/pages/Profile";
import TopTags from "@/components/TopTags";
import TrendingPosts from "@/components/pages/TrendingPosts";
import UserPosts from "@/components/pages/UserPosts";
import UserWall from "@/components/pages/UserWall";
import SearchQueryResult from "@/components/pages/SearchQueryResult";

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: Home,
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
      component: Home,
    },
    {
      path: '/top-tags',
      component: TopTags,
    },
    {
      path: '/trending-posts',
      component: TrendingPosts,
    },
    {
      path: '/user/:username/',
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
        return {search: route.query.search}
      }
    }
  ]
})
