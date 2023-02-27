/**
 * Name: Escubido, Jarisse
 * Project: #3
 * Due: 20 February 2023
 * Course: cs-2450-01-sp23
 * 
 * Description:
 *      Implementing an application that previews a phrase after font, style, or effects are added.
 */

 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

 public class JFontViewer {
    
    // global variables
    private JScrollBar horzScroll;
    private JScrollPane fontScroll;
    private JList<String> fontList;
    
    JFontViewer()
    {
        JFrame frame = new JFrame("JFontViewer");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setIconImage(new ImageIcon("JFontViewer.png").getImage());

        // Create JPanel for size and horizontal scrollbar.
        JPanel top = new JPanel(new GridLayout(0,1));
        JLabel size = new JLabel("Size:");
        horzScroll = new JScrollBar(Adjustable.HORIZONTAL);

        top.add(size);
        top.add(horzScroll);


        // Create JPanel for Fonts.
        JPanel left = new JPanel(new GridLayout(0,1));
        JLabel fonts = new JLabel("Fonts: ");
        fontList = new JList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());

        // Set single-selection mode and selection listener for fontList.
        fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fontList.setPreferredSize(new Dimension(120,90));
        fontList.addListSelectionListener((new ListSelectionListener() 
        {
            public void valueChanged(ListSelectionEvent le)
            {
                int index = fontList.getSelectedIndex();
            }
        }));



        // Create JPanel for Style.


        // Create JPanel for Effects.


        // Create JLabel for text to be chnaged.
        JLabel phrase = new JLabel("the quick brown fox jumps over the lazy dog 0123456789");


        // Add components to content pane.
        frame.add(top, BorderLayout.NORTH);


        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new JFontViewer());;
    }

 }