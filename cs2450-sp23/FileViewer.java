    /**
     * Name:    Escubido, Jarisse
     * Project: #4
     * Due:     March 15, 2023
     * Course:  cs-2450-01-sp23
     * 
     * Description:
     *          A Swing application that edits the contents of a file. 
     *          file to view is specified in command line argument: java FileViewer <textName.txt>
     */

    import java.awt.*;
    import java.awt.event.*;
    import java.io.*;
    import javax.swing.*;
    import javax.swing.border.EtchedBorder;

    public class FileViewer
    {
        // global variables
        static private JTextArea text;
        private JComboBox<String> fontlist;
        private JLabel name = new JLabel();
        static private String filename = "";
        static private int result;


        FileViewer()
        {
            JFrame frame = new JFrame(filename + " - File Viewer");
            frame.setSize(500,300);
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

            text = new JTextArea(); // Create Text Area
            text.setBorder(new EtchedBorder());
            text.setFont(new Font("Courier New",Font.PLAIN,12));  
            
            
            JMenuBar menuBar = new JMenuBar(); // Create a menu bar.

            /**
                * File Menu: 
                * Exit MenuItem simply exits the program.
            */
            JMenu file = new JMenu("File");
            file.setMnemonic(KeyEvent.VK_F);

            JMenuItem exit = new JMenuItem("Exit");
            exit.setMnemonic(KeyEvent.VK_X);
            exit.addActionListener((ae) ->
            {
                result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?",
                    "Select an Option", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }
            });
            
            file.add(exit); // Add component to content pane.


            /**
                * Format Menu:
                * First MenuItem allows for word to be in next line once it reaches the end of the screen.
                * Font Submenu changes the size of the font larger or smaller in increments of 2.
                * Color Submenu changes the color of the background and foreground w/ three preset settings.
            */ 
            JMenu format = new JMenu("Format");
            format.setMnemonic(KeyEvent.VK_O);

            JMenuItem wordwrap = new JMenuItem("Word Wrap", KeyEvent.VK_W);
            wordwrap.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
            wordwrap.addActionListener(ae -> 
                {
                    text.setLineWrap(true);
                    text.setWrapStyleWord(true);
                }); // Create newline when word reaches end of screen.


            JMenu font = new JMenu("Font"); // Create Font submenu
            font.setMnemonic(KeyEvent.VK_F);


            JMenuItem larger = new JMenuItem("Larger", KeyEvent.VK_L); // Create Larger MenuItem and increase font size by 2.
            larger.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, InputEvent.CTRL_MASK));

            larger.addActionListener(ae ->
            {
                int fontSize = text.getFont().getSize();
                if(fontSize < 20) // Maximum size to increase font is 20.
                {
                    fontSize += 2;
                    text.setFont(new Font("Courier New", Font.PLAIN, fontSize));
                }
            });
            
            JMenuItem smaller = new JMenuItem("Smaller", KeyEvent.VK_S); // Create Smaller MenuItem and decrease font size by 2.
            smaller.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, InputEvent.CTRL_MASK));;
            smaller.addActionListener(ae -> 
            {
                int fontSize = text.getFont().getSize();
                if(fontSize > 10) // Maximum size to decrease font is 10.
                {
                    fontSize -= 2;
                    text.setFont(new Font("Courier New", Font.PLAIN, fontSize));
                }
            });


            JMenu color = new JMenu("Color"); // Create Color Submenu.
            color.setMnemonic(KeyEvent.VK_C);

            JMenuItem defaultColor = new JMenuItem("Default");
            
            defaultColor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));;
            defaultColor.addActionListener(ae ->
            {
                text.setBackground(Color.WHITE);
                text.setForeground(Color.BLACK);
            });
            JMenuItem ibmColor = new JMenuItem("IBM");
            ibmColor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_MASK));;
            ibmColor.addActionListener(ae ->
            {
                Color navyBlue = new Color(0,0,128);
                text.setBackground(navyBlue);
                text.setForeground(Color.WHITE);
            });
            JMenuItem windowColor = new JMenuItem("Window");
            windowColor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_MASK));;
            windowColor.addActionListener(ae ->
            {
                text.setBackground(Color.BLACK);
                text.setForeground(Color.WHITE);
            });

            
            // Adding components to menus and content pane.
            font.add(larger);
            font.add(smaller);        

            color.add(defaultColor);
            color.addSeparator(); // Adds Line Separator to Format Menu.
            color.add(ibmColor);
            color.add(windowColor);

            format.add(wordwrap);
            format.add(font); // Add Font Submenu to Format Menu
            format.add(color); // Add Color Submenun to Format Menu


            /**
                * Create Help Menu:
                * About MenuItem displays my name in the NORTH for 5 seconds.
                */
            JMenu help = new JMenu("Help");
            help.setMnemonic(KeyEvent.VK_H);

            JMenuItem about = new JMenuItem("About");
            about.setMnemonic(KeyEvent.VK_A);

            
            about.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent ae)
                {
                    name.setText("(c) Jarisse Escubido)");
                    Timer timer = new Timer(5000, new ActionListener() 
                    {
                        public void actionPerformed(ActionEvent e) 
                        {
                            name.setText("");
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                }
            });
            
                        
            help.add(about); // Add component to Help Menu.
            

            // Create drop-down list where selected index changes font of the text.
                fontlist = new JComboBox<>(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
                fontlist.setSelectedIndex(14);
                fontlist.addActionListener(ae ->
            {
                    int fontSize = text.getFont().getSize();
                    String fontName = fontlist.getSelectedItem().toString();
                    text.setFont(new Font(fontName, Font.PLAIN, fontSize));
            });

            // Add menus to menu bar and to content pane.
            menuBar.add(file);
            menuBar.add(format);
            menuBar.add(help);

            frame.setJMenuBar(menuBar);
            frame.add(text, BorderLayout.CENTER);
            frame.add(fontlist, BorderLayout.SOUTH);
            frame.add(name, BorderLayout.NORTH);

            // Display frame.
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);

        }

        public static void main(String[] args)
        {
            SwingUtilities.invokeLater(() -> new FileViewer());

            try
            {
                if(!args[0].isEmpty())
                {
                    filename = args[0];
                }
            }
            catch(Exception e)
            {
                text.setText("Argument is Empty");
                System.out.println(e + ": Argument is Empty");
            }

            try
            {
                FileReader reader = new FileReader(filename);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line;
                while ((line = bufferedReader.readLine()) != null)
                {
                    text.append(line + "\n");
                }
                bufferedReader.close();
            }
            catch(IOException e)
            {
                text.setText("Error has occured while reading file.");
                System.out.println(e + " Error has occured while reading file.");
            }
        }
    }