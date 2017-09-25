<template>
  <div>
    <span style="color: red">命名视图</span>
    <ul>
      <li>
        <router-link to="/this">HIT-here</router-link>
      </li>
      <li>
        <router-link to="/that">HIT-there</router-link>
      </li>
    </ul>   <!--一个路由需要三个视图，组件-->
    <router-view class="view-one"></router-view>
    <router-view class="view-one" name="Isa"></router-view>
    <router-view class="view-one" name="Isb"></router-view>
  </div>
</template>
<script>
  import Router from 'vue-router'

  const X = {template: '<div>This is X</div>'}
  const AA = {template: '<div>This is AA</div>'}
  const BB = {template: '<div>This is BB</div>'}

  const router = new Router({
    mode: 'history',
    routes: [
      {
        path: '/this',
        components: {default: X, Isa: AA, Isb: BB}
      }, {
        path: '/that',
        components: {default: BB, Isa: X, Isb: AA}
      }
    ]
  })

  export default ({
    router
  })
</script>
