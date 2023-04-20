/**
 * Name:        Jarisse Escubido
 * Project:     #5
 * Due:         Wednesday April 12, 2023
 * Course:      cs-2450-01-sp23
 * 
 * Desciption:
 *              Swing application that displays a contact file using tabs.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Rolodex
{
    // global variables.
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private JLabel picture;
    private JPanel myContact;
    private JPanel panel;

    Rolodex()
    {
        frame = new JFrame("Rolodex");
        frame.setSize(550, 200);
        frame.setIconImage(new ImageIcon("Rolodex.png").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tabbedPane = new JTabbedPane(SwingConstants.TOP, JTabbedPane.SCROLL_TAB_LAYOUT); // Create a tabbed pane.
        // Hardcode first tab as my contact.
        myContact = new JPanel();
        JPanel myImagePanel = new JPanel();
        JPanel myInfoPanel = new JPanel();
        myContact.setLayout(new GridLayout(1,2)); //left holds pic, right holds name and email.
        JLabel myPicture = new JLabel(new ImageIcon("nopic.jpg"));
        JLabel myNameLabel = new JLabel("Name: ");
        JTextField myNameText = new JTextField("Escubido, Jarisse", 15);
        myNameText.setEditable(false);
        JLabel myEmailLabel = new JLabel("Email: ");
        JTextField myEmailText = new JTextField("jescubido@cpp.edu", 15);
        myEmailText.setEditable(false);

        myImagePanel.add(myPicture, BorderLayout.CENTER);
        myInfoPanel.add(myNameLabel);
        myInfoPanel.add(myNameText);
        myInfoPanel.add(myEmailLabel);
        myInfoPanel.add(myEmailText);
        myContact.add(myImagePanel);
        myContact.add(myInfoPanel, BorderLayout.CENTER);
        tabbedPane.addTab("Escubido, Jarisse", myContact);

        // read contents from file and add as tabs.
        try
        {
            BufferedReader reader = new BufferedReader(new java.io.FileReader("contacts.txt"));
            String line = null;
            while((line = reader.readLine()) != null)
            {
                String[] info = line.split("~"); //info[0] last, first; info[1] email; info[2] image
                panel = new JPanel();
                panel.setLayout(new GridLayout(1, 2));
                JPanel imagePanel = new JPanel();
                JPanel infoPanel = new JPanel();

                if (new File(info[2]).isFile())
                {
                    picture = new JLabel(new ImageIcon(info[2]));
                }
                else
                {
                    picture = new JLabel(new ImageIcon("nopic.jpg"));
                }

                JLabel nameLabel = new JLabel("Name: ");
                JTextField nameText = new JTextField(info[0], 15);
                nameText.setEditable(false);
                JLabel emailLabel = new JLabel("Email: ");
                JTextField emailText = new JTextField(info[1], 15);
                emailText.setEditable(false);

                imagePanel.add(picture, BorderLayout.CENTER);
                infoPanel.add(nameLabel);
                infoPanel.add(nameText);
                infoPanel.add(emailLabel);
                infoPanel.add(emailText);
                panel.add(imagePanel);
                panel.add(infoPanel, BorderLayout.CENTER);
                tabbedPane.addTab(info[0], panel);
            }
        }
        
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(frame, "contacts.txt cannot be found", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        // Create Menubar with SubMenus: File, Tabs, Help.
        JMenuBar menubar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu tabsMenu = new JMenu("Tabs");
        JMenu helpMenu = new JMenu("Help");

        // Components of File Menu
        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenuItem openMenuItem = new JMenuItem("Open", KeyEvent.VK_O);
        openMenuItem.setEnabled(false); // Disables Open MenuItem.
        JMenuItem findMenuItem = new JMenuItem("Find", KeyEvent.VK_F);
        findMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
        findMenuItem.addActionListener((ae) ->
        {
            String nameInput = JOptionPane.showInputDialog(frame, "Enter Name");
            if (nameInput != null)
            {
                int index = tabbedPane.indexOfTab(nameInput);
                if (index != -1)
                {
                    tabbedPane.setSelectedIndex(index);
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "'" + nameInput + "'" + " cannot be found.\nPlease enter name using this format: 'Last, First'",
                    "Invalid Name", JOptionPane.ERROR_MESSAGE);
                }
            }
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
        topPlacement.addActionListener((ae) -> tabbedPane.setTabPlacement(JTabbedPane.TOP));
        JMenuItem rightPlacement = new JMenuItem("Right", KeyEvent.VK_R);
        rightPlacement.addActionListener((ae) -> tabbedPane.setTabPlacement(JTabbedPane.RIGHT));
        JMenuItem bottomPlacement = new JMenuItem("Bottom", KeyEvent.VK_B);
        bottomPlacement.addActionListener((ae) -> tabbedPane.setTabPlacement(JTabbedPane.BOTTOM));
        JMenuItem leftPlacement = new JMenuItem("Left", KeyEvent.VK_L);
        leftPlacement.addActionListener((ae) -> tabbedPane.setTabPlacement(JTabbedPane.LEFT));
        // Add components to Placement SubMenu.
        placementMenu.add(topPlacement);
        placementMenu.add(rightPlacement);
        placementMenu.add(bottomPlacement);
        placementMenu.add(leftPlacement);
        // Components for Layout SubMenu.
        JMenu layoutPolicyMenu = new JMenu("Layout");
        layoutPolicyMenu.setMnemonic(KeyEvent.VK_L);
        JMenuItem scrollMenuItem = new JMenuItem("Scroll", KeyEvent.VK_S);
        scrollMenuItem.addActionListener((ae) -> tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT));
        JMenuItem wrapMenuItem = new JMenuItem("Wrap", KeyEvent.VK_W);
        wrapMenuItem.addActionListener((ae) -> tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT));
        // Add components to Layout SubMenu.
        layoutPolicyMenu.add(scrollMenuItem);
        layoutPolicyMenu.add(wrapMenuItem);
        // Create defaults MenuItem
        JMenuItem defaultsMenuItem = new JMenuItem("Defaults", KeyEvent.VK_D);
        defaultsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
        defaultsMenuItem.addActionListener((ae) -> 
        {
            tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
            tabbedPane.setTabPlacement(JTabbedPane.TOP);
        });

        tabsMenu.add(placementMenu);
        tabsMenu.add(layoutPolicyMenu);
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
        frame.add(tabbedPane);

        //frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String args[])
    {
        SwingUtilities.invokeLater(() -> new Rolodex());
    }
}