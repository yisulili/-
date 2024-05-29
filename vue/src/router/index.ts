import { createRouter, createWebHistory } from 'vue-router'
import login from '../views/Login.vue'
import Admin_index from '@/views/admin_index.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: login
    },
    {
      path: '/admin',
      name: 'admin',
      component: Admin_index
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue')
    }
  ]
})

export default router
