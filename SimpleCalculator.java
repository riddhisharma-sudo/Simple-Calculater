import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator extends JFrame {

    private JTextField inputField;
    private JButton[] numberButtons;
    private JButton[] operationButtons;
    private JButton equalsButton;
    private JButton clearButton;
    private JTextArea resultArea;

    private String currentInput = "";

    public SimpleCalculator() {
        setTitle("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 24));
        inputField.setHorizontalAlignment(JTextField.RIGHT);
        inputField.setEditable(false);

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        numberButtons = new JButton[10];
        operationButtons = new JButton[4];

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            final int number = i;
            numberButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    onNumberButtonClick(number);
                }
            });
        }

        String[] operationLabels = {"+", "-", "*", "/"};
        for (int i = 0; i < 4; i++) {
            operationButtons[i] = new JButton(operationLabels[i]);
            operationButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            final String operation = operationLabels[i];
            operationButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    onOperationButtonClick(operation);
                }
            });
        }

        equalsButton = new JButton("=");
        equalsButton.setFont(new Font("Arial", Font.PLAIN, 18));
        equalsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateResult();
            }
        });

        clearButton = new JButton("C");
        clearButton.setFont(new Font("Arial", Font.PLAIN, 18));
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInput();
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        for (int i = 1; i <= 9; i++) {
            buttonPanel.add(numberButtons[i]);
        }
        buttonPanel.add(operationButtons[0]);
        buttonPanel.add(numberButtons[0]);
        for (int i = 1; i < 4; i++) {
            buttonPanel.add(operationButtons[i]);
        }
        buttonPanel.add(equalsButton);
        buttonPanel.add(clearButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputField, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(resultArea, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
    }

    private void onNumberButtonClick(int number) {
        currentInput += number;
        inputField.setText(currentInput);
    }

    private void onOperationButtonClick(String operation) {
        if (!currentInput.isEmpty()) {
            currentInput += " " + operation + " ";
            inputField.setText(currentInput);
        }
    }

    private void calculateResult() {
        if (!currentInput.isEmpty()) {
            String[] parts = currentInput.split(" ");
            if (parts.length == 3) {
                double operand1 = Double.parseDouble(parts[0]);
                double operand2 = Double.parseDouble(parts[2]);
                String operation = parts[1];

                double result = 0;

                switch (operation) {
                    case "+":
                        result = operand1 + operand2;
                        break;
                    case "-":
                        result = operand1 - operand2;
                        break;
                    case "*":
                        result = operand1 * operand2;
                        break;
                    case "/":
                        result = operand1 / operand2;
                        break;
                }

                resultArea.setText(parts[0] + " " + operation + " " + parts[2] + " = " + result);
                currentInput = "";
                inputField.setText("");
            }
        }
    }

    private void clearInput() {
        currentInput = "";
        inputField.setText("");
        resultArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SimpleCalculator().setVisible(true);
            }
        });
    }
}
