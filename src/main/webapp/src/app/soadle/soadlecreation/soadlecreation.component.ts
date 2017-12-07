import { Component, Input, Output, EventEmitter, ElementRef, ViewChild } from '@angular/core';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { SoadleService } from '../service/soadle.service';
import { DoodleService } from '../service/doodle.service';


/**
 * creation d'un soadle/doodle 
 */
@Component( {
    selector: 'app-soadlecreation',
    templateUrl: './soadlecreation.component.html',
    styleUrls: ['../../app.component.css', '../../bootstrap.min.css']
} )
export class SoadleCreationComponent {

    
    @ViewChild("idAdress")
    public idAdressMap: ElementRef;
    
    @Input( "indCreate" )
    indCreate = null;

    @Input( "result" )
    result = null;
   

    @Output() eventgetMeeting = new EventEmitter();
    @Output() eventList = new EventEmitter();

    /**
     * Constructeur 
     * @param : service soadle 
     *          service doodle 
     */
    constructor( private soadleService: SoadleService, private doodleService: DoodleService ) {
    }

    /**
     * Création d'un évènement 
     * @param title       : titre
     *        description : description 
     *        date        : date de l'évènement
     *        name        : nom de l'évènement
     *        adress      : adress de lieu d'évènement
     *        tags        : tags de l'évenement 
     */
    private crateEvent( title, description, date, name, address, tags,url1,url2 ): void {
        if ( this.indCreate == 1 ) {
            this.soadleService.crateEvent( title, description, date, name, address, tags,url1,url2 )
                .subscribe( response => this.eventgetMeeting.emit( response.id ), e => this.handleError( e ) );
        } else {
            this.doodleService.crateEvent( title, description, date, name, address, tags,url1,url2)
                .subscribe( response => this.eventgetMeeting.emit( response.id ), e => this.handleError( e ) );
        }

    }

    /**
     * recuperation de la liste des évènements sans filtre
     */
    private getList(): void {
        this.eventList.emit( '' );
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
