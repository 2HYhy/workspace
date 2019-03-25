import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HeroesComponent } from './heroes/heroes.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HeroDetailComponent }  from './hero-detail/hero-detail.component';
import { LocalTestComponent } from './local-test/local-test.component';

const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },  //默认路由
  { path : 'dashboard', component: DashboardComponent},
  { path: 'heroes', component: HeroesComponent },
  { path: 'detail/:id', component: HeroDetailComponent },
  { path: 'local-test', component: LocalTestComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
