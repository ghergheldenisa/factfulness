import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, of} from 'rxjs';
import {CookieService} from 'ngx-cookie-service';
import {HttpClient} from '@angular/common/http';
import {User} from '../../models/user.model';
import {CurrentUser} from '../../models/user-full.model';
import {catchError, map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthUserService {
  baseUrl = 'api/users';

  constructor(private cookieService: CookieService, private httpClient: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<CurrentUser>(undefined);
  }

  get currentUser(): Observable<CurrentUser> {
    return this.currentUserSubject.asObservable();
  }

  get currentUserInstant(): CurrentUser {
    return this.currentUserSubject.value;
  }

  private currentUserSubject: BehaviorSubject<CurrentUser>;

  login(user: FormData) {
    return this.httpClient.post(this.baseUrl + '/login', user, {observe: 'response', withCredentials: true});
  }

  resetSession() {
    this.currentUserSubject.next(null);
  }

  init() {
    return this.httpClient.get<CurrentUser>('api/users/current').pipe(
      map(res => {
        this.currentUserSubject.next(res);
        return this.currentUserSubject.value;
      }),
      catchError(err => {
        this.currentUserSubject.next(null);
        return of(this.currentUserSubject.value);
      })
    );
  }
}

