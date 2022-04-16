import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { HttpClient } from "@angular/common/http";
import { MatSnackBar } from "@angular/material/snack-bar";
import { CustomerService } from '../shared/services/customer.service';
import { SnackBarService } from '../shared/services/snackBar.service';
import { StorageHelper } from '../shared/helpers/storage-helper';
import { CustomerDto } from '../shared/models/customerDto.model';
import { CommunicationService } from '../shared/services/communication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  email = '';
  password = '';

  constructor(private customerService: CustomerService,
    private router: Router,
    private snackBarService: SnackBarService,
    private communicationService: CommunicationService) {
  }

  ngOnInit(): void {
  }

  /**
   * attempt login with user input
   */
  logIn() {
    let body = {
      email: this.email,
      password: this.password
    }
    this.communicationService.notifyLoading(true);
    this.customerService.login(body).subscribe({
      next: res => {
        if (res) {
          StorageHelper.setUser(res as CustomerDto);
          this.snackBarService.info('Login successful')
        } else {
          this.snackBarService.error('Invalid credentials');
        }
      },
      complete: () => this.communicationService.notifyLoading(false)
    });
  }

  /**
   * navigate to the sign up page
   */
  navigateSignUp() {
    this.router.navigateByUrl('signup');
  }
}
