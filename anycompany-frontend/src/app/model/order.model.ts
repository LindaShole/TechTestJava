export class Order {
  constructor(
    public orderId: number,
    public customerId: number,
    public amount: number,
    public vat: number
  ) {}
}
