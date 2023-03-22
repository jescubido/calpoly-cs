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

import javax.swing.filechooser.FileFilter;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

// Custom file filter that displays Java source files and directories.
class JavaFileFilter extends FileFilter
{
    public boolean accept(File file)
    {
        if (file.getName().endsWith(".java")) return true;
        if (file.isDirectory()) return true;

        return false;
    }

    public String getDescription()
    {
        return "Java Source Code Files";
    }
}

public class JavaViewer
{
    // global veriables
    static private JTextArea textArea;
    private JFileChooser fileChooser;
    static private int result;


    JavaViewer()
    {
        JFrame frame = new JFrame("JavaViewer");
        frame.setSize(500, 300);
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

        textArea = new JTextArea(); // Create a TextArea where source file will be displayed.
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(Color.BLUE);


        JMenuBar menuBar = new JMenuBar(); // Create a menu bar.

        JMenu fileMenu = new JMenu("File"); // Create File Menu; contains Open and Exit MenuItems.
        JMenu helpMenu = new JMenu("Help"); // Create Help Menu; contains About MenuItem.

        // Components and MenuItems for fileMenu Menu.
        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenuItem openMenuItem = new JMenuItem("Open", KeyEvent.VK_O);
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
        
        
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new JavaFileFilter());
        openMenuItem.addActionListener((ae) ->
        {
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION)
                textArea.setText("Selected file is: " + fileChooser.getSelectedFile().getName());
            else
                textArea.setText("No file selected.");
        });
        
        


        JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
        exitMenuItem.addActionListener((ae) ->
        {
            result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?",
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
        copyMenuItem.addActionListener((ae) ->
        {
            textArea.copy(); // Copies text to clipboard
        });

        textArea.addMouseListener(new MouseAdapter() 
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
        
        // Add components.
        menuBar.add(fileMenu); // Add fileMenu to menuBar.
        menuBar.add(helpMenu); // Add helpMenu to menuBar.
        popupMenu.add(copyMenuItem); // Add copyMenuItem to popupMenu.
        frame.setJMenuBar(menuBar); // Add menuBar to frame.
        frame.add(textArea); // Add textArea to frame.

        // Add components to content pane.
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new JavaViewer());
        System.out.println(result);
    }
}