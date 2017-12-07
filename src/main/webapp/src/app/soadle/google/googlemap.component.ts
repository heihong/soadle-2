import {Component, ElementRef, NgZone, OnInit, ViewChild, Input } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Http, Response } from '@angular/http';
import { } from 'googlemaps';
import { MapsAPILoader } from '@agm/core';


/**
 * creation d'un soadle/doodle 
 */
@Component( {
    selector: 'app-map',
    styles: [`
      agm-map {
        height: 250px;
        whith: 400px;
      }
    `],
    template: `
      <div class="container" style="padding: 3px; margin-top: 3px; border: 2px solid #b8dc6f;">
        <agm-map [latitude]="latitude" [longitude]="longitude" [scrollwheel]="false" [zoom]="zoom">
          <agm-marker [latitude]="latitude" [longitude]="longitude"></agm-marker>
        </agm-map>
      </div>
    `
} )
export class GoogleMapComponent implements OnInit {

    public latitude: number;
    public longitude: number;

    public searchControl: FormControl;
    public zoom: number;

    @Input("idAdressMap")
    public searchElementRef: ElementRef;


    constructor(private mapsAPILoader: MapsAPILoader,private ngZone: NgZone, private http: Http) 
    {
        
    }

    ngOnInit() {
      //create search FormControl
      this.searchControl = new FormControl();

      this.initPosition();
    

      //load Places Autocomplete
      this.mapsAPILoader.load().then(() => {
        let autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement, {
          types: ["address"]
        });
        autocomplete.addListener("place_changed", () => {
          this.ngZone.run(() => {
            //get the place result
            let place: google.maps.places.PlaceResult = autocomplete.getPlace();

            //verify result
            if (place.geometry === undefined || place.geometry === null) {
              return;
            }

            //set latitude, longitude and zoom
            this.latitude = place.geometry.location.lat();
            this.longitude = place.geometry.location.lng();
            this.zoom = 12;
          });
        });
      });
    }

    

    
    private initPosition()
    {
        //set google maps defaults
        this.zoom = 4;
        this.latitude = 48.8318317;
        this.longitude = 2.3797488000000158;

        //set current position
        this.setCurrentPosition();
    }
   
    
    private setCurrentPosition() {
      if ("geolocation" in navigator) {
        navigator.geolocation.getCurrentPosition((position) => {
          this.latitude = position.coords.latitude;
          this.longitude = position.coords.longitude;
          this.zoom = 12;
        });
      }
    }
    
  }