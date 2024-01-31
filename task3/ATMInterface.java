import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMInterface extends JFrame {
    private String userId = "3215";
    private String pin = "3232";
    private double balance = 10000.0;

    private JTextField userIdField;
    private JPasswordField pinField;
    private JButton loginButton;

    public ATMInterface() {
        // Set up the UI components
        JLabel userIdLabel = new JLabel("User ID:");
        JLabel pinLabel = new JLabel("PIN:");

        userIdField = new JTextField(10);
        pinField = new JPasswordField(10);
        loginButton = new JButton("Login");

        // Set up event handling for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        // Set up the layout for the login panel
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        loginPanel.add(userIdLabel);
        loginPanel.add(userIdField);
        loginPanel.add(pinLabel);
        loginPanel.add(pinField);
        loginPanel.add(new JLabel()); // Placeholder
        loginPanel.add(loginButton);

        // Set up the main frame
        setLayout(new BorderLayout());
        add(loginPanel, BorderLayout.CENTER);

        // Set frame properties
        setTitle("ATM Interface");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void login() {
        String enteredUserId = userIdField.getText();
        String enteredPin = new String(pinField.getPassword());

        if (enteredUserId.equals(userId) && enteredPin.equals(pin)) {
            showMainMenu();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid user ID or PIN. Please try again.", "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showMainMenu() {
        // Create a new frame for the main menu
        JFrame mainMenuFrame = new JFrame("ATM Main Menu");
        mainMenuFrame.setSize(400, 300);
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setLocationRelativeTo(null);

        // Create main menu components
        JButton viewBalanceButton = new JButton("View Balance");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton quitButton = new JButton("Quit");

        // Set up event handling for main menu buttons
        viewBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(mainMenuFrame, "Balance: $" + balance, "View Balance",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String depositAmountString = JOptionPane.showInputDialog(mainMenuFrame, "Enter deposit amount:");
                try {
                    double depositAmount = Double.parseDouble(depositAmountString);
                    if (depositAmount > 0) {
                        balance += depositAmount;
                        JOptionPane.showMessageDialog(mainMenuFrame, "Deposit successful. New balance: $" + balance,
                                "Deposit", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(mainMenuFrame, "Invalid deposit amount.", "Deposit Failed",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainMenuFrame, "Invalid input. Please enter a valid number.",
                            "Deposit Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String withdrawAmountString = JOptionPane.showInputDialog(mainMenuFrame, "Enter withdrawal amount:");
                try {
                    double withdrawAmount = Double.parseDouble(withdrawAmountString);
                    if (withdrawAmount > 0 && withdrawAmount <= balance) {
                        balance -= withdrawAmount;
                        JOptionPane.showMessageDialog(mainMenuFrame, "Withdrawal successful. New balance: $" + balance,
                                "Withdraw", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(mainMenuFrame, "Invalid withdrawal amount or insufficient funds.",
                                "Withdrawal Failed", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainMenuFrame, "Invalid input. Please enter a valid number.",
                            "Withdrawal Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Set up the layout for the main menu panel
        JPanel mainMenuPanel = new JPanel(new GridLayout(4, 1));
        mainMenuPanel.add(viewBalanceButton);
        mainMenuPanel.add(depositButton);
        mainMenuPanel.add(withdrawButton);
        mainMenuPanel.add(quitButton);

        // Add the main menu panel to the main menu frame
        mainMenuFrame.add(mainMenuPanel);

        // Make the main menu frame visible
        mainMenuFrame.setVisible(true);

        // Close the login frame
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATMInterface().setVisible(true);
            }
        });
    }
}
