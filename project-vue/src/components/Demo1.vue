<template xmlns:v-on="http://www.w3.org/1999/xhtml" xmlns:v-bind="http://www.w3.org/1999/xhtml">
  <div id="vm1">
    <p>{{message}}</p>
    <button v-on:click="reverseMsg">hit reverse</button>
    <p>by method: {{reverseInfo()}}</p>
    <p>by computed: {{reverseFunc}}</p>
    <p>
      <button v-on:click="counter +=1">{{counter}}</button>
      <button v-on:click="func">hit1</button>
      <button @click="say('This is a event!')">hit2</button>
    </p>
    <h2 style="color: aqua">
      <span>单行文本：</span>{{single}}<br>
      <input v-model="single">
    </h2>
    <h2 style="color: blueviolet">
      <span>多行文本：</span>{{multiply}}<br>
      <textarea v-model="multiply"></textarea>
    </h2>
    <p v-bind:title="suggest">float some seconds</p>
    <a v-bind:href="url">jump to link</a>
  </div>
</template>
<script>
  export default {
    e1: 'vm1',
    data () {
      return {
        message: 'This is Friday',
        information: 'This is Cloudy',
        counter: 0,
        single: '',
        multiply: '',
        suggest: 'message is submit in ' + new Date().toLocaleString(),
        url: 'http://www.runoob.com'
      }
    },
    methods: {
      reverseMsg: function () {
        this.message = this.message.split('').reverse().join('')
      },
      reverseInfo: function () {
        return this.information.split('').reverse().join('')
      },
      func (event) {
        alert('Hello: ' + this.counter)
        if (event) {
          alert('Event: ' + event.target.tagName)
        }
      },
      say (msg) {
        alert(msg)
      }
    },
    computed: {
      reverseFunc: function () {
        return this.information.split('').reverse().join('')
      }
    }
  }
</script>
<style scoped>
  #vm1 {
    text-align: center;
  }
</style>
