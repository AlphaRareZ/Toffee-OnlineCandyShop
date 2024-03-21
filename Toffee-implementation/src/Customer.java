import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class Customer extends Human {
    private String address, password;
    private int loyaltyPoints, otp;
    private Cart shoppingCart;
    private WishList wishList;

    Customer(String name, String id, String password, String email, String telephone, String address, int loyaltyPoints) {
        super(name, id, email, telephone);
        this.address = address;
        this.password = password;
        this.loyaltyPoints = loyaltyPoints;
        this.shoppingCart = new Cart();
        wishList = new WishList();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public int getOtp() {
        return otp;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
        try {
            FileWriter file = new FileWriter("src\\WishLists.txt", true);
            file.write(this.id + " ");
            for (Product item : wishList.getWantedProducts()) {
                file.write(item.getName() + " ");
            }
            file.write(" -");
            file.write("\n");
            SystemData.wishLists.add(wishList);
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public WishList getWishList() {
        return wishList;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = Math.max(loyaltyPoints, 0);
    }

    public Cart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(Cart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * generates a bill from shopping cart products
     * @return the bill for these products
     */
    public Bill generateBill() {
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        return new Bill(this, this.getShoppingCart().products, date, true);
    }

    /**
     * function that adds product into wish list
     * @param product the product that is wanted to be added
     */
    public void addProductIntoWishlist(Product product) {
        wishList.addProduct(product);
    }
}
