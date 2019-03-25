import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LocalTestComponent } from './local-test.component';

describe('LocalTestComponent', () => {
  let component: LocalTestComponent;
  let fixture: ComponentFixture<LocalTestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LocalTestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LocalTestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
