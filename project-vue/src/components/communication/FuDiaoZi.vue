<template>
  <div id="app">
    <input type="button" @click="parentCall" value="父调子" />
    <hello ref="chill"></hello>
    <button style="background-color: aqua" @click="show">点击显示子组件</button>
    <welcome ref="welcome" v-if="flags"></welcome>
  </div>
</template>

<script>
  import Vue from 'vue'

  const hello = Vue.extend({
    name: 'hello',
    template: '<div>子组件属性值= {{info}}</div>',
    data () {
      return {
        info: 'Apple'
      }
    },
    methods: {
      func (msg) {
        alert(msg)
      }
    }
  })

  const welcome = Vue.extend({
    name: 'welcome',
    template: `<div>This is son component !</div>`
  })

  export default {
    name: 'app',
    data () {
      return {
        flags: false
      }
    },
    components: {
      hello,
      welcome
    },
    methods: {
      parentCall () {
        this.$refs.chill.func('我是父元素传过来的')
        this.$refs.chill.info = 'Change-Later'
      },
      show () {
        alert('flag from false to true')
        this.flags = !this.flags
      }
    }
  }
</script>
