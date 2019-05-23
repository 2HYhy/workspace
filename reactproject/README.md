This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

## Available Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.<br>
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.<br>
You will also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.<br>
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.<br>
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.<br>
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

### `npm run eject`

**Note: this is a one-way operation. Once you `eject`, you can’t go back!**

If you aren’t satisfied with the build tool and configuration choices, you can `eject` at any time. This command will remove the single build dependency from your project.

Instead, it will copy all the configuration files and the transitive dependencies (Webpack, Babel, ESLint, etc) right into your project so you have full control over them. All of the commands except `eject` will still work, but they will point to the copied scripts so you can tweak them. At this point you’re on your own.

You don’t have to ever use `eject`. The curated feature set is suitable for small and middle deployments, and you shouldn’t feel obligated to use this feature. However we understand that this tool wouldn’t be useful if you couldn’t customize it when you are ready for it.

## Learn More

You can learn more in the [Create React App documentation](https://facebook.github.io/create-react-app/docs/getting-started).

To learn React, check out the [React documentation](https://reactjs.org/).

### Code Splitting

This section has moved here: https://facebook.github.io/create-react-app/docs/code-splitting

### Analyzing the Bundle Size

This section has moved here: https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size

### Making a Progressive Web App

This section has moved here: https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app

### Advanced Configuration

This section has moved here: https://facebook.github.io/create-react-app/docs/advanced-configuration

### Deployment

This section has moved here: https://facebook.github.io/create-react-app/docs/deployment

### `npm run build` fails to minify

This section has moved here: https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify


#### create a react project:

> npm install -g create-react-app
> create-react-app <工程名,eg:react-project>

#### start a react project:

> cd react-project
> npm start  /  npm run start

#### react project add a new plugins: 

> npm install <插件名>@<版本号> --save  

>- 未在package.json中添加插件，先安装好再写进该文件

> npm install

>- 先将所需插件写入文件再进行安装，只会安装新增的未安装过的

##### 关于前后端交互网络请求跨域问题

|后端url|前端url|Access-Control-Allow-Origin|跨域是否成功|
|------|------|---------------------------|----------|
| 127.0.0.1:8080 | 127.0.0.1:3000 |  * |  是(因为*代表允许所有前端主机ip通过) |
| 127.0.0.1:8080 | 127.0.0.1:3000 |  localhost:3000  |  是 |
| 127.0.0.1:8080 | 127.0.0.1:3000 |  www.portal.com  |  否(因为只允许前端主机域名=www.portal.com通过) |


> 前端解决跨域问题:  
>- 通过package.json中设置`"proxy": "https://tong.xing.zheng.testUrl.com/"`，后端服务器地址代理

> 后端解决跨域问题:
>- 通过过滤器(或拦截器等)中设置请求头信息`{Access-Control-Allow-Origin: http://www.portal.com}`，前端服务器地址


> redux使用场景:
>- 某个组件的状态，需要共享   
       
>- 某个状态需要在任何地方都可以拿到   
  
>- 一个组件需要改变全局状态 

>- 一个组件需要改变另一个组件的状态

> devDependencies和dependencies区别
>- dependencies依赖的包不仅开发环境能使用，生产环境也能使用,安装模块时使用--save
>- devDependencies是只会在开发环境下依赖的模块，生产环境不会被打入包内,安装模块时使用--save-dev


> 要使用相对路径，在package.json中添加"homepage":"."即可
  
