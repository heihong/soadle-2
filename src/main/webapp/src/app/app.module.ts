import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import {UserService} from './user/service/user.service';
import {SoadleService} from './soadle/service/soadle.service';
import {DoodleService} from './soadle/service/doodle.service';

import { AppComponent } from './app.component';
import {SoadleComponent} from './soadle/soadle.component';
import {SoadleListComponent} from './soadle/soadlelist/soadlelist.component';
import {SoadleDetailComponent} from './soadle/soadledetail/soadledetail.component';
import {SoadleCreationComponent} from './soadle/soadlecreation/soadlecreation.component';

@NgModule({
  declarations: [
    AppComponent,
    SoadleComponent,
    SoadleListComponent,
    SoadleDetailComponent,
    SoadleCreationComponent
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
