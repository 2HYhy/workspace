<template>
  <div id="app">
    <h2 style="color: brown">vue前后端分离</h2>
    <router-view></router-view>    <!--用于渲染匹配的组件Login，如果去掉该标签，就无法跳转至index.js中"/"对应的页面-->
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
  text-align: center;
  color: #2c3e50;
  margin-top: 6px;
}
</style>

<!--入口组件-->
