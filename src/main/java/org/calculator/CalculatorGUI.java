package org.calculator;

import javax.swing.*;
import java.awt.*;

public class CalculatorGUI extends JFrame {
    private JTextField inputField;
    private JTextField resultField;
    private Calculator calculator;

    public CalculatorGUI() {
        calculator = new Calculator();

        // Apply Nimbus Look and Feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Scientific Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel label = new JLabel("Enter number:");
        label.setFont(new Font("Arial", Font.BOLD, 14));
        inputField = new JTextField(10);
        inputField.setFont(new Font("Arial", Font.PLAIN, 14));

        resultField = new JTextField(15);
        resultField.setFont(new Font("Arial", Font.BOLD, 14));
        resultField.setEditable(false);
        resultField.setBackground(Color.LIGHT_GRAY);

        JButton sqrtButton = createStyledButton("√x");
        JButton factorialButton = createStyledButton("x!");
        JButton logButton = createStyledButton("ln(x)");
        JButton powerButton = createStyledButton("x^b");

        sqrtButton.addActionListener(e -> computeResult("sqrt"));
        factorialButton.addActionListener(e -> computeResult("fact"));
        logButton.addActionListener(e -> computeResult("log"));
        powerButton.addActionListener(e -> computeResult("power"));

        gbc.gridx = 0; gbc.gridy = 0; add(label, gbc);
        gbc.gridx = 1; gbc.gridy = 0; add(inputField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; add(sqrtButton, gbc);
        gbc.gridx = 1; gbc.gridy = 1; add(factorialButton, gbc);
        gbc.gridx = 0; gbc.gridy = 2; add(logButton, gbc);
        gbc.gridx = 1; gbc.gridy = 2; add(powerButton, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        add(resultField, gbc);

        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(70, 130, 180)); // Steel Blue
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    private void computeResult(String operation) {
        try {
            double num = Double.parseDouble(inputField.getText());
            double result = 0;

            switch (operation) {
                case "sqrt": result = calculator.squareRoot(num); break;
                case "fact": result = calculator.factorial((int) num); break;
                case "log": result = calculator.log(num); break;
                case "power":
                    double exponent = Double.parseDouble(JOptionPane.showInputDialog("Enter exponent:"));
                    result = calculator.power(num, exponent);
                    break;
            }
            resultField.setText(String.valueOf(result));
        } catch (Exception e) {
            resultField.setText("Invalid Input!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculatorGUI::new);
    }
}
