import { Injectable } from "@angular/core";
import * as _ from "lodash";
import { IndividualConfig, ToastrService } from "ngx-toastr";

@Injectable()
export class NotificationService {

    config: Partial<IndividualConfig> = {
        newestOnTop: true
    }

    constructor(private toastr: ToastrService) {

    }

    error(message: string) {
        this.toastr.error(message, '', this.config);
    }

    warning(message: string) {
        this.toastr.warning(message, '', this.config);
    }

    info(message: string) {
        this.toastr.info(message, '', this.config);
    }

    clearNotifications() {
        this.toastr.clear();
    }
}