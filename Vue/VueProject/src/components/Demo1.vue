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

    <!--模态框按钮-->
    <hr/>
    <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" @click="show">点击触发模态框</button>
    <!--模态框实体-->
    <modal></modal>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" v-if="display">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
              &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
              模态框（Modal）标题
            </h4>
          </div>
          <div class="modal-body">
            在这里添加一些文本
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary">
              提交更改
            </button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal -->
    </div>
  </div>
</template>
<script>
  export default {
    e1: 'vm1',
    data () {
      return {
        display: false,
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
      show () {
        this.display = !this.display
      },
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
