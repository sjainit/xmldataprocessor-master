import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InputfilenameComponent } from './inputfilename.component';

describe('InputfilenameComponent', () => {
  let component: InputfilenameComponent;
  let fixture: ComponentFixture<InputfilenameComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InputfilenameComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InputfilenameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
