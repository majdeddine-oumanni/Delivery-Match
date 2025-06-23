import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule, MatDialog } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-users',
  standalone: true,
  imports: [MatTableModule, MatButtonModule, MatDialogModule, MatFormFieldModule, MatInputModule, MatSelectModule, FormsModule],
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  users: any[] = [];
  displayedColumns: string[] = ['name', 'email', 'role', 'actions'];

  constructor(private authService: AuthService, private dialog: MatDialog) {}

  ngOnInit() {
    this.authService.getUsers().subscribe({
      next: (data) => this.users = data,
      error: (error) => alert('Failed to load users: ' + (error.error.message || 'Unknown error'))
    });
  }

  deleteUser(id: number) {
    if (confirm('Are you sure you want to delete this user?')) {
      this.authService.deleteUser(id).subscribe({
        next: () => {
          this.users = this.users.filter(user => user.id !== id);
          alert('User deleted successfully');
        },
        error: (error) => alert('Failed to delete user: ' + (error.error.message || 'Unknown error'))
      });
    }
  }

  openUpdateDialog(user: any) {
    const dialogRef = this.dialog.open(UpdateUserDialogComponent, {
      data: { ...user }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.authService.updateUser(user.id, result).subscribe({
          next: () => {
            this.users = this.users.map(u => u.id === user.id ? { ...u, ...result } : u);
            alert('User updated successfully');
          },
          error: (error) => alert('Failed to update user: ' + (error.error.message || 'Unknown error'))
        });
      }
    });
  }
}

@Component({
  selector: 'app-update-user-dialog',
  standalone: true,
  imports: [MatDialogModule, MatFormFieldModule, MatInputModule, MatSelectModule, MatButtonModule, FormsModule],
  template: `
    <h2 mat-dialog-title>Update User</h2>
    <mat-dialog-content>
      <mat-form-field appearance="fill">
        <mat-label>Name</mat-label>
        <input matInput [(ngModel)]="data.name">
      </mat-form-field>
      <mat-form-field appearance="fill">
        <mat-label>Email</mat-label>
        <input matInput type="email" [(ngModel)]="data.email">
      </mat-form-field>
      <mat-form-field appearance="fill">
        <mat-label>Role</mat-label>
        <mat-select [(ngModel)]="data.role">
          <mat-option value="DRIVER">Driver</mat-option>
          <mat-option value="SENDER">Sender</mat-option>
          <mat-option value="ADMIN">Admin</mat-option>
        </mat-select>
      </mat-form-field>
    </mat-dialog-content>
    <mat-dialog-actions>
      <button mat-button (click)="dialogRef.close()">Cancel</button>
      <button mat-raised-button color="primary" (click)="dialogRef.close(data)">Save</button>
    </mat-dialog-actions>
  `
})
export class UpdateUserDialogComponent {
  constructor(public dialogRef: MatDialog) {}
  data: any;
}
