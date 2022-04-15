import { Injectable } from "@angular/core";
import { MatSnackBar } from "@angular/material/snack-bar";

@Injectable()
export class SnackBarService {

    constructor(private _snackBar: MatSnackBar) {

    }

    error(message: string) {
        this.showSnackBar(message, 'snackbar-error');
    }

    warning(message: string) {
        this.showSnackBar(message, 'snackbar-warning');
    }

    info(message: string) {
        this.showSnackBar(message, 'snackbar-info');
    }

    private showSnackBar(message: string, panelClass: string) {
        return this._snackBar.open(message, 'X', {
            duration: 3000,
            panelClass: panelClass,
            horizontalPosition: "right",
            verticalPosition: "top" 
        });
    }
}