import { Component } from '@angular/core'; 
import {
FormGroup, 
FormControl, 
Validators, 
ReactiveFormsModule
} from '@angular/forms';
@Component({
selector: 'app-user-form', 
standalone: true,
imports: [ReactiveFormsModule], 
templateUrl: './user-form.html'
})
export class UserForm { 
userForm = new FormGroup({
name: new FormControl('', [ 
Validators.required, 
Validators.minLength(3)
]),
email: new FormControl('', [ 
Validators.required, 
Validators.email
])
});
submitted = false;
onSubmit() { 
this.submitted = true;
if (this.userForm.valid) { 
alert('User added successfully!'); 
console.log(this.userForm.value);
}
}
}
