import java.util.*;

public class Cart {
    ArrayList<Product> products = new ArrayList<Product>();

    Cart(ArrayList<Product> products) {
        this.products = products;
    }

    Cart() {
        this.products = new ArrayList<Product>();
    }

    /**
     * this function returns the total payment in the current customer's cart
     *
     * @return total payment in the current customer's cart
     */
    public double totalPayment() {
        double total = 0.00;
        for (Product item : products) {
            total += item.getPrice();
        }
        return total;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    /**
     * ENUM that holds the type of Product
     */
    enum Value {
        kilo,
        Package,
        NULL
    }

    /**
     * function that adds a product into the current cart
     *
     * @param product               the product that is wanted to be added in the cart
     * @param numberOfProductsTaken the quantity of the current product
     */
    public void addProduct(Product product, int numberOfProductsTaken) {
        for (int i = 0; i < numberOfProductsTaken; i++) {
            products.add(product);
        }
    }

    /**
     * function that removes a specific product in the cart
     *
     * @param product product that is wanted to be removed
     */
    public void removeProduct(Product product) {
        products.remove(product);
    }

    /**
     * function that searches for a all products that matches a specific schema
     *
     * @param attribute the key that holds the type of search
     * @param value     value of the key (attribute)
     * @return ArrayList of products selected by its attribute
     */
    public ArrayList<Product> searchBy(String attribute, Object value) {
        ArrayList<Product> newProducts = new ArrayList<Product>();
        if (attribute.equalsIgnoreCase("name")) {
            for (Product item : products) {
                if (item.getName() == value) {
                    newProducts.add(item);
                }
            }
        }
        if (attribute.equalsIgnoreCase("price")) {
            for (Product item : products) {
                if (item.getPrice() == (double) value) {
                    newProducts.add(item);
                }
            }
        }
        if (attribute.equalsIgnoreCase("type")) {
            if (((String) value).equalsIgnoreCase("kilo") || ((String) value).equalsIgnoreCase("bykilo")) {
                for (Product item : products) {
                    if (item.getType() == Product.type.BYKILO) {
                        newProducts.add(item);
                    }
                }
            }

            if (((String) value).toLowerCase().equals("package") || ((String) value).toLowerCase().equals("bypackage")) {
                for (Product item : products) {
                    if (item.getType() == Product.type.BYPACKAGE) {
                        newProducts.add(item);
                    }
                }
            }
        }
        if (attribute.toLowerCase().equals("quantity")) {
            for (Product item : products) {
                if (item.getQuantity() == (int) value) {
                    newProducts.add(item);
                }
            }
        }
        return newProducts;
    }
}
