<template>
  <div id="home">
    <h2>页面概览 <small>（{{ msg }}）</small></h2>
    <router-link to="/lu-you-1">toLuYou1</router-link>&nbsp;&nbsp;
    <router-link to="/lu-you-2">toLuYou2</router-link>&nbsp;&nbsp;
    <router-link to="/lu-you-3">toLuYou3</router-link>&nbsp;&nbsp;
    <router-view></router-view>   <!--用于渲染匹配的组件Hello和Content-->
  </div>
</template>

<script>
  export default {
    name: 'home',
    data () {
      return {
        msg: 'Home is component of App!'
      }
    }
  }
</script>

<style scoped>
  #home {
    text-align: left;
    margin-top: -195px;
  }
</style>
<!--第二父组件-->
