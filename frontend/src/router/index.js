import Vue from 'vue'
import VueRouter from 'vue-router'

import AppMain from "@/views/AppMain";
import AppHouse from "@/views/AppHouse";
import HouseListItem from "@/components/house/HouseListItem";
import AppNews from "@/views/AppNews";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'main',
    component: AppMain,
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/AppUser"),
    children: [
      {
        path: "join",
        name: "join",
        component: () => import("@/components/user/UserJoin"),
      },
      {
        path: "login",
        name: "login",
        component: () => import("@/components/user/UserLogin"),
      },
      {
        path: "findpwd",
        name: "findpwd",
        component: () => import("@/components/user/UserFindPwd"),
      },
    ],
  },
  {
    path: '/house',
    name: 'house',
    component: AppHouse,
  },
  {
    path: '/house/item',
    name: 'houseitem',
    component: HouseListItem,
  },
  {
    path: '/news',
    name: 'news',
    component: AppNews,
  },
  {
    path: "/board",
    name: "board",
    component: () => import("@/views/AppBoard"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardlist",
        component: () => import("@/components/board/BoardList"),
      },
      {
        path: "write",
        name: "boardwrite",
        component: () => import("@/components/board/BoardWrite"),
      },
      {
        path: "view/:articleno",
        name: "boardview",
        component: () => import("@/components/board/BoardView"),
      },
      {
        path: "modify",
        name: "boardmodify",
        component: () => import("@/components/board/BoardModify"),
      },
      {
        path: "delete/:articleno",
        name: "boarddelete",
        component: () => import("@/components/board/BoardDelete"),
      },
    ],
  },
  // {
  //   path: '/about',
  //   name: 'about',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  // }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
