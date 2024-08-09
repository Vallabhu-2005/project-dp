import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Ayurveda extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ayurvedic"; 
    private static final String DB_USER = "root"; 
    private static final String DB_PASSWORD = "vallabhu"; 

    private JTextField indIdField, indNameField, ageField, genderField, historyField;
    private JTextField disIdField, disNameField, descriptionField, symptomsField, otherNamesField;
    private JTextField forIdField, forNameField, forDescriptionField, sourceField, typeField, propertiesField, suitabilityField, nonSuitabilityField;
    private JTextField herbIdField, botNameField, herbNameField, herbDescriptionField, herbOtherNamesField;
    private JTextField recIdField, recHerbIdField, recIndIdField, recDisIdField, recForIdField;

    public Ayurveda() {
        setTitle("Ayurvedic and Formulation Recommendation System");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Individual Panel
        JPanel individualPanel = new JPanel(new GridLayout(6, 2));
        indIdField = new JTextField();
        indNameField = new JTextField();
        ageField = new JTextField();
        genderField = new JTextField();
        historyField = new JTextField();
        individualPanel.add(new JLabel("Individual ID:"));
        individualPanel.add(indIdField);
        individualPanel.add(new JLabel("Name:"));
        individualPanel.add(indNameField);
        individualPanel.add(new JLabel("Age:"));
        individualPanel.add(ageField);
        individualPanel.add(new JLabel("Gender:"));
        individualPanel.add(genderField);
        individualPanel.add(new JLabel("History:"));
        individualPanel.add(historyField);
        JButton indSubmitButton = new JButton("Submit");
        indSubmitButton.addActionListener(new InsertIndividualAction());
        individualPanel.add(indSubmitButton);

        // Diseases Panel
        JPanel diseasesPanel = new JPanel(new GridLayout(6, 2));
        disIdField = new JTextField();
        disNameField = new JTextField();
        descriptionField = new JTextField();
        symptomsField = new JTextField();
        otherNamesField = new JTextField();
        diseasesPanel.add(new JLabel("Disease ID:"));
        diseasesPanel.add(disIdField);
        diseasesPanel.add(new JLabel("Name:"));
        diseasesPanel.add(disNameField);
        diseasesPanel.add(new JLabel("Description:"));
        diseasesPanel.add(descriptionField);
        diseasesPanel.add(new JLabel("Symptoms:"));
        diseasesPanel.add(symptomsField);
        diseasesPanel.add(new JLabel("Other Names:"));
        diseasesPanel.add(otherNamesField);
        JButton disSubmitButton = new JButton("Submit");
        disSubmitButton.addActionListener(new InsertDiseaseAction());
        diseasesPanel.add(disSubmitButton);

        // Formulation Panel
        JPanel formulationPanel = new JPanel(new GridLayout(9, 2));
        forIdField = new JTextField();
        forNameField = new JTextField();
        forDescriptionField = new JTextField();
        sourceField = new JTextField();
        typeField = new JTextField();
        propertiesField = new JTextField();
        suitabilityField = new JTextField();
        nonSuitabilityField = new JTextField();
        formulationPanel.add(new JLabel("Formulation ID:"));
        formulationPanel.add(forIdField);
        formulationPanel.add(new JLabel("Name:"));
        formulationPanel.add(forNameField);
        formulationPanel.add(new JLabel("Description:"));
        formulationPanel.add(forDescriptionField);
        formulationPanel.add(new JLabel("Source:"));
        formulationPanel.add(sourceField);
        formulationPanel.add(new JLabel("Type:"));
        formulationPanel.add(typeField);
        formulationPanel.add(new JLabel("Properties:"));
        formulationPanel.add(propertiesField);
        formulationPanel.add(new JLabel("Suitability:"));
        formulationPanel.add(suitabilityField);
        formulationPanel.add(new JLabel("Non-Suitability:"));
        formulationPanel.add(nonSuitabilityField);
        JButton forSubmitButton = new JButton("Submit");
        forSubmitButton.addActionListener(new InsertFormulationAction());
        formulationPanel.add(forSubmitButton);

        // Single Herbs Panel
        JPanel herbsPanel = new JPanel(new GridLayout(6, 2));
        herbIdField = new JTextField();
        botNameField = new JTextField();
        herbNameField = new JTextField();
        herbDescriptionField = new JTextField();
        herbOtherNamesField = new JTextField();
        herbsPanel.add(new JLabel("Herb ID:"));
        herbsPanel.add(herbIdField);
        herbsPanel.add(new JLabel("Botanical Name:"));
        herbsPanel.add(botNameField);
        herbsPanel.add(new JLabel("Name:"));
        herbsPanel.add(herbNameField);
        herbsPanel.add(new JLabel("Description:"));
        herbsPanel.add(herbDescriptionField);
        herbsPanel.add(new JLabel("Other Names:"));
        herbsPanel.add(herbOtherNamesField);
        JButton herbSubmitButton = new JButton("Submit");
        herbSubmitButton.addActionListener(new InsertHerbAction());
        herbsPanel.add(herbSubmitButton);

        // Recipes Panel
        JPanel recipesPanel = new JPanel(new GridLayout(6, 2));
        recIdField = new JTextField();
        recHerbIdField = new JTextField();
        recIndIdField = new JTextField();
        recDisIdField = new JTextField();
        recForIdField = new JTextField();
        recipesPanel.add(new JLabel("Recipe ID:"));
        recipesPanel.add(recIdField);
        recipesPanel.add(new JLabel("Herb ID:"));
        recipesPanel.add(recHerbIdField);
        recipesPanel.add(new JLabel("Individual ID:"));
        recipesPanel.add(recIndIdField);
        recipesPanel.add(new JLabel("Disease ID:"));
        recipesPanel.add(recDisIdField);
        recipesPanel.add(new JLabel("Formulation ID:"));
        recipesPanel.add(recForIdField);
        JButton recSubmitButton = new JButton("Submit");
        recSubmitButton.addActionListener(new InsertRecipeAction());
        recipesPanel.add(recSubmitButton);

        tabbedPane.addTab("Individual", individualPanel);
        tabbedPane.addTab("Diseases", diseasesPanel);
        tabbedPane.addTab("Formulation", formulationPanel);
        tabbedPane.addTab("Single Herbs", herbsPanel);
        tabbedPane.addTab("Recipes", recipesPanel);

        add(tabbedPane);
    }

    private class InsertIndividualAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String query = "INSERT INTO individual VALUES (?,?,?,?,?)";
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = connection.prepareStatement(query)) {

                pstmt.setLong(1, Long.parseLong(indIdField.getText()));
                pstmt.setString(2, indNameField.getText());
                pstmt.setInt(3, Integer.parseInt(ageField.getText()));
                pstmt.setString(4, genderField.getText());
                pstmt.setString(5, historyField.getText());
                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Individual data inserted successfully.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error inserting individual data.");
            }
        }
    }

    private class InsertDiseaseAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String query = "INSERT INTO diseases VALUES (?, ?, ?, ?, ?)";
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = connection.prepareStatement(query)) {

                pstmt.setInt(1, Integer.parseInt(disIdField.getText()));
                pstmt.setString(2, disNameField.getText());
                pstmt.setString(3, descriptionField.getText());
                pstmt.setString(4, symptomsField.getText());
                pstmt.setString(5, otherNamesField.getText());
                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Disease data inserted successfully.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error inserting disease data.");
            }
        }
    }

    private class InsertFormulationAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String query = "INSERT INTO formulation VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = connection.prepareStatement(query)) {

                pstmt.setInt(1, Integer.parseInt(forIdField.getText()));
                pstmt.setString(2, forNameField.getText());
                pstmt.setString(3, forDescriptionField.getText());
                pstmt.setString(4, sourceField.getText());
                pstmt.setString(5, typeField.getText());
                pstmt.setString(6, propertiesField.getText());
                pstmt.setString(7, suitabilityField.getText());
                pstmt.setString(8, nonSuitabilityField.getText());
                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Formulation data inserted successfully.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error inserting formulation data.");
            }
        }
    }

    private class InsertHerbAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String query = "INSERT INTO singleherbs VALUES (?, ?, ?, ?, ?)";
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = connection.prepareStatement(query)) {

                pstmt.setInt(1, Integer.parseInt(herbIdField.getText()));
                pstmt.setString(2, botNameField.getText());
                pstmt.setString(3, herbNameField.getText());
                pstmt.setString(4, herbDescriptionField.getText());
                pstmt.setString(5, herbOtherNamesField.getText());
                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Herb data inserted successfully.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error inserting herb data.");
            }
        }
    }

    private class InsertRecipeAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String query = "INSERT INTO recipes VALUES (?, ?, ?, ?, ?)";
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = connection.prepareStatement(query)) {

                pstmt.setInt(1, Integer.parseInt(recIdField.getText()));
                pstmt.setInt(2, Integer.parseInt(recHerbIdField.getText()));
                pstmt.setLong(3, Long.parseLong(recIndIdField.getText()));
                pstmt.setInt(4, Integer.parseInt(recDisIdField.getText()));
                pstmt.setInt(5, Integer.parseInt(recForIdField.getText()));
                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Recipe data inserted successfully.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error inserting recipe data.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Ayurveda app = new Ayurveda();
            app.setVisible(true);
        });
    }
}