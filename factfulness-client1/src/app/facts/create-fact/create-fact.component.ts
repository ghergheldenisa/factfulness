import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {FactService} from '../../core/services/fact.service';
import {Fact} from '../../models/fact.model';

@Component({
  selector: 'app-create-fact',
  templateUrl: './create-fact.component.html',
  styleUrls: ['./create-fact.component.scss']
})
export class CreateFactComponent implements OnInit {
  form: FormGroup;
  id: number;

  constructor(
    private service: FactService,
    private router: Router,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder) {
    this.form = fb.group({
      factId: [''],
      text: ['', [Validators.required]]
    });
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      this.init(params.id);
    });
  }

  init(factId: number) {
    if (factId !== undefined) {
      this.id = factId;
      this.service.get(this.id).subscribe((res: Fact) => {
        Object.keys(this.form.controls).forEach(controlName => {
          if (controlName === 'text') {
            this.form.controls[controlName].setValue(res.text);
          } else if (controlName === 'factId') {
            this.form.controls[controlName].setValue(this.id);
          }
        });
      });
    }
  }

  submit() {
    if (!this.id) {
      this.addNewFact();
    } else {
      this.editFact();
    }
  }

  editFact() {
    console.log(this.form.value)
    this.service.updateFact(this.form.value).subscribe(res => {
      this.router.navigateByUrl('/fact-list');
    }, error => {
    });
  }

  addNewFact() {
    this.service.addFact(this.form.value).subscribe(res => {
      this.router.navigateByUrl('/fact-list');
    }, error => {
    });
  }

  redirect() {
    this.router.navigateByUrl('/fact-list');
  }

  redirectToHome() {
    this.router.navigateByUrl('/fact');
  }
}
