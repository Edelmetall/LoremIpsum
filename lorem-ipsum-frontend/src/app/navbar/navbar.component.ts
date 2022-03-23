import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(private router: Router) {}

  ngOnInit(): void {
  }

  homeId = "home-menu-item";
  peopleId = "people-menu-item";
  settingsId = "settings-menu-item";
  profileId = "profile-menu-item";
  logOutId = "log-out-menu-item";

  markAsActive(id: string) {
    //todo
  }

  navigate(url: string) {
    this.router.navigateByUrl(url);
  }

}
