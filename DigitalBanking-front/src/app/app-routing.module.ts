import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CustomerComponent} from "./customer/customer.component";
import {HomeComponent} from "./home/home.component";
import {FormCustomerComponent} from "./form-customer/form-customer.component";
import {CustomerAccountsComponent} from "./customer-accounts/customer-accounts.component";
import {AccountsComponent} from "./accounts/accounts.component";
import {LoginComponent} from "./login/login.component";

const routes: Routes = [
  {
    path : "home", component: HomeComponent
  },
  {
    path:"" ,
    redirectTo : "/home",
    pathMatch: "full"
  },
  {
    path : "login", component: LoginComponent
  },
  {
    path:"new-customer",component:FormCustomerComponent
  },
  {
    path:"customers",component:CustomerComponent
  },{
    path:"customer-accounts/:id",component:CustomerAccountsComponent
  },{
    path:"accounts/:id",component:AccountsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
