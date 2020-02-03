import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {AuthUserService} from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(public auth: AuthUserService, public router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const currentUser = this.auth.init();
    if (currentUser) {
      if (state.url === '/login') {
        this.router.navigateByUrl('/fact');
        return false;
      }
      return true;
    } else {
      this.router.navigateByUrl('/login');
      return false;
    }
  }
}
