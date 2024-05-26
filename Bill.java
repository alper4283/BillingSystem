import java.util.ArrayList;
import java.util.List;

public class Bill {
    private Customer customer;
    private List<Item> items;
    private double totalAmount;

    public Bill(Customer customer) {
        this.customer = customer;
        this.items = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    public void addItem(Item item) {
        items.add(item);
        totalAmount += item.getPrice();
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "Bill [customer=" + customer + ", items=" + items + ", totalAmount=" + totalAmount + "]";
    }
}

