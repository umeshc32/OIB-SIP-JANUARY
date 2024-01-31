import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class LoginForm extends JFrame {
    private Map<String, String> userCredentials;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    private JTextField basicDetailsField;
    private JTextField trainNumberField;
    private JTextField classTypeField;
    private JTextField dateField;
    private JTextField fromField;
    private JTextField toField;

    private JTextField pnrField;

    public LoginForm() {
        userCredentials = new HashMap<>();
        userCredentials.put("umeshch", "3232"); // Sample user credentials

        setTitle("Online Reservation System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        createLoginForm();
        createReservationForm();
        createCancellationForm();

        add(mainPanel);
        setVisible(true);
    }

    private void createLoginForm() {
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                if (validateLogin(username, password)) {
                    cardLayout.show(mainPanel, "reservation");
                } else {
                    JOptionPane.showMessageDialog(LoginForm.this, "Invalid username or password");
                }
            }
        });

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);

        mainPanel.add(loginPanel, "login");
    }

    private void createReservationForm() {
        JPanel reservationPanel = new JPanel(new GridLayout(7, 2));
        JLabel basicDetailsLabel = new JLabel("Basic Details:");
        JLabel trainNumberLabel = new JLabel("Train Number:");
        JLabel classTypeLabel = new JLabel("Class Type:");
        JLabel dateLabel = new JLabel("Date of Journey:");
        JLabel fromLabel = new JLabel("From (Place):");
        JLabel toLabel = new JLabel("To (Destination):");
        JButton insertButton = new JButton("Insert");

        basicDetailsField = new JTextField();
        trainNumberField = new JTextField();
        classTypeField = new JTextField();
        dateField = new JTextField();
        fromField = new JTextField();
        toField = new JTextField();

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Insert logic here (dummy implementation)
                JOptionPane.showMessageDialog(LoginForm.this, "Reservation successful!");
                clearReservationFields();
            }
        });

        reservationPanel.add(basicDetailsLabel);
        reservationPanel.add(basicDetailsField);
        reservationPanel.add(trainNumberLabel);
        reservationPanel.add(trainNumberField);
        reservationPanel.add(classTypeLabel);
        reservationPanel.add(classTypeField);
        reservationPanel.add(dateLabel);
        reservationPanel.add(dateField);
        reservationPanel.add(fromLabel);
        reservationPanel.add(fromField);
        reservationPanel.add(toLabel);
        reservationPanel.add(toField);
        reservationPanel.add(insertButton);

        mainPanel.add(reservationPanel, "reservation");
    }

    private void createCancellationForm() {
        JPanel cancellationPanel = new JPanel(new GridLayout(2, 2));
        JLabel pnrLabel = new JLabel("PNR Number:");
        pnrField = new JTextField();
        JButton cancelButton = new JButton("Cancel");

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cancellation logic here (dummy implementation)
                String pnr = pnrField.getText();
                String reservationDetails = getReservationDetailsByPNR(pnr);

                if (reservationDetails != null) {
                    int choice = JOptionPane.showConfirmDialog(
                        LoginForm.this,
                            "Are you sure you want to cancel this reservation?\n" + reservationDetails,
                            "Confirm Cancellation",
                            JOptionPane.OK_CANCEL_OPTION
                    );

                    if (choice == JOptionPane.OK_OPTION) {
                        // Perform cancellation
                        JOptionPane.showMessageDialog(LoginForm.this, "Cancellation successful!");
                        clearCancellationFields();
                    }
                } else {
                    JOptionPane.showMessageDialog(LoginForm.this, "Invalid PNR number");
                }
            }
        });

        cancellationPanel.add(pnrLabel);
        cancellationPanel.add(pnrField);
        cancellationPanel.add(cancelButton);

        mainPanel.add(cancellationPanel, "cancellation");
    }

    private boolean validateLogin(String username, String password) {
        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
    }

    private String getReservationDetailsByPNR(String pnr) {
        // Dummy method to retrieve reservation details
        // In a real system, this would involve querying a database
        // For demonstration purposes, let's assume a reservation map
        Map<String, String> reservations = new HashMap<>();
        reservations.put("PNR123", "Train: ABC123, Class: First, Date: 2024-02-01, From: CityA, To: CityB");
        reservations.put("PNR456", "Train: XYZ789, Class: Second, Date: 2024-02-05, From: CityC, To: CityD");

        return reservations.get(pnr);
    }

    private void clearReservationFields() {
        basicDetailsField.setText("");
        trainNumberField.setText("");
        classTypeField.setText("");
        dateField.setText("");
        fromField.setText("");
        toField.setText("");
    }

    private void clearCancellationFields() {
        pnrField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginForm::new);
    }
}
