<template>
  <div class="biv">
    <p v-show="methodByb">This is component of B !</p>
  </div>
</template>

<script>
  export default {
    props: ['isShow'],
    data () {
      return {

      }
    },
    computed: {
      methodByb: function () {
        if (this.isShow === 'true') {
          return true
        } else {
          return false
        }
      }
    }
  }
</script>

<style>
</style>
