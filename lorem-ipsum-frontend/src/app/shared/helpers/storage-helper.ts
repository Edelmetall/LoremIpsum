import * as _ from "lodash";
import { CustomerDto } from "../models/customerDto.model";

export class StorageHelper {
    static setUser(user: CustomerDto){
        sessionStorage.setItem('customer', JSON.stringify(user));
    }

    static getCurrentUserId(): bigint | undefined {
        const currentUser = JSON.parse(String(sessionStorage.getItem('customer'))) as CustomerDto;
        if (!_.isNil(currentUser)) {
            return currentUser.id;
        }
        return undefined;
    }
}