import { Component } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app works!';
  result = {};

  constructor(private http: Http){
  }

  private sayHello(): void {
    this.http.get(`/api/v1/doodle/2bawsrkfwanfrhqp`).subscribe(response => this.result = response);
  }

}
