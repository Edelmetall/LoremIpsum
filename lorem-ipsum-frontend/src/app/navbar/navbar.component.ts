import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {CustomerDto} from "../shared/models/customerDto.model";
import {StorageHelper} from "../shared/helpers/storage-helper";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  homeId = "home-menu-item";
  peopleId = "people-menu-item";
  settingsId = "settings-menu-item";
  profileId = "profile-menu-item";
  logOutId = "log-out-menu-item";

  /**
   * get logged in customer/user
   */
  getCustomer(): CustomerDto {
    return StorageHelper.getCustomer();
  }

  /**
   * navigate by url
   * @param url destination
   */
  navigate(url: string) {
    if (url === 'login' && StorageHelper.getCustomer()) {
      StorageHelper.setCustomer(undefined);
    }
    this.router.navigateByUrl(url);
  }
}
