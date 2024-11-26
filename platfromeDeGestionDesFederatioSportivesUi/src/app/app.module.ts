import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import { RegisterComponent } from './pages/register/register.component';
import { ActivateAccountComponent } from './component/activate-account/activate-account.component';
import {CodeInputModule} from "angular-code-input";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {InterceptorInterceptor} from "./services/interceptor/interceptor.interceptor";
import { ToastrModule } from 'ngx-toastr';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    ActivateAccountComponent,

  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        BrowserAnimationsModule,
        CodeInputModule,
        ToastrModule.forRoot({
          progressBar:true,
         closeButton:true,
         newestOnTop:true,
        tapToDismiss: true,
        positionClass:'toast-bottom-right',
        timeOut:5000
        })
    ],
  providers: [HttpClient,{provide:HTTP_INTERCEPTORS,useClass:InterceptorInterceptor,multi:true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
