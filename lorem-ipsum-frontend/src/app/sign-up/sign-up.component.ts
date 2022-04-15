import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {
  firstName = '';
  lastName = '';
  email = '';
  password = '';

  constructor(private httpClient: HttpClient, private router: Router, private _snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
  }

  /**
   * send sign up request to the server
   */
  signUp(): void {
    let body = {
      'firstName': this.firstName,
      'lastName': this.lastName,
      'email': this.email,
      'password': this.password
    };
    this.httpClient.post("/api/customer/signup", body, {responseType: "text"}).subscribe(res => {
      if (res) {
        this.router.navigateByUrl('login');
        this._snackBar.open('Sign up successful', '', {duration: 3000});
      } else {
        this._snackBar.open('Could not complete sign up', '', {duration: 3000});
      }
    });
  }
}
