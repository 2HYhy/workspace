## 关于nodeJs和Vue项目   
### 安装npm  
1. 查看版本: `npm -v`  
2. 升级安装： `npm install npm -g `   
>` cnpm install npm -g` 使用淘宝镜像 

**解决npm安装依赖包缓慢的问题：**  

1. 通过修改npm配置文件的源地址    
> 进入npm安装目录找到npmrc文件，清空其内容，编辑为`registry = https://registry.npm.taobao.org`，保存退出。  

2. 通过config配置指定下载源  
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
//全局安装 vue-cli
cnpm install --global vue-cli 
// 创建一个基于 webpack 模板的新项目 
vue init webpack my-project  
//接下来是进行一系列配置，默认回车即可

//进入项目，安装并运行
cd my-project
cnpm install
cnpm run dev

//浏览器访问`http://localhost:8080/`即可
```