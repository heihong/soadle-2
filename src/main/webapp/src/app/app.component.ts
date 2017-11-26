import { Component , OnInit } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css', './bootstrap.min.css']
})
export class AppComponent implements OnInit{
  title = 'app works!';
  result = {};
  user ={};
  resulUser = null;
  
  ngOnInit() {
      this.getUser()
      .subscribe(response => this.resulUser = response);
  }


  constructor(private http: Http){
  }

  private getDoodle(id): void {
    this.http.get(`/api/v1/doodle/${id}`)
    .subscribe(response => this.result = response.json());
  }

  private getUser() {
    return   this.http.get(`/user/v1/user`)
      .map((response : Response) => this.result = response.json())
      .catch(this.handleError);

    }

    handleError(error : Response){
      console.log(error);
      return Observable.throw(error);
    }

     private logout(): void {
      this.http.get(`/logout`)
      .subscribe(response => this.resulUser = null);
    }

}
