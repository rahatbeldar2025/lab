import { Component, inject } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';

@Component({
  selector: 'app-user-details',
  imports: [RouterLink],
  templateUrl: './user-details.html'
})
export class UserDetails {
  private route = inject(ActivatedRoute);
  userId = this.route.snapshot.paramMap.get('id');
}