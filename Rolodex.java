/**
 * Name:        Jarisse Escubido
 * Project:     #5
 * Due:         Wednesday April 12, 2023
 * Course:      cs-2450-01-sp23
 * 
 * Desciption:
 *              hello
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Rolodex
{
    // global variables


    Rolodex()
    {
        JFrame frame = new JFrame("Rolodex");
        frame.setSize(400, 250);
        frame.setIconImage(new ImageIcon("Rolodex.png").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create Menubar with SubMenus: File, Tabs, Help.
        JMenuBar menubar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu tabsMenu = new JMenu("Tabs");
        JMenu helpMenu = new JMenu("Help");

        // Components of File Menu
        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenuItem openMenuItem = new JMenuItem("Open", KeyEvent.VK_O);
        openMenuItem.addActionListener((ae) ->
        {

        });

        JMenuItem findMenuItem = new JMenuItem("Find", KeyEvent.VK_F);
        findMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
        findMenuItem.addActionListener((ae) ->
        {

        });

        JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
        exitMenuItem.addActionListener((ae) -> System.exit(0));

        fileMenu.add(openMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(findMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        // Components for Tabs Menu.
        tabsMenu.setMnemonic(KeyEvent.VK_T);
        // Components for Placement SubMenu.
        JMenu placementMenu = new JMenu("Placement");
        placementMenu.setMnemonic(KeyEvent.VK_P);
        JMenuItem topPlacement = new JMenuItem("Top", KeyEvent.VK_T);
        JMenuItem rightPlacement = new JMenuItem("Right", KeyEvent.VK_R);
        JMenuItem bottomPlacement = new JMenuItem("Bottom", KeyEvent.VK_B);
        JMenuItem leftPlacement = new JMenuItem("Left", KeyEvent.VK_L);
        // Add components to Placement SubMenu.
        placementMenu.add(topPlacement);
        placementMenu.add(rightPlacement);
        placementMenu.add(bottomPlacement);
        placementMenu.add(leftPlacement);
        // Components for Layout SubMenu.
        JMenu layoutMenu = new JMenu("Layout");
        layoutMenu.setMnemonic(KeyEvent.VK_L);
        JMenuItem scrollMenuItem = new JMenuItem("Scroll", KeyEvent.VK_S);
        JMenuItem wrapMenuItem = new JMenuItem("Wrap", KeyEvent.VK_W);
        // Add components to Layout SubMenu.
        layoutMenu.add(scrollMenuItem);
        layoutMenu.add(wrapMenuItem);
        // Create defaults MenuItem
        JMenuItem defaultsMenuItem = new JMenuItem("Defaults", KeyEvent.VK_D);
        defaultsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));

        tabsMenu.add(placementMenu);
        tabsMenu.add(layoutMenu);
        tabsMenu.addSeparator();
        tabsMenu.add(defaultsMenuItem);

        // Components of Help Menu.
        helpMenu.setMnemonic(KeyEvent.VK_H);
        JMenuItem aboutMenuItem = new JMenuItem("About", KeyEvent.VK_A);
        aboutMenuItem.addActionListener((ae) ->
        {
            Icon aboutAppIcon = new ImageIcon("Rolodex.png");
            JOptionPane.showMessageDialog(frame, "<html><b>Rolodex version 0.1<br>Copyright (c) 2023 <b> J.Escubido<html>",
                 "About", JOptionPane.PLAIN_MESSAGE, aboutAppIcon);
        });
        
        helpMenu.add(aboutMenuItem);

        // Add components to content pane.
        menubar.add(fileMenu);
        menubar.add(tabsMenu);
        menubar.add(helpMenu);
        frame.setJMenuBar(menubar);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String args[])
    {
        SwingUtilities.invokeLater(() -> new Rolodex());
    }
}