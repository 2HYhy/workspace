<template>
  <div id="secondChild">
    <p>received-data: {{msg}}</p>
  </div>

</template>

<script>
  import bus from './../../assets/eventBus'
  export default {
    data () {
      return {
        msg: '默认值'
      }
    },
    mounted () {
      var self = this
      bus.$on('defaultEvent', function (msg) {
        self.msg = msg
      })
      bus.$on('anotherEvent', function (info) {
        this.msg = info
      }.bind(this))
    }
  }
</script>

<style>

</style>
