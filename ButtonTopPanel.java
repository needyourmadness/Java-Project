package project;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ButtonTopPanel extends JPanel
{
	JButton save,open;
	JTextField saveField,openField;
	JPanel left,rigth;
	ButtonTopPanel()
	{
		this.setLayout(new GridLayout(1,2));
		this.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		//Lewy guzik
		left= new JPanel();
		save=new JButton("Save");
		saveField= new JTextField("Wpisz nazwe pliku");
		left.setLayout(new FlowLayout());
		left.add(saveField);
		left.add(save);
		
		//Prawy guzik
		rigth=new JPanel();
		open=new JButton("Open");
		openField=new JTextField("Wpisz nazwe pliku");
		rigth.setLayout(new FlowLayout());
		rigth.add(openField);
		rigth.add(open);

		this.add(left);
		this.add(rigth);
		
		
		
	}
}
