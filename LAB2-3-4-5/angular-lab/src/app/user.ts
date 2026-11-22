import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class UserService {
  private http = inject(HttpClient);
  private apiUrl = 'https://jsonplaceholder.typicode.com/users';

  getUsers() {
    return this.http.get<any[]>(this.apiUrl);
  }

  saveUser(user: any) {
    return this.http.post(this.apiUrl, user);
  }
}