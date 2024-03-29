import { Component, OnInit } from '@angular/core';
import { Hero } from '../hero';
import { HeroService } from '../hero.service';

@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.css']
})

export class HeroesComponent implements OnInit {

  constructor(private heroService: HeroService) {
  }
  ngOnInit() {  /*生命周期钩子 */
    this.getHeroes();
  }

  heroes: Hero[];

  getHeroes(): void {
    this.heroService.getHeroes().subscribe(heroes => this.heroes = heroes);   //订阅消息
  }

}
