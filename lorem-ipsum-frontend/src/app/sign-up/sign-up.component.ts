import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { NotificationService } from '../shared/services/notification.service';
import { CustomerService } from '../shared/services/customer.service';
import { SignUpData } from './model';
import { CommunicationService } from '../shared/services/communication.service';
import { finalize } from 'rxjs';
import {StorageHelper} from "../shared/helpers/storage-helper";
import {CustomerDto} from "../shared/models/customerDto.model";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {
  signUpData = new SignUpData();

  constructor(private customerService: CustomerService,
    private router: Router,
    private notificationService: NotificationService,
    private communicationService: CommunicationService) {
  }

  ngOnInit(): void {
  }

  /**
   * send sign up request to the server
   */
  signUp(): void {
    this.communicationService.notifyLoading(true)
    this.customerService.signUp(this.signUpData)
      .pipe(finalize(() => this.communicationService.notifyLoading(false)))
      .subscribe({
        next: res => {
          if (res) {
            StorageHelper.setCustomer(res as CustomerDto);
            this.router.navigateByUrl('');
            this.notificationService.info('Sign up successful');
          } else {
            this.notificationService.error('Could not complete sign up');
          }
        },
        error: () => this.notificationService.error('Could not complete sign up')
      });
  }
}
