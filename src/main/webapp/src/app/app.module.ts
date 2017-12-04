import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import {UserService} from './user/service/user.service';
import {SoadleService} from './soadle/service/soadle.service';
import {DoodleService} from './soadle/service/doodle.service';

import { AppComponent } from './app.component';
import {SoadleComponent} from './soadle/soadle.component';

@NgModule({
  declarations: [
    AppComponent,
    SoadleComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [UserService , SoadleService , DoodleService],
  bootstrap: [AppComponent]
})
export class AppModule { }
