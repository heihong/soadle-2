import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map'
import { Constants } from './../constants/constants';

/**
 * Service de gestion soadle   
 */
@Injectable()
export class SoadleService {

    private baseUrl = "";

    private soadleUrl = Constants.CONST_BASEURL + "/api/v1/soadle/";
    private filterUrl = Constants.CONST_BASEURL + "/api/v1/soadle/filter";
    private participeSoadleUrl = Constants.CONST_BASEURL + "/api/v1/soadle/participe/";

    /**
     * Constructeur 
     * @param : Http class   
     */
    constructor( private http: Http ) {
    }

    /**
     * recuperation d'un soadle a partir de son Id
     * @param id : identifiant soadle
     * @return : Object SoadleMettenig d'origine soadle
     */
    public getSoadle( id ) {
        return this.http.get( this.soadleUrl + id ).map( response => response.json() );
    }

    
    
    public getList()
    {
        return this.http.get( this.soadleUrl )
        .map( response => response.json() );
    }
    
    /**
     * recuperation de la liste des évènements avec un filtre sur le tag
     * @param tags : tags (filtre)
     * @return : List SoadleMettenig d'origine soadle et doodle filtré par tags
     */
    public getListFilter( tags , dateDebut, dateFin) {
            return this.http.get( this.filterUrl + '?tag='+tags+'&dateDebut='+dateDebut+'&dateFin='+dateFin)
                .map( response => response.json() );
       
    }

    /**
     * Création d'un évènement soadle
     * @param title       : titre
     *        description : description 
     *        date        : date de l'évènement
     *        name        : nom de l'évènement
     *        address     : address de lieu d'évènement
     *        tags        : tags de l'évènement 
     * @return : Object SoadleMettenig d'origine soadle
     */
    public crateEvent( title, description, date, name, address, tags, pictures) {
        return this.http
            .post( this.soadleUrl, { title: title, description: description, options: [{ date: date }], location: { name: name, address: address }, tags: tags,pictures:pictures} )
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
     *        url1        : url image 1
     *        url2        : url image 2  
     * @return : Object SoadleMettenig d'origine doodle
     */
    public saveMeeting( id, title, description, date, name, address, tags, pictures) {
        return this.http.put( this.soadleUrl + id, {id:id, title: title, description: description, options: [{ date: date }], location: { name: name, address: address }, tags: tags, pictures:pictures} );
    }

    /**
     * Suppression d'un évènement dans la base soadle
     * @param id          : identifiant soadle
     * @return : Observateur sans resultat
     */
    public delete( id ) {
        return this.http.delete( this.soadleUrl + id );
    }

    /**
     * Participer ou non à un soadle
     * @param pParticipation : objet participant s'il existe 
     *        preference     : reponse : true ou false
     *        id             : identifiant soadle
     *        name           : nom de participant
     *        email          : email de participant
     *        picture        : lien vers l'avatar de participant 
     * @return : participant
     */
    public participe( pParticipation, preference, id, name, email, picture ) {
        return this.http
            .post( this.participeSoadleUrl + id, { name: name, email: email, preference: preference, smallAvatarUrl: picture } )
            .map( response => response );
    }

}