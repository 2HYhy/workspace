## 关于nodeJs和Vue项目   
### 安装npm  
1. 查看版本: `npm -v`  
2. 升级安装： `npm install npm -g `   
>` cnpm install npm -g` 使用淘宝镜像 

**解决npm安装依赖包缓慢的问题：**  

1. 通过修改npm配置文件的源地址    
> 进入npm安装目录找到npmrc文件，清空其内容，编辑为`registry = https://registry.npm.taobao.org`，保存退出。  

2. 通过config配置指定下载源  
> npm config get registry 
>- 获取配置的下载源，默认是`https://registry.npmjs.org/`

> npm config set registry htttps://registry.npm.taobao.org  

3. 通过npm命令指定下载源
> npm install xxxx -D --registry https://registry.npm.taobao.org

4. 直接安装淘宝定制的cnpm模块  
> npm install cnpm -g --registry https://registry.npm.taobao.org     
> cnpm install xxxx
### 安装vue  
1. 查看版本: `vue -V`  
2. 升级更新: `npm oinstall vue`  

### 创建nodeJs+Vue程序

**命令行运行简单nodeJs程序**
> 方法一：    
```java
node    
 console.log("hello")   
```  

> 方法二：   
>- 创建xx.js文件  
>- 运行 `node xx.js`  

**新建初始化vue项目框架**
```java
//环境:全局安装 vue-cli(只安装一次就不再需要)
cnpm install --global vue-cli 

// start:创建一个基于 webpack 模板的新项目 
vue init webpack my-project  
//接下来是进行一系列配置，默认回车即可

//进入项目，安装并运行
cd my-project
cnpm install
cnpm run dev

//启动后，若有其他组件需求也要安装
npm install vue-router vue-resource vuex --save

//浏览器访问`http://localhost:8080/`即可
```
```java
npm install xxx            //安装但不写入package.json；
npm install xxx –save      //安装并写入package.json的”dependencies”中；
npm install xxx –save-dev  //安装并写入package.json的”devDependencies”中。
npm uninstall xxx          //删除xxx模块； 
npm uninstall -g xxx       //删除全局模块xxx；
npm cache clean            //清除 npm 内部缓存
rm -rf node_modules/       //删除已安装的模块
```


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
