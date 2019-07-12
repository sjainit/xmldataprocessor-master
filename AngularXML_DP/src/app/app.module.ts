import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FileSelectDirective } from 'ng2-file-upload/ng2-file-upload'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CrudComponent } from './crud/crud.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { DataService } from './data.service';
import {Subject} from 'rxjs';
import { HttpClientModule } from '@angular/common/http';
import { ContactComponent } from './contact/contact.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SearchComponent } from './search/search.component';
import { PushtoqueueComponent } from './pushtoqueue/pushtoqueue.component';
import { FooterComponent } from './footer/footer.component';
import { UploadComponent } from './upload/upload.component';
import { ViewComponent } from './view/view.component';
import { AddComponent } from './add/add.component';
import { HomeComponent } from './home/home.component';
import { InputfilenameComponent } from './inputfilename/inputfilename.component';

const appRoutes: Routes = [
  { path: 'crud', component:CrudComponent },
  {path: 'contact', component:ContactComponent},
  {path: 'home', component:HomeComponent},
  {path: 'search', component:SearchComponent},
  {path: 'pushtoqueue', component:PushtoqueueComponent},
  {path: 'upload', component:UploadComponent},
  {path: '', component:HomeComponent},
  {path: 'view', component:ViewComponent},
  {path: 'add', component:AddComponent},
  {path: 'inputFileName', component:InputfilenameComponent}

];



@NgModule({
  declarations: [
    FileSelectDirective,
    AppComponent,
    CrudComponent,
    ContactComponent,
    NavbarComponent,
    HomeComponent,
    SearchComponent,
    PushtoqueueComponent,
    FooterComponent,
    UploadComponent,
    ViewComponent,
    AddComponent,
    InputfilenameComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CommonModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true } // <-- debugging purposes only
    )
  ],
  providers: [DataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
