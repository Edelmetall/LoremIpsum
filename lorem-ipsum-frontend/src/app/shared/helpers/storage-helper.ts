import * as _ from "lodash";
import {CustomerDto} from "../models/customerDto.model";

export class StorageHelper {

  static setCustomer(customer: CustomerDto | undefined) {
    if (customer) {
      sessionStorage.setItem('customer', JSON.stringify(customer));
    } else {
      sessionStorage.removeItem('customer');
    }
  }

  static getCustomer(): CustomerDto {
    return JSON.parse(String(sessionStorage.getItem('customer'))) as CustomerDto;
  }

  static getCustomerId(): bigint | undefined {
    const currentUser = JSON.parse(String(sessionStorage.getItem('customer'))) as CustomerDto;
    if (!_.isNil(currentUser)) {
      return currentUser.id;
    }
    return undefined;
  }
}
