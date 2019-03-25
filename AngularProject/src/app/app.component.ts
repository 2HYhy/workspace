/* 从Angular核心模块里面引入component装饰器 */
import { Component } from '@angular/core';

/* 用装饰器定义一个组件，所有的组件都必须使用这个装饰器来注解 */
@Component({
  /* 组件的元数据 */
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

/*组件的控制器，就是一个普通的typescript类，用于开发业务逻辑代码，整个工程的根组件*/
export class AppComponent {
  title = 'Tour of Heroes';
}
