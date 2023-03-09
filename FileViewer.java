/**
 * Name:    Escubido, Jarisse
 * Project: #4
 * Due:     March 15, 2023
 * Course:  cs-2450-01-sp23
 * 
 * Description:
 *          A Swing application that opens a file.
 */

 import java.awt.*;
 import java.awt.event.*;
import javax.swing.*;
 import javax.swing.border.EtchedBorder;

 public class FileViewer
 {
    JTextArea text;

    FileViewer()
    {
        JFrame frame = new JFrame(" - File Viewer");
        frame.setSize(500,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        text = new JTextArea(); // Create Text Area
        text.setBorder(new EtchedBorder());
        text.setFont(new Font("Courier New",Font.PLAIN,12));  
        
        
        
        // Create a menu bar.
        JMenuBar menuBar = new JMenuBar();

        // Create Menus.
        JMenu file = new JMenu("File");
        JMenu format = new JMenu("Format");
        JMenu help = new JMenu("Help");

        // Components for File Menu
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(ae -> System.exit(0)); // Exit program when clicked.
        file.add(exit);

        // Components for Format Menu
        JMenuItem wordwrap = new JMenuItem("Word Wrap");
        JMenu font = new JMenu("Font"); // Create Font submenu
        JMenu color = new JMenu("Color");

        wordwrap.addActionListener(ae -> {text.setLineWrap(true);}); // wraps word across screen
        
        // Components for Font submenu.
        JMenuItem larger = new JMenuItem("Larger");
        larger.addActionListener(ae ->
        {
            int fontSize = text.getFont().getSize();
            if(fontSize < 20)
            {
                fontSize += 2;
                text.setFont(new Font("Courier New", Font.PLAIN, fontSize));
            }
        });
        
        JMenuItem smaller = new JMenuItem("Smaller");
        smaller.addActionListener(ae -> 
        {
            int fontSize = text.getFont().getSize();
            if(fontSize > 10)
            {
                fontSize -= 2;
                text.setFont(new Font("Courier New", Font.PLAIN, fontSize));
            }
        });
        
        font.add(larger);
        font.add(smaller);

        // Create components for Color submenu.
        JMenuItem defaultColor = new JMenuItem("Default");
        defaultColor.addActionListener(ae ->
        {
            text.setBackground(Color.WHITE);
            text.setForeground(Color.BLACK);
        });
        JMenuItem ibmColor = new JMenuItem("IBM");
        ibmColor.addActionListener(ae ->
        {
            Color navyBlue = new Color(0,0,128);
            text.setBackground(navyBlue);
            text.setForeground(Color.WHITE);
        });
        JMenuItem windowColor = new JMenuItem("Window");
        windowColor.addActionListener(ae ->
        {
            text.setBackground(Color.BLACK);
            text.setForeground(Color.WHITE);
        });

        color.add(defaultColor);
        color.addSeparator();
        color.add(ibmColor);
        color.add(windowColor);

        // Add components to Format Menu.
        format.add(wordwrap);
        format.add(font); // Add font as submenu to Format Menu
        format.add(color);

        // Create components for Help Menu.
        JMenuItem about = new JMenuItem("About");
        JLabel name = new JLabel("(c) Jarisse Escubido");
                    

        help.add(about);

        // Add menus to menu bar.
        menuBar.add(file);
        menuBar.add(format);
        menuBar.add(help);

        // Add components to the content pane.
        frame.setJMenuBar(menuBar);
        frame.add(text, BorderLayout.CENTER);

        // Display frame.
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new FileViewer());
    }
 }