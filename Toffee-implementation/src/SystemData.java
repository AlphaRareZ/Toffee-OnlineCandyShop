import java.io.Closeable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class SystemData {
    // constructors
    SystemData() {
        inventory.readFromProducts();
        readCustomers();
        readFromBills();
        readFromWishLists();
    }


    // attributes
    protected static ArrayList<WishList> wishLists = new ArrayList<WishList>();
    private static ArrayList<Orders> orders = new ArrayList<Orders>();
    private static ArrayList<Customer> customers = new ArrayList<Customer>();
    private static ArrayList<Bill> bills = new ArrayList<Bill>();
    static protected Inventory inventory = new Inventory();
    private static StoreSales storeSales = new StoreSales();
    protected static final ArrayList<Admin> admins = new ArrayList<Admin>();
    ArrayList<DeliveryPerson> deliveryGuys = new ArrayList<DeliveryPerson>();
    static Scanner scanner = new Scanner(System.in);

    // setters and getters
    public static void setCustomers(ArrayList<Customer> customers) {
        SystemData.customers = customers;
    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    public static void setBills(ArrayList<Bill> bills) {
        SystemData.bills = bills;
    }

    public static ArrayList<Admin> getAdmins() {
        return admins;
    }

    public void setDeliveryGuys(ArrayList<DeliveryPerson> deliveryGuys) {
        this.deliveryGuys = deliveryGuys;
    }

    public static ArrayList<Bill> getBills() {
        return bills;
    }

    public void setOrders(ArrayList<Orders> orders) {
        SystemData.orders = orders;
    }

    public void setStoreSales(StoreSales storeSales) {
        SystemData.storeSales = storeSales;
    }

    public void setWishLists(ArrayList<WishList> wishLists) {
        SystemData.wishLists = wishLists;
    }

    public ArrayList<DeliveryPerson> getDeliveryGuys() {
        return deliveryGuys;
    }

    public ArrayList<Orders> getOrders() {
        return orders;
    }

    public ArrayList<WishList> getWishLists() {
        return wishLists;
    }

    public StoreSales getStoreSales() {
        return storeSales;
    }

    public Inventory getInventory() {
        return inventory;
    }

    // methods

    /**
     * function that imports customers from DATABASE
     */
    private void readCustomers() {
        ArrayList<String> a = new ArrayList<String>();
        int n = 0;
        File reader = new File("src\\Customers.txt");
        try {
            Scanner scanner1 = new Scanner(reader);
            while (scanner1.hasNext()) {
                a.add(scanner1.next());
                n++;//1 ,2,3,4,5,6
                if (n == 7) {
                    n = 0;
                    Customer customer = new Customer(a.get(0), a.get(1), a.get(2), a.get(3), a.get(4), a.get(5), Integer.parseInt(a.get(6)));
                    customers.add(customer);
                    a.clear();
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * function that exports customers into DATABASE
     */
    private void writeIntoCustomers() {
        FileWriter reader = null;
        try {
            reader = new FileWriter("src\\Customers.txt");
            for (Customer i : customers) {
                reader.write(i.name + ' ' + i.id + ' ' + i.getPassword() + ' ' + i.email + ' ' + i.telephone + ' ' + i.getAddress() + ' ' + i.getLoyaltyPoints() + '\n');
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * function that imports BILLS from DATABASE
     */
    private void readFromBills() {
        bills.clear();
        StoreSales.getSales().clear();
        ArrayList<String> a = new ArrayList<String>();
        int n = 0;
        File reader = new File("src\\Bills.txt");
        try {
            Scanner scanner1 = new Scanner(reader);
            while (scanner1.hasNext()) {
                String t = scanner1.next();
                if (Objects.equals(t, "-")) {
                    ArrayList<Product> products = new ArrayList<>();
                    for (int i = 1; i < a.size() - 1; i++) {
                        for (Product product : inventory.inventoryProducts) {
                            if (Objects.equals(a.get(i), product.getName())) {
                                products.add(product);
                                break;
                            }
                        }
                    }
                    for (Customer i : customers) {
                        if (Objects.equals(i.id, a.get(0))) {
                            // BILL
                            Bill tempBill = new Bill(i, products, a.get(a.size() - 1), false);
                            bills.add(tempBill);
                            StoreSales.getSales().add(tempBill);
                        }
                    }
                    a.clear();
                    continue;
                }
                a.add(t);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * function that imports WISHLISTS from DATABASE
     */
    private void readFromWishLists() {
        ArrayList<String> test = new ArrayList<String>();
        String stop = "-";
        File reader = new File("src\\WishLists.txt");
        try {
            Scanner sc = new Scanner(reader);
            while (sc.hasNext()) {
                test.add(sc.next());
                if (test.contains(stop)) {
                    WishList newWishList = new WishList();
                    for (String item : test) {
                        for (int i = 0; i < inventory.getInventoryProducts().size(); i++) {
                            if (item.equals(inventory.getInventoryProducts().get(i).getName())) {
                                newWishList.addProduct(inventory.getInventoryProducts().get(i));
                            }
                        }
                    }
                    wishLists.add(newWishList);
                    newWishList.getWantedProducts().clear();
                    test.clear();
                }
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
    }

    /**
     * function that adds new customer into DATABASE
     *
     * @param customer the customer that is wanted to be added
     */
    private void addCustomerIntoFile(Customer customer) {
        File file = new File("src\\Customers.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file, true);
            fr.write('\n' + customer.name + ' ' + customer.id + ' ' + customer.getPassword() + ' ' + customer.email + ' ' + customer.telephone + ' ' + customer.getAddress() + ' ' + customer.getLoyaltyPoints());
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * fucntion that displays the statistics of TOFFEE sales
     */
    public void makeReport() {
        System.out.println("Operating System: " + System.getProperty("os.name"));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("This report made in : " + dtf.format(now));
        System.out.println("///////////////////////////////////////////////////////");
        System.out.println("Number of customers: " + customers.size());
        System.out.println("State of happiness from customers: " + Math.floor(Math.random() * (100 - 85 + 1) + 85));
        double TotalPurchase = 0;
        for (int i = 0; i < bills.size(); i++) {
            TotalPurchase += bills.get(i).totalPayment();
        }
        System.out.println("Profit = TotalPurchase - FixedCost = " + (TotalPurchase - 2500) + "L.E");
        System.out.println("The FixedCost contains: ");
        System.out.println("Salaries: " + 250 + "L.E");
        System.out.println("Fix issues: " + 100 + "L.E");
        System.out.println("claim Products from their Producers " + 2150 + "L.E");
        System.out.println("Products Scheduled(to be Purchase): ");
        System.out.print("[");
        for (int i = 0; i < 4; i++) {
            System.out.print("Toffee " + (i + 1) + ", ");
        }
        System.out.println("Toffee " + 5 + "]");

        int mostQuantity = inventory.getInventoryProducts().get(0).getQuantity();
        String name = inventory.getInventoryProducts().get(0).getName();

        for (int i = 1; i < inventory.getInventoryProducts().size(); i++) {
            if (mostQuantity < inventory.getInventoryProducts().get(i).getQuantity()) {
                mostQuantity = inventory.getInventoryProducts().get(i).getQuantity();
                name = inventory.getInventoryProducts().get(i).getName();
            }
        }
        System.out.println("Most demand product is " + name + " with quantity of " + mostQuantity);

    }

    public void addCustomer() {
        String name, ID, password, email, telephone, address;
        System.out.println("Enter Name");
        name = scanner.next();
        System.out.println("Enter ID");
        ID = scanner.next();
        System.out.println("Enter Password");
        password = scanner.next();
        System.out.println("Enter Email");
        email = scanner.next();
        System.out.println("Enter Telephone Number");
        telephone = scanner.next();
        System.out.println("Enter Address:");
        address = scanner.next();
        for (Customer i : SystemData.getCustomers()) {
            if (Objects.equals(i.getId(), ID)) {
                System.out.println("FAILED SIGNUP CUSTOMER WITH ID " + ID + "  HAS BEEN ALREADY REGISTERED");
                return;
            }
        }
        Customer customer = new Customer(name, ID, password, email, telephone, address, 0);
        customers.add(customer);
        addCustomerIntoFile(customer);
    }

    /**
     * function that makes an order for the current customer
     *
     * @param customerIndex the index of customer in the Array of Customers
     */
    private void order(int customerIndex) {
        System.out.println("Enter Number Of Wanted Products");
        int z = scanner.nextInt();
        while (z != 0) {
            System.out.println("Enter Name of Product and Quantity respectively");
            String name = scanner.next();
            int quantity = scanner.nextInt();
            for (int i = 0; i < inventory.inventoryProducts.size(); i++) {
                if (inventory.inventoryProducts.get(i).getName().equalsIgnoreCase(name)) {
                    while (quantity > inventory.inventoryProducts.get(i).getQuantity()) {
                        System.out.println("ONLY " + inventory.inventoryProducts.get(i).getQuantity() + " IN STOCK ,Please Enter Sufficient Quantity: ");
                        quantity = scanner.nextInt();
                    }
                    customers.get(customerIndex).getShoppingCart().addProduct(inventory.getInventoryProducts().get(i), quantity);
                }
            }
            z--;
        }
    }

    /**
     * function that shows cart and checks if the user wants to CHECKOUT
     * @param customer the current customer that wants to order
     */
    private void showCart(Customer customer) {
        ArrayList<Product> taken = new ArrayList<>();
        for (Product item : customer.getShoppingCart().getProducts()) {
            if (taken.contains(item)) continue;
            int counter = 0;
            for (Product i : customer.getShoppingCart().getProducts()) {
                if (i == item) {
                    counter++;
                }
            }
            taken.add(item);
            System.out.println(item.getName() + " , " + counter + "PCS , Total = " + item.getPrice() * counter);
        }
        if (taken.size() >= 1) {
            System.out.println("With TOTAL PAYMENT : " + customer.getShoppingCart().totalPayment());
            System.out.println("\nCONFIRM PURCHASE (Y/N)?");
            char c = scanner.next().charAt(0);
            if (c == 'Y' || c == 'y') {
                Bill generatedBill = customer.generateBill();
                storeSales.addBill(generatedBill);
                for (int i = 0; i < customers.size(); i++) {
                    if (customers.get(i).getId().equals(customer.getId())) {
                        customers.get(i).setLoyaltyPoints(customers.get(i).getLoyaltyPoints() + customer.getShoppingCart().getProducts().size());
                        writeIntoCustomers();
                        break;
                    }
                }
                customer.getShoppingCart().products.clear();
                inventory.writeIntoProducts();
            } else {
                return;
            }
        } else {
            System.out.println("Your cart is empty");
            return;
        }
    }

    /**
     * function that displays all products from inventory
     * @param customerIndex customer index in array of customers
     */
    private void browseProducts(int customerIndex) {
        inventory.readFromProducts();
        inventory.display();
        while (true) {
            System.out.println("___");
            System.out.println("1.Add To Cart       2.Previous Page");
            int n;
            n = scanner.nextInt();
            if (n == 1) {
                order(customerIndex);
            } else if (n == 2) {
                return;
            }
        }

    }

    /**
     * The GUI to the customer
     */
    public void main_page() {
        while (true) {
            int choice;
            String id, password;
            System.out.flush();
            System.out.println("1.Sign in   2.Sign up   3.Exit");
            choice = scanner.nextInt();
            int customerIndex = -1;
            if (choice == 1) {
                int c;
                System.out.println("Enter ID");
                id = scanner.next();
                System.out.println("1.Enter Password    2.Forget Password");
                c = scanner.nextInt();
                if (c == 1) {
                    System.out.println("Enter Password");
                    password = scanner.next();
                    for (int i = 0; i < customers.size(); i++) {
                        if (Objects.equals(customers.get(i).getId(), id)) {
                            customerIndex = i;
                            String p = customers.get(i).getPassword();
                            while (!p.equals(password)) {
                                System.out.println("Invalid Password, Enter Password: ");
                                password = scanner.next();
                            }
                            break;
                        }
                    }
                } else {
                    for (int i = 0; i < customers.size(); i++) {
                        if (Objects.equals(customers.get(i).getId(), id)) {
                            OTPSender.sendOTP(customers.get(i).getEmail(), customers.get(i));
                            int otp;
                            String newPassword;
                            System.out.println("ENTER OTP: ");
                            otp = scanner.nextInt();
                            while (otp != customers.get(i).getOtp()) {
                                System.out.println("ENTER VALID OTP: ");
                                otp = scanner.nextInt();
                            }
                            System.out.println("ENTER NEW PASSWORD");
                            newPassword = scanner.next();
                            customers.get(i).setPassword(newPassword);
                            System.out.println("PASSWORD Has Been Set Sucessfully!");
                            writeIntoCustomers();
                            main_page();
                            return;
                        }
                    }
                }
                if (customerIndex == -1) {
                    System.out.println("THIS CUSTOMER IS NOT ALREADY REGISTERED");
                    continue;
                }
                // LOGGED IN
            } else if (choice == 2) {
                addCustomer();
                System.out.println("___________________________________");
                continue;
            } else {
                System.out.println("THANKS FOR USING OUR APPLICATION");
                return;
            }
            System.out.println("___");
            System.out.println("HELLO, " + customers.get(customerIndex).getName());
            while (true) {
                // profile -> data , show wishlist ,
                System.out.println("1.Browse Products    2.Show Orders    3.Show Cart  4.Profile   5.LogOut");
                choice = scanner.nextInt();
                if (choice == 1) {
                    browseProducts(customerIndex);
                } else if (choice == 2) {
                    showOrders(customers.get(customerIndex));
                } else if (choice == 3) {
                    showCart(customers.get(customerIndex));
                } else if (choice == 4) {
                    showProfile(customers.get(customerIndex));
                } else if (choice == 5) {
                    break;
                }
            }
        }
    }

    /**
     * function that shows profile data of a customer
     * @param customer customer
     */
    private void showProfile(Customer customer) {
        System.out.println();
        System.out.println("Name: " + customer.getName());
        System.out.println("Email: " + customer.getEmail());
        System.out.println("Address: " + customer.getAddress());
        System.out.println("Telephone: " + customer.getTelephone());
//		System.out.println(customer.getWishList().getWantedProducts().isEmpty() ? "You currently don't have a wishlist do you want to make one?(y|n)" : "show your wishList:(y|n)");
        File file = new File("src\\WishLists.txt");
        try {
            Scanner sc = new Scanner(file);
            String testID = customer.getId();
            while (sc.hasNext()) {
                if (Objects.equals(sc.next(), testID)) {
                    System.out.println("your wishList: ");
//					wishLists.get(Integer.parseInt(customer.getId()) - 1).displayWantedProducts();
                    String current = sc.next();
                    while (!current.equals("-")) {
                        System.out.println(current + " ");
                        current = sc.next();
                    }
                    return;
                }
            }
            System.out.println("You currently don't have a wishList. do you want to create one?(y|n)");
            Scanner scanner = new Scanner(System.in);
            char c = scanner.next().charAt(0);
            if (c == 'y' || c == 'Y') {
                WishList newWishList = new WishList();
                SystemData.inventory.display();
                System.out.println();
                System.out.println("Enter Number Of Wanted Products");
                int z = scanner.nextInt();
                while (z != 0) {
                    System.out.println("Enter Name of Product");
                    String name = scanner.next();
                    for (int i = 0; i < inventory.inventoryProducts.size(); i++) {
                        if (inventory.inventoryProducts.get(i).getName().equalsIgnoreCase(name)) {
                            newWishList.addProduct(inventory.inventoryProducts.get(i));
                        }
                    }
                    z--;
                }
                customer.setWishList(newWishList);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * function that shows orders of the current customer
     * @param customer customer
     */
    private void showOrders(Customer customer) {
        readFromBills();
        int n = 1;
        for (Bill bill : bills) {
            if (bill.getCustomer() == customer) {
                bill.displayDetails(n);
                n++;
            }
        }
    }


}
