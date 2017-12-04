import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map'

@Injectable()
export class SoadleService {
  
    private baseUrl            = "";  
    
    private soadleUrl          = this.baseUrl + "/api/v1/soadle/";
    private tagUrl             = this.baseUrl + "/api/v1/soadle/tags/";
    private participeSoadleUrl = this.baseUrl + "/api/v1/soadle/participe/";

    constructor(private http: Http){
    }

    public getSoadle(id) {
        return this.http.get(this.soadleUrl+id).map(response => response.json());
    }
    
  
    public getList(tags) {
        if(tags)
        {
            return this.http.get(this.tagUrl+tags)
            .map(response => response.json());
        } else
        {
            return this.http.get(this.soadleUrl)
            .map(response => response.json());
        }
     }
    
    
    
    public crateEvent(title,description, date, name, address, tags)  {
         return this.http
                .post(this.soadleUrl,{title:title,description:description,options:[{date:date}],location:{name:name,address:address},tags:tags})
                .map(response => response.json());
    }
    
    public saveMeeting(id, tags) 
    {
        return this.http.get(this.tagUrl+id+`/`+tags);      
    }
    
    
    public delete(id) {       
        return this.http.delete(this.soadleUrl+id);       
      }
      
    
    public participe(pParticipation , preference , id, name, email, picture) {
        return this.http
            .post(this.participeSoadleUrl+id,{name:name,email:email,preference:preference,smallAvatarUrl:picture})
            .map(response => response); 
    }
    
  

}