import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
                String[] data = line.split(",");
                Customer customer = new Customer(data[0], data[1], data[2]);
                customers.add(customer);
            }
        }
    }

    public void loadItems(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Item item = new Item(data[0], Double.parseDouble(data[1]));
                items.add(item);
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
    

}
