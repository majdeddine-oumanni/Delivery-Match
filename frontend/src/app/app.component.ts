import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
  http = inject(HttpClient);
  constructor(){
    this.http.get("http://localhost:8080/api/v1/auth/register").subscribe((res)=>{
      console.log(res);
    })
  }
}
