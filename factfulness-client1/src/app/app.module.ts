import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { TokenInterceptor } from './core/interceptor/request-interceptor';
import { FactComponent } from './facts/fact/fact.component';
import { CreateFactComponent } from './facts/create-fact/create-fact.component';
import { SessionInitializer } from './core/auth/session-initializer';
import { AuthUserService } from './core/auth/auth.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule, MatFormFieldModule, MatIconModule, MatInputModule, MatTableModule} from '@angular/material';
import { FactListComponent } from './facts/fact-list/fact-list.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    FactComponent,
    CreateFactComponent,
    FactListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatTableModule,
    MatIconModule
  ],
  providers: [CookieService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    {
        provide: APP_INITIALIZER,
        useFactory: SessionInitializer,
        deps: [AuthUserService],
        multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
