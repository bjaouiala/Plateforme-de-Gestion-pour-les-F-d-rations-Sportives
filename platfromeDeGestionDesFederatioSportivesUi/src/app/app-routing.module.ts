import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "./pages/register/register.component";
import {ActivateAccountComponent} from "./component/activate-account/activate-account.component";
import {authGuard} from "./services/auth.guard";

const routes: Routes = [{
  path :'',
  component: LoginComponent
},
  {
    path:'register',
    component: RegisterComponent
  },{
  path:'account-activation',
    component: ActivateAccountComponent
  },{
  path:"admin",
    loadChildren: () => import("./modules/admin-federation/admin-federation.module").then(module => module.AdminFederationModule),
    canActivate:[authGuard],
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
