package junior.projects;

import javax.swing.*;
import java.awt.*;

public class Calculator {

    Calculator() {
        JFrame frame = new JFrame("Calculator");
        frame.setSize(450, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Displays numbers entered
        JTextField textfield = new JTextField("hello");
        textfield.setEditable(false);

        // Creating the operation buttons
        JButton addButton = new JButton("+");
        addButton.setPreferredSize(new Dimension(100, 100));
        JButton subButton = new JButton("-");
        subButton.setPreferredSize(new Dimension(100, 100));
        JButton divButton = new JButton("/");
        divButton.setPreferredSize(new Dimension(100, 100));
        JButton mulButton = new JButton("x");
        mulButton.setPreferredSize(new Dimension(100, 100));
        JButton percButton = new JButton("%");
        percButton.setPreferredSize(new Dimension(100, 100));
        JButton clearButton = new JButton("c");
        clearButton.setPreferredSize(new Dimension(100, 100));
        JButton decButton = new JButton(".");
        decButton.setPreferredSize(new Dimension(100, 100));
        JButton equalsButton = new JButton("=");

        // Creating number buttons
        JButton one = new JButton("1");
        one.setPreferredSize(new Dimension(100, 100));
        JButton two = new JButton("2");
        two.setPreferredSize(new Dimension(100, 100));
        JButton three = new JButton("3");
        three.setPreferredSize(new Dimension(100, 100));
        JButton four = new JButton("4");
        four.setPreferredSize(new Dimension(100, 100));
        JButton five = new JButton("5");
        five.setPreferredSize(new Dimension(100, 100));
        JButton six = new JButton("6");
        six.setPreferredSize(new Dimension(100, 100));
        JButton seven = new JButton("7");
        seven.setPreferredSize(new Dimension(100, 100));
        JButton eight = new JButton("8");
        eight.setPreferredSize(new Dimension(100, 100));
        JButton nine = new JButton("9");
        nine.setPreferredSize(new Dimension(100, 100));
        JButton zero = new JButton("0");

        // Panel for all buttons

        /*
         * c / x %
         * 7 8 9 -
         * 4 5 6 +
         * 1 2 3 =
         * 0 . =
         */

        JPanel numPad = new JPanel(new GridLayout(0, 1));

        // Panel for regular sized buttons
        JPanel regularButtons = new JPanel(new GridLayout(4, 4));
        regularButtons.add(clearButton);
        regularButtons.add(divButton);
        regularButtons.add(mulButton);
        regularButtons.add(percButton);

        regularButtons.add(seven);
        regularButtons.add(eight);
        regularButtons.add(nine);
        regularButtons.add(subButton);

        regularButtons.add(four);
        regularButtons.add(five);
        regularButtons.add(six);
        regularButtons.add(addButton);

        regularButtons.add(one);
        regularButtons.add(two);
        regularButtons.add(three);

        // Panel for special dimensioned buttons
        JPanel specialButtons = new JPanel(new GridLayout(1, 3));
        specialButtons.add(zero);
        specialButtons.add(decButton);
        specialButtons.add(equalsButton);

        numPad.add(regularButtons, BorderLayout.NORTH);
        numPad.add(specialButtons, BorderLayout.SOUTH);

        // Add components to content pane
        frame.add(textfield);
        frame.add(numPad, BorderLayout.CENTER);

        // set frame to visible
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator());
    }

}