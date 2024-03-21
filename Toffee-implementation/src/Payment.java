public class Payment {
    /**
     * function that makes this the current order payment method is "PAY WITH CREDIT CARD"
     * @param order the current order
     */
    public void makePayementByCard(Orders order) {
        order.paymentMethod = Orders.PaymentMethod.By_Credit_Card;
    }

    /**
     * function that makes this the current order payment method is "PAY ON DELIVERY"
     *
     * @param order the current order
     */
    public void makePayementOnDelivery(Orders order) {
        order.paymentMethod = Orders.PaymentMethod.On_Delivery;
    }

    /**
     * @param customer
     * @param points
     */
    public void addLoyaltyPoints(Customer customer, int points) {
        customer.setLoyaltyPoints(points);
    }
}
