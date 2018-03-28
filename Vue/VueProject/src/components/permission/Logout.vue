<template>
  <div>
    <h2>{{info}}</h2>
    <router-link :to="{path:'/login'}">重新登录</router-link>
  </div>
</template>
<script>
  export default {
    name: 'logout',
    data () {
      return {
        info: '您已成功退出当前系统!'
      }
    }
  }
</script>
