<template>
  <div id="app">
    <span style="color: red">嵌套路由</span>
    <p>
      <router-link to="/user/123/getName">getName</router-link>&nbsp;&nbsp;
      <router-link to="user/123/getPhone">getPhone</router-link>
    </p>
    <span style="color: red">路由重定向</span>
    <p>
      <router-link to="/redirect/r1">router-1</router-link>&nbsp;&nbsp;
      <router-link to="/redirect/r2">router-2</router-link>&nbsp;&nbsp;
    </p>
    <router-view></router-view>
  </div>
</template>

<script>
  import VueRouter from 'vue-router'

  const Name = {template: '<div>my-name</div>'}
  const Phone = {template: '<div>my-phone</div>'}
  const User = {
    template: '<div><h3>user:{{$route.params.id}}</h3><router-view></router-view></div>'
  }
  const Default = {template: '<div>默认页面</div>'}

  const Apple = {template: '<div>Real Path: Apple</div>'}
  const Pear = {template: '<div>Real Path: Pear</div>'}

  const router = new VueRouter({
    mode: 'history',
    routes: [
      {
        path: '/user/:id',
        component: User,
        children: [
          {
            path: '',
            component: Default
          }, {
            path: 'getName',
            component: Name
          }, {
            path: 'getPhone',
            component: Phone
          }
        ]
      }, {
        path: '/apple', component: Apple
      }, {
        path: '/pear', component: Pear
      }, {
        path: '/redirect/r1', redirect: '/apple'
      }, {
        path: '/redirect/r2', redirect: '/pear'
      }
    ]
  })
  export default {
    name: 'app',
    router
  }
</script>

<style>
</style>
