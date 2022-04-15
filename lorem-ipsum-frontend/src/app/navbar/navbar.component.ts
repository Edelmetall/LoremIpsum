import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {CustomerDto} from "../shared/models/customerDto.model";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  customer!: CustomerDto;

  constructor(private router: Router) {
  }

  ngOnInit(): void {
    let customer = sessionStorage.getItem('customer');
    if (customer) {
      this.customer = JSON.parse(customer) as CustomerDto;
    }
  }

  homeId = "home-menu-item";
  peopleId = "people-menu-item";
  settingsId = "settings-menu-item";
  profileId = "profile-menu-item";
  logOutId = "log-out-menu-item";

  markAsActive(id: string) {
    //todo
  }

  /**
   * navigate by url
   * @param url destination
   */
  navigate(url: string) {
    if (url === 'login' && this.customer) {
      sessionStorage.removeItem('customer');
    }
    this.router.navigateByUrl(url);
  }
}
