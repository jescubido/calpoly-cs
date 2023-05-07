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
<<<<<<< HEAD
<<<<<<< HEAD
=======
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
>>>>>>> parent of 0105a9a (no change)
=======
>>>>>>> parent of 7245913 (implement save and exit changes)
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JNotepad
{
    // initializing variables
    private JFrame frame;
    JTextArea text;
    JMenuBar menubar;
    private String selectedFile;
    private JPopupMenu editPopup;
<<<<<<< HEAD
<<<<<<< HEAD
=======
    private boolean unsavedChanges = false;
>>>>>>> parent of 0105a9a (no change)
=======
>>>>>>> parent of 7245913 (implement save and exit changes)

    JNotepad()
    {
        frame = new JFrame("JNotepad");
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
        // for MacOS use: newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

        // Custom file filter that displays Java source files and directories.
        class JavaFileFilter extends FileFilter
        {
            public boolean accept(File file)
            {
                if (file.getName().endsWith(".java")) return true;
                if (file.getName().endsWith(".txt")) return true;
                if (file.isDirectory()) return true;

                return false;
            }

            public String getDescription()
            {
                return "Java and Text Source Code Files";
            }
        }

        JMenuItem openMenuItem = new JMenuItem("Open...", KeyEvent.VK_O);
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        // for MacOS use: newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new JavaFileFilter());
        openMenuItem.addActionListener((ae) ->
        {
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = fileChooser.getSelectedFile().getPath();
                try 
                {
                    FileReader fileReader = new FileReader(selectedFile);
                    text.read(fileReader, null);
                    fileReader.close();
                }
                catch (IOException e) 
                {
                    JOptionPane.showMessageDialog(frame, "Error opening or reading file.");
                    System.out.println("File cannot be opened: " + selectedFile);
                }
            }
        });

        JMenuItem saveMenuItem = new JMenuItem("Save", KeyEvent.VK_S);
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        // for MacOS use: newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        saveMenuItem.addActionListener((ae) ->
        {
            if (selectedFile != null)
            {
                try
                {
                    FileWriter fileWriter = new FileWriter(selectedFile);
                    fileWriter.write(text.getText());
                    fileWriter.close();
                    JOptionPane.showMessageDialog(frame, "File saved successfully!");
                } 
                catch (IOException e) 
                {
                    JOptionPane.showMessageDialog(frame, "Error saving file: " + e.getMessage());
                    System.out.println("File cannot be saved: " + selectedFile);
                }
            }
<<<<<<< HEAD
<<<<<<< HEAD
=======
            if (fileChooser.getSelectedFile() == null)
            {
                int result = fileChooser.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION)
                {
                    try 
                    {
                        FileWriter fileWriter = new FileWriter(selectedFile);
                        fileWriter.write(text.getText());
                        fileWriter.close();
                        JOptionPane.showMessageDialog(frame, "File saved successfully!");
                    } 
                    catch (IOException e) 
                    {
                        JOptionPane.showMessageDialog(frame, "Error saving file: " + e.getMessage());
                        System.out.println("File cannot be saved: " + selectedFile);
                    }
                }
            }
>>>>>>> parent of 0105a9a (no change)
=======
>>>>>>> parent of 7245913 (implement save and exit changes)
        });

        //-------------------------------

        JMenuItem saveAsMenuItem = new JMenuItem("Save As...", KeyEvent.VK_A);
        saveAsMenuItem.addActionListener((ae) ->
        {
            int result = fileChooser.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION)
            {
                try 
                {
                    FileWriter fileWriter = new FileWriter(selectedFile);
                    fileWriter.write(text.getText());
                    fileWriter.close();
                    JOptionPane.showMessageDialog(frame, "File saved successfully!");
                } 
                catch (IOException e) 
                {
                    JOptionPane.showMessageDialog(frame, "Error saving file: " + e.getMessage());
                    System.out.println("File cannot be saved: " + selectedFile);
                }
            }
        });

        JMenuItem pageSetupMenuItem = new JMenuItem("Page Setup...", KeyEvent.VK_U); // Extra Credit

        JMenuItem printMenuItem = new JMenuItem("Print...", KeyEvent.VK_P); // Extra Credit
        printMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
        // for MacOS use: newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        printMenuItem.addActionListener((ae) -> 
        {
            PrinterJob printerJob = PrinterJob.getPrinterJob();
            if (printerJob.printDialog())
            {
                try
                {
                    printerJob.print();
                }
                catch (PrinterException e)
                {
                    e.printStackTrace();
                }
            }
        });

        //-------------------------------

        JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
