/**
 * Name: Escubido, Jarisse
 * Homework: #2
 * Due: 21 April 2023
 * Course: cs-2450-01-sp23
 * 
 * Description:
 *      Converts font viewer frame in project 3 into a font chooser dialog.
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.LineBorder;

    public class Dialogs {
    
    // global variables
    private JList<String> fontList;
    private JLabel text;
    
    static Font font;
    
    static Font showDialog(JFrame frame, String title, Font initialFont)
    {
        JDialog dlg = new JDialog(frame, "Choose Font", true);
        dlg.setSize(100, 150);
        dlg.setLayout(new FlowLayout());
        dlg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton ok = new JButton("Ok");
        ok.addActionListener((ae) -> 
        {
            font = new Font("Courier", 12, Font.PLAIN);
            dlg.dispose();
        });
        dlg.add(ok);

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener((ae) -> dlg.dispose());
        dlg.add(cancel);

        font = null;

        dlg.setLocationRelativeTo(frame);
        dlg.setVisible(true);

        return font;
    }
    

    Dialogs()
    {
        JFrame frame = new JFrame("JFontViewer");
        frame.setSize(650, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setIconImage(new ImageIcon("JFontViewer.png").getImage());

        JPanel south = new JPanel(new GridLayout(1, 2));
        JButton ok = new JButton("Ok");
        ok.addActionListener((ae) -> 
        {
            font = new Font("Courier", 12, Font.PLAIN);
            frame.dispose();
        });
        south.add(ok);

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener((ae) -> frame.dispose());
        south.add(cancel);

        // Create JPanel for size and horizontal scrollbar.
        JPanel top = new JPanel(new GridLayout(0,1));
        top.setPreferredSize(new Dimension(600,85));
        top.setBorder(new LineBorder(Color.black));
        JLabel size = new JLabel("Size:");
        size.setDisplayedMnemonic('S');
        size.setLabelFor(text);

        JSlider horzSlider = new JSlider(JSlider.HORIZONTAL, 8,20,8);
        horzSlider.setPaintTicks(true);
        horzSlider.setPaintLabels(true);
        horzSlider.setMajorTickSpacing(2);
        horzSlider.setSnapToTicks(true);
        
        horzSlider.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent ce)
            {
                text.setFont(new Font(
                text.getFont().getName(),
                text.getFont().getStyle(),
                horzSlider.getValue()
                ));
            }
        });

        top.add(size);
        top.add(horzSlider);


        // Create JPanel for Fonts.
        JPanel left = new JPanel(new FlowLayout());
        left.setPreferredSize(new Dimension(200,100));
        left.setBorder(new LineBorder(Color.black));
        JLabel fonts = new JLabel("Fonts:");
        fonts.setSize(50,50);
        fonts.setDisplayedMnemonic('F');
        // Create font list with vertical slider and single selection.
        fontList = new JList<>(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
        JScrollPane vertScroll = new JScrollPane(fontList);
        vertScroll.getVerticalScrollBar().setFocusable(true);
        fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fontList.addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent ce)
            {
                int index = fontList.getSelectedIndex();
                if (index != -1)
                {
                    text.setFont(new Font(
                        fontList.getSelectedValue(),
                        text.getFont().getStyle(),
                        horzSlider.getValue()
                    ));
                }
            }
        });

        left.add(fonts);
        left.add(vertScroll);
        

        // Create JPanel for Style.
        JPanel center = new JPanel(new GridLayout(0,1));
        center.setPreferredSize(new Dimension(200,100));
        center.setBorder(new LineBorder(Color.black));
        JLabel style = new JLabel("Style:");
        JRadioButton regularStyle = new JRadioButton("Regular", true);
        regularStyle.setMnemonic('R');
        regularStyle.addActionListener(al ->
        {
            text.setFont(new Font(
                text.getFont().getName(),
                Font.PLAIN,
                horzSlider.getValue()
                ));
        });
        JRadioButton italicStyle = new JRadioButton("Italic");
        italicStyle.setMnemonic('I');
        italicStyle.addActionListener(al ->
        {
            text.setFont(new Font(
                text.getFont().getName(),
                Font.ITALIC,
                horzSlider.getValue()
                ));
        });
        JRadioButton boldStyle = new JRadioButton("Bold");
        boldStyle.setMnemonic('B');
        boldStyle.addActionListener(al ->
        {
            text.setFont(new Font(
                text.getFont().getName(),
                Font.BOLD,
                horzSlider.getValue()
                ));
        });
        ButtonGroup styleType = new ButtonGroup();


        styleType.add(regularStyle);
        styleType.add(italicStyle);
        styleType.add(boldStyle);
        center.add(style);
        center.add(regularStyle);
        center.add(italicStyle);
        center.add(boldStyle);


        // Create JPanel for Effects.
        JPanel right = new JPanel(new GridLayout(0,1));
        right.setPreferredSize(new Dimension(200,100));
        right.setBorder(new LineBorder(Color.black));
        JLabel effects = new JLabel("Effects:");
        JCheckBox allCaps = new JCheckBox("All caps", false);
        allCaps.setMnemonic('c');

        allCaps.addActionListener((ae) ->
        {
            if(allCaps.isSelected()) 
                text.setText(text.getText().toUpperCase());
            else
                text.setText(text.getText().toLowerCase());
        });
        

        right.add(effects);
        right.add(allCaps);



        // Add components to content pane.
        frame.add(south, BorderLayout.SOUTH);
        frame.add(top, BorderLayout.NORTH);
        frame.add(left, BorderLayout.WEST);
        frame.add(center, BorderLayout.CENTER);
        frame.add(right, BorderLayout.EAST);


        frame.setVisible(true);
        frame.setLocationRelativeTo(frame);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new Dialogs());
    }
}
