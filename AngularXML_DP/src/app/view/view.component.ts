import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DataService } from '../data.service';
@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit {
  data: String;

  constructor(private dataService: DataService, private http: HttpClient) {
    this.dataService.shareDataSubject.subscribe(receiveddata => {
      this.data = receiveddata;
      console.log(this.data);
    });
  }

  ngOnInit() {
  }


}
