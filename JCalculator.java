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
    JButton[] operator = new JButton[6];
    JButton[] operand = new JButton[10];

    
    
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
        screen = new JLabel(" ");
        screen.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        // Create operand buttons.
        

        // Create operator buttons.
        operator[0] = new JButton("/");
        operator[1] = new JButton("x");
        operator[2] = new JButton("-");
        operator[3] = new JButton("c");
        operator[4] = new JButton("=");
        operator[5] = new JButton("+");

        for(int i = 0; i < 6; i++)
        {
            operator[i].addActionListener(this);
        }

        for(int i = 0; i < 10; i++)
        {
            operand[i] = new JButton(String.valueOf(i));
            operand[i].addActionListener(this);
        }

        // Create JPanel for keypad buttons.
        JPanel keypad = new JPanel();
        keypad.setLayout(new GridLayout(4,4));

        // Add operand and operator buttons to JPanel.
        keypad.add(operand[7]);
        keypad.add(operand[8]);
        keypad.add(operand[9]);
        keypad.add(operator[0]);

        keypad.add(operand[4]);
        keypad.add(operand[5]);
        keypad.add(operand[6]);
        keypad.add(operator[1]);

        keypad.add(operand[1]);
        keypad.add(operand[2]);
        keypad.add(operand[3]);
        keypad.add(operator[2]);

        keypad.add(operand[0]);
        keypad.add(operator[3]);
        keypad.add(operator[4]);
        keypad.add(operator[5]);
        

        // Add components to content pane.
        frame.add(screen, BorderLayout.NORTH);
        frame.add(keypad, BorderLayout.CENTER);
        
        // Display the frame at center.
		frame.setVisible(true);
        frame.setLocationRelativeTo(null);


    }

    public void actionPerformed(ActionEvent ae)
	{
        
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