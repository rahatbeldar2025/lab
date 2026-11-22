import { Component } from '@angular/core';
import { UserProfile } from './user-profile/user-profile';
@Component({ 
selector: 'app-root', 
imports: [UserProfile],
templateUrl: './app.html', 
styleUrl: './app.css'
})
export class App {
userName = 'Guest';
updateUser(name:string) { 
this.userName = name;
}
}
