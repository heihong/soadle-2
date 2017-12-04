import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { SoadleService } from '../service/soadle.service';
import { DoodleService } from '../service/doodle.service';
import { Constants } from './../constants/constants';

/**
 * composant  liste soadle/doodle 
 */
@Component( {
    selector: 'app-soadlelist',
    templateUrl: './soadlelist.component.html',
    styleUrls: ['../../app.component.css', '../../bootstrap.min.css']
} )
export class SoadleListComponent {

    @Input( "resultList" )
    resultList = null;

    @Output() eventgetSoadle = new EventEmitter();
    @Output() eventgetDoodle = new EventEmitter();
    @Output() eventList = new EventEmitter();


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
     * recuperation d'un soadle a partir de son Id, fait appel a la méthode mere
     * @param id : identifiant soadle
     */
    getSoadle( id ): void {
        this.eventgetSoadle.emit( id );
    }

    /**
     * recuperation d'un doodle a partir de son Id, fait appel a la méthode mere
     * @param id : identifiant doodle
     */
    private getDoodle( id ): void {
        this.eventgetDoodle.emit( id );
    }

    /**
     * Suppression d'un évènement dans la base soadle
     * @param id          : identifiant soadle
     *        origine     : D = doodle , S = soadle
     */
    private delete( id, origine ): void {
        if ( confirm( "Êtes-vous sûr de vouloir supprimer le meeting : " + id ) ) {
            if ( origine == 'D' ) {
                this.doodleService.delete( id )
                    .subscribe( response => this.eventList.emit( '' ) );
            } else {
                this.soadleService.delete( id )
                    .subscribe( response => this.eventList.emit( '' ) );
            }
        }
    }

}
