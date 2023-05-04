package finalProject;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;

public class Dialogs
{
	public class JFontChooser
	{
		static JList<String> fontList;
		static Font font;
		static String selection;

		static Font showDialog(JFrame parent, String title, Font initialFont)
		{
			JDialog dlg = new JDialog(parent, "Choose Font", true);
			dlg.setSize(500, 300);
			dlg.setLayout(new FlowLayout());
			dlg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			/*
			* format the dialog as the frame in project 3
			* use initialFont to set the selected font, size, and style
			* Add Ok and Cancel buttons under the sample text
			*/

			// Create font list with vertical slider and single selection.
			JPanel fontPanel = new JPanel();
        	fontPanel.setPreferredSize(new Dimension(200,150));
        	JLabel fonts = new JLabel("Fonts:", KeyEvent.VK_F);
			fontList = new JList<>(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
			JScrollPane vertScroll = new JScrollPane(fontList);
			vertScroll.getVerticalScrollBar().setFocusable(true);
			fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			fontList.addListSelectionListener((ListSelectionListener) new ListSelectionListener()
			{
				public void valueChanged(ListSelectionEvent ce)
				{
					int index = fontList.getSelectedIndex();
					if (index != -1)
					{
						selection = fontList.getSelectedValue().toString();
						//text.setFont(new Font(fontList.getSelectedValue().toString(), Font.PLAIN, 12));
					}
				}
			});
	
			fontPanel.add(fonts);
			fontPanel.add(vertScroll);

			// Create Ok and Cancel buttons.
			JButton ok = new JButton("Ok");
			ok.addActionListener((ae) -> 
			{
				font = new Font(selection, Font.PLAIN, 12);
				dlg.dispose();
			});
			dlg.add(ok);

			JButton cancel = new JButton("Cancel");
			cancel.addActionListener((ae) -> dlg.dispose());
			
			dlg.add(cancel);

			// Create panel to add buttons.
			JPanel panel = new JPanel();
			panel.add(ok);
			panel.add(cancel);

			// Add components to content pane.
			dlg.add(fontPanel, BorderLayout.WEST);
			dlg.add(panel, BorderLayout.SOUTH);

			dlg.setLocationRelativeTo(null);
			dlg.setVisible(true);

			return font;
		}
	}
}