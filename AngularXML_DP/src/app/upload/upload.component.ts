import { Component, OnInit } from '@angular/core';
import { FileUploader } from 'ng2-file-upload/ng2-file-upload';
import { DataService } from '../data.service';
const URL = 'http://localhost:8009/getxml';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {

  response: any;
  constructor(private dataService: DataService) { }

  public uploader: FileUploader = new FileUploader({ url: URL, itemAlias: 'file' });

  ngOnInit() {
    
    this.uploader.onAfterAddingFile = (file) => { file.withCredentials = false; };
    this.uploader.onCompleteItem = (item: any, response: any, status: any, headers: any) => {
      console.log(status)        
      this.passData(response);
    
    }
  }
  passData(responseXml) {
    this.dataService.sendDataToOtherComponent(responseXml);
  }
}