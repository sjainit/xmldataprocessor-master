import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
    selector: 'app-search',
    templateUrl: './search.component.html',
    styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
    dataa: String;
    show: boolean = false;

    constructor(private http: HttpClient) { }

    ngOnInit() {
    }

    back() {
        this.show = false;
    }
    search() {

        var fname = document.getElementById('searchTd').innerText;
        console.log(fname);
        this.http.post('http://localhost:8009/getFile', fname).subscribe(
            (data: any[]) => {
                this.dataa = JSON.stringify(data);
                console.log(JSON.stringify(data));
                this.show = true;


            }

        )
    }

}