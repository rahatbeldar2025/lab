import { Component } from '@angular/core';
import { UserForm } from './user-form/user-form';
@Component({ 
selector: 'app-root', 
standalone: true, 
imports: [UserForm], 
template: `
<app-user-form></app-user-form>
`
})
export class App {
}