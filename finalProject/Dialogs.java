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
import java.awt.*;

public class Dialogs
{
	public class JFontChooser
	{
		static Font font;

		static Font showDialog(JFrame frame, String title, Font initialFont)
		{
			JDialog dlg = new JDialog(frame, "Choose Font", true);
			dlg.setSize(100, 150);
			dlg.setLayout(new FlowLayout());
			dlg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			/*
			* format the dialog as the frame in project 3
			* use initialFont to set the selected font, size, and style
			* Add Ok and Cancel buttons under the sample text
			*/

			// Create Ok and Cancel buttons.
			JButton ok = new JButton("Ok");
			ok.addActionListener((ae) -> {
				Font font = JFontChooser.showDialog(null, "Select Font", null);
				dlg.dispose();
			});
			dlg.add(ok);

			JButton cancel = new JButton("Cancel");
			cancel.addActionListener((ae) -> {
				dlg.dispose();
			});
			dlg.add(cancel);

			// Create panel to add buttons.
			JPanel panel = new JPanel();
			panel.add(ok);
			panel.add(cancel);

			// Add components to content pane.
			dlg.add(panel, BorderLayout.SOUTH);

			font = null;
			dlg.setLocationRelativeTo(frame);
			dlg.setVisible(true);

			return font;
		}
	}
}