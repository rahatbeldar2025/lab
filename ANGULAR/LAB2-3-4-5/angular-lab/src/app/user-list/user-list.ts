import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-user-list',
  imports: [RouterLink],
  templateUrl: './user-list.html'
})
export class UserList {
  users = [
    { id: 1, name: 'Amit' },
    { id: 2, name: 'Sneha' }
  ];
}