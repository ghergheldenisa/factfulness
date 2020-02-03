import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {FactService} from '../../core/services/fact.service';
import {Fact} from '../../models/fact.model';

@Component({
  selector: 'app-fact',
  templateUrl: './fact.component.html',
  styleUrls: ['./fact.component.scss']
})
export class FactComponent implements OnInit {
  fact: Fact;

  constructor(
    private service: FactService,
    private router: Router) {
  }

  ngOnInit() {
    this.randomFact();
  }

  randomFact() {
    this.service.getRandom().subscribe((res: Fact) => {
      this.fact = res;
    });
  }

  manageFacts() {
    this.router.navigateByUrl('/fact-list');
  }

}
