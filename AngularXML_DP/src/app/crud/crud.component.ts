import { Component, OnInit, ViewChild, Injectable } from '@angular/core';
import { DataService } from '../data.service';
import { HttpClient } from '@angular/common/http';
import { ValueTransformer } from '@angular/compiler/src/util';
@Injectable({
  providedIn: 'root',
})
@Component({
  selector: 'app-crud',
  templateUrl: './crud.component.html',
  styleUrls: ['./crud.component.css']
})
export class CrudComponent implements OnInit {
  show: boolean = false;
  receiveddata: any[];
  keys: any;
  values: any;
  temp = 1;
  finalvalues: any;
  finalkeys: any;
  Objkeys: String[] = [];
  allvalues: String[] = [];
  count: any = 0;
  Obj1keys: any[];
  mainxpath: string;
  data2: string;

  constructor(private dataService: DataService, private http: HttpClient) {
    this.dataService.shareDataSubject.subscribe(receiveddata => {
      this.receiveddata = receiveddata;
      this.xmlData(receiveddata);
    });
  }

  xmlData(receiveddata) {
    console.log(receiveddata);
    this.receiveddata = JSON.parse(receiveddata);
    this.keys = Object.keys(this.receiveddata);
    this.values = Object.values(this.receiveddata);
    console.log(typeof (this.values));

  }

  ngOnInit() {
  }

  Save(i, temp) {
    console.log(this.temp);
    if (!temp) {
      var newxpath
      var j = i - 1
      var newTagName = document.getElementById('name' + (i)).innerText;
      var newvalue = document.getElementById('value' + (i)).innerText;
      while (!newxpath) {
        newxpath = document.getElementById('xpath' + (j)).innerText;
        j--
      }
      this.temp = 1
      console.log(newTagName);
      console.log(newvalue);
      console.log(newxpath);
      this.http.post('http://localhost:8009/saveChild', [newxpath, newTagName, newvalue]).subscribe(
        (data: any[]) => {
          console.log(data);
        }
      )
    }
    else if (this.temp == 2) {
      this.allvalues.splice(0, 0, this.mainxpath)
      for (let i = 0; i < this.count; i++) {
        var newvalue = document.getElementById('values' + i).innerText;
        console.log(newvalue);
        this.allvalues.push(newvalue)

        console.log(this.allvalues);
      }
      this.temp = 1;
      this.http.post('http://localhost:8009/saveObject', this.allvalues).subscribe(
        (data: any[]) => {
          console.log(data);
        }
      )
      this.allvalues = []
      this.show = false;
    }
    else {
      var newvalue = document.getElementById('value' + i).innerText;
      var xpath = document.getElementById('xpath' + i).innerText;
      console.log(newvalue);
      //this.values[i].tagValue = newvalue;
      this.http.post('http://localhost:8009/update', [xpath, newvalue]).subscribe(
        (data: any[]) => {
          console.log(data);
        }
      )
    }
  }

  Delete(i) {
    var j = 0
    var xpath = document.getElementById('xpath' + i).innerText;
    console.log(xpath)
    if (window.confirm("Are you sure you want to delete?")) {
      console.log(this.keys.length)
      this.keys.splice(i, 1)
      this.values.splice(i, 1)
      console.log(i)

      for (j = (i); j < this.keys.length; j++) {

        var newxpath = document.getElementById('xpath' + (i + 1)).innerText
        i = i + 1
        console.log(newxpath)
        if (newxpath.includes(xpath)) {
          this.keys.splice(j, 1)
          this.values.splice(j, 1)
          j = j - 1
          console.log(j)
        }

      }

      this.http.post('http://localhost:8009/delete', xpath).subscribe(
        (data: any[]) => {
          console.log(data);
        }
      )
    }
  }

  Add(i) {
    if (!i) {
      alert("Cannot add the root Element of the Document!");
    }
    else {
      //this.temp = 0
      var k = 0
      var j
      this.count = 0
      
      var xpath = document.getElementById('xpath' + i).innerText
      this.mainxpath = xpath;
      var length = this.keys.length;
      var Objkeys = [];
      for (j = i + 1; j < length; j++) {
        var newxpath = document.getElementById('xpath' + j).innerText
        if (newxpath.includes(xpath)) {
          var tagName = document.getElementById('name' + (j)).innerText
          var tagValue = document.getElementById('value' + (j)).innerText
          if (tagValue) {
            this.count++;
            Objkeys.splice(k++, 0, tagName)

          }
        }
        if (Objkeys.length != 0) {
          this.Obj1keys = Objkeys
          this.show = true;
        }
      }
      j = 0

      if (Objkeys.length == 0) {
        this.temp = 0
        this.keys.splice(i + 1, 0, " ");
        this.values.splice(i + 1, 0, " ");
      }
      else this.temp = 2

      Objkeys = []
    }
  }

  SaveAs() {
    if (this.keys.length == 0) {
      alert("Empty File! Please Upload new file")
    }

  }
}
