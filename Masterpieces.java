import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Masterpieces extends JFrame {
    private JTextField customerIdField, customerNameField, customerCategoryField;
    private JTextField paintingIdField, paintingNameField, artistNameField, themeField, rentalPriceField;
    
    public Masterpieces() {
        setTitle("Masterpieces Ltd - Painting Rental System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Customer Panel
        JPanel customerPanel = new JPanel(new GridLayout(4, 2));
        customerIdField = new JTextField();
        customerNameField = new JTextField();
        customerCategoryField = new JTextField();
        customerPanel.add(new JLabel("Customer ID:"));
        customerPanel.add(customerIdField);
        customerPanel.add(new JLabel("Name:"));
        customerPanel.add(customerNameField);
        customerPanel.add(new JLabel("Category (B/S/G/P):"));
        customerPanel.add(customerCategoryField);
        JButton customerSubmitButton = new JButton("Submit");
        customerSubmitButton.addActionListener(new InsertCustomerAction());
        customerPanel.add(customerSubmitButton);

        // Painting Panel
        JPanel paintingPanel = new JPanel(new GridLayout(5, 2));
        paintingIdField = new JTextField();
        paintingNameField = new JTextField();
        artistNameField = new JTextField();
        themeField = new JTextField();
        rentalPriceField = new JTextField();
        paintingPanel.add(new JLabel("Painting ID:"));
        paintingPanel.add(paintingIdField);
        paintingPanel.add(new JLabel("Name:"));
        paintingPanel.add(paintingNameField);
        paintingPanel.add(new JLabel("Artist Name:"));
        paintingPanel.add(artistNameField);
        paintingPanel.add(new JLabel("Theme:"));
        paintingPanel.add(themeField);
        paintingPanel.add(new JLabel("Rental Price:"));
        paintingPanel.add(rentalPriceField);
        JButton paintingSubmitButton = new JButton("Submit");
        paintingSubmitButton.addActionListener(new InsertPaintingAction());
        paintingPanel.add(paintingSubmitButton);

        tabbedPane.addTab("Customer", customerPanel);
        tabbedPane.addTab("Painting", paintingPanel);

        add(tabbedPane);
    }

    private class InsertCustomerAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Implement insertion of customer data to the database
            JOptionPane.showMessageDialog(null, "Customer data inserted successfully.");
        }
    }

    private class InsertPaintingAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Implement insertion of painting data to the database
            JOptionPane.showMessageDialog(null, "Painting data inserted successfully.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Masterpieces app = new Masterpieces();
            app.setVisible(true);
        });
    }
}
