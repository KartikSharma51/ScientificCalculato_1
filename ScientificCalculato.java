import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ScientificCalculator extends JFrame implements ActionListener {
    // Create a text field for input and display
    JTextField textField;

    // Create buttons for the calculator
    JButton[] buttons;

    // Create a string to store the current input
    String input = "";

    public ScientificCalculator() {
        // Set up the JFrame
        setTitle("Scientific Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        // Create and configure the text field
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEditable(false);
        add(textField, BorderLayout.NORTH);

        // Create an array of button labels
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C", "sqrt", "x^2", "1/x"
        };

        // Create and configure the button grid
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4));

        // Create buttons and add them to the panel
        buttons = new JButton[buttonLabels.length];
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            buttons[i].addActionListener(this);
            buttonPanel.add(buttons[i]);
        }

        // Add the button panel to the JFrame
        add(buttonPanel, BorderLayout.CENTER);

        // Make the JFrame visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Handle the "C" button (clear)
        if (command.equals("C")) {
            input = "";
            textField.setText("");
        }

        // Handle the "=" button (calculate)
        else if (command.equals("=")) {
            try {
                input = evaluateExpression(input);
                textField.setText(input);
            } catch (Exception ex) {
                textField.setText("Error");
            }
        }

        // Handle other buttons
        else {
            input += command;
            textField.setText(input);
        }
    }

    // Function to evaluate the expression
    private String evaluateExpression(String expression) {
        try {
            // You can implement your evaluation logic here using libraries like
            // javax.script.ScriptEngine or implement your own parsing and calculation.
            // For simplicity, we'll just use JavaScript engine for evaluation here.
            javax.script.ScriptEngine engine = new javax.script.ScriptEngineManager().getEngineByName("JavaScript");
            return engine.eval(expression).toString();
        } catch (Exception e) {
            return "Error";
        }
    }

    public static void main(String[] args) {
        new ScientificCalculator();
    }
}