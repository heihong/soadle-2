import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map'
import { Constants } from './../constants/constants';

/**
 * Service de gestion doodle   
 */
@Injectable()
export class DoodleService {

    private baseUrl = "";

    private doodleUrl = Constants.CONST_BASEURL + "/api/v1/doodle/";
    private importUrl = Constants.CONST_BASEURL + "/api/v1/doodle/import/";
    private participeDoodleUrl = Constants.CONST_BASEURL + "/api/v1/doodle/participe/";

    /**
     * Constructeur 
     * @param : Http class   
     */
    constructor( private http: Http ) {
    }

    /**
     * recuperation d'un doodle a partir de son Id
     * @param id : identifiant doodle
     * @return : Object SoadleMettenig d'origine doodle
     */
    public getDoodle( id ) {
        return this.http.get( this.doodleUrl + id )
            .map( response => response.json() );
    }

    /**
     * import d'un doodle dans la base soadle a partir de son Id
     * @param id : identifiant doodle
     * @return : Object SoadleMettenig d'origine doodle
     */
    public importDoodle( id ) {
        return this.http.get( this.importUrl + id )
            .map( response => response.json() );
    }

    /**
     * Création d'un évènement doodle
     * @param title       : titre
     *        description : description 
     *        date        : date de l'évènement
     *        name        : nom de l'évènement
     *        adress      : adress de lieu d'évènement
     *        tags        : tags de l'éveènement 
     * @return : Object SoadleMettenig d'origine doodle
     */
    public crateEvent( title, description, date, name, address, tags ) {

        return this.http
            .post( this.doodleUrl, { title: title, description: description, options: [{ date: date }], location: { name: name, address: address }, tags: tags } )
            .map( response => response.json() );
    }
    

    /**
     * Modification d'un évènement 
     * @param title       : titre
     *        description : description 
     *        date        : date de l'évènement
     *        name        : nom de l'évènement
     *        address     : address de lieu d'évènement
     *        tags        : tags de l'évènement 
     * @return : Object SoadleMettenig d'origine doodle
     */
    public saveMeeting( id, title, description, date, name, address, tags ) {
        return this.http.put( this.doodleUrl + id, {id:id, title: title, description: description, options: [{ date: date }], location: { name: name, address: address }, tags: tags } );
    }

    /**
     * Suppression d'un évènement dans la base soadle
     * @param id          : identifiant doodle
     * @return : Observateur sans resultat
     */
    public delete( id ) {
        return this.http.delete( this.doodleUrl + id );
    }

    /**
     * Participer ou non à un doodle
     * @param pParticipation : objet participant s'il existe 
     *        preference     : reponse : true ou false
     *        id             : identifiant doodle
     *        name           : nom de participant
     *        email          : email de participant
     *        picture        : lien vers l'avatar de participant 
     * @return : participant
     */
    public participe( pParticipation, preference, id, name, email, picture ) {

        var participation = pParticipation;
        if ( participation == null ) {
            participation = { name: name, email: email, preference: preference, smallAvatarUrl: picture };
        } else {
            participation.preference = preference;
            participation.email = email;
            participation.smallAvatarUrl = picture;
        }
        return this.http
            .post( this.participeDoodleUrl + id, participation )
            .map( response => response );

    }

}
