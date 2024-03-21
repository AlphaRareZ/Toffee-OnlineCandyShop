import java.util.ArrayList;
import java.util.Vector;

public class DeliveryPerson extends Human {
    enum Status {
        Free,
        Busy,
        In_Holiday,
        Out_For_Delivery
    }

    DeliveryPerson(String name, String id, String email, String telephone) {
        super(name, id, email, telephone);
        deliveryCapacity = 10;
        status = Status.Free;
    }

    private final ArrayList<Orders> historyOrders = new ArrayList<Orders>();
    private int deliveryCapacity;
    private Status status;

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setDeliveryCapacity(int deliveryCapacity) {
        this.deliveryCapacity = deliveryCapacity;
    }

    public int getDeliveryCapacity() {
        return deliveryCapacity;
    }

    /**
     * fucntion that displays Delivery Person Orders history
     */
    public void displayOrdersHistory() {
        System.out.println(name + "'s History Orders: ");
        for (Orders i : historyOrders) {
            i.showOrders();
        }
    }

    /**
     * fuction that shows the data of delivery person
     */
    public void showData() {
        System.out.println('[' + name + " , " + id + " , " + email + " , " + telephone + ']');
        displayOrdersHistory();
    }

    /**
     * function that adds order into current delivery person queue of orders
     * @param order the order that is wanted to be added
     */
    public void addOrder(Orders order) {
        historyOrders.add(order);
    }

    /**
     * function that remove order from current delivery person queue of orders
     * @param order the order that is wanted to be removed
     */
    public void removeOrder(Orders order) {
        historyOrders.remove(order);
    }
}
