
public class Orders {
    // in need for enum that holds payment methods

    /**
     * ENUM that holds the way of payment method
     */
    enum PaymentMethod {
        On_Delivery,
        By_Credit_Card
    }

    /**
     * ENUM that holds the state of the product
     */
    enum OrderState {
        not_shipped_yet,
        shipped,
        out_for_delivery,
        in_shipping_center
    }

    Orders(Bill bill, String deliveryLocation, String deliveryDate, PaymentMethod paymentMethod, DeliveryPerson deliveryPerson) {
        this.bill = bill;
        this.deliveryLocation = deliveryLocation;
        this.deliveryDate = deliveryDate;
        this.paymentMethod = paymentMethod;
        this.deliveryPerson = deliveryPerson;
        this.orderState = OrderState.not_shipped_yet;
    }

    Bill bill;
    String deliveryLocation, deliveryDate;
    PaymentMethod paymentMethod;
    OrderState orderState;
    DeliveryPerson deliveryPerson;

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Bill getBill() {
        return bill;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryPerson(DeliveryPerson deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
        deliveryPerson.addOrder(this);
    }

    public DeliveryPerson getDeliveryPerson() {
        return deliveryPerson;
    }

    /**
     * function that shows the current order details
     */
    public void showOrders() {
        System.out.println("Delivery Location : " + deliveryLocation);
        System.out.println("Delivery Date : " + deliveryDate);
        System.out.println("Payment Method : " + paymentMethod);
        System.out.println("Order State : " + orderState);
        System.out.println("Total Price : " + bill.totalPayment());
    }
}
