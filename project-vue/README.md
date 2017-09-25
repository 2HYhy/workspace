# project-vue

> A Vue.js project

## Build Setup

``` bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report

# run unit tests
npm run unit

# run e2e tests
npm run e2e

# run all tests
npm test
```

### 父组件向子组件传值：  
1. 子组件在props中创建一个属性，用以接收父组件传过来的值  
> `props: ['child1'] ` 

2. 父组件中注册子组件  
> `components: {
      hello
    }` 

3. 在子组件标签中添加子组件props中创建的属性  
>  <h3>{{ child1 }}</h3> 

4. 把需要传给子组件的值赋给该属性
>  `<hello child1="Child message !"</hello>`

### 子组件向父组件传值:
1. 子组件中需要以某种方式例如点击事件的方法来触发一个自定义事件
> `<button v-on:click="sendToParent">向父传值</button>`

2. 将需要传的值作为$emit的第二个参数，该值将作为实参传给响应自定义事件的方法
> `methods: {
      sendToParent: function () {
        this.$emit('listenFromChild', 'Parent message !')
      }
    }`

3. 在父组件中注册子组件并在子组件标签上绑定对自定义事件的监听
> <hello v-on:listenFromChild="showMsg"></hello>  
  `methods: {
      showMsg: function (data) {
        console.log('data: ', data)
      }
    }`

>- 无论是子组件向父组件传值还是父组件向子组件传值，他们都有一个共同点就是有中间介质，子向父的介质是自定义事件，父向子的介质是props中的属性。父传子用v-bind : 子传父用v-on @    
For detailed explanation on how things work, checkout the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).
