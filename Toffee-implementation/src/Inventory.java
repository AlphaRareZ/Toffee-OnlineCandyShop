import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Inventory {
    public ArrayList<Product> inventoryProducts = new ArrayList<Product>();

    public Inventory(ArrayList<Product> inventoryProducts) {
        this.inventoryProducts = inventoryProducts;
    }

    public Inventory() {
        readFromProducts();
    }

    /**
     * function that imports products from DATABASE
     */
    public void readFromProducts() {
        // clear the existing and read from somewhere
        inventoryProducts.clear();//C:\\Users\\mohamed alaa\\Desktop\\TofeeClone\\untitled\\src\\ArrayOfProducts.txt **old path
        File read = new File("src\\ArrayOfProducts.txt");
        ArrayList<String> a = new ArrayList<String>();
        int n = 0;
        try {
            Scanner scanner = new Scanner(read);
            while (scanner.hasNext()) {
                n++;
                String data = scanner.next();
                a.add(data);
                if (n == 4) {
                    n = 0;
                    Product product = new Product(a.get(0), Double.parseDouble(a.get(1)), a.get(2), Integer.parseInt(a.get(3)));
                    inventoryProducts.add(product);
                    a.clear();
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * function that exports the products into DATABASE
     */
    public void writeIntoProducts() {
        // clear file and write in it the new changes
        try {//C:\\Users\\mohamed alaa\\Desktop\\TofeeClone\\untitled\\src\\ArrayOfProducts.txt ** old path
            FileWriter writer = new FileWriter("src\\ArrayOfProducts.txt");
            for (Product i : inventoryProducts) {
                writer.write(i.getName() + ' ' + i.getPrice() + ' ' + i.getType() + ' ' + i.getQuantity() + '\n');
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setInventoryProducts(ArrayList<Product> inventoryProducts) {
        this.inventoryProducts = inventoryProducts;
    }

    public ArrayList<Product> getInventoryProducts() {
        return inventoryProducts;
    }

    /**
     * function that sorts products by any of its attributes
     *
     * @param condition the attribute
     * @return ArrayList that holds the sorted products array
     */
    public ArrayList<Product> sortBy(String condition) {
        ArrayList<Product> sortedinventoryProducts = new ArrayList<Product>();
        switch (condition.toLowerCase()) {
            case "name" -> {
                ArrayList<String> sorted = new ArrayList<String>();
                for (Product item : inventoryProducts) {
                    sorted.add(item.getName());
                }
                Collections.sort(sorted);
                for (int i = 0; i < sorted.size(); i++) {
                    for (int j = 0; j < sorted.size(); j++) {
                        if (Objects.equals(sorted.get(i), inventoryProducts.get(j).getName())) {
                            sortedinventoryProducts.add(inventoryProducts.get(j));
                            break;
                        }
                    }
                }
            }
            case "price" -> {
                ArrayList<Double> sortedDoubles = new ArrayList<Double>();
                for (Product item : inventoryProducts) {
                    sortedDoubles.add(item.getPrice());
                }
                Collections.sort(sortedDoubles);
                for (int i = 0; i < sortedDoubles.size(); i++) {
                    for (int j = 0; j < sortedDoubles.size(); j++) {
                        if (sortedDoubles.get(i) == inventoryProducts.get(j).getPrice()) {
                            sortedinventoryProducts.add(inventoryProducts.get(j));
                            break;
                        }
                    }
                }
            }
            case "quantity" -> {
                ArrayList<Integer> sortedIntegers = new ArrayList<Integer>();
                for (Product item : inventoryProducts) {
                    sortedIntegers.add(item.getQuantity());
                }
                Collections.sort(sortedIntegers);
                for (int i = 0; i < sortedIntegers.size(); i++) {
                    for (int j = 0; j < sortedIntegers.size(); j++) {
                        if (sortedIntegers.get(i) == inventoryProducts.get(j).getQuantity()) {
                            sortedinventoryProducts.add(inventoryProducts.get(j));
                            break;
                        }
                    }
                }
            }
        }
        return sortedinventoryProducts;
    }

    /**
     * fucntion that displays all products in the inventory
     */
    public void display() {
        for (Product item : inventoryProducts) {
            System.out.println(item.getName() + " , " + item.getPrice() + " LE , " + item.getType() + " , " + item.getQuantity() + " PCS ");
        }
    }
    /**
     * function that searches for a all products that matches a specific schema
     * @param attribute the key that holds the type of search
     * @param value     value of the key (attribute)
     * @return ArrayList of products selected by its attribute
     */

    public ArrayList<Product> searchBy(String attribute, Object value) {
        ArrayList<Product> newinventoryProducts = new ArrayList<Product>();
        if (attribute.equalsIgnoreCase("name")) {
            for (Product item : inventoryProducts) {
                if (item.getName() == value) {
                    newinventoryProducts.add(item);
                }
            }
        }
        if (attribute.equalsIgnoreCase("price")) {
            for (Product item : inventoryProducts) {
                if (item.getPrice() == (double) value) {
                    newinventoryProducts.add(item);
                }
            }
        }
        if (attribute.equalsIgnoreCase("type")) {

            if (((String) value).equalsIgnoreCase("kilo") || ((String) value).equalsIgnoreCase("bykilo")) {
                for (Product item : inventoryProducts) {
                    if (item.getType() == Product.type.BYKILO) {
                        newinventoryProducts.add(item);
                    }
                }
            }

            if (((String) value).equalsIgnoreCase("package") || ((String) value).equalsIgnoreCase("bypackage")) {
                for (Product item : inventoryProducts) {
                    if (item.getType() == Product.type.BYPACKAGE) {
                        newinventoryProducts.add(item);
                    }
                }
            }
        }
        if (attribute.equalsIgnoreCase("quantity")) {
            for (Product item : inventoryProducts) {
                if (item.getQuantity() == (int) value) {
                    newinventoryProducts.add(item);
                }
            }
        }
        return newinventoryProducts;
    }

    /**
     * function that adds product into the inventory
     * @param product the product that is wanted to be added
     */
    public void addProduct(Product product) {
        inventoryProducts.add(product);
        System.out.println(product.getName() + " has been added successfully into inventory");
    }

    /**
     * function that removes product from inventory
     * @param product product that is wanted to be removed
     */
    public void removeProduct(Product product) {
        for (Product item : inventoryProducts) {
            if (Objects.equals(item.getName(), product.getName()) && item.getType() == product.getType()) {
                inventoryProducts.remove(product);
                System.out.println(product.getName() + " has been removed successfully");
                this.writeIntoProducts();
                return;
            }
        }
        System.out.println("this product can't be found in the inventory");
    }


}
