<template>
  <div id="autoinput">
    <ul> <!--ul,ol只能直接包含li, is特性可以在标签内引入自定义标签-->
      <li is="child" v-for="item in lists" :twice="item"></li>
    </ul>
    <input placeholder="please input textarea" v-model="newPut" @keyup.enter="addText">
  </div>
</template>
<script>
  import Vue from 'vue'

  const child = Vue.extend({
    name: 'child',
    props: ['twice'],
    template: '<p style="color:deepskyblue ">{{twice}}</p>'   // 绑定每一项输入
  })

  export default {
    name: 'autoinput',
    data () {
      return {
        newPut: '',
        lists: [
          'May I see the wine menu',
          'They stop at the traffic lights',
          'I need to powder my nose'
        ]
      }
    },
    components: {
      child
    },
    methods: {
      addText () { // 回车时触发该函数
        this.lists.push(this.newPut)
        this.newPut = ''
      }
    }
  }
</script>
