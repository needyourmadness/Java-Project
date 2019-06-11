package project;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import java.awt.*;

public class CheckboxListener implements ItemListener {
	
	int actionNumber;
	MidPanel mid;
	JComboBox box;
	Options opt;
	JSlider slide;
	JLabel label;
	
	public CheckboxListener(Options o, MidPanel m, JComboBox b, int number, JSlider s, JLabel l) {
		mid = m;
		box = b;
		actionNumber = number;
		opt = o;
		slide = s;
		label = l;
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if (box.getSelectedIndex() == 1) {
			mid.change[actionNumber] = true;
			opt.pos[2][actionNumber].add(slide);
			opt.pos[2][actionNumber].add(label);
			opt.pos[2][actionNumber].updateUI();
		}
		else {
			mid.change[actionNumber] = false;
			opt.pos[2][actionNumber].removeAll();
			opt.pos[2][actionNumber].updateUI();
		}
	}
}
