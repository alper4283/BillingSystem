import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BillingSystemGUI extends JFrame {
    private BillingSystem billingSystem;
    private JTextArea customerArea;
    private JTextArea itemArea;
    private JTextArea billArea;
    private List<Bill> bills;

    public BillingSystemGUI() {
        billingSystem = new BillingSystem();
        bills = new ArrayList<>();
        loadInitialData();

        setTitle("Billing System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("View Customers", createCustomerViewPanel());
        tabbedPane.addTab("View Items", createItemViewPanel());
        tabbedPane.addTab("Add Customer", createAddCustomerPanel());
        tabbedPane.addTab("Add Item", createAddItemPanel());
        tabbedPane.addTab("Modify Customer", createModifyCustomerPanel());
        tabbedPane.addTab("Make Purchase", createPurchasePanel());
        tabbedPane.addTab("View Bills", createViewBillsPanel());

        add(tabbedPane);

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

    private JPanel createCustomerViewPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        customerArea = new JTextArea(20, 50);
        customerArea.setEditable(false);
        updateCustomerArea();

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCustomerArea();
            }
        });

        panel.add(new JScrollPane(customerArea), BorderLayout.CENTER);
        panel.add(refreshButton, BorderLayout.SOUTH);
        return panel;
    }

    private void updateCustomerArea() {
        customerArea.setText("Customers:\n");
        for (Customer customer : billingSystem.getCustomers()) {
            customerArea.append(customer.toString() + "\n");
        }
    }

    private JPanel createItemViewPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        itemArea = new JTextArea(20, 50);
        itemArea.setEditable(false);
        updateItemArea();

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateItemArea();
            }
        });

        panel.add(new JScrollPane(itemArea), BorderLayout.CENTER);
        panel.add(refreshButton, BorderLayout.SOUTH);
        return panel;
    }

    private void updateItemArea() {
        itemArea.setText("Items:\n");
        for (Item item : billingSystem.getItems()) {
            itemArea.append(item.toString() + "\n");
        }
    }

    private JPanel createAddCustomerPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));

        JLabel idLabel = new JLabel("Customer ID:");
        JTextField idField = new JTextField();
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JButton addButton = new JButton("Add Customer");

        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                String email = emailField.getText();
                Customer customer = new Customer(id, name, email);
                billingSystem.addCustomer(customer);
                JOptionPane.showMessageDialog(panel, "Customer added successfully!");
                updateCustomerArea();
            }
        });

        return panel;
    }

    private JPanel createAddItemPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel nameLabel = new JLabel("Item Name:");
        JTextField nameField = new JTextField();
        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField();
        JButton addButton = new JButton("Add Item");

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(priceLabel);
        panel.add(priceField);
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                Item item = new Item(name, price);
                billingSystem.addItem(item);
                JOptionPane.showMessageDialog(panel, "Item added successfully!");
                updateItemArea();
            }
        });

        return panel;
    }

    private JPanel createModifyCustomerPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2));

        JLabel idLabel = new JLabel("Customer ID:");
        JTextField idField = new JTextField();
        JLabel nameLabel = new JLabel("New Name:");
        JTextField nameField = new JTextField();
        JLabel emailLabel = new JLabel("New Email:");
        JTextField emailField = new JTextField();
        JButton modifyButton = new JButton("Modify Customer");

        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(modifyButton);

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                Customer customer = billingSystem.findCustomerById(id);
                if (customer != null) {
                    String newName = nameField.getText();
                    String newEmail = emailField.getText();
                    customer.setName(newName);
                    customer.setEmail(newEmail);
                    JOptionPane.showMessageDialog(panel, "Customer modified successfully!");
                    updateCustomerArea();
                } else {
                    JOptionPane.showMessageDialog(panel, "Customer not found!");
                }
            }
        });

        return panel;
    }

    private JPanel createPurchasePanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));

        JLabel customerIdLabel = new JLabel("Customer ID:");
        JTextField customerIdField = new JTextField();
        JLabel itemNameLabel = new JLabel("Item Name:");
        JTextField itemNameField = new JTextField();
        JButton purchaseButton = new JButton("Purchase Item");

        panel.add(customerIdLabel);
        panel.add(customerIdField);
        panel.add(itemNameLabel);
        panel.add(itemNameField);
        panel.add(purchaseButton);

        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerId = customerIdField.getText();
                String itemName = itemNameField.getText();
                Customer customer = billingSystem.findCustomerById(customerId);
                Item item = null;
                for (Item i : billingSystem.getItems()) {
                    if (i.getName().equals(itemName)) {
                        item = i;
                        break;
                    }
                }

                if (customer != null && item != null) {
                    Bill bill = null;
                    for (Bill b : bills) {
                        if (b.getCustomer().getId().equals(customerId)) {
                            bill = b;
                            break;
                        }
                    }

                    if (bill == null) {
                        bill = new Bill(customer);
                        bills.add(bill);
                    }

                    bill.addItem(item);
                    JOptionPane.showMessageDialog(panel, "Item purchased successfully!");
                    updateBillArea();
                } else {
                    JOptionPane.showMessageDialog(panel, "Customer or item not found!");
                }
            }
        });

        return panel;
    }

    private JPanel createViewBillsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        billArea = new JTextArea(20, 50);
        billArea.setEditable(false);
        updateBillArea();

        panel.add(new JScrollPane(billArea), BorderLayout.CENTER);
        return panel;
    }

    private void updateBillArea() {
        billArea.setText("Bills:\n");
        for (Bill bill : bills) {
            billArea.append(bill.toString() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BillingSystemGUI());
    }
}