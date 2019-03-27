package project;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.*;

public class ValueListener implements ChangeListener {
	
	JLabel label;
	JSlider slider;
	double divider;
	String unit;
	
	public ValueListener(JLabel l, JSlider s, String u) {
		this.label = l;
		this.slider = s;
		this.divider = 1;
		this.unit = u;
	}
	
	public ValueListener(JLabel l, JSlider s, double d, String u) {
		this.label = l;
		this.slider = s;
		this.divider = d;
		this.unit = u;
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		String value = String.format("%.2f " + unit, slider.getValue()/divider);
		label.setText(value);

	}

}