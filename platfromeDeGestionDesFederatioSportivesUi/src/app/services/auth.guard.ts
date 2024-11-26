import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {TokenService} from "./authentication/token.service";

export const authGuard: CanActivateFn = (route, state) => {
  const tokenService = inject(TokenService)
  const routeService = inject(Router)
  if (tokenService.isExpired()){
    routeService.navigate([''])
    return false
  }
  return true;
};
