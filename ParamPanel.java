package project;

import java.awt.*;

import javax.swing.*;

public class ParamPanel extends JPanel {
	JPanel up;
	JPanel down;
	
	public ParamPanel() {
		this.setLayout(new GridLayout(2,1));
		up = new JPanel();
		down = new JPanel();
		this.add(up);
		this.add(down);
	}
}
