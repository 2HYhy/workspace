<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <router-link to="/content/1006">跳转content</router-link>
  </div>
</template>

<script>
export default {
  name: 'hello',
  data () {
    return {
      msg: 'Hello, it is component of Home !'
    }
  }
}
</script>

<style scoped>
  .hello {
    text-align: center;
    margin-top: 140px;
  }
</style>
