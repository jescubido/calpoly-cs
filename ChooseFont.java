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

    public class ChooseFont 
    {

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
    }