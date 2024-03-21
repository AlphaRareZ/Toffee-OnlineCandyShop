import java.util.*;

public class Bill {
    private final Customer customer;
    private ArrayList<Product> products = new ArrayList<Product>();
    private final String date;

    Bill(Customer c, ArrayList<Product> p, String d, boolean remove) {
        this.customer = c;
        this.products = p;
        this.date = d;
        if (remove) {
            for (Product i : p) {
                for (Product item : SystemData.inventory.inventoryProducts) {
                    if (i == item) {
                        if (item.quantity == 0) break;
                        item.quantity--;
                        break;
                    }
                }

            }
        }
        SystemData.inventory.writeIntoProducts();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public String getDate() {
        return date;
    }

    /**
     * this function returns the total Bill Payment price
     * @return the total Bill Payment price
     */
    public double totalPayment() {
        double total = 0.00;
        for (Product item : products) {
            total += item.getPrice();
        }
        return total;
    }

    /**
     * function that displays the products in the current bill
     * @param number Bill Number in customer's account
     */
    public void displayDetails(int number) {
        System.out.println("Bill Number " + number + '.');
        ArrayList<Product> taken = new ArrayList<>();
        for (Product item : products) {
            if (taken.contains(item)) continue;
            taken.add(item);
            int counter = 0;
            for (Product i : products) {
                if (i == item) counter++;
            }
            System.out.println("    " + item.getName() + "  " + counter + " PCS  " + counter * item.getPrice() + "LE");
        }
        System.out.println("    " + "TOTAL: " + totalPayment() + "LE .. Order Date : " + this.date);
        System.out.println("_____________________________________________________________________");
    }
}
