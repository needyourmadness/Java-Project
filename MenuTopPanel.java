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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MenuTopPanel extends JPanel 
{
	
	JMenuBar menuBar;
	JMenu menu, submenu;
	JMenuItem menuItemSave,menuItemExit,menuItemOpen,menuItemStart;
	JRadioButtonMenuItem rbMenuItem;
	JCheckBoxMenuItem cbMenuItem;
	MidPanel mid;
	boolean check=true;
	MenuTopPanel(MidPanel middle)
	{
		mid=middle;
		this.setLayout(new GridLayout(1,1));
		
		//Menu
		menuBar= new JMenuBar();
		
		menu=new JMenu("A menu");
		menu.getAccessibleContext().setAccessibleDescription("bajerek");
		menuBar.add(menu);
		
		//Save
		menuItemSave = new JMenuItem("Save");
		menuItemSave.getAccessibleContext().setAccessibleDescription("Save");
		
		//Open
		menuItemOpen= new JMenuItem("Open");
		menuItemSave.getAccessibleContext().setAccessibleDescription("Open");
		
		//Exit
		menuItemExit=new JMenuItem("Exit");
		menuItemExit.getAccessibleContext().setAccessibleDescription("Exit");
		//Kontrola zdarzeñ
		ActionListener exitListener=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		};
		menuItemExit.addActionListener(exitListener);

		
		
		menuItemStart=new JMenuItem("Start");
		ActionListener startListener=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(check)
				{
					mid.startAnimation();
					check=false;
				}
				
			}
		};
		menuItemStart.addActionListener(startListener);
		menuItemStart.getAccessibleContext().setAccessibleDescription("Start");
		
		
		
		menu.add(menuItemStart);
		menu.add(menuItemSave);
		menu.add(menuItemOpen);
		menu.add(menuItemExit);
		this.add(menuBar);
	}

	

}
