<template xmlns:v-on="http://www.w3.org/1999/xhtml">
  <div id="firstChild">
    <span style="color: red">send-receive平级组件通信(通过中央事件总线></span>
    <p>
      <button v-on:click="func1">点击传值1</button>
      <button v-on:click="func2">点击传值2</button>
    </p>
  </div>

</template>

<script>
  import bus from './../../assets/eventBus'
  export default {
    methods: {
      func1: function () {
        // 响应点击事件的sendMsg函数中用$emit触发了一个自定义的userDefinedEvent事件，并传递了一个字符串参数
        bus.$emit('defaultEvent', 'This message is from send')
      },
      func2: function () {
        bus.$emit('anotherEvent', 'This message is to receive')
      }
    }
  }
</script>

<style>

</style>
