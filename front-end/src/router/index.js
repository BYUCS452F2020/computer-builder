import Vue from 'vue'
import VueRouter from 'vue-router'
import User from '../view/user_page.vue'
import Builder from '../view/builder_page.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: "/",
      redirect: {
        name: "builder"
    },
  },
  {
    path: '/builder',
    name: 'builder',
    component: Builder
  },
  {
    path: '/user',
    name: 'user',
    component: User
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router


