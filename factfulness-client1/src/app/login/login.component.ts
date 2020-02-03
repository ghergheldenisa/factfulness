import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthUserService} from 'src/app/core/auth/auth.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {HttpResponse} from '@angular/common/http';
import {User} from '../models/user.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
  user: User = {username: '', password: ''};
  loginForm: FormGroup;
  submitted = false;
  error: string = null;

  constructor(private formBuilder: FormBuilder, private router: Router, private authUserService: AuthUserService) {
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  login() {
    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    }
    this.user.username = this.loginForm.value.username;
    this.user.password = this.loginForm.value.password;
    this.error = null;
    const formData = new FormData();
    formData.append('username', this.user.username);
    formData.append('password', this.user.password);

    this.authUserService.login(formData)
      .subscribe((resp: HttpResponse<any>) => {
        if (resp.status === 200) {
          this.router.navigateByUrl('/fact');
        }
      }, error => {
        this.error = 'Invalid credentials';
      });
  }
}
