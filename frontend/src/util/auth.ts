import { Role } from 'types/role';
import { getTokenData } from './token';

export const isAuthenticated = (): boolean => {
  const tokenData = getTokenData();
  return tokenData && tokenData.exp * 1000 > Date.now() ? true : false;
};

export const hasAnyRole = (roles: Role[]): boolean => {
  if (roles.length === 0) {
    return true;
  }

  const tokenData = getTokenData();

  /*IMPLEMENTAÇÃO ALTERNATIVA USANDO FUNÇÃO DE ALTA ORDEM 
      if(tokenData !== undefined){
        return roles.some(role => tokenData.authorities.includes(roles));
      }
    */

  if (tokenData !== undefined) {
    for (var i = 0; i < roles.length; i++) {
      if (tokenData.authorities.includes(roles[i])) {
        return true;
      }
    }
  }

  return false;
};
