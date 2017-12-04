import { Component , OnInit } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map'

import {UserService} from "./user/service/user.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css', './bootstrap.min.css']
})
export class AppComponent implements OnInit{
  
  public resulUser = null;
   
  private baseUrl            = "";  
  
  private logoutUrl          = this.baseUrl + "/logout";
  private loginUrl           = this.baseUrl + "/google/login";
  

  constructor(private http: Http, private userService: UserService){
  }

  
  ngOnInit() {
      this.userService.getUser()
      .subscribe(response => this.resulUser = response, e => e);
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
