import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './core/guard/auth.guard';
import { FactComponent } from './facts/fact/fact.component';
import { CreateFactComponent } from './facts/create-fact/create-fact.component';
import {FactListComponent} from './facts/fact-list/fact-list.component';


const routes: Routes = [
  {path: '', redirectTo: '/fact', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'fact', component: FactComponent, canActivate: [AuthGuard]},
  {path: 'fact-list', component: FactListComponent, canActivate: [AuthGuard]},
  {path: 'create-fact', component: CreateFactComponent, canActivate: [AuthGuard]},
  {path: 'create-fact/:id', component: CreateFactComponent, canActivate: [AuthGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
