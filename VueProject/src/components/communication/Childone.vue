<template>
  <div>
    <span style="color: red">Childone.vue(父-子组件通信:固定/动态传值/编辑父值)</span>
    <h3>{{message1}}</h3>
    <h4>{{message2}}</h4>
    <button>{{message3}}</button>&nbsp;<button>{{changeIt}}</button>
  </div>
</template>

<script>
  export default {
    props: ['message1', 'message2', 'message3'],
    computed: {
      changeIt: function () {
        return this.message3 + '>>>CHANGE>>>!'
      }
    }
  }
</script>

<style>

</style>
