import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: () => import('@/views/login/Index')
    },
    {
      path: '/',
      name: 'main',
      component: () => import('@/views/main/Index'),
      children: [
        {
          name: '직원 관리',
          path: '/Staffs',
          component: () => import('@/views/main/pages/Staffs'),
        }
      ],
    }
  ],
})

