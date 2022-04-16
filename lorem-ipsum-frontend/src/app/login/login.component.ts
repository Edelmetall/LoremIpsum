import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { HttpClient } from "@angular/common/http";
import { MatSnackBar } from "@angular/material/snack-bar";
import { CustomerService } from '../shared/services/customer.service';
import { SnackBarService } from '../shared/services/snackBar.service';
import { StorageHelper } from '../shared/helpers/storage-helper';
import { CustomerDto } from '../shared/models/customerDto.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  email = '';
  password = '';

  constructor(private customerService: CustomerService, private router: Router, private snackBarService: SnackBarService) {
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
    this.customerService.login(body).subscribe(res => {
      if (res) {
        StorageHelper.setUser(res as CustomerDto);
        this.snackBarService.info('Login successful')
      } else {
        this.snackBarService.error('Invalid credentials');
      }
    });
  }

  /**
   * navigate to the sign up page
   */
  navigateSignUp() {
    this.router.navigateByUrl('signup');
  }
}
