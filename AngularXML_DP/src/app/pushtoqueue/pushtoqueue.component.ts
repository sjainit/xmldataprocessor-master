import { Component, OnInit } from '@angular/core';
import { FileUploader, FileSelectDirective } from 'ng2-file-upload/ng2-file-upload';
import { templateJitUrl } from '@angular/compiler';
import { Template } from '@angular/compiler/src/render3/r3_ast';
import { DataService } from '../data.service';
const URL = 'http://localhost:8009/pushToQueue';
@Component({
  selector: 'app-pushtoqueue',
  templateUrl: './pushtoqueue.component.html',
  styleUrls: ['./pushtoqueue.component.css']
})
export class PushtoqueueComponent implements OnInit {
  response: any;
  show: boolean = false;
  show1: boolean = false;
  constructor(private dataService: DataService) { }

  public uploader: FileUploader = new FileUploader({ url: URL, itemAlias: 'file' });


  ngOnInit() {
    
    this.uploader.onAfterAddingFile = (file) => { file.withCredentials = false; };
    this.uploader.onCompleteItem = (item: any, response: any, status: any, headers: any) => {
      console.log(status)
     if (status==200){
       this.show=true;
       this.show1=false;
     }
     else {
      this.show1=true;
      this.show=false;
     }
      
      this.passData(response);
    
    }
  }
  passData(responseXml) {
    this.dataService.sendDataToOtherComponent(responseXml);
  }

}







  
