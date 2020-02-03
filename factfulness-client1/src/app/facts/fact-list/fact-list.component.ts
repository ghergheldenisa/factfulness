import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {MatTableDataSource} from '@angular/material';
import {Fact} from '../../models/fact.model';
import {FactService} from '../../core/services/fact.service';

@Component({
  selector: 'app-fact-list',
  templateUrl: './fact-list.component.html',
  styleUrls: ['./fact-list.component.scss']
})
export class FactListComponent implements OnInit {

  displayedColumns: string[] = ['id', 'fact', 'action'];
  dataSource: any = new MatTableDataSource<Fact>();

  constructor(private router: Router,
              private factService: FactService) {
  }

  ngOnInit() {
    this.initDataSource();
  }

  initDataSource() {
    this.factService.getAllForUser().subscribe(res => {
      this.dataSource.data = res;
    });
  }

  redirectToHome() {
    this.router.navigateByUrl('/fact');
  }

  addNew() {
    this.router.navigateByUrl('/create-fact');
  }

  delete(factId: number) {
    this.factService.delete(factId).subscribe(res => {
      this.initDataSource();
    });
  }

  update(factId: number) {
      this.router.navigate([`create-fact/`, factId]);
  }
}
