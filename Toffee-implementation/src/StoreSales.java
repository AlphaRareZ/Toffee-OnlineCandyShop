import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;
import java.util.*;

public class StoreSales {
    private static ArrayList<Bill> sales = new ArrayList<Bill>();

    public static ArrayList<Bill> getSales() {
        return sales;
    }

    public static void setSales(ArrayList<Bill> sales) {
        StoreSales.sales = sales;
    }

    /**
     * function that exports the bills into DATABASE
     *
     * @param bill the bill that is wanted to be added
     */
    public void writeIntoBills(Bill bill) {
        File file = new File("src\\Bills.txt");
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(bill.getCustomer().id + ' ');
            for (Product i : bill.getProducts()) {
                writer.write(i.getName() + ' ');
            }
            writer.write(bill.getDate() + " -" + '\n');
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * function of the total payment of all bills
     *
     * @return the total payment of all bills that all the customers have generated
     */
    public double totalPayment() {
        double tot = 0.0;
        for (Bill i : sales) {
            tot += i.totalPayment();
        }
        return tot;
    }

    /**
     * function that generates the total payment of all bills that have the selected product
     * @param product the product that is wanted to get it's total purchase price amount
     * @return total payment of all bills that have the selected product
     */
    public double totalPayment(Product product) {
        double tot = 0.0;
        for (Bill sale : sales) {
            for (int j = 0; j < sale.getProducts().size(); j++) {
                if (sale.getProducts().get(j) == product) {
                    tot += product.getPrice();
                }
            }
        }
        return tot;
    }

    /**
     * function that generates the total amount of money that the customer has already Purchased
     * @param customer the targeted customer
     * @return total amount of money that the customer has already Purchased
     */
    public double totalPayment(Customer customer) {
        double tot = 0.0;
        for (Bill sale : sales) {
            if (sale.getCustomer() == customer) {
                tot += sale.totalPayment();
            }
        }
        return tot;
    }

    /**
     * function that adds bill into all bills that has been generated
     * @param bill the bill that is wanted to be added
     */
    public void addBill(Bill bill) {
        sales.add(bill);
        SystemData.getBills().add(bill);
        writeIntoBills(bill);
    }
}