<<<<<<< HEAD
<<<<<<< HEAD
        exitMenuItem.addActionListener((ae) -> System.exit(0));
=======
        exitMenuItem.addActionListener((ae) -> 
        {
            if (fileChooser.getSelectedFile() == null && unsavedChanges == false)
            {
                int exitWithoutSaving = JOptionPane.showConfirmDialog(frame,"Do you want to save changes to this document before exiting?", "Confirm Save Changes", JOptionPane.YES_NO_CANCEL_OPTION);
                if (exitWithoutSaving == JOptionPane.YES_OPTION)
                {
                    int result = fileChooser.showSaveDialog(frame);
                    if (result == JFileChooser.APPROVE_OPTION)
                    {
                        try 
                        {
                            FileWriter fileWriter = new FileWriter(selectedFile);
                            fileWriter.write(text.getText());
                            fileWriter.close();
                            JOptionPane.showMessageDialog(frame, "File saved successfully!");
                        } 
                        catch (IOException e) 
                        {
                            JOptionPane.showMessageDialog(frame, "Error saving file: " + e.getMessage());
                            System.out.println("File cannot be saved: " + selectedFile);
                        }
                    }
                }
                if (exitWithoutSaving == JOptionPane.NO_OPTION)
                {
                    System.exit(0);
                }
                return;
            }
            System.exit(0);
        });
>>>>>>> parent of 0105a9a (no change)
=======
        exitMenuItem.addActionListener((ae) -> System.exit(0));
