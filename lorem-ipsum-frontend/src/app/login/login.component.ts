import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {CustomerService} from '../shared/services/customer.service';
import {NotificationService} from '../shared/services/notification.service';
import {StorageHelper} from '../shared/helpers/storage-helper';
import {CustomerDto} from '../shared/models/customerDto.model';
import {CommunicationService} from '../shared/services/communication.service';
import {finalize} from 'rxjs';
import * as _ from 'lodash';

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
              private notificationService: NotificationService,
              private communicationService: CommunicationService) {
  }

  ngOnInit(): void {
    if (!_.isNil(StorageHelper.getCustomerId())) {
      this.router.navigateByUrl('');
    }
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
    this.customerService.login(body)
      .pipe(finalize(() => this.communicationService.notifyLoading(false)))
      .subscribe({
        next: res => {
          if (res) {
            let customer = res as CustomerDto;
            StorageHelper.setCustomer(customer);

            if (customer.passwordResetAt) {
              this.notificationService.info('Login successful, please change your password')
              this.router.navigateByUrl('profile');
            } else {
              this.notificationService.info('Login successful')
              this.router.navigateByUrl('');
            }
          } else {
            this.notificationService.error('Invalid credentials');
          }
        },
        error: () => this.notificationService.error('Could not complete login')
      });
  }

  /**
   * navigate to the sign up page
   */
  navigateSignUp() {
    this.router.navigateByUrl('signup');
  }

  resetPassword() {
    this.communicationService.notifyLoading(true);
    this.customerService.resetPassword(this.email)
      .pipe(finalize(() => this.communicationService.notifyLoading(false)))
      .subscribe({
        next: res => {
          this.notificationService.info('Temporary password has been sent');
        },
        error: () => this.notificationService.error('Technical error')
      });
  }
}
