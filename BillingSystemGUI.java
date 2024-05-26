import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BillingSystemGUI extends JFrame {
    private BillingSystem billingSystem;

    public BillingSystemGUI() {
        billingSystem = new BillingSystem();
        loadInitialData();

        setTitle("Billing System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        JTextArea customerArea = new JTextArea(10, 30);
        customerArea.setText("Customers:\n");
        for (Customer customer : billingSystem.getCustomers()) {
            customerArea.append(customer.toString() + "\n");
        }

        JTextArea itemArea = new JTextArea(10, 30);
        itemArea.setText("Items:\n");
        for (Item item : billingSystem.getItems()) {
            itemArea.append(item.toString() + "\n");
        }

        panel.add(new JScrollPane(customerArea));
        panel.add(new JScrollPane(itemArea));

        add(panel);

        setVisible(true);
    }

    private void loadInitialData() {
        try {
            billingSystem.loadCustomers("customers.txt");
            billingSystem.loadItems("items.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BillingSystemGUI());
    }
}
