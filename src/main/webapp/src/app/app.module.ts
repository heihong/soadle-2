import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HttpModule } from '@angular/http';
import {UserService} from './user/service/user.service';
import {SoadleService} from './soadle/service/soadle.service';
import {DoodleService} from './soadle/service/doodle.service';
import { AgmCoreModule } from '@agm/core';
import { AppComponent } from './app.component';
import {SoadleComponent} from './soadle/soadle.component';
import {SoadleListComponent} from './soadle/soadlelist/soadlelist.component';
import {SoadleConsultationComponent} from './soadle/soadleconsultation/soadleconsultation.component';
import {SoadleModificationComponent} from './soadle/soadlemodification/soadlemodification.component';
import {SoadleCreationComponent} from './soadle/soadlecreation/soadlecreation.component';
import {GoogleMapComponent} from './soadle/google/googlemap.component';

@NgModule({
  declarations: [
    AppComponent,
    SoadleComponent,
    SoadleListComponent,
    SoadleConsultationComponent,
    SoadleModificationComponent,
    SoadleCreationComponent,
    GoogleMapComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    AgmCoreModule.forRoot({
        apiKey: "AIzaSyD5oPzCYfx_v_W7xmHawqtqeUukH1pVeA0",
        libraries: ["places"]
      })
  ],
  providers: [UserService , SoadleService , DoodleService],
  bootstrap: [AppComponent]
})
export class AppModule { }
