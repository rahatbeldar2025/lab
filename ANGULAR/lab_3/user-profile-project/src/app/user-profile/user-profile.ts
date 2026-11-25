import { Component, Output, EventEmitter } from '@angular/core';
@Component({
selector: 'app-user-profile', 
imports: [],
templateUrl: './user-profile.html', 
styleUrl: './user-profile.css'
})
export class UserProfile {
@Output() updateProfile = new EventEmitter<string>(); 
onUpdate() {
this.updateProfile.emit('Rahat Jamal Beldar');
}
}
