import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PushtoqueueComponent } from './pushtoqueue.component';

describe('PushtoqueueComponent', () => {
  let component: PushtoqueueComponent;
  let fixture: ComponentFixture<PushtoqueueComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PushtoqueueComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PushtoqueueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
