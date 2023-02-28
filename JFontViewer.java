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
    private JLabel phrase;
    
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


        // Create JLabel for text to be changed.
        JLabel text = new JLabel("the quick brown fox jumps over the lazy dog 0123456789");


        //Create JPanel for Fonts.
        JPanel left = new JPanel(new GridLayout(0,1));
        JLabel fonts = new JLabel("Fonts: ");
        fontList = new JList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());

        left.add(fonts);
        left.add(fontList);

        // Set single-selection mode and selection listener for fontList.
        



        // Create JPanel for Style.
        JPanel center = new JPanel(new GridLayout(0,1));
        JLabel style = new JLabel("Style: ");
        JRadioButton regular = new JRadioButton("Regular", true);
        JRadioButton italic = new JRadioButton("Italic");
        JRadioButton bold = new JRadioButton("Bold");
        ButtonGroup styleType = new ButtonGroup();

        styleType.add(regular);
        styleType.add(italic);
        styleType.add(bold);
        center.add(style);
        center.add(regular);
        center.add(italic);
        center.add(bold);




        // Create JPanel for Effects.
        JPanel right = new JPanel(new GridLayout(0,1));
        JLabel effects = new JLabel("Effects: ");
        JCheckBox allCaps = new JCheckBox("All caps", false);
        ButtonGroup rightBtnGrp = new ButtonGroup();

        rightBtnGrp.add(allCaps);
        right.add(effects);
        right.add(allCaps);


        // Add components to content pane.
        frame.add(text, BorderLayout.SOUTH);
        frame.add(top, BorderLayout.NORTH);
        frame.add(left, BorderLayout.WEST);
        frame.add(center, BorderLayout.CENTER);
        frame.add(right, BorderLayout.EAST);


        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new JFontViewer());;
    }

 }
