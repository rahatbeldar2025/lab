import { Component, input, output } from '@angular/core';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.html'
})
export class UserProfile {
  // Practical 4: Parent se data receive karne ke liye (Property Binding)
  name = input<string>('');
  email = input<string>('');

  // Practical 5: Parent ko event bhejne ke liye (Event Binding)
  updateClicked = output<void>();

  onUpdate() {
    this.updateClicked.emit();
  }
}