export class UpdatePasswordData {
  customerId!: bigint;
  oldPassword!: string;
  newPassword!: string;
}

export class UpdateEmailData {
  customerId!: bigint;
  newEmail!: string;
  password!: string;
}
