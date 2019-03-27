package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
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

public class MidPanel extends JPanel
{
	int x=0;
	Random rand;
	int y=0;
	MidPanel()
	{
		rand=new Random();
		this.setMinimumSize(new Dimension(1000,1000));
		this.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	}
	public void paintComponent(Graphics g)
    { 
		//System.out.println("dziala");
		this.setBackground(Color.white);
		g.setColor(Color.WHITE);
   	 	g.fillRect(0,0,this.getWidth(),this.getHeight());
   	 	g.setColor(Color.GREEN);
   	 	g.fillOval(x,y,Math.abs(150-x),Math.abs(150-y));
   	 	g.setColor(Color.RED);
	 	g.fillOval(x,y+300,Math.abs(150-y),Math.abs(150-x));
	 	g.setColor(Color.BLUE);
	 	g.fillOval(x+300,y,Math.abs(150-x),Math.abs(150-y));
   	 	if(x>=this.getWidth())
   	 	{
   	 		//System.out.println("elo elo");
   	 		x=0;
   	 		y=0;
   	 	}
   	 	else if(y>=this.getHeight())
   	 	{
   	 		x=0;
   	 		y=0;
   	 	}
    }

	public void play()
	   {
			//System.out.println("dziala");
			this.setBackground(Color.white);
	        	x+=3;
	            y+=3;
	            this.repaint();//tells the panel to redraw itself so we can see the circle in new location
	            try
	            {
	            	Thread.sleep(5);
	            }catch(Exception e)
	            {}
	            
	   }

}
