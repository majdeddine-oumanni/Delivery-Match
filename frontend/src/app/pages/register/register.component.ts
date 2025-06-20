import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatSelectModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  fullname: string = '';
  email: string = '';
  password: string = '';
  role: string = '';

  constructor(private authService: AuthService) {}

  onRegister() {
    this.authService.register({ fullname: this.fullname, email: this.email, password: this.password, role: this.role })
      .subscribe({
        next: (response) => {
          alert('Registration successful! Please login.');
          window.location.href = '/login'; // Redirect to login page
        },
        error: (error) => {
          alert('Registration failed: ' + error.error.message);
        }
      });
  }
}
