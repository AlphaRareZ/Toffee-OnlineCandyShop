import java.util.*;

public class Product {
    /**
     * ENUM that holds the type of product quantity
     */
    static enum type {
        BYKILO,
        BYPACKAGE;
    }

    private String name;
    private double price;
    private type Type;
    public int quantity;


    public Product(String name, double price, String Type, int quantity) {
        setName(name);
        setPrice(price);
        setType(Type);
        setQuantity(quantity);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(String Type) {
        if (Type.toLowerCase().equals("bykilo") || Type.toLowerCase().equals("kilo")) {
            this.Type = type.BYKILO;
        } else if (Type.toLowerCase().equals("bypackage") || Type.toLowerCase().equals("package")) {
            this.Type = type.BYPACKAGE;
        } else {
            System.out.println("this product is invalid!");
        }
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;

    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public type getType() {
        return this.Type;
    }

    public int getQuantity() {
        return this.quantity;
    }


    private Object search(Object object) {
        if (object == this.name) {
            return name;
        }
        if (object == (Double) this.price) {
            return price;
        }
        if (object == (Integer) this.quantity) {
            return quantity;
        }
        return object;
    }

}
