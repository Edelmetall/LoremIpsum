import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { SnackBarService } from '../shared/services/snackBar.service';
import { CustomerService } from '../shared/services/customer.service';
import { SignUpData } from './model';
import { CommunicationService } from '../shared/services/communication.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {
  signUpData = new SignUpData();

  constructor(private customerService: CustomerService,
    private router: Router,
    private snackBarService: SnackBarService,
    private communicationService: CommunicationService) {
  }

  ngOnInit(): void {
  }

  /**
   * send sign up request to the server
   */
  signUp(): void {
    this.communicationService.notifyLoading(true)
    this.customerService.signUp(this.signUpData).subscribe({
      next: res => {
        if (res) {
          this.router.navigateByUrl('login');
          this.snackBarService.info('Sign up successful');
        } else {
          this.snackBarService.info('Could not complete sign up');
        }
      },
      complete: () => this.communicationService.notifyLoading(false)
    });
  }
}
