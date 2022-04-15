import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  email = '';
  password = '';

  constructor(private httpClient: HttpClient, private router: Router, private _snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
  }

  /**
   * attempt login with user input
   */
  logIn() {
    let body = {
      'email': this.email,
      'password': this.password
    }
    this.httpClient.post("/api/customer/login", body, {responseType: "text"}).subscribe(res => {
      if (res) {
        sessionStorage.setItem('customer', JSON.stringify(res));
      } else {
        this._snackBar.open('Invalid credentials', '', {duration: 3000});
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
