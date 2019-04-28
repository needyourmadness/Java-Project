package project;

import java.awt.Color;
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
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TopPanel extends JPanel
{
	MenuTopPanel menuPanel;
	MidPanel middle;
	LeftPanel left;
	TopPanel(MidPanel mid,LeftPanel lef)
	{
		left=lef;
		middle=mid;
		menuPanel=new MenuTopPanel(middle,left);
		this.setLayout(new GridLayout(1,1));
		this.add(menuPanel);

	}
}
