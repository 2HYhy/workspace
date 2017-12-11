<template>
  <div class="content">
    <h1>{{ info }}</h1>
    <span>id={{$route.params.id}}</span>
  </div>
</template>

<script>
  export default {
    name: 'content',
    data () {
      return {
        info: 'Content, it is component of Home'
      }
    }
  }
</script>

<style>
  .content {
    text-align: center;
    margin-top: 140px;
  }
</style>
