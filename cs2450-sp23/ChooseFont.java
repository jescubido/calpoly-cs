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

import finalProject.Dialogs;

public class ChooseFont extends JFrame{
    
    // global variables
    private JFrame frame;
    Font initialFont;
    JLabel fontName;
    

    ChooseFont()
    {
        frame = new JFrame("Choose Font");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setIconImage(new ImageIcon("JFontViewer.png").getImage());

        JLabel emptyLabel = new JLabel(" ");
        JLabel fontName = new JLabel(" ", (int) CENTER_ALIGNMENT);
        JButton chooseFontButton = new JButton("Font");
        chooseFontButton.addActionListener((ae) ->
        {
            initialFont = fontName.getFont();
            Font font = Dialogs.showDialog(frame, "Choose Font", initialFont);
            if (font != null)
                fontName.setFont(font);
                fontName.setText(font.getName().toString());
        });


        frame.add(emptyLabel, BorderLayout.NORTH);
        frame.add(chooseFontButton, BorderLayout.CENTER);
        frame.add(fontName, BorderLayout.SOUTH);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new ChooseFont());
    }
}
