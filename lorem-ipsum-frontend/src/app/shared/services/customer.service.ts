import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {SignUpData} from "src/app/sign-up/model";
import {UpdateEmailData, UpdatePasswordData} from "../../profile/model";

@Injectable()
export class CustomerService {

  constructor(private httpClient: HttpClient) {
  }

  /**
   * Request to log in existing customer
   * @param body
   */
  login(body: { email: string, password: string }) {
    return this.httpClient.post("/api/customer/login", body);
  }

  /**
   * Request to sign up a new customer
   * @param signUpData
   */
  signUp(signUpData: SignUpData) {
    return this.httpClient.post("/api/customer/signup", signUpData);
  }

  /**
   * Request to reset password
   * @param email
   */
  resetPassword(email: string) {
    return this.httpClient.get("/api/customer/resetPassword/" + email);
  }

  /**
   * Request to update password of existing customer
   * @param updatePasswordData
   */
  updatePassword(updatePasswordData: UpdatePasswordData) {
    return this.httpClient.post("/api/customer/updatePassword", updatePasswordData);
  }

  /**
   * Request to update e-mail address of existing customer
   * @param updateEmailData
   */
  updateEmail(updateEmailData: UpdateEmailData) {
    return this.httpClient.post("/api/customer/updateEmail", updateEmailData);
  }
}
