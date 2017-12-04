import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map'

@Injectable()
export class DoodleService {
      
    private baseUrl            = "";  
    
    private doodleUrl          = this.baseUrl + "/api/v1/doodle/";
    private importUrl          = this.baseUrl + "/api/v1/doodle/import/";
    private participeDoodleUrl = this.baseUrl + "/api/v1/doodle/participe/";
    

    constructor(private http: Http){
    }


    public getDoodle(id) {
      return this.http.get(this.doodleUrl+id)
                      .map(response => response.json());
    }
    
    
    public importDoodle(id) {
        return this.http.get(this.importUrl+id)
                   .map(response => response.json());
      }
    
   
    
    public crateEvent(title,description, date, name, address, tags) {
              
        return this.http
                   .post(this.doodleUrl,{title:title,description:description,options:[{date:date}],location:{name:name,address:address},tags:tags})
                   .map(response => response.json());       
    }
     
    
    
    public delete(id)  {
          return this.http.delete(this.doodleUrl+id);        
      }
      
    
    public participe(pParticipation , preference , id, name, email, picture)  {
 
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
            return this.http
                       .post(this.participeDoodleUrl+id, participation)
                       .map(response => response);  
              
    }    

}
