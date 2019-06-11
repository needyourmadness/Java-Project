package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

public class ForceListener implements ActionListener {
	
	int action;
	MidPanel mid;
	JCheckBox box;
	
	public ForceListener(int a, MidPanel m, JCheckBox b) {
		action = a;
		mid = m;
		box = b;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mid.change[action] = box.isSelected();

	}

}
