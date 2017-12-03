import { Component , OnInit } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css', './bootstrap.min.css']
})
export class AppComponent implements OnInit{
  title = 'app works!';
  result = null;
  resultList = null;
  user ={};
  resulUser = null;
  indCreate = null;
  indModif = null;
  
  googleMapUrl     = "https://www.google.fr/maps/place/";
  pollsDoodleUrl   = "https://doodle.com/poll/";
    
  private baseUrl            = "";  
  
  private logoutUrl          = this.baseUrl + "/logout";
  private loginUrl           = this.baseUrl + "/google/login";
  
  private userUrl            = this.baseUrl + "/user/v1/user";
  private soadleUrl          = this.baseUrl + "/api/v1/soadle/";
  private doodleUrl          = this.baseUrl + "/api/v1/doodle/";
  private tagUrl             = this.baseUrl + "/api/v1/soadle/tags/";
  private importUrl          = this.baseUrl + "/api/v1/doodle/import/";
  private participeSoadleUrl = this.baseUrl + "/api/v1/soadle/participe/";
  private participeDoodleUrl = this.baseUrl + "/api/v1/doodle/participe/";
  

  constructor(private http: Http){
  }

  
  ngOnInit() {
     this.initUser();
  }

  private initUser() : void {
      this.getUser()
      .subscribe(response => this.resulUser = response, e => e,() => this.getList(''));
  }
  private getUser() {
    return   this.http.get(this.userUrl)
      .map((response : Response) => this.resulUser = response.json())
      .catch(this.handleError);

    }
  
  private initIHM(): void {
      this.result = null;
      this.resultList = null;
      this.indCreate = null;
      this.indModif = null;
  }
  

  private getSoadle(id): void {
    this.initIHM();
    this.http.get(this.soadleUrl+id)
    .subscribe(response => this.result = response.json() , e => this.result = null);
  }
  
  
  
  private getDoodle(id): void {
    this.initIHM();
    this.http.get(this.doodleUrl+id)
    .subscribe(response => this.result = response.json() , e => this.result = null);
  }
  
  private getMeeting(id): void {
      this.initIHM();
      this.http.get(this.soadleUrl+id)      
      .subscribe(response => this.result = response.json() , 
                  e  => this.getDoodle(id),
                  () => {     
                              if(this.result == null || this.result.origine == 'D')
                              {
                                    this.getDoodle(id);
                              }
                        }
                )
    }
  

  private getList(tags) : void {
      this.initIHM();
      if(tags)
      {
          this.http.get(this.tagUrl+tags)
          .subscribe(response => this.resultList = response.json() , e => this.resultList = null);
      } else
      {
          this.http.get(this.soadleUrl)
          .subscribe(response => this.resultList = response.json() , e => this.resultList = null);
      }
   }
  
  private importDoodle(id): void {
      this.initIHM();
      this.http.get(this.importUrl+id)
      .subscribe(response => this.result = response.json() , e => this.result = null);
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
          this.crateEventSoadle(title,description, date, name, address, tags);
      } else
      {
          this.crateEventDoodle(title,description, date, name, address, tags);
      }
             
  }
   
  
  private crateEventDoodle(title,description, date, name, address, tags) : void {
            
      this.http
          .post(this.doodleUrl,{title:title,description:description,options:[{date:date}],location:{name:name,address:address},tags:tags})
          .subscribe(response => {this.initIHM(); this.result = response.json();} , e => this.result = null);       
  }
   
  
  private crateEventSoadle(title,description, date, name, address, tags) : void {
      this.http
          .post(this.soadleUrl,{title:title,description:description,options:[{date:date}],location:{name:name,address:address},tags:tags})
          .subscribe(response => {this.initIHM(); this.result = response.json();} , e => this.handleError(e));       
  }
  
  private saveMeeting(id, tags) : void
  {
      this.http.get(this.tagUrl+id+`/`+tags)
      .subscribe(response => response, e => this.handleError(e), () => this.getMeeting(id));      
  }
  
  
  private delete(id, origine): void {
      if(confirm("Êtes-vous sûr de vouloir supprimer le meeting : "+id)) {
          if(origine == 'D')
          {
              this.http.delete(this.doodleUrl+id)
              .subscribe(response => this.getList('') , e => this.result = null);
          } else
          {
              this.http.delete(this.soadleUrl+id)
              .subscribe(response => this.getList('') , e => this.result = null);
          }
      }
    }
    
  
  private participe(pParticipation , preference , id, origine, name, email, picture) : void {
      
      if(origine == 'S')
      {
          this.http
          .post(this.participeSoadleUrl+id,{name:name,email:email,preference:preference,smallAvatarUrl:picture})
          .subscribe(response => this.getSoadle(id) , e => this.handleError(e));  
      }
      
      if(origine == 'D')
      {
          var participation = pParticipation;
          if(participation == null)
          {
              participation = {name:name,email:email,preference:preference,smallAvatarUrl:picture};
          } else
          {
              participation.preference = preference;
              participation.email=email;
              participation.smallAvatarUrl=picture;
          }
          this.http
          .post(this.participeDoodleUrl+id, participation)
          .subscribe(response => this.getDoodle(id) , e => this.handleError(e));  
      }
            
  }
  
  
  
    handleError(error : Response){
      console.log(error);
      return Observable.throw(error);
    }
    
     private logout(): void {
      this.http.get(this.logoutUrl)
      .subscribe(response => this.resulUser = null);
    }

}
