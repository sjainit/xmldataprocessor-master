import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DataService } from '../data.service';
 
@Component({
 selector: 'app-home',
 templateUrl: './home.component.html',
 styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
 data2: any;
 dataa: any;
 keys: any[];
 values: any[];
 recieveddata: string;
 keys1: any[];
 values1: any[];
 data: String;
 show: boolean = false;
 constructor(private http: HttpClient, private dataService: DataService) { }
 
 ngOnInit() {
 this.ListFiles();
 }
 
 ListFiles() {
 this.http.get("http://localhost:8009/list").subscribe(
 (data: any[]) => {
 console.log(data);
 this.dataa = JSON.stringify(data);
 this.dataa = JSON.parse(this.dataa);
 this.keys1 = Object.keys(this.dataa)
 this.values1 = Object.values(this.dataa)
 
 }
 )
 }
 
 Upload(i) {
 var path = document.getElementById('filepath' + i).innerText
 this.http.post('http://localhost:8009/filepath', path).subscribe(
 (data: any[]) => {
 console.log(data);
 
 var newData = JSON.stringify(data);
 this.passData(newData);
 console.log(this.data);
 }
 
 )
 
 }
 View(i) {
 var filepath = document.getElementById('filepath' + i).innerText;
 this.http.post('http://localhost:8009/view', filepath, { responseType: 'text' }).subscribe(
 (data: String) => {
 console.log(data);
 this.data = JSON.stringify(data);
 this.passData(this.data);
 
 }
 )
 }
 
 Delete(i) {
 
 var filepath = document.getElementById('filepath' + i).innerText;
 var filename=document.getElementById('name'+i).innerText;
 if (window.confirm("Are you sure you want to delete "+filename+"?")) {
 this.http.post('http://localhost:8009/deletehome', filepath).subscribe(
 (data: any[]) => {
 console.log(data);
 })
  window.location.reload();
}
 }


 
 passData(responseXml) {
 console.log("-----in passData Of hOme------");
 this.dataService.sendDataToOtherComponent(responseXml);
 
 }
 
}