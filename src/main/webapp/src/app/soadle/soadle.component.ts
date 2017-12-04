import { Component, OnInit, Input } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { SoadleService } from './service/soadle.service';
import { DoodleService } from './service/doodle.service';

/**
 * composant  principal soadle  
 */
@Component( {
    selector: 'app-soadle',
    templateUrl: './soadle.component.html',
    styleUrls: ['../app.component.css', '../bootstrap.min.css']
} )
export class SoadleComponent implements OnInit {

    /**
     *  user connecté  
     */    
    @Input( "resulUser" )
    resulUser = null;

    /**
     *  détail d'un évènement  
     */   
    result = null;
    
    /**
     *  list des évènements   
     */  
    resultList = null;
    
   /**
    *  indicateur affichage detail de creation 
    */  
    indCreate = null;
    
    /**
     *  indicateur affichage detail de modification 
     */  
    indModif = null;

    /**
     * Constructeur 
     * @param : service soadle 
     *          service doodle 
     */
    constructor( private soadleService: SoadleService, private doodleService: DoodleService ) {
    }

    /**
     * Initialisation :  recuperation de la liste si l'utilisateur est connecté 
     */
    ngOnInit() {
        if ( this.resulUser != null ) {
            this.getList( '' );
        }
    }

    /**
     * Initialisation :  mise à null des indicateur  
     */
    private initIHM(): void {
        this.result = null;
        this.resultList = null;
        this.indCreate = null;
        this.indModif = null;
    }

    /**
     * recuperation d'un soadle a partir de son Id
     * @param id : identifiant soadle
     */
    private getSoadle( id ): void {
        this.initIHM();
        this.soadleService.getSoadle( id )
            .subscribe( response => this.result = response, e => this.result = null );
    }

    /**
     * recuperation d'un doodle a partir de son Id
     * @param id : identifiant soadle
     */
    private getDoodle( id ): void {
        this.initIHM();
        this.doodleService.getDoodle( id )
            .subscribe( response => this.result = response, e => this.result = null );
    }

    /**
     * recuperation d'un meetnig soadle ou doodle a partir de son Id
     * @param id : identifiant soadle ou doodle
     */
    private getMeeting( id ): void {
        this.initIHM();
        this.soadleService.getSoadle( id )
            .subscribe( response => this.result = response,
            e => this.doodleService.getDoodle( id ).subscribe( response => this.result = response ),
            () => {
                if ( this.result == null || this.result.origine == 'D' ) {
                    this.doodleService.getDoodle( id ).subscribe( response => this.result = response );
                }
            }
            )
    }

    /**
     * recuperation de la liste des évènements avec un filtre sur le tag
     * @param tags : tags (filtre)
     */
    private getList( tags ): void {
        this.initIHM();
        this.soadleService.getList( tags )
            .subscribe( response => this.resultList = response, e => this.resultList = null );
    }

    /*
     * importation d'un doodle dans la base Soadle a partir de son Id
     * @param id : identifiant doodle
     */
    private importDoodle( id ): void {
        this.initIHM();
        this.doodleService.importDoodle( id )
            .subscribe( response => this.result = response, e => this.result = null );
    }

    /**
     * Affichage de bloc de cretaion d'un évènement 
     * @param indicateur : type d'évènement : 1 = soadle , 2 = doodle
     */
    private create( indicateur ): void {
        this.initIHM();
        this.indCreate = indicateur;
    }

    /**
     * Traitement des erreurs : affichage dans le console et génération d'exception 
     * @param error : erreur de traitement 
     * @return : exception 
     */
    handleError( error: Response ) {
        console.log( error );
        return Observable.throw( error );
    }

}
