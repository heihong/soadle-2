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
  
  ngOnInit() {
      this.getUser()
      .subscribe(response => this.resulUser = response);      
      this.getList();
  }


  constructor(private http: Http){
  }

  

  private getUser() {
    return   this.http.get(`/user/v1/user`)
      .map((response : Response) => this.resulUser = response.json())
      .catch(this.handleError);

    }
  
  private initIHM(): void {
      this.result = null;
      this.resultList = null;
      this.indCreate = null;
  }
  

  private getSoadle(id): void {
    this.initIHM();
    this.http.get(`/api/v1/soadle/${id}`)
    .subscribe(response => this.result = response.json() , e => this.result = null);
  }
  
  
  
  private getDoodle(id): void {
    this.initIHM();
    this.http.get(`/api/v1/doodle/${id}`)
    .subscribe(response => this.result = response.json() , e => this.result = null);
  }
  
  private getMeeting(id): void {
      this.initIHM();
      this.http.get(`/api/v1/soadle/${id}`)      
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
  

  private getList() : void {
      this.initIHM();
      this.http.get(`/api/v1/soadle/`)
      .subscribe(response => this.resultList = response.json() , e => this.resultList = null);
    }
  
  private importDoodle(id): void {
      this.initIHM();
      this.http.get(`/api/v1/doodle/import/${id}`)
      .subscribe(response => this.result = response.json() , e => this.result = null);
    }
  
  
  private create(indicateur) : void {
      this.initIHM();
      this.indCreate=indicateur;
  }
  
  private crateEventDoodle(title,description, date, name, address) : void {
            
      this.http
          .post(`/api/v1/doodle/`,{title:title,description:description,options:[{date:date}],location:{name:name,address:address}})
          .subscribe(response => {this.initIHM(); this.result = response.json();} , e => this.result = null);       
  }
 
  
  private crateEventSoadle(title,description, date, name, address) : void {
      this.http
          .post(`/api/v1/soadle/`,{title:title,description:description,options:[{date:date}],location:{name:name,address:address}})
          .subscribe(response => {this.initIHM(); this.result = response.json();} , e => this.handleError(e));       
  }
  
  
  private delete(id, origine): void {
      if(confirm("Êtes-vous sûr de vouloir supprimer le meeting : "+id)) {
          if(origine == 'D')
          {
              this.http.delete(`/api/v1/doodle/${id}`)
              .subscribe(response => this.getList() , e => this.result = null);
          } else
          {
              this.http.delete(`/api/v1/soadle/${id}`)
              .subscribe(response => this.getList() , e => this.result = null);
          }
      }
    }
    
  
    handleError(error : Response){
      console.log(error);
      return Observable.throw(error);
    }

     private logout(): void {
      this.http.get(`/logout`)
      .subscribe(response => this.resulUser = null);
    }

}
