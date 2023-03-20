/**
 * Name:    Escubido, Jarisse
 * Quiz#:    7
 * Due:     March 20, 2023 @ 11:00 AM
 * Course:  cs-2450-01-sp23
 * 
 * Description:
 *          A Swing Application that executes a 60-second countdown timer. 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class CountdownTimer
{
   private Timer timer;

    CountdownTimer()
    {
        // Create frame.
        JFrame frame = new JFrame("Timer");
        frame.setSize(300,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("T - ", JLabel.CENTER);
        label.setFont(new Font("Courier New", Font.PLAIN, 20));
        timer = new Timer(60000, (ae) ->
        {
            label.setText("T - ");
        });
        timer.setRepeats(false);
        timer.start();

        // Create menubar.
        JMenuBar menubar = new JMenuBar();

        JMenu actionMenu = new JMenu("Action");
        JMenu helpMenu = new JMenu("Help");

        // actionMenu MenuItems: Exit
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener((ae) ->
        {
            System.exit(0);
        });

        // helpMenu MenuItems: About
        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener((ae) ->
        {
            String message = "<html><p align=center>Timer<br>by J. Escubido<html>";
            JOptionPane.showMessageDialog(frame, message, "About", JOptionPane.PLAIN_MESSAGE);
        });

        // Add components to menubar.
        frame.add(label);
        actionMenu.add(exitMenuItem);
        helpMenu.add(aboutMenuItem);
        menubar.add(actionMenu);
        menubar.add(helpMenu);
        frame.setJMenuBar(menubar);

        // Display frame
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new CountdownTimer());
    }
}
