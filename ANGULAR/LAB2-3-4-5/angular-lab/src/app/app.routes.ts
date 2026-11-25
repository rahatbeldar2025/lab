import { Routes } from '@angular/router';
import { UserList } from './user-list/user-list';
import { UserDetails } from './user-details/user-details';

export const routes: Routes = [
  { path: '', redirectTo: 'users', pathMatch: 'full' }, // App start hote hi user-list khulega
  { path: 'users', component: UserList },
  { path: 'users/:id', component: UserDetails }
];