### 创建Angular项目的流程
1. sudo npm install -g @angular/cli  --unsafe-perm    (--unsafe-perm参数保证不报permission denied问题)
2. ng v (查看版本)
3. ng new <项目名>
4. npm start / ng serve / ng serve --open (均可启动服务)
5. 访问 `http://localhost:4200/`  (4200是默认端口)

运行单元测试，karma.conf.js，可一直在后端运行
> npm test   

运行端对端测试，protractor-conf.js，测试结束关闭浏览器退出
> npm run update-webdriver  (只要一次)   
> npm run protractor


### 创建相关文件的命令
> ng generate c / component <组件名>
> ng generate component <组件名> -it 
> ng generate component  -it <组件名>   (该命令可以将独立的html文件覆盖掉，生成内联的模板文件)
> ng generate s / service  <服务名>    
> ng g s services/local-storage --module app  (在/app下创建service，并将其置于/services目录下)
> ng generate class <类名>  


### AngularJs工程主要目录
> src/app: 包含应用的组件和模块，开发代码都在这个目录
> e2e: 端到端测试目录
> karma.conf.js: karma是单元测试执行器，karma的配置文件
> protractor.conf.js: 端到端自动化测试的配置文件
> src/assets: 静态资源存储目录
> component.ts 是组建类
> component.html 是模板文件
> component.css 是样式文件


##### 单向从数据源到视图（属性）
{{expression}}
[target]="expression"
bind-target="expression"

##### 单向从视图到数据源（事件）
(target)="statement"
on-target="statement"

##### 双向数据绑定
[(target)]="expression"
bindon-target="expression"


### 数据绑定
###### 分为属性、事件、style/class/attribute绑定
属性绑定:
<img [src]="itemImageUrl">
事件绑定:
<button (click)="deleteHero()">
指令绑定:
<li *ngFor="let customer of customers">
<div *ngIf="messages.length">  
<div [ngSwitch]="currentHero.emotion">
  <p *ngSwitchCase/ngSwitchDefault="'happy'">
</div>
样式绑定:
<button [style.color]="red"> 
attribute绑定(当没有属性可以绑定时):
<td [attr.colspan]="1 + 1">


1. <img src="{{heroImageUrl}}">  等价于  <img [src]="heroImageUrl">  即插值和属性绑定

2. <input [value]="myHero" (input)="myHero=$event.target.value" > 等价于 <input [(ngModel)]="myHero">

3. 除了直接赋值外，也可在构造函数中初始化属性值:  constructor() { this.title = "Hero List"; }
  
4. 模板输入变量(let hero)，模板引用变量(#heroForm)

5. Angular编译器不会将一个组件的属性绑定到其它组件上，除非这些属性是输入或输出属性。
@Input()  hero: Hero;
@Output() deleteRequest = new EventEmitter<Hero>();

6. 使用ngModel指令进行双向数据绑定前，必须导入FormsModule并把它添加到NgModule的imports列表中。

7. <input (keyup)="onKey($event)">   this.values += event.target.value + ' | ';
target 是<input> 元素， event.target.value 返回该元素的当前内容。event.key是按键本身。直接传event不常用。

8. <input #box (keyup)="onKey(box.value)">  使用模板引用变量box，代表<input>元素本身。
