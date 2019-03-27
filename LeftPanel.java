package project;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LeftPanel extends JPanel
{
	//JButton button1, button2;
	
	GraphPanel panelgraph1x,panelgraph1y,panelgraph2x,panelgraph2y;
	LeftPanel()
	{
		this.setLayout(new GridLayout(4,1,10,10));
		this.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		panelgraph1x=new GraphPanel();
		panelgraph1y=new GraphPanel();
		panelgraph2x=new GraphPanel();
		panelgraph2y=new GraphPanel();
		
		JLabel title1x= new JLabel("	Wykres x1(t)	");
		JLabel title1y= new JLabel("	Wykres y1(t)	");
		JLabel title2x= new JLabel("	Wykres x2(t)	");
		JLabel title2y= new JLabel("	Wykres y2(t)	");
		
		panelgraph1x.add(title1x,BorderLayout.PAGE_START);
		panelgraph1y.add(title1y,BorderLayout.PAGE_START);
		panelgraph2x.add(title2x,BorderLayout.PAGE_START);
		panelgraph2y.add(title2y,BorderLayout.PAGE_START);
		
	
		
		this.add(panelgraph1x);
		this.add(panelgraph1y);
		this.add(panelgraph2x);
		this.add(panelgraph2y);

	}
}
