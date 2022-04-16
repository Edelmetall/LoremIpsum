import { Injectable } from "@angular/core";
import { MatSnackBar, MatSnackBarConfig } from "@angular/material/snack-bar";
import * as _ from "lodash";

@Injectable()
export class SnackBarService {

    defaultConfig: MatSnackBarConfig = {
        duration: 3000,
        horizontalPosition: "center",
        verticalPosition: "top"
    };

    constructor(private _snackBar: MatSnackBar) {

    }

    error(message: string, config?: MatSnackBarConfig) {
        this.showSnackBar(message, 'snackbar-error', config);
    }

    warning(message: string, config?: MatSnackBarConfig) {
        this.showSnackBar(message, 'snackbar-warning', config);
    }

    info(message: string, config?: MatSnackBarConfig) {
        this.showSnackBar(message, 'snackbar-info', config);
    }

    private showSnackBar(message: string, panelClass: string, snackBarConfig?: MatSnackBarConfig) {
        const config = !_.isNil(snackBarConfig) ? snackBarConfig : this.defaultConfig;
        config.panelClass = panelClass;
        return this._snackBar.open(message, 'X', config);
    }
}