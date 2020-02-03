import {AuthUserService} from './auth.service';

export function SessionInitializer(authService: AuthUserService) {
  return () => authService.init().toPromise();
}
