package project;
import java.awt.*;
import javax.swing.*; 
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Window extends JFrame 
{
	
	Options right;
	MidPanel mid;
	LeftPanel left;
	BottomPanel bottom;
	TopPanel topPanel;

	Window()
	{
		this.setSize(1000,1440);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout(10,10));
		left= new LeftPanel();
		mid = new MidPanel(left);
		bottom=new BottomPanel(mid);
		topPanel=new TopPanel(mid,left);


		right=new Options(mid);
		
	
	
		this.add(topPanel,BorderLayout.PAGE_START);
		this.add(bottom,BorderLayout.PAGE_END);
		this.add(left,BorderLayout.LINE_START);
		this.add(mid,BorderLayout.CENTER);
		this.add(right,BorderLayout.LINE_END);
		
		
		
	}

	public static void main(String[] args)
	{
		//W¹tki
		SwingUtilities.invokeLater(new Runnable()
		{
			
		@Override
		public void run() {
			Window frame=new Window();
			frame.setVisible(true);
									
		}
		
		});
	}

	

}
