import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    private int targetNumber;
    private int numberOfGuesses;

    private JTextField guessTextField;
    private JLabel resultLabel;

    public NumberGuessingGame() {
        // Generate a random number between 1 and 100
        Random random = new Random();
        targetNumber = random.nextInt(100) + 1;
        numberOfGuesses = 0;

        // Set up the UI components
        JLabel titleLabel = new JLabel("Number Guessing Game");
        JLabel instructionsLabel = new JLabel("Guess a number between 1 and 100:");
        guessTextField = new JTextField(10);
        JButton guessButton = new JButton("Guess");
        resultLabel = new JLabel("");

        // Set up event handling for the guess button
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int userGuess = Integer.parseInt(guessTextField.getText());
                    checkGuess(userGuess);
                    numberOfGuesses++;
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid input. Please enter a number.");
                }
            }
        });

        // Set up the layout
        setLayout(new FlowLayout());
        add(titleLabel);
        add(instructionsLabel);
        add(guessTextField);
        add(guessButton);
        add(resultLabel);

        // Set frame properties
        setTitle("Number Guessing Game");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void checkGuess(int userGuess) {
        if (userGuess < targetNumber) {
            resultLabel.setText("Too low. Try again!");
        } else if (userGuess > targetNumber) {
            resultLabel.setText("Too high. Try again!");
        } else {
            resultLabel.setText("Congratulations! You guessed the number in " + numberOfGuesses + " guesses.");
            guessTextField.setEnabled(false); // Disable the text field after correct guess
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGame().setVisible(true);
            }
        });
    }
}