>>>>>>> parent of 7245913 (implement save and exit changes)

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
        // for MacOS use: newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        cutMenuItem.addActionListener((ae) -> text.cut());

        JMenuItem copyMenuItem = new JMenuItem("Copy", KeyEvent.VK_C);
        copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        // for MacOS use: newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        copyMenuItem.addActionListener((ae) -> text.copy());

        JMenuItem pasteMenuItem = new JMenuItem("Paste", KeyEvent.VK_P);
        pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        // for MacOS use: newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        pasteMenuItem.addActionListener((ae) -> text.paste());

        JMenuItem deleteMenuItem = new JMenuItem("Delete", KeyEvent.VK_L);
        deleteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        deleteMenuItem.addActionListener((ae) -> 
        {
            String selectedText = text.getSelectedText();
            if(selectedText != null)
                text.replaceSelection("");
        });

        editMenu.add(undoMenuItem);
        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);
        editMenu.add(deleteMenuItem);
        
        //-------------------------------

        JMenuItem findMenuItem = new JMenuItem("Find...", KeyEvent.VK_F);
        findMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
        // for MacOS use: newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        findMenuItem.addActionListener((ae) ->
        {
            String searchString = JOptionPane.showInputDialog("Find: ");
            if (searchString != null)
            {
                int index = text.getText().indexOf(searchString);
                if (index != -1)
                {
                    text.select(index, index + searchString.length());
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Cannot find '" + searchString + "'", "JNotepad", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JMenuItem findNextMenuItem = new JMenuItem("Find Next", KeyEvent.VK_N); // Extra Credit
        findNextMenuItem.setEnabled(false);

        JMenuItem replaceMenuItem = new JMenuItem("Replace...", KeyEvent.VK_R); // Extra Credit
        replaceMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));
        // for MacOS use: newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        replaceMenuItem.addActionListener((ae) ->
        {
            String searchString = JOptionPane.showInputDialog("Find: ");
            String replaceString = JOptionPane.showInputDialog("Replace: ");
            if (searchString != null && replaceString != null)
            {
                String selectedText = text.getSelectedText();
                if (selectedText != null && selectedText.equals(searchString))
                {
                    text.replaceSelection(replaceString);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Cannot find '" + searchString + "'", "JNotepad", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JMenuItem goToMenuItem = new JMenuItem("Go To...", KeyEvent.VK_G);
        goToMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
        // for MacOS use: newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));


        //-------------------------------

        JMenuItem selectAllMenuItem = new JMenuItem("Select All", KeyEvent.VK_A);
        selectAllMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        // for MacOS use: newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        selectAllMenuItem.addActionListener((ae) -> text.selectAll());

        JMenuItem timeDateMenuItem = new JMenuItem("Time/Date", KeyEvent.VK_D);
        timeDateMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        // for MacOS use: newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        JLabel time = new JLabel("");
        time.setHorizontalAlignment(SwingConstants.CENTER);
        timeDateMenuItem.addActionListener((ae) ->
        {
            SimpleDateFormat sdf = new SimpleDateFormat("h:mm a MM/dd/yyyy");
            String date = sdf.format(new Date());
            text.append("\n" + date + "\n");
        });

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
            Font initialFont = text.getFont();
            Font font = Dialogs.showDialog(frame, "Choose Font", initialFont);
            if (font != null)
                text.setFont(font);
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

        // Extra 5: Drag a file into the notepad and it opens the file (Drag & Drop)
            // Make sure no changes in current file have been made

        //-------------------------------

        JMenuItem aboutNotepad = new JMenuItem("About Notepad", KeyEvent.VK_A);
        aboutNotepad.addActionListener((ae) ->
        {
            Icon icon = new ImageIcon("JNotepad.png");
            JOptionPane.showMessageDialog(frame, "<html>JNotepad v0.1<br>Copyright (c) J. Escubido<html>",
                "About", JOptionPane.PLAIN_MESSAGE, icon);
        });

        helpMenu.add(viewHelpMenuItem);
        helpMenu.add(extraCreditsMenuItem);
        helpMenu.addSeparator();
        helpMenu.add(aboutNotepad);

        // Add menus to menubar
        menubar.add(fileMenu);
        menubar.add(editMenu);
        menubar.add(formatMenu);
        menubar.add(viewMenu);
        menubar.add(helpMenu);

        text = new JTextArea();
        Font initialFont = (new Font("Courier New", Font.PLAIN, 12)); // default or initial font
        text.setFont(initialFont);
        JScrollPane scrollPane = new JScrollPane(text);


        // Create PopupMenu for cut, copy, paste
        editPopup = new JPopupMenu();
        JMenuItem cutPopup = new JMenuItem("Cut");
        cutPopup.addActionListener((ae) -> text.cut());
        JMenuItem copyPopup = new JMenuItem("Copy");
        copyPopup.addActionListener((ae) -> text.copy());
        JMenuItem pastePopup = new JMenuItem("Paste");
        pastePopup.addActionListener((ae) -> text.paste());
        editPopup.add(cutPopup);
        editPopup.add(copyPopup);
        editPopup.add(pastePopup);

        editPopup.addPopupMenuListener(new PopupMenuListener() 
        {
            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) 
            {
                if(text.getSelectedText() == null)
                {
                    cutPopup.setVisible(false);
                    copyPopup.setVisible(false);
                }
            }
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) 
            {
                if(text.getSelectedText() != null)
                {
                    cutPopup.setVisible(true);
                    copyPopup.setVisible(true);
                    pastePopup.setVisible(true);
                }
            }
            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {}
        });

        text.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    editPopup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    editPopup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        // Add to frame
        frame.setJMenuBar(menubar);
        frame.add(time, BorderLayout.NORTH);
        frame.add(scrollPane);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new JNotepad());
    }
}