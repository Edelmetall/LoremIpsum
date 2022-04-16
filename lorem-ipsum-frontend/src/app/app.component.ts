import { Component, OnDestroy, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { CommunicationService } from './shared/services/communication.service';
import { NotificationService } from './shared/services/notification.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'lorem-ipsum-frontend';
  loading = false;
  loadingSubscription!: Subscription;

  constructor(private communicationService: CommunicationService, private router: Router, private notificationService: NotificationService) {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.notificationService.clearNotifications();
        setTimeout(() => this.loading = false);
      }
    });
  }

  ngOnInit(): void {
    this.loadingSubscription = this.communicationService.onLoadingChanged().subscribe((loading: boolean) => {
      setTimeout(() => this.loading = loading);
    });
  }

  ngOnDestroy(): void {
    this.loadingSubscription?.unsubscribe();
  }

}
