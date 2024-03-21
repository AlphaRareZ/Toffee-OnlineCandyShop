import java.util.*;

public class Admin extends Human {
    private final Scanner scanner = new Scanner(System.in);
    private final Inventory inventory;

    Admin(String name, String id, String email, String telephone) {
        super(name, id, email, telephone);
        inventory = SystemData.inventory;
    }

    /**
     * Function In Admin Class that prints the news added
     */
    public void addNews() {
        System.out.println("added news!");
    }

    /**
     * this function adds new product into inventory
     *
     * @param product the product that is wanted to be added in inventory
     * @return returns the product added
     */
    public Product addProduct(Product product) {
        inventory.addProduct(product);
        return product;
    }

    /**
     * this function takes the product that is wanted to be removed
     *
     * @param product the product that is wanted to be removed
     */
    public void removeProduct(Product product) {
        inventory.removeProduct(product);
    }

    /**
     * an admin function that can edit the product's price and quantity
     *
     * @param product the product that is wanted to be edited
     */
    public void editProduct(Product product) {
        ArrayList<Product> editable = new ArrayList<Product>();
        editable = inventory.searchBy("name", product.getName());

        if (editable.contains(product)) {
            System.out.println("Change price or the quantity?");
            String cond = scanner.nextLine();
            if (cond.equals("quantity")) {
                System.out.println("Enter the new amount: ");
                int newQuantity = scanner.nextInt();
                product.setQuantity(newQuantity);
            } else if (cond.equals("price")) {
                System.out.println("Enter the new amount: ");
                double newPrice = scanner.nextDouble();
                product.setPrice(newPrice);
            }
        } else {
            System.out.println("this product is not found in the invenotry");
        }
    }

    /**
     * Function that admin can set loyalty points to a customer with it
     * @param customer the customer that is wanted to get his loyalty points set
     * @param newLoyaltyPoints the new amount of loyalty points
     */
    public void editLoyaltySchema(Customer customer, int newLoyaltyPoints) {
        // add or suspend loyalty points
        customer.setLoyaltyPoints(newLoyaltyPoints);
    }

}
