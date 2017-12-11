<template>
  <div>
    <span style="color: red">路由(无参/动态路由参数/查询参数)</span>
    <p>
      <router-link to="/first">Go-first</router-link>&nbsp;&nbsp;
      <router-link to="/second">Go-second</router-link>&nbsp;&nbsp;
      <router-link to="/third/123">Go-third</router-link>&nbsp;&nbsp;
      <router-link to="/fourth?name=hyh">Go-fourth</router-link>
    </p>
    <span style="color: red">命名路由</span>
    <p>
      <router-link :to="{ name: 'vv1', params: {uid:1006} }">vv1-url</router-link>&nbsp;&nbsp;
      <router-link :to="{ name: 'vv2', query: {username: 'Lina'}}">vv2-url</router-link>
    </p>
    <router-view></router-view>
  </div>
</template>

<script>
  import VueRouter from 'vue-router'

  // 1.定义路由组件
  const First = {template: '<div>first-content</div>'}
  const Second = {template: '<div>two-content</div>'}
  const Third = {template: '<div>third:{{$route.params.id}}</div>'}
  const Fourth = {template: '<div>fourth:{{$route.query.name}}</div>'}
  const Vv1 = {template: '<div>vv1:{{$route.params.uid}}</div>'}
  const Vv2 = {template: '<div>vv2:{{$route.query.username}}</div>'}

  // 2. 创建路由实例，定义路由，一个路由对应一个组件
  const router = new VueRouter({
    mode: 'history',
    routes: [
      {path: '/first', component: First},
      {path: '/second', component: Second},
      {path: '/third/:id', component: Third},
      {path: '/fourth', component: Fourth},
      {path: '/fifth/:uid', name: 'vv1', component: Vv1},
      {path: '/sixth', name: 'vv2', component: Vv2}
    ]
  })

  export default {
    name: 'luyou',
    router
  }
</script>

<style>
</style>
