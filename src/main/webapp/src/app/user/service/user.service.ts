import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map'

@Injectable()
export class UserService {
  
    private baseUrl            = "";      
    private userUrl            = this.baseUrl + "/user/v1/user";
   

    constructor(private http: Http){
    }


    public getUser() {
      return   this.http.get(this.userUrl)
        .map((response : Response) =>  response.json())
        .catch(this.handleError);
      }
   
    
      handleError(error : Response){
        console.log(error);
        return Observable.throw(error);
      }
    
 }