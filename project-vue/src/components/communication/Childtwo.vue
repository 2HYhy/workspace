<template xmlns:v-on="http://www.w3.org/1999/xhtml">
  <div id="childtwo">
    <span>Childtwo.vue(子-父组件通信)</span>
    <p><button v-on:click="sendToParent">点击发送</button></p>
  </div>
</template>

<script>
  export default {
    name: 'childTwo',
    methods: {
      sendToParent: function () {
        this.$emit('listenEvent', 'message of child to parent!')    // 该字符串在控制台打印
      }
    }
  }
</script>

<style>
  #childtwo {
    color: red;
  }
</style>
