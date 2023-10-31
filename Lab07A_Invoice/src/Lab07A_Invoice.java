import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Lab07A_Invoice {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Invoice Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField();

        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField();

        JButton addItemButton = new JButton("Add Line Item");
        JTextArea invoiceTextArea = new JTextArea();

        List<LineItem> lineItems = new ArrayList<>();

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a LineItem, Product, and add it to the invoice
                Product product = new Product("Product Name", 10.0); // Replace with actual product info
                LineItem lineItem = new LineItem(product, 5); // Replace with actual quantity
                lineItems.add(lineItem);

                // Update the invoice text area
                double totalAmount = calculateTotalAmount(lineItems);
                invoiceTextArea.setText(generateInvoiceText(titleField.getText(), addressField.getText(), lineItems, totalAmount));
            }
        });

        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(addressLabel);
        panel.add(addressField);
        panel.add(addItemButton);
        panel.add(new JLabel());
        panel.add(invoiceTextArea);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static double calculateTotalAmount(List<LineItem> lineItems) {
        double total = 0;
        for (LineItem item : lineItems) {
            total += item.getTotal();
        }
        return total;
    }

    private static String generateInvoiceText(String title, String address, List<LineItem> lineItems, double totalAmount) {
        StringBuilder sb = new StringBuilder();
        sb.append("Invoice Title: ").append(title).append("\n");
        sb.append("Customer Address: ").append(address).append("\n");
        sb.append("Line Items:\n");
        for (LineItem item : lineItems) {
            sb.append(item.getProduct().getName()).append("\t")
                    .append("Quantity: ").append(item.getQuantity()).append("\t")
                    .append("Total: $").append(item.getTotal()).append("\n");
        }
        sb.append("Total Amount Due: $").append(totalAmount);
        return sb.toString();
    }
}
