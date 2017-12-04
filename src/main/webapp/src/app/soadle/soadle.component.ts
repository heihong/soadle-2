import { Component , OnInit } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import {UserService} from '../user/service/user.service';
import {SoadleService} from './service/soadle.service';
import {DoodleService} from './service/doodle.service';

@Component({
  selector: 'app-soadle',
  templateUrl: './soadle.component.html',
  styleUrls: ['../app.component.css', '../bootstrap.min.css']
})
export class SoadleComponent implements OnInit{
  resulUser = null;
  result = null;
  resultList = null;
  indCreate = null;
  indModif = null;
  
  googleMapUrl     = "https://www.google.fr/maps/place/";
  pollsDoodleUrl   = "https://doodle.com/poll/";
    

  constructor(private http: Http, private userService: UserService,private soadleService: SoadleService,private doodleService: DoodleService){
  }

  
  ngOnInit() {
      this.userService.getUser()
      .subscribe(response => this.resulUser = response, e => e,() => this.getList(''));      
  }
  
  private initIHM(): void {
      this.result = null;
      this.resultList = null;
      this.indCreate = null;
      this.indModif = null;
  }
  

  private getSoadle(id): void {
    this.initIHM();
    this.soadleService.getSoadle(id)
    .subscribe(response => this.result = response , e => this.result = null);
  }
  
  
  
  private getDoodle(id): void {
    this.initIHM();
    this.doodleService.getDoodle(id)
    .subscribe(response => this.result = response, e => this.result = null);
  }
  
  private getMeeting(id): void {
      this.initIHM();
      this.soadleService.getSoadle(id)
      .subscribe(response => this.result = response , 
                  e  => this.doodleService.getDoodle(id).subscribe(response => this.result = response),
                  () => {     
                              if(this.result == null || this.result.origine == 'D')
                              {
                                  this.doodleService.getDoodle(id).subscribe(response => this.result = response);
                              }
                        }
                )
    }
  

  private getList(tags) : void {
      this.initIHM();
      this.soadleService.getList(tags)
      .subscribe(response => this.resultList = response , e => this.resultList = null);     
   }
  
  private importDoodle(id): void {
      this.initIHM();
      this.doodleService.importDoodle(id)
      .subscribe(response => this.result = response , e => this.result = null);
    }
  
  
  private create(indicateur) : void {
      this.initIHM();
      this.indCreate=indicateur;
  }
  
  private modificationMeeting(id,indicateur) : void {
    this.indModif=indicateur;
  }
  
  private annulModif() : void {
      this.indModif=null;
  }
  
  
  private crateEvent(title,description, date, name, address, tags) : void {
      if(this.indCreate == 1)
      {
          this.soadleService.crateEvent(title,description, date, name, address, tags)
          .subscribe(response => {this.initIHM(); this.result = response;} , e => this.result = null);                 
      } else
      {
          this.doodleService.crateEvent(title,description, date, name, address, tags)
          .subscribe(response => {this.initIHM(); this.result = response;} , e => this.result = null); 
      }
             
  }
   
 
  private saveMeeting(id, tags) : void
  {
      this.soadleService.saveMeeting(id, tags)
      .subscribe(response => this.getMeeting(id), e => this.handleError(e), () => this.getMeeting(id));      
  }
  
  
  private delete(id, origine): void {
      if(confirm("Êtes-vous sûr de vouloir supprimer le meeting : "+id)) {
          if(origine == 'D')
          {
              this.doodleService.delete(id)
              .subscribe(response => this.getList('') , e => this.result = null);
          } else
          {
              this.soadleService.delete(id)
              .subscribe(response => this.getList('') , e => this.result = null);
          }
      }
    }
    
  
  private participe(pParticipation , preference , id, origine, name, email, picture) : void {
      
      if(origine == 'S')
      {
          this.soadleService.participe(pParticipation, preference, id, name, email, picture)
          .subscribe(response => this.getSoadle(id) , e => this.handleError(e));  
      }
      
      if(origine == 'D')
      {
          this.doodleService.participe(pParticipation, preference, id, name, email, picture)
          .subscribe(response => this.getDoodle(id)  , e => this.handleError(e));  
      }
            
  }
  
  
  
    handleError(error : Response){
      console.log(error);
      return Observable.throw(error);
    }    

}
