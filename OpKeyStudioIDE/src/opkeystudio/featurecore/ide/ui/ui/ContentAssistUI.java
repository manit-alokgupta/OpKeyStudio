package opkeystudio.featurecore.ide.ui.ui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Color;

public class ContentAssistUI extends JPanel {

	/**
	 * Create the panel.
	 */
	public ContentAssistUI() {
		setBorder(new CompoundBorder());
		setLayout(new GridLayout(1, 0, 0, 0));

		JList list = new JList();
		list.setBackground(Color.YELLOW);
		add(list);

	}

}
