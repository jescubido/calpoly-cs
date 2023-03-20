/**
 * Name:        Escubido, Jarisse
 * Homework:    #1 (15)
 * Due:         Wednesday March 22, 2023
 * Course:      cs-2450-01-sp23
 * 
 * Description:
 *              An application that reads a Java source file 
 *              and display it using the text area component.
 */

import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class JavaViewer
{
    // global veriables
    static private JTextArea text;

    JavaViewer()
    {
        JFrame frame = new JFrame("JavaViewer");
        frame.setSize(400, 200);
        frame.setIconImage(new ImageIcon("JavaViewer.png").getImage());

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // Event that asks user for confirmation to close application when System close icon [X] is pressed.
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                // Create popup that asks user for confirmation on exiting application.
                int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?",
                "Select an Option", JOptionPane.YES_NO_OPTION);
                
                switch(result)
                {
                    case JOptionPane.YES_OPTION: // If YES is pressed, popup and application closes.
                        System.exit(0);
                        break;
                    case JOptionPane.NO_OPTION: // If NO is pressed, applicatin does not close.
                        break;
                }
            }
        });

        text = new JTextArea();
        text.setFont(new Font("Courier New", Font.PLAIN, 12));
        text.setForeground(Color.WHITE);
        text.setBackground(Color.BLUE);


        JMenuBar menuBar = new JMenuBar(); // Create a menu bar.

        JMenu fileMenu = new JMenu("File"); // Create File Menu; contains Open and Exit MenuItems.
        JMenu helpMenu = new JMenu("Help"); // Create Help Menu; contains About MenuItem.

        // Components and MenuItems for fileMenu Menu.
        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenuItem openMenuItem = new JMenuItem("Open", KeyEvent.VK_O);
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));


        JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
        exitMenuItem.addActionListener((ae) ->
        {
            int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?",
                "Select an Option", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
        });

        // Add MenuItems to fileMenu.
        fileMenu.add(openMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        // Components and MenuItems for helpMenu Menu.
        helpMenu.setMnemonic(KeyEvent.VK_H);
        JMenuItem aboutMenuItem = new JMenuItem("About", KeyEvent.VK_A);
        aboutMenuItem.addActionListener((ae) ->
        {
            Icon icon = new ImageIcon("JavaViewer.png");
            JOptionPane.showMessageDialog(frame, "<html>JavaViewer v0.1<br>Copyright (c) J. Escubido<html>",
                 "About", JOptionPane.PLAIN_MESSAGE, icon);
        });

        helpMenu.add(aboutMenuItem); // Add aboutMenuItem to helpMenu.

        // Create popup menu for copy
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem copyMenuItem = new JMenuItem("Copy");

        frame.getContentPane().addMouseListener(new MouseAdapter() 
        {
            public void mousePressed(MouseEvent me)
            {
                popupMenu.show(me.getComponent(), me.getX(), me.getY());
            }
            public void mouseReleased(MouseEvent me)
            {
                if(me.isPopupTrigger())
                    popupMenu.show(me.getComponent(), me.getX(), me.getY());
            }
        });
        

        popupMenu.add(copyMenuItem);

       // Add Menus to menuBar.
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        frame.setJMenuBar(menuBar);

        // Add components to content pane.
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new JavaViewer());
    }
}