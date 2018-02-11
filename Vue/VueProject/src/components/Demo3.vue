<template>
  <div id="par">
    <h3>
      <button @click="add">+</button>
      Adjust the state
      <button @click="subtract">-</button>
    </h3>
    <hr/>
    <h3>
      This data is from par: <span>{{counter}}</span>
    </h3>
    <hr/>
    <h3>
      <child counter="11"></child>
      <p>This data is from child which use 静态量 as props</p>
    </h3>
    <hr/>
    <h3>
      <child :counter="counter"></child>
      <p>This data is from child which use 状态量 as props</p>
    </h3>
  </div>
</template>

<script>
  import Vue from 'vue'
  import swal from 'sweetalert'

  const child = Vue.extend({
    props: {
      counter: {
        type: Number,
        required: true
      }
    },
    template: '<div><< {{counter}} >></div>'
  })

  export default {
    name: 'par',
    data () {
      return {
        counter: 0
      }
    },
    created () {
      swal('This is Title！', 'This is message！', 'success')
    },
    methods: {
      add () {
        this.counter++
      },
      subtract () {
        this.counter--
      }
    },
    components: {
      child
    }
  }
</script>
