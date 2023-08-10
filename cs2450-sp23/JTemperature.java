/**
 * Name: Escubido, Jarisse
 * Project: #1-1
 * Due: February 10, 2023
 * Course: cs-2450-01-sp23
 * 
 * Description:
 * 		Simple application that converts temperature from Fahrenheit
 * 			to Celsius using Java Swing GUI
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JTemperature implements ActionListener
{
	// Global Variables
	JLabel tempC;
	JTextField tempF;
	
	JTemperature() 
	{	
		// Create new JFrame container.
		JFrame frame = new JFrame("Temperature Converter");
		// Set frame size.
		frame.setSize(240, 120);
		
		// Specify type of layout for the layout manager
		frame.setLayout(new BorderLayout());
		
		// Terminate program when user clicks close button.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Display the frame.
		frame.setVisible(true);
		
		// Create the components.
		tempF = new JTextField(10);
		
		JLabel labelName = new JLabel("by. J. Escubido", JLabel.CENTER);
		JLabel labelEnter = new JLabel("Enter: ");
		JLabel labelFahrenheit = new JLabel("degrees F");
		tempC = new JLabel("- degrees C", JLabel.RIGHT);
		
		// Add an action listener.
		tempF.addActionListener(this);

		// Add components to the content pane.
		frame.add(labelName, BorderLayout.NORTH);
		frame.add(labelEnter, BorderLayout.WEST);
		frame.add(labelFahrenheit, BorderLayout.EAST);
		frame.add(tempF, BorderLayout.CENTER);
		frame.add(tempC, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		// Using try-catch to handle action events
		try
		{
			double input = Double.parseDouble(tempF.getText());
			double result = Math.round(5 * (input - 32) / 9);
			tempC.setText("= " + result + " degrees C");
			
		}
		catch(NumberFormatException e) 
		{
			tempC.setText("Invalid Input");
		}
	}
	
	public static void main(String args[]) 
	{
		
		// Create the frame on event dispatching thread.
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				new JTemperature();
			}
		});
	}
}
