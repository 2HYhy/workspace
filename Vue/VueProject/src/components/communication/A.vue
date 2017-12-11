<template xmlns:v-on="http://www.w3.org/1999/xhtml">
  <div class="aiv">
    <span>A-B兄弟组件通信，可通过子-父，父-子，转一次</span>
    <p><button v-on:click="whenClick">点击打开</button></p>
  </div>
</template>

<script>
  export default {
    methods: {
      whenClick () {
        this.$emit('isOpen', 'hit')
      }
    }
  }
</script>

<style>
.aiv {
  color: red;
}
</style>
