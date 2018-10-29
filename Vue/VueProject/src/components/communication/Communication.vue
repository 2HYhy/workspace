<template>
  <div class="com">
    <chone messge1="parent to child directly!" v-bind:message2="parentMsg" v-bind:message3="originMsg"></chone>
    <hr/>
    <chtwo v-on:listenEvent="showMsg"></chtwo>
    <hr/>
    <acom v-on:isOpen="methodBya"></acom>
    <bcom v-bind:isShow="result"></bcom>
    <hr/>
    <send></send>
    <receive></receive>
    <hr/>
    <autoinput></autoinput>
    <hr/>
    <myslot></myslot>
    <hr/>
    <futozi></futozi>
  </div>
</template>

<script>
  import chone from './Childone.vue'
  import chtwo from './Childtwo.vue'
  import acom from './A.vue'
  import bcom from './B.vue'
  import send from './Send.vue'
  import receive from './Receive.vue'
  import autoinput from './AutoInput.vue'
  import myslot from './MySlot.vue'
  import futozi from './FuDiaoZi.vue'

  export default {
    name: 'com',
    components: {
      chone,
      chtwo,
      acom,
      bcom,
      send,
      receive,
      autoinput,
      myslot,
      futozi
    },
    data () {
      return {
        parentMsg: 'parent to child by v-bind dynamically!',
        originMsg: 'message from parent to child will add:',
        result: 'false'
      }
    },
    methods: {
      showMsg: function (data) {  //接收子组件的值
        console.log('data: ', data)
        this.receiveData = data
        console.log(this.receiveData)
      },
      methodBya: function (data) {
        if (data === 'hit') {
          this.result = 'true'
        }
      }
    }
  }
</script>

<style scoped>
  .com {
    margin-top: 150px
  }
</style>
<!--六个通信组件的父组件-->
<!--总结即是：父传子用bind，子传父用on，=前面的都是子组件的属性，=后面的都是父组件的属性-->
