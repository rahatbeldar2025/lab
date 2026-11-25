import { Component, Input } from '@angular/core';
@Component({
selector: 'app-user-profile', 
imports: [],
templateUrl: './user-profile.html', 
styleUrl: './user-profile.css'
})
export class UserProfile {
// Receive data from parent component 
@Input() name: string = '';
@Input() email: string = ''; 
@Input() age: number = 0; 
@Input() course: string = '';
}
