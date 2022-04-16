import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { SignUpData } from "src/app/sign-up/model";

@Injectable()
export class CustomerService {
    constructor(private httpClient: HttpClient) {

    }

    login(body: { email: string, password: string }) {
        return this.httpClient.post("/api/customer/login", body);
    }

    signUp(signUpData: SignUpData) {
        return this.httpClient.post("/api/customer/signup", signUpData);
    }
}