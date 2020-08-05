export class CustomerOrderDetails {
  constructor(
    public orderId: number,
    public customerId: number,
    public customerName: string,
    public country: string,
    public dateOfBirth: string,
    public amount: number,
    public vat: number
  ) {}
}
