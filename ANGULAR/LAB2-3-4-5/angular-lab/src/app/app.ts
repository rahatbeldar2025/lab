import { Component, inject, signal, OnInit } from '@angular/core';
import { UserService } from './user';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html'
})
export class App implements OnInit {
  private userService = inject(UserService);
  users = signal<any[]>([]);

  ngOnInit() {
    this.userService.getUsers().subscribe(data => this.users.set(data));
  }
}