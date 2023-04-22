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
import java.awt.event.*;

public class Dialogs {

    private static JFrame dialogFrame;

    public static Font showFontDialog(JFrame parent, String title, Font initialFont) 
    {
        dialogFrame.setSize(300, 200);
        
        dialogFrame = new JFrame("Choose Font");
        dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ChooseFont viewer = (ChooseFont) parent;
        JTextArea text = viewer.text;
        
        JScrollPane textAreaScrollPane = new JScrollPane(text);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(textAreaScrollPane, BorderLayout.CENTER);

        dialogFrame.add(panel); 
        dialogFrame.pack();
        dialogFrame.setLocationRelativeTo(parent); 
        dialogFrame.setVisible(true); 
        
        if (initialFont == text.getFont()) 
        {
            return null;
        } 
        else 
        {
            return text.getFont();
        }
    }
}
