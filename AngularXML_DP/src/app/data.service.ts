import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import {Subject} from 'rxjs';
 
 
@Injectable()
export class DataService {
 
    shareDataSubject = new Subject<any>(); //Decalring new RxJs Subject 
     sendDataToOtherComponent(somedata){
      this.shareDataSubject.next(somedata);
  }
}
