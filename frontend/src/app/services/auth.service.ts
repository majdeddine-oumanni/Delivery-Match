import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
private authUrl = 'http://localhost:8080/api/v1/auth';
  private userUrl = 'http://localhost:8080/user';

  constructor(private http: HttpClient) {}

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders().set('Authorization', `Bearer ${token}`);
  }

  register(user: { fullname: string; email: string; password: string; role: string }): Observable<any> {
    const payload = {
      name: user.fullname,
      email: user.email,
      password: user.password,
      role: user.role.toUpperCase()
    };
    return this.http.post(`${this.authUrl}/register`, payload);
  }

  login(credentials: { email: string; password: string }): Observable<any> {
    return this.http.post(`${this.authUrl}/login`, credentials);
  }

  getUsers(): Observable<any[]> {
    return this.http.get<any[]>(`${this.userUrl}/userList`, { headers: this.getHeaders() });
  }

  deleteUser(id: number): Observable<any> {
    return this.http.delete(`${this.userUrl}/deleteUser/${id}`, { headers: this.getHeaders() });
  }

  updateUser(id: number, user: { name: string; email: string; role: string }): Observable<any> {
    return this.http.put(`${this.userUrl}/update/${id}`, user, { headers: this.getHeaders() });
  }
}
