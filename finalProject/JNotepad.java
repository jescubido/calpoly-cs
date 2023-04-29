package finalProject;
/**
 * Name: Escubido, Jarisse
 * Project: #6
 * Due: 12 May 2023
 * Course: cs-2450-01-sp23
 * 
 * Description:
 *      Implement a version of the Windows Notepad application.
 */

import javax.swing.*;

import finalProject.Dialogs.JFontChooser;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class JNotepad
{
    // initializing variables
    private JFrame frame;
    JTextArea text;
    JMenuBar menubar;

    JNotepad()
    {
        frame = new JFrame();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("JNotepad.png").getImage());

        // Creating Menu
        menubar = new JMenuBar();

        // Create File Menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenuItem newMenuItem = new JMenuItem("New", KeyEvent.VK_N);
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));

        JMenuItem openMenuItem = new JMenuItem("Open...", KeyEvent.VK_O);
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));

        JMenuItem saveMenuItem = new JMenuItem("Save", KeyEvent.VK_S);
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));

        //-------------------------------

        JMenuItem saveAsMenuItem = new JMenuItem("Save As...", KeyEvent.VK_A);

        JMenuItem pageSetupMenuItem = new JMenuItem("Page Setup...", KeyEvent.VK_U);

        JMenuItem printMenuItem = new JMenuItem("Print...", KeyEvent.VK_P);
        printMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));

        //-------------------------------

        JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
        exitMenuItem.addActionListener((ae) -> System.exit(0));

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveAsMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(pageSetupMenuItem);
        fileMenu.add(printMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        // Create Edit Menu
        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        JMenuItem undoMenuItem = new JMenuItem("Undo", KeyEvent.VK_U); // Extra Credit
        undoMenuItem.setEnabled(false);

        //-------------------------------

        JMenuItem cutMenuItem = new JMenuItem("Cut", KeyEvent.VK_T);
        cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        cutMenuItem.addActionListener((ae) -> text.cut());

        JMenuItem copyMenuItem = new JMenuItem("Copy", KeyEvent.VK_C);
        copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        copyMenuItem.addActionListener((ae) -> text.copy());

        JMenuItem pasteMenuItem = new JMenuItem("Paste", KeyEvent.VK_P);
        pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        pasteMenuItem.addActionListener((ae) -> text.paste());

        JMenuItem deleteMenuItem = new JMenuItem("Delete", KeyEvent.VK_L);
        deleteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        deleteMenuItem.addActionListener((ae) -> text.setText(""));
        
        //-------------------------------

        JMenuItem findMenuItem = new JMenuItem("Find...", KeyEvent.VK_F);
        findMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));

        JMenuItem findNextMenuItem = new JMenuItem("Find Next", KeyEvent.VK_N); // Extra Credit
        findNextMenuItem.setEnabled(false);

        JMenuItem replaceMenuItem = new JMenuItem("Replace...", KeyEvent.VK_R); // Extra Credit
        replaceMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));

        JMenuItem goToMenuItem = new JMenuItem("Go To...", KeyEvent.VK_G);
        goToMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));

        //-------------------------------

        JMenuItem selectAllMenuItem = new JMenuItem("Select All", KeyEvent.VK_A);
        selectAllMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));

        JMenuItem timeDateMenuItem = new JMenuItem("Time/Date", KeyEvent.VK_D);
        timeDateMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));

        editMenu.add(undoMenuItem);
        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);
        editMenu.add(deleteMenuItem);
        editMenu.add(findMenuItem);
        editMenu.add(findNextMenuItem);
        editMenu.add(replaceMenuItem);
        editMenu.add(goToMenuItem);
        editMenu.add(selectAllMenuItem);
        editMenu.add(timeDateMenuItem);

        // Create Format Menu
        JMenu formatMenu = new JMenu("Format");
        formatMenu.setMnemonic(KeyEvent.VK_O);
        JMenuItem wordWrapMenuItem = new JMenuItem("Word Wrap", KeyEvent.VK_W);
        wordWrapMenuItem.addActionListener(ae -> 
        {
            text.setLineWrap(true);
            text.setWrapStyleWord(true);
        }); // Create newline when word reaches end of screen.

        JMenuItem fontMenuItem = new JMenuItem("Font...", KeyEvent.VK_F);
        fontMenuItem.addActionListener((ae) ->
        {
            JFontChooser.showDialog(frame, "Choose Font", null);
        });

        JMenu colorSubmenu = new JMenu("Color");
        colorSubmenu.setMnemonic(KeyEvent.VK_C);
        JMenuItem backgroundMenuItem = new JMenuItem("Background...", KeyEvent.VK_B);
        backgroundMenuItem.addActionListener((ae) ->
        {
            Color backgroundColor = JColorChooser.showDialog(frame, "Select Background Color", Color.WHITE);
            if (backgroundColor != null)
            {
                System.out.println(backgroundColor);
            }
            text.setBackground(backgroundColor);
        });
    
        JMenuItem foregroundMenuItem = new JMenuItem("Foreground...", KeyEvent.VK_F);
        foregroundMenuItem.addActionListener((ae) ->
        {
            Color foregroundColor = JColorChooser.showDialog(frame, "Select Foreground Color", Color.BLACK);
            if (foregroundColor != null)
            {
                System.out.println(foregroundColor);
            }
            text.setForeground(foregroundColor);
        });

        colorSubmenu.add(backgroundMenuItem);
        colorSubmenu.add(foregroundMenuItem);
        formatMenu.add(wordWrapMenuItem);
        formatMenu.add(fontMenuItem);
        formatMenu.add(colorSubmenu);

        // Create View menu
        JMenu viewMenu = new JMenu("View");
        viewMenu.setMnemonic(KeyEvent.VK_V);
        JMenuItem statusBarMenuItem = new JMenuItem("Status Bar", KeyEvent.VK_S); // Extra Credit
        statusBarMenuItem.setEnabled(false);

        viewMenu.add(statusBarMenuItem);

        // Create Help menu
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        JMenuItem viewHelpMenuItem = new JMenuItem("View Help", KeyEvent.VK_H); // Extra Credit
        viewHelpMenuItem.setEnabled(false);

        JMenuItem extraCreditsMenuItem = new JMenuItem("Extra Credits...", KeyEvent.VK_X);  // Extra Credit
        extraCreditsMenuItem.setEnabled(false);

        helpMenu.add(viewHelpMenuItem);
        helpMenu.add(extraCreditsMenuItem);

        // Add menus to menubar
        menubar.add(fileMenu);
        menubar.add(editMenu);
        menubar.add(formatMenu);
        menubar.add(viewMenu);
        menubar.add(helpMenu);

        text = new JTextArea();
        text.setFont(new Font("Courier New", Font.PLAIN, 12)); // default or initial font

        // Add to frame
        frame.setJMenuBar(menubar);
        frame.add(text);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new JNotepad());
    }
}