<template>
  <div id="vm2">
    <span>push:</span>
    <input type="text" v-model="content" @keyup.enter="methodPush">
    <input type="button" value="测试" @click="methodPush">
    <ul>
      <li v-for="item in array">
        <span v-text="item"></span>
      </li>
    </ul>
    <span>pop:</span>
    <input type="button" value="测试" @click="methodPop">
    <ul>
      <li v-for="item in array">
        <span v-text="item"></span>
      </li>
    </ul>
    <span>unshift:</span>
    <input type="text" v-model="content" @keyup.enter="methodUnshift">
    <input type="button" value="测试" @click="methodUnshift">
    <ul>
      <li v-for="item of array">
        <span v-text="item"></span>
      </li>
    </ul>
    <span>shift:</span>
    <input type="button" value="测试" @click="methodShift">
    <ul>
      <li v-for="item in array">
        <span v-text="item"></span>
      </li>
    </ul>
    <span>splice:</span>
    <input type="button" value="测试" @click="methodSplice">
    <ul>
      <li v-for="item of array">
        <span v-text="item"></span>
      </li>
    </ul>
    <p>结果：<span v-text="everyResult"></span></p>
    <h5 style="color: red">{{message | capitalise}}</h5>
    <ol>
      <li v-for="n in func(nums)">{{n}}</li>
    </ol>
    <ol> <li v-for="x in 10">{{x}}</li> </ol>
  </div>
</template>

<script>
  export default {
    name: 'vm2',
    data () {
      return {
        content: '',
        everyResult: '',
        array: [],
        message: 'hello-world',
        nums: [11, 23, 55, 28, 38]
      }
    },
    methods: {
      methodPush: function () {
        this.everyResult = this.array.push(this.content)
      },
      methodPop () {
        this.everyResult = this.array.pop()
      },
      methodUnshift: function () {
        this.everyResult = this.array.unshift(this.content)
      },
      methodShift: function () {
        this.everyResult = this.array.shift()
      },
      methodSplice () {
        this.everyResult = this.array.splice(2, 1, 'newOne')
      },
      func () {
        return this.nums.filter(function (i) {
          return i % 2 === 0
        })
      }
    },
    filters: {
      capitalise: function (value) {
        if (!value) { return '' }
        return value.charAt(0).toUpperCase() + ' >>> ' + value.slice(2)
      }
    }
  }
</script>

<style scoped>
  #vm2 {
    text-align: center;
  }
</style>
