import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";

@Injectable()
export class CommunicationService {
    private loadingSubject$ = new Subject<boolean>();
    private outputTabSubject$ = new Subject<void>();

    notifyLoading(loading: boolean) {
        this.loadingSubject$.next(loading);
    }

    onLoadingChanged(): Observable<boolean> {
        return this.loadingSubject$.asObservable();
    }

    notifyOutputTabChanged() {
        this.outputTabSubject$.next();
    }

    onOutputTabChanged(): Observable<void> {
        return this.outputTabSubject$.asObservable();
    }
}