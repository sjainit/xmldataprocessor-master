import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
 
@Component({
 selector: 'app-inputfilename',
 templateUrl: './inputfilename.component.html',
 styleUrls: ['./inputfilename.component.css']
})
export class InputfilenameComponent implements OnInit {
 
 constructor(private http: HttpClient) { }
 
 ngOnInit() {
 }
Save()
{
 
 var filename=document.getElementById('searchTd').innerText;
 this.http.post('http://localhost:8009/generate', filename).subscribe(
 (data: any[]) => {
 console.log(data);
 }
 )
}
}