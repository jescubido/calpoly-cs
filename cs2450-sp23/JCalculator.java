/**
 * Name: Escubido, Jarisse
 * Project: #2
 * Due: 20 February 2023
 * Course: cs-2450-01-sp23
 * 
 * Description:
 *      Implementing a simple integer calculator application.
 */

 import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JCalculator implements ActionListener 
{
    // Global variables.
    JFrame frame;
    JPanel keypad;
    JLabel screen;
    JButton[] number = new JButton[10];
    JButton division, multiplication, subtraction, clear, equalSign, addition;
    
    String result;
    String operator;
    int operand1 = 0;
    int operand2 = 0;
    int temp;
    
    
    JCalculator() 
    {
        // Create a new frame.
        frame = new JFrame("Calculator");
        frame.setSize(250,300);    

        // Terminate program when user clicks close button.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add image as application icon.
        frame.setIconImage(new ImageIcon("JCalculator.png").getImage());
        
        // Create JLabel that shows integer inputs
        screen = new JLabel("", JLabel.RIGHT);
        screen.setBorder(BorderFactory.createLineBorder(Color.black, 1)); 
        
        // Create JPanel for keypad buttons.
        JPanel keypad = new JPanel();
        keypad.setLayout(new GridLayout(4,4));
        
        // Create number buttons.
        for (int i = 0; i < 10; i++)
        {
            number[i] = new JButton(String.valueOf(i));
            number[i].addActionListener(this);
            keypad.add(number[i]);
        }
        
        // Create operator buttons.
        JButton division = new JButton("/");
        JButton multiplication = new JButton("x");
        JButton subtraction = new JButton("-");
        JButton equalSign = new JButton("=");
        JButton addition = new JButton("+");
        JButton clear = new JButton("C");
        
        // Set mnemonic key to clear button.
        clear.setMnemonic('C');
        
        // Set equal sign as default button.
        frame.getRootPane().setDefaultButton(equalSign);
        
        // Add ActionListener to every operator button.
        division.addActionListener(this);
        multiplication.addActionListener(this);
        subtraction.addActionListener(this);
        clear.addActionListener(this);
        equalSign.addActionListener(this);
        addition.addActionListener(this);
        
        // Add number and function buttons to JPanel.
        keypad.add(number[7]);
        keypad.add(number[8]);
        keypad.add(number[9]);
        keypad.add(division);
        
        keypad.add(number[4]);
        keypad.add(number[5]);
        keypad.add(number[6]);
        keypad.add(multiplication);
        
        keypad.add(number[1]);
        keypad.add(number[2]);
        keypad.add(number[3]);
        keypad.add(subtraction);
        
        keypad.add(number[0]);
        keypad.add(clear);
        keypad.add(equalSign);
        keypad.add(addition);
        
        // Add components to content pane.
        frame.add(screen, BorderLayout.NORTH);
        frame.add(keypad, BorderLayout.CENTER);
        
        // Display and center frame.
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if ((ActionEvent.CTRL_MASK & ae.getModifiers()) != 0)
        {
            screen.setText("(C) Jarisse Escubido");
         }
        
         for (int i = 0; i < 10; i++)
        {
            if (ae.getSource() == number[i])
            {
                screen.setText(screen.getText().concat(String.valueOf(i)));
            }
        }
        
        JButton division = (JButton)ae.getSource();
        if(division.getText().equals("/"))
        {
            operand1 = Integer.parseInt(screen.getText());
            System.out.println(screen.getText());
            operator = "/";
            screen.setText("");
        }
        
        JButton multiplication = (JButton)ae.getSource();
        if(multiplication.getText().equals("x"))
        {
            operand1 = Integer.parseInt(screen.getText());
            System.out.println(screen.getText());
            operator = "x";
            screen.setText("");
        }
        
        JButton subtraction = (JButton)ae.getSource();
        if(subtraction.getText().equals("-"))
        {
            operand1 = Integer.parseInt(screen.getText());
            System.out.println(screen.getText());
            operator = "-";
            screen.setText("");
        }
        
        JButton clear = (JButton)ae.getSource();
        if(clear.getText().equals("C"))
        {
            screen.setText("");
        }
        
        JButton addition = (JButton)ae.getSource();
        if(addition.getText().equals("+"))
        {
            operand1 = Integer.parseInt(screen.getText());
            System.out.println(screen.getText());
            operator = "+";
            screen.setText("");
        }
        
        JButton equalSign = (JButton)ae.getSource();
        if(equalSign.getText().equals("="))
        {
            if (operand1 >= 8 && operand2 >= 8)
            {
                screen.setText("Overflow");
            }
            
            operand2 = Integer.parseInt(screen.getText());
            if (operator == "/")
            {
                // Throw an error when dividing by 0.
                if (operand2 == 0)
                {
                    result = String.valueOf(temp);
                    screen.setText("Div by 0 ERROR");
                }
                
                else
                {
                    temp = operand1 / operand2;
                    result = String.valueOf(temp);
                    screen.setText(result);
                }
            }
            
            if (operator == "x")
            {
                temp = operand1 * operand2;
                result = String.valueOf(temp);
                screen.setText(result);
            }
            
            if (operator == "-")
            {
                temp = operand1 - operand2;
                result = String.valueOf(temp);
                screen.setText(result);
            }
            
            if (operator == "+")
            {
                temp = operand1 + operand2;
                result = String.valueOf(temp);
                screen.setText("" + result);
            }
            System.out.println(screen);
        }
    }
    public static void main(String args[]) 
    {
        // Create the frame on event dispatching thread.
        SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new JCalculator();
            }
        });
    }
}