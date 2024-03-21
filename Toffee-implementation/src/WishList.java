import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.io.File;

public class WishList {
    private ArrayList<Product> wantedProducts = new ArrayList<Product>();


    public void setWantedProducts(ArrayList<Product> wantedProducts) {
        this.wantedProducts = wantedProducts;
    }

    public ArrayList<Product> getWantedProducts() {
        return wantedProducts;
    }

    /**
     * function that adds a product to the current customer's wishlist
     *
     * @param product the product that is wanted to be added
     */
    public void addProduct(Product product) {
        wantedProducts.add(product);
    }

    /**
     * function that removes a product from the current customer's wishlist
     *
     * @param product the product that is wanted to be removed
     */
    public void removeProductIntoWishlist(Product product) {
        wantedProducts.remove(product);
    }

    /**
     * function that displays the products that the current customer needs to buy in the future
     */
    public void displayWantedProducts() {
        for (Product i : wantedProducts) {
            System.out.println("Name: " + i.getName() + " , with Quantity = " + i.getQuantity());
        }
    }

    /**
     * function that searches for a all products that matches a specific schema
     *
     * @param attribute the key that holds the type of search
     * @param value     value of the key (attribute)
     * @return ArrayList of products selected by its attribute
     */
    public ArrayList<Product> searchBy(String attribute, Object value) {
        ArrayList<Product> newWantedProducts = new ArrayList<Product>();
        if (attribute.equalsIgnoreCase("name")) {
            for (Product item : wantedProducts) {
                if (item.getName() == value) {
                    newWantedProducts.add(item);
                }
            }
        }
        if (attribute.equalsIgnoreCase("price")) {
            for (Product item : wantedProducts) {
                if (item.getPrice() == (double) value) {
                    newWantedProducts.add(item);
                }
            }
        }
        if (attribute.equalsIgnoreCase("type")) {

            if (((String) value).equalsIgnoreCase("kilo") || ((String) value).equalsIgnoreCase("bykilo")) {
                for (Product item : wantedProducts) {
                    if (item.getType() == Product.type.BYKILO) {
                        newWantedProducts.add(item);
                    }
                }
            }

            if (((String) value).equalsIgnoreCase("package") || ((String) value).equalsIgnoreCase("bypackage")) {
                for (Product item : wantedProducts) {
                    if (item.getType() == Product.type.BYPACKAGE) {
                        newWantedProducts.add(item);
                    }
                }
            }
        }
        if (attribute.equalsIgnoreCase("quantity")) {
            for (Product item : wantedProducts) {
                if (item.getQuantity() == (int) value) {
                    newWantedProducts.add(item);
                }
            }
        }
        return newWantedProducts;
    }

    /**
     * function that sorts products by any of its attributes
     *
     * @param condition the attribute
     * @return ArrayList that holds the sorted products array
     */
    public ArrayList<Product> sortBy(String condition) {
        ArrayList<Product> sortedWantedProducts = new ArrayList<Product>();
        switch (condition.toLowerCase()) {
            case "name" -> {
                ArrayList<String> sorted = new ArrayList<String>();
                for (Product item : wantedProducts) {
                    sorted.add(item.getName());
                }
                Collections.sort(sorted);
                for (int i = 0; i < sorted.size(); i++) {
                    for (int j = 0; j < sorted.size(); j++) {
                        if (Objects.equals(sorted.get(i), wantedProducts.get(j).getName())) {
                            sortedWantedProducts.add(wantedProducts.get(j));
                            break;
                        }
                    }
                }
            }
            case "price" -> {
                ArrayList<Double> sortedDoubles = new ArrayList<Double>();
                for (Product item : wantedProducts) {
                    sortedDoubles.add(item.getPrice());
                }
                Collections.sort(sortedDoubles);
                for (int i = 0; i < sortedDoubles.size(); i++) {
                    for (int j = 0; j < sortedDoubles.size(); j++) {
                        if (sortedDoubles.get(i) == wantedProducts.get(j).getPrice()) {
                            sortedWantedProducts.add(wantedProducts.get(j));
                            break;
                        }
                    }
                }
            }
            case "quantity" -> {
                ArrayList<Integer> sortedIntegers = new ArrayList<Integer>();
                for (Product item : wantedProducts) {
                    sortedIntegers.add(item.getQuantity());
                }
                Collections.sort(sortedIntegers);
                for (int i = 0; i < sortedIntegers.size(); i++) {
                    for (int j = 0; j < sortedIntegers.size(); j++) {
                        if (sortedIntegers.get(i) == wantedProducts.get(j).getQuantity()) {
                            sortedWantedProducts.add(wantedProducts.get(j));
                            break;
                        }
                    }
                }
            }
        }
        return sortedWantedProducts;
    }

}
