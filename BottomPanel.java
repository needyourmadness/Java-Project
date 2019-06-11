package project;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BottomPanel extends JPanel {
	JLabel step;
	JSlider stepSlider;
	JLabel stepEcho;
	JPanel panel1,panel2,panel3;
	MidPanel middle;
	public BottomPanel(MidPanel mid) 
	{
		middle=mid;
		this.setLayout(new GridLayout(1,3));
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		panel2.setLayout(new BorderLayout());
		step = new JLabel("Krok symulacji",SwingConstants.CENTER);
		panel2.add(step, BorderLayout.PAGE_START);
		
		stepSlider = new JSlider(1, 100, 10);
		panel2.add(stepSlider, BorderLayout.CENTER);
		
		stepEcho = new JLabel(String.format("%.2f s", (double) stepSlider.getValue()),SwingConstants.CENTER);
		panel2.add(stepEcho, BorderLayout.LINE_END);
		stepSlider.addChangeListener(new ValueListener(stepEcho, stepSlider, 1000, "s",middle,3));
		stepSlider.setMajorTickSpacing(50);
		stepSlider.setMinorTickSpacing(5);
		stepSlider.setPaintTicks(true);
	}
}
