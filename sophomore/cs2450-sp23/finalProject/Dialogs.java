package finalProject;



import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Dialogs
{
	static Font font;

	public static Font showDialog(JFrame parent, String title, Font initialFont)
	{
		JDialog dlg = new JDialog(parent, "Choose Font", true);
		dlg.setSize(550, 350);
		dlg.setLayout(new FlowLayout());
		dlg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Create font list with vertical slider and single selection.
		JPanel left = new JPanel(new FlowLayout());
		left.setPreferredSize(new Dimension(247,175));
		left.setBorder(new LineBorder(Color.BLACK));
		JLabel fonts = new JLabel("    Fonts:    ");
		fonts.setSize(50,50);
		JList<String> fontList = new JList<>(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
		JScrollPane vertScroll = new JScrollPane(fontList);
		fontList.setSelectedValue(initialFont.getFamily(), true);

		left.add(fonts);
		left.add(vertScroll);

		// Create JPanel for size and horizontal scrollbar.
		JPanel top = new JPanel(new GridLayout(0,1));
		top.setPreferredSize(new Dimension(500,90));
		top.setBorder(new LineBorder(Color.black));
		JLabel size = new JLabel(" Size:");
		JSlider horzSlider = new JSlider(JSlider.HORIZONTAL, 8,20,8);

		horzSlider.setPaintTicks(true);
		horzSlider.setPaintLabels(true);
		horzSlider.setMajorTickSpacing(2);
		horzSlider.setSnapToTicks(true);

		top.add(size);
		top.add(horzSlider);

		// Create JPanel for Style.
		JPanel right = new JPanel(new GridLayout(0, 1));
		right.setPreferredSize(new Dimension(247,175));
		right.setBorder(new LineBorder(Color.black));
		JLabel styleLabel = new JLabel("  Style:");
		ButtonGroup style = new ButtonGroup();
		JRadioButton regularStyle = new JRadioButton("Regular", true);
		regularStyle.setSelected(initialFont.getStyle() == Font.PLAIN);
		JRadioButton italicStyle = new JRadioButton("Italic", true);
		italicStyle.setSelected(initialFont.getStyle() == Font.ITALIC);
		JRadioButton boldStyle = new JRadioButton("Bold", true);
		boldStyle.setSelected(initialFont.getStyle() == Font.BOLD);
		
		style.add(regularStyle);
		style.add(italicStyle);
		style.add(boldStyle);
		right.add(styleLabel);
		right.add(regularStyle);
		right.add(italicStyle);
		right.add(boldStyle);

		// Create Ok and Cancel buttons.
		JButton ok = new JButton("Ok");
		ok.addActionListener((ae) -> 
		{
			String fontName = (String) fontList.getSelectedValue();
			int fontSize = horzSlider.getValue();
			int fontStyle = Font.PLAIN;
			if (regularStyle.isSelected()) {
				fontStyle = Font.PLAIN;
			}
			if (italicStyle.isSelected()) {
				fontStyle = Font.ITALIC;
			}
			if (boldStyle.isSelected()) {
				fontStyle = Font.BOLD;
			}
			Font font = new Font(fontName, fontStyle, fontSize);
			Dialogs.font = font;
			dlg.dispose();
		});
		dlg.add(ok);

		JButton cancel = new JButton("Cancel");
		cancel.addActionListener((ae) -> 
		{
			font = null;
			dlg.dispose();
		});
		dlg.add(cancel);

		// Create panel to add buttons.
		JPanel bottom = new JPanel();
		bottom.add(ok);
		bottom.add(cancel);

		// Add components to content pane.
		dlg.add(top, BorderLayout.NORTH);
		dlg.add(left, BorderLayout.WEST);
		dlg.add(right, BorderLayout.EAST);
		dlg.add(bottom, BorderLayout.SOUTH);

		dlg.setLocationRelativeTo(null);
		dlg.setVisible(true);

		return font;
	}
}