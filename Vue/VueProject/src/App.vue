<template>
  <div id="app">
    <img src="./assets/logo.png">
    <router-view></router-view>    <!--用于渲染匹配的组件Home-->
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style scoped>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: right;
  color: #2c3e50;
  margin-top: 6px;
}
</style>

<!--总的父组件，只渲染了一张图片-->
