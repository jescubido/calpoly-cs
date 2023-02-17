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
    JButton operator[];
    JButton operand;
    
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

        // Create JPanel for keypad buttons.
        JPanel keypad = new JPanel();
        keypad.setLayout(new GridLayout(4,4));

        char[] btnLabels = {'7','8','9','/',
                            '4','5','6','X',
                            '1','2','3','-',
                            '0','C','=','+'};

         // Creating JButtons using for-loop and adding to JPanel.
         for(int i = 0; i < 16; i++)
         {
            JButton operand = new JButton("" + btnLabels[i]);
            keypad.add(operand); 
            operand.addActionListener(this);
         }
         
        // Add components to content pane.
        frame.add(screen, BorderLayout.NORTH);
        frame.add(keypad, BorderLayout.CENTER);
        
        // Display the frame at center.
		frame.setVisible(true);
        frame.setLocationRelativeTo(null);


    }

    public void actionPerformed(ActionEvent ae)
	{
        System.out.println(ae.getActionCommand());	
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
//Teset