import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map'

/**
 * Service de gestion user   
 */
@Injectable()
export class UserService {
  
    private baseUrl            = "";      
    private userUrl            = this.baseUrl + "/user/v1/user";

    /**
     * Constructeur 
     * @param : Http class   
     */
    constructor(private http: Http){
    }

    /**
     * Recuperation d'utilisateur connecté 
     * @return : utilisateur  
     */
    public getUser() {
      return   this.http.get(this.userUrl)
        .map((response : Response) =>  response.json())
        .catch(this.handleError);
      }
    
    /**
     * Traitement des erreurs : affichage dans le console et génération d'exception 
     * @param error : erreur de traitement 
     * @return : exception 
     */
      handleError(error : Response){
        console.log(error);
        return Observable.throw(error);
      }    
 }