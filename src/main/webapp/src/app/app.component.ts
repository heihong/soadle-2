import { Component, OnInit } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map'

/**
 * Service de gestion user   
 */
import { UserService } from "./user/service/user.service";

/**
 * Composant de gestion du connexion  
 */
@Component( {
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css', './bootstrap.min.css']
} )
export class AppComponent implements OnInit {

    public resulUser = null;

    private baseUrl = "";

    private logoutUrl = this.baseUrl + "/logout";
    private loginUrl = this.baseUrl + "/google/login";


    /**
     * Constructeur 
     * @param : Htpp, service user
     */
    constructor( private http: Http, private userService: UserService ) {
    }

    /*
     * Initialisation : récupération d'user 
     * 
     */
    ngOnInit() {
        this.userService.getUser()
            .subscribe( response => this.resulUser = response, e => e );
    }

    /*
     * Traitement des erreurs : affichage dans le console et génération d'exception 
     * @param error : erreur de traitement 
     * @return : exception 
     */
    handleError( error: Response ) {
        console.log( error );
        return Observable.throw( error );
    }

    /*
     * Déconnexion de l'appli 
     * 
     */
    private logout(): void {
        this.http.get( this.logoutUrl )
            .subscribe( response => this.resulUser = null );
    }

}
