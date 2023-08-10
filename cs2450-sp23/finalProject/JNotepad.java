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
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.DefaultHighlighter;

import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.awt.event.*;
import java.awt.print.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JNotepad
{
    // initializing variables
    private JFrame frame;
    JTextArea text;
    JMenuBar menubar;
    private File selectedFile;
    private JPopupMenu editPopup;
    private boolean unsavedChanges = false;
    JFileChooser fileChooser;
    int count;

    JNotepad()
    {
        // Initialize first document.
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Downloads"));
        selectedFile = new File("Untitled");
        count = 1;
        while (selectedFile.exists())
        {
            selectedFile = new File("Untitled" + count);
            count++;
        }

        // Initialize frame
        frame = new JFrame(selectedFile.getName() + " - JNotepad");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setIconImage(new ImageIcon("JNotepad.png").getImage());

        // If unsaved changes have been detected, ask the user if they want to save before exiting.
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent we)
            {
                if (unsavedChanges)
                {
                    int saveChanges = JOptionPane.showConfirmDialog(frame,"Do you want to save changes to this document before exiting?", "Confirm Save Changes", JOptionPane.YES_NO_CANCEL_OPTION);
                    switch(saveChanges)
                    {
                        // Case 1: They want to save changes before exiting.
                        case JOptionPane.YES_OPTION:
                        {
                            if (selectedFile.getName().equals("Untitled") || selectedFile.getName().equals("Untitled" + count))
                            {
                                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Downloads"));
                                int newSaveChanges = fileChooser.showSaveDialog(frame);
                                if (newSaveChanges == JFileChooser.APPROVE_OPTION)
                                {
                                    selectedFile = fileChooser.getSelectedFile();
                                    JOptionPane.showMessageDialog(frame, "File saved successfully!");
                                    frame.setTitle(selectedFile.getName().replace(".txt", "") + " - JNotepad");
                                    unsavedChanges = false;
                                }
                                return;
                            }
                            else
                            {
                                try 
                                {
                                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Downloads"));
                                    FileWriter fileWriter = new FileWriter(selectedFile);
                                    fileWriter.write(text.getText());
                                    fileWriter.close();
                                    JOptionPane.showMessageDialog(frame, "File saved successfully!");
                                    frame.setTitle(selectedFile.getName().replace(".txt", "") + " - JNotepad");
                                    unsavedChanges = false;
                                } 
                                catch (IOException e) 
                                {
                                    JOptionPane.showMessageDialog(frame, "Error saving file: " + e.getMessage());
                                    System.out.println("File cannot be saved: " + selectedFile);
                                }
                            }
                        }
                        // Case 2: They don't want to save changes when exiting.
                        case JOptionPane.NO_OPTION:
                            System.exit(0);
                            // Case 3: Return to main frame.
                        case JOptionPane.CANCEL_OPTION:
                            return;
                    }
                }
                System.exit(0);
            }
        });

        // Creating Menu
        menubar = new JMenuBar();

        // Create File Menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        // Erases content of current document and initializes as new Untitled document.
        JMenuItem newMenuItem = new JMenuItem("New", KeyEvent.VK_N);
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        
        newMenuItem.addActionListener((ae) ->
        {
            text.setText("");
            selectedFile = null;
            unsavedChanges = false;
            selectedFile = new File("Untitled");
            count = 1;
            while (selectedFile.exists())
            {
                selectedFile = new File("Untitled" + count);
                count++;
            }
            frame.setTitle(selectedFile.getName() + " - JNotepad");
        });

        // for MacOS use: newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

        // Custom file filter that displays Java/Text source files and directories.
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

        // Loads an existing java or text source file to the JTextArea.
        JMenuItem openMenuItem = new JMenuItem("Open...", KeyEvent.VK_O);
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        // for MacOS use: newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        fileChooser.setFileFilter(new JavaFileFilter());
        openMenuItem.addActionListener((ae) ->
        {
            int openFile = fileChooser.showOpenDialog(frame);
            if (openFile == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = fileChooser.getSelectedFile();
                try 
                {
                    FileReader fileReader = new FileReader(selectedFile);
                    text.read(fileReader, null);
                    fileReader.close();
                    frame.setTitle(selectedFile.getName().replace(".txt", "") + " - JNotepad");
                }
                catch (IOException e) 
                {
                    JOptionPane.showMessageDialog(frame, "Error opening or reading file.");
                    System.out.println("File cannot be opened: " + selectedFile);
                }
            }
        });

        // Saves current contents of a file; if file is "Untitled" or doesn't exist, prompts user to save Dialog.
        JMenuItem saveMenuItem = new JMenuItem("Save", KeyEvent.VK_S);
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        // for MacOS use: newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        saveMenuItem.addActionListener((ae) ->
        {
            if (selectedFile.getName().equals("Untitled") || selectedFile.getName().equals("Untitled" + count))
            {
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Downloads"));
                int saveChanges = fileChooser.showSaveDialog(frame);
                if (saveChanges == JFileChooser.APPROVE_OPTION)
                {
                    selectedFile = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(frame, "File saved successfully!");
                    frame.setTitle(selectedFile.getName().replace(".txt", "") + " - JNotepad");
                    unsavedChanges = false;
                }
                return;
            }
            else
            {
                try 
                {
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Downloads"));
                    FileWriter fileWriter = new FileWriter(selectedFile);
                    fileWriter.write(text.getText());
                    fileWriter.close();
                    selectedFile = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(frame, "File saved successfully!");
                    frame.setTitle(selectedFile.getName().replace(".txt", "") + " - JNotepad");
                    unsavedChanges = false;
                } 
                catch (IOException e) 
                {
                    JOptionPane.showMessageDialog(frame, "Error saving file: " + e.getMessage());
                    System.out.println("File cannot be saved: " + selectedFile);
                }
            }
        });

        //-------------------------------

        // Prompts user to save a file as a new file in the directory.
        JMenuItem saveAsMenuItem = new JMenuItem("Save As...", KeyEvent.VK_A);
        saveAsMenuItem.addActionListener((ae) ->
        {
            int saveChanges = fileChooser.showSaveDialog(frame);
            if (saveChanges == JFileChooser.APPROVE_OPTION)
            {
                try 
                {
                    FileWriter fileWriter = new FileWriter(selectedFile + ".txt");
                    fileWriter.write(text.getText());
                    fileWriter.close();
                    JOptionPane.showMessageDialog(frame, "File saved successfully!");
                    frame.setTitle(selectedFile.getName().replace(".txt", "") + " - JNotepad");
                    unsavedChanges = false;
                } 
                catch (IOException e) 
                {
                    JOptionPane.showMessageDialog(frame, "Error saving file: " + e.getMessage());
                    System.out.println("File cannot be saved: " + selectedFile);
                }
            }
        });

        // Orients contents of page to desired setup.
        JMenuItem pageSetupMenuItem = new JMenuItem("Page Setup...", KeyEvent.VK_U); // Extra Credit
        pageSetupMenuItem.addActionListener((ae) ->
        {
            PrinterJob job = PrinterJob.getPrinterJob();
            PageFormat pageFormat = job.pageDialog(job.defaultPage());
            job.validatePage(pageFormat);

            text.setSize((int) pageFormat.getImageableWidth(), (int) pageFormat.getImageableHeight());
            text.setMargin(new Insets((int) pageFormat.getImageableY(), (int) pageFormat.getImageableX(),
                (int) (pageFormat.getHeight() - pageFormat.getImageableHeight() - pageFormat.getImageableY()),
                (int) (pageFormat.getWidth() - pageFormat.getImageableWidth() - pageFormat.getImageableX())));
        });

        // Initiates a printer job to the user to printing the document.
        JMenuItem printMenuItem = new JMenuItem("Print...", KeyEvent.VK_P); // Extra Credit
        printMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
        // for MacOS use: newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        printMenuItem.addActionListener((ae) -> 
        {
            // PrinterJob printerJob = PrinterJob.getPrinterJob();
            // if (printerJob.printDialog())
            // try
            // {
            //     try
            //     {
            //         printerJob.print();
            //     }
            //     catch (PrinterException e)
            //     {
            //         e.printStackTrace();
            //     }

            try
            {
                text.print();
            }
            catch (PrinterException e)
            {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error printing document!","Printer",JOptionPane.ERROR_MESSAGE);
            }
        });

        //-------------------------------

        // Exits the program. If unsaved changes are detected, prompts user if they want to save before exiting.
        JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
        exitMenuItem.addActionListener((ae) -> 
        {
            if (unsavedChanges)
            {
                int saveChanges = JOptionPane.showConfirmDialog(frame,"Do you want to save changes to this document before exiting?", "Confirm Save Changes", JOptionPane.YES_NO_CANCEL_OPTION);
                switch(saveChanges)
                {
                    case JOptionPane.YES_OPTION:
                    {
                        if (selectedFile.getName().equals("Untitled") || selectedFile.getName().equals("Untitled" + count))
                        {
                            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Downloads"));
                            saveChanges = fileChooser.showSaveDialog(frame);
                            if (saveChanges == JFileChooser.APPROVE_OPTION)
                            {
                                selectedFile = fileChooser.getSelectedFile();
                                JOptionPane.showMessageDialog(frame, "File saved successfully!");
                                frame.setTitle(selectedFile.getName().replace(".txt", "") + " - JNotepad");
                            }
                            return;
                        }
                        try 
                        {
                            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Downloads"));
                            FileWriter fileWriter = new FileWriter(selectedFile + ".txt");
                            fileWriter.write(text.getText());
                            fileWriter.close();
                            JOptionPane.showMessageDialog(frame, "File saved successfully!");
                            frame.setTitle(selectedFile.getName().replace(".txt", "") + " - JNotepad");
                            unsavedChanges = false;
                        } 
                        catch (IOException e) 
                        {
                            JOptionPane.showMessageDialog(frame, "Error saving file: " + e.getMessage());
                            System.out.println("File cannot be saved: " + selectedFile);
                        }
                    }
                    case JOptionPane.NO_OPTION:
                        System.exit(0);
                    case JOptionPane.CANCEL_OPTION:
                        return;
                }
            }
            System.exit(0);
        });

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

        // Deletes and saves selected string(s) to clipboard.
        JMenuItem cutMenuItem = new JMenuItem("Cut", KeyEvent.VK_T);
        cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        // for MacOS use: newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        cutMenuItem.addActionListener((ae) -> text.cut());

        // Saves selected string(s) to clipboard.
        JMenuItem copyMenuItem = new JMenuItem("Copy", KeyEvent.VK_C);
        copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        // for MacOS use: newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        copyMenuItem.addActionListener((ae) -> text.copy());

        // Prints string(s) from clipboard to text area.
        JMenuItem pasteMenuItem = new JMenuItem("Paste", KeyEvent.VK_P);
        pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        // for MacOS use: newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        pasteMenuItem.addActionListener((ae) -> text.paste());

        // Deletes selected string(s) from text area.
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

        // Searches for string in text area and highlights every occurence.
        JMenuItem findMenuItem = new JMenuItem("Find...", KeyEvent.VK_F);
        findMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
        // for MacOS use: newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        findMenuItem.addActionListener((ae) ->
        {
            JDialog searchDialog = new JDialog(null, "JNotepad - Find", ModalityType.MODELESS);
            String searchString = JOptionPane.showInputDialog(searchDialog, "Find: ");
            if (searchString != null)
            {
                text.getHighlighter().removeAllHighlights();
                int searchIndex = 0;
                searchIndex = text.getText().toLowerCase().indexOf(searchString.toLowerCase());
                while (searchIndex >= 0)
                {
                    try 
                    {
                        text.getHighlighter().addHighlight(searchIndex, searchIndex + searchString.length(), new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW));
                        text.setCaretPosition(searchIndex);
                        unsavedChanges = true;
                    } 
                    catch (Exception e) 
                    {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Cannot find '" + searchString + "'", "JNotepad - Find Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                    searchIndex = text.getText().toLowerCase().indexOf(searchString.toLowerCase(), searchIndex + searchString.length());
                }
            }
            return;
        });

        JMenuItem findNextMenuItem = new JMenuItem("Find Next", KeyEvent.VK_N); // Extra Credit
        findNextMenuItem.setEnabled(false);

        // Searches for string(s) in text area and replaces it with new desired string.
        JMenuItem replaceMenuItem = new JMenuItem("Replace...", KeyEvent.VK_R); // Extra Credit
        replaceMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));
        // for MacOS use: newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        replaceMenuItem.addActionListener((ae) ->
        {
            JPanel replacePanel = new JPanel(new GridLayout(3, 2));
            JLabel findLabel = new JLabel("Find:");
            JTextField findTextField = new JTextField();
            JLabel replaceLabel = new JLabel("Replace:");
            JTextField replaceTextField = new JTextField();
            JCheckBox caseSensitive = new JCheckBox("Case sensitive");
            replacePanel.add(findLabel);
            replacePanel.add(findTextField);
            replacePanel.add(replaceLabel);
            replacePanel.add(replaceTextField);
            replacePanel.add(new JLabel()); // empty cell for spacing
            replacePanel.add(caseSensitive);
            int result = JOptionPane.showConfirmDialog(null, replacePanel, "Replace", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION)
            {
                String searchString = findTextField.getText();
                String replaceString = replaceTextField.getText();
                String textContent = text.getText();
                int searchIndex = textContent.indexOf(searchString, text.getCaretPosition());
                int replacedCount = 0;
                while (searchIndex >= 0)
                {
                    if (!caseSensitive.isSelected())
                    {
                        textContent = textContent.substring(0, searchIndex) + replaceString + textContent.substring(searchIndex + searchString.length());
                    }
                    else
                    {
                        String substring = textContent.substring(searchIndex, searchIndex + searchString.length());
                        if (substring.equals(searchString))
                        {
                            textContent = textContent.substring(0, searchIndex) + replaceString + textContent.substring(searchIndex + searchString.length());
                        }
                    }
                    replacedCount++;
                    searchIndex = textContent.indexOf(searchString, searchIndex + replaceString.length());
                    unsavedChanges = true;
                }
                if (replacedCount > 0)
                {
                    text.setText(textContent);
                    JOptionPane.showMessageDialog(null, "Replaced " + replacedCount + " occurrence(s) of '" + searchString + "'", "JNotepad - Replace Result", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Cannot find '" + searchString + "'", "JNotepad - Replace Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Moves cursor to the desired line.
        JMenuItem goToMenuItem = new JMenuItem("Go To...", KeyEvent.VK_G);
        goToMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
        // for MacOS use: newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        goToMenuItem.addActionListener((ae) ->
        {
            try {
                int totalLineCount = text.getLineCount();
                int offset = text.getCaretPosition();
                int lineNumber = text.getLineOfOffset(offset);
                String goToSearch = JOptionPane.showInputDialog(null,"Go to Line Number :",(lineNumber + 1));
                if(goToSearch != null) {
                    int inputLine = Integer.parseInt(goToSearch);
                    if(inputLine <= totalLineCount) {
                        int columnNumber = text.getLineStartOffset(inputLine - 1);
                        text.setCaretPosition(columnNumber);
                    }else {
                        JOptionPane.showMessageDialog(null, "The line number is beyond the total number of lines.", "JNotepad - Go To Line",JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }catch(Exception exp) {
                JOptionPane.showMessageDialog(null, "Somthing went wrong!");
            }
        });


        //-------------------------------

        // Highlights all the text in text area.
        JMenuItem selectAllMenuItem = new JMenuItem("Select All", KeyEvent.VK_A);
        selectAllMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        // for MacOS use: newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        selectAllMenuItem.addActionListener((ae) -> text.selectAll());

        // Prints time and date to the bottom of the text area.
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
        // Creates a new line at the end of the screen.
        JMenuItem wordWrapMenuItem = new JMenuItem("Word Wrap", KeyEvent.VK_W);
        wordWrapMenuItem.addActionListener(ae -> 
        {
            text.setLineWrap(true);
            text.setWrapStyleWord(true);
        });

        // Displays Font Dialog that changes font, font size, and effects (regular, italics, bold).
        JMenuItem fontMenuItem = new JMenuItem("Font...", KeyEvent.VK_F);
        fontMenuItem.addActionListener((ae) ->
        {
            Font initialFont = text.getFont();
            Font font = Dialogs.showDialog(frame, "Choose Font", initialFont);
            if (font != null)
                text.setFont(font);
        });

        // Prompts the user to change the color of the background.
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
    
        // Prompts the user to change the color of the foreground(text).
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

        // Displays a message dialog that includes all the implemented extra credit items.
        JMenuItem extraCreditsMenuItem = new JMenuItem("Extra Credits...", KeyEvent.VK_X);  // Extra Credit
        extraCreditsMenuItem.addActionListener((ae) ->
        {
            JOptionPane.showMessageDialog(frame, "<html>Extra Credit implementations:<br>- Print<br>- Page Setup<br>- Replace",
                "About", JOptionPane.PLAIN_MESSAGE);
        });

        // Extra 5: Drag a file into the notepad and it opens the file (Drag & Drop)
            // Make sure no changes in current file have been made

        //-------------------------------

        // Displays message dialog about credits.
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

        // Initialize scrollable text area.
        text = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(text);
        Font initialFont = (new Font("Courier New", Font.PLAIN, 12)); // default or initial font
        text.setFont(initialFont);

        // Catches if a change has been made.
        text.addKeyListener(new KeyAdapter() 
        {
            public void keyTyped(KeyEvent ke)
            {
                unsavedChanges = true;
                System.out.println("text added or removed.");
            }
        });

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
        frame.add(scrollPane);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new JNotepad());
    }
}