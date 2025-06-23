import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(): boolean {
    const token = localStorage.getItem('token');
    if (token) {
      try {
        const decoded: any = jwtDecode(token);
        const roles = decoded.authorities || decoded.roles || []; // Adjust based on the JWT structure
        if (roles.includes('ROLE_ADMIN')) {
          return true;
        }
        alert('Access denied: Admin role required');
        this.router.navigate(['/login']);
        return false;
      } catch (error) {
        console.error('Invalid token:', error);
        this.router.navigate(['/login']);
        return false;
      }
    }
    this.router.navigate(['/login']);
    return false;
  }
}
