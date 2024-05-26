import java.util.HashMap;
import java.util.Map;

public class Bill {
    private Customer customer;
    private Map<Item, Integer> itemQuantities; // Map to store items and their quantities
    private double totalAmount;

    public Bill(Customer customer) {
        this.customer = customer;
        this.itemQuantities = new HashMap<>();
        this.totalAmount = 0.0;
    }

    public void addItem(Item item, int quantity) {
        itemQuantities.put(item, itemQuantities.getOrDefault(item, 0) + quantity);
        totalAmount += item.getPrice() * quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Map<Item, Integer> getItemQuantities() {
        return itemQuantities;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer: \n");
        sb.append(customer.toString());
        sb.append("\n\nItems Purchased:\n");
        for (Map.Entry<Item, Integer> entry : itemQuantities.entrySet()) {
            Item item = entry.getKey();
            int quantity = entry.getValue();
            sb.append(item.getName()).append(" (Quantity: ").append(quantity).append(")\n");
            sb.append("Price per unit: ").append(item.getPrice()).append("\n");
            sb.append("Total Price: ").append(item.getPrice() * quantity).append("\n\n");
        }
        sb.append(String.format("\nTotal Amount: $%.2f", totalAmount));
        return sb.toString();
    }
}
