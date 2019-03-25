import { Injectable } from '@angular/core';
import { Hero } from './hero';
import { HEROES } from './mock-heroes';
import { Observable, of } from 'rxjs';
import { MessageService } from './message.service';


@Injectable({ //表示该类是依赖注入系统的参与者
  providedIn: 'root'
})
export class HeroService {   //将被注册到HeroesComponents中

  constructor(private messageService: MessageService) {   //将MessageService服务注册到HeroService服务中
  }

  getHeroes(): Observable<Hero[]> {  //异步获取远程服务器数据
    this.messageService.add('HeroService: fetched heroes');   //获取到数组时发送一条消息
    return of(HEROES);
  }

  getHero(id: number): Observable<Hero> {
    this.messageService.add(`HeroService: fetched hero id = ${id}`);
    return of(HEROES.find(hero => hero.id === id));
  }

}


