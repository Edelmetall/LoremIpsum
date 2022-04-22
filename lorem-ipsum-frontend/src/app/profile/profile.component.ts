import { Component, OnInit } from '@angular/core';
import {UpdateEmailData, UpdatePasswordData} from "./model";
import {StorageHelper} from "../shared/helpers/storage-helper";
import {finalize} from "rxjs";
import {CustomerDto} from "../shared/models/customerDto.model";
import {CustomerService} from "../shared/services/customer.service";
import {Router} from "@angular/router";
import {NotificationService} from "../shared/services/notification.service";
import {CommunicationService} from "../shared/services/communication.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  customer!: CustomerDto;

  updatePassword = new UpdatePasswordData();
  updateEmail = new UpdateEmailData();

  constructor(private customerService: CustomerService,
              private router: Router,
              private notificationService: NotificationService,
              private communicationService: CommunicationService) { }

  ngOnInit(): void {
    this.customer = StorageHelper.getCustomer();

    this.updatePassword.customerId = this.customer.id;

    this.updateEmail.customerId = this.customer.id;
    this.updateEmail.newEmail = this.customer.email;
  }

  /**
   * send request for password change
   */
  changePassword() {
    this.communicationService.notifyLoading(true);
    this.customerService.updatePassword(this.updatePassword)
      .pipe(finalize(() => this.communicationService.notifyLoading(false)))
      .subscribe({
        next: res => {
          if (res) {
            this.notificationService.info('Password change successful');
          } else {
            this.notificationService.error('Could not change password');
          }
        },
        error: () => this.notificationService.error('Could not change password')
      });
  }

  /**
   * send request for e-mail address change
   */
  changeEmail() {
    this.communicationService.notifyLoading(true);
    this.customerService.updateEmail(this.updateEmail)
      .pipe(finalize(() => this.communicationService.notifyLoading(false)))
      .subscribe({
        next: res => {
          if (res) {
            StorageHelper.setCustomer(res as CustomerDto);
            this.notificationService.info('E-mail address change successful');
          } else {
            this.notificationService.error('Could not change e-mail address');
          }
        },
        error: () => this.notificationService.error('Could not change e-mail address')
      });
  }
}
