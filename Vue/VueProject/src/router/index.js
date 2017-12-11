import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Content from '@/components/Content'
import Home from '@/components/Home'
import Communication from './../components/communication/Communication.vue'
import Demo1 from './../components/Demo1.vue'
import Demo2 from './../components/Demo2.vue'
import LuYou1 from './../components/luyou/LuYouone.vue'
import LuYou2 from './../components/luyou/LuYoutwo.vue'
import LuYou3 from './../components/luyou/LuYouthree.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',   // 即App.vue渲染的默认页面即为Home组件
      name: 'home',
      component: Home,
      children: [
        {
          path: 'hello',
          name: 'hello',
          component: Hello
        }, {
          path: 'content/:id',
          component: Content
        }, {
          path: 'communication',
          component: Communication
        }, {
          path: 'lu-you-1',
          component: LuYou1
        }, {
          path: 'lu-you-2',
          component: LuYou2
        }, {
          path: 'lu-you-3',
          component: LuYou3
        }
      ]
    }, {
      path: '/demo1',
      component: Demo1
    }, {
      path: '/demo2',
      component: Demo2
    }, {
      path: '/demo3',
      component: Demo3
    }
  ]
})
