import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { SoadleService } from '../service/soadle.service';
import { DoodleService } from '../service/doodle.service';

import { Constants } from './../constants/constants';

/**
 * composant  detail d'un soadle/doodle 
 */
@Component( {
    selector: 'app-soadledetail',
    templateUrl: './soadledetail.component.html',
    styleUrls: ['../../app.component.css', '../../bootstrap.min.css']
} )
export class SoadleDetailComponent {

    @Input( "result" )
    result = null;

    @Input( "resulUser" )
    resulUser = null;

    @Input( "indModif" )
    indModif = null;

    @Output() eventgetSoadle = new EventEmitter();
    @Output() eventgetDoodle = new EventEmitter();

    googleMapUrl = Constants.CONST_GOOGLE_MAP_URL;
    pollsDoodleUrl = Constants.CONST_POLLS_DOODLE_URL;

    /**
     * Constructeur 
     * @param : service soadle 
     *          service doodle 
     */
    constructor( private soadleService: SoadleService, private doodleService: DoodleService ) {
    }

    /**
     * Affichage ecran de modification d'un évènement 
     * @param id          : id évènement 
     *        indicateur  : indicateur d'évènement : 1 = spadle , 2 = doodle
     */
    private modificationMeeting( id, indicateur ): void {
        this.indModif = indicateur;
    }

    /**
     * Annulation modification  
     */
    private annulModif(): void {
        this.indModif = null;
    }

    /**
     * Enregestrement des tags d'un évènement 
     * @param id          : identifiant soadle ou doodle
     *        tags        : tags de l'éveènement 
     *        Origine     : origine de l'évènement : S = soadle , D = doodle
     * @return : Object SoadleMettenig d'origine doodle
     */
    private saveMeeting( id, title, description, date, name, address, tags, origine ): void {
        if ( origine == 'S' )
         {
            this.soadleService.saveMeeting( id, title, description, date, name, address, tags )
                .subscribe( response => this.eventgetSoadle.emit( id ), e => this.handleError( e ) );
         } else
         {        
             this.doodleService.saveMeeting( id, title, description, date, name, address, tags )
               .subscribe( response => this.eventgetDoodle.emit( id ), e => this.handleError( e ) );
         }            
    }

    /**
     * Participer ou non à un soadle/doodle
     * @param pParticipation : objet participant s'il existe 
     *        preference     : reponse : true ou false
     *        id             : identifiant soadle/doodle
     *        origine        : origine de l'évènement : S = soadle , D = doodle
     *        name           : nom de participant
     *        email          : email de participant
     *        picture        : lien vers l'avatar de participant 
     * @return : participant
     */
    private participe( pParticipation, preference, id, origine, name, email, picture ): void {

        if ( origine == 'S' ) {
            this.soadleService.participe( pParticipation, preference, id, name, email, picture )
                .subscribe( response => this.eventgetSoadle.emit( id ), e => this.handleError( e ) );
        }

        if ( origine == 'D' ) {
            this.doodleService.participe( pParticipation, preference, id, name, email, picture )
                .subscribe( response => this.eventgetDoodle.emit( id ), e => this.handleError( e ) );
        }

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
