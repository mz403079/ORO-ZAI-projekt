import Vue from 'vue';
import Router from 'vue-router';
import HelloWorld from "@/components/HelloWorld";
import PostsWithTag from "@/components/PostsWithTag";

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
        }
    ]
})
