import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class BillingSystem {

    private List<Customer> customers;
    private List<Item> items;

    public BillingSystem() {
        customers = new ArrayList<>();
        items = new ArrayList<>();
    }

    public void loadCustomers(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) { 
                    String[] data = line.split(",");
                    if (data.length == 3) { 
                        Customer customer = new Customer(data[0], data[1], data[2]);
                        customers.add(customer);
                    } else {
                        System.err.println("Invalid format in customers.txt: " + line);
                    }
                }
            }
        }
    }
    
    

    public void loadItems(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) { 
                    String[] data = line.split(",");
                    if (data.length == 2) { 
                        Item item = new Item(data[0], Double.parseDouble(data[1]));
                        items.add(item);
                    } else {
                        System.err.println("Invalid format in items.txt: " + line);
                    }
                }
            }
        }
    }
    

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Customer findCustomerById(String id) {
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        return null;
    }

    
    public void saveData(String customersFileName, String itemsFileName) throws IOException {
        saveCustomers(customersFileName);
        saveItems(itemsFileName);
    }

    private void saveCustomers(String fileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Customer customer : customers) {
                writer.println(customer.getId() + "," + customer.getName() + "," + customer.getEmail());
            }
        }
    }

    private void saveItems(String fileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Item item : items) {
                writer.println(item.getName() + "," + item.getPrice());
            }
        }
    }

    

}
