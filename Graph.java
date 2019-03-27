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

public class Graph extends JPanel
{
	int x;
	int y;
	int i;
	int j;
	Random rand;
	Color[] rgb;
	Graph()
	{
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		rgb=new Color[3];
	}
	public void paintComponent(Graphics g)
    { 
		
		for(int i=0;i<3;i++)
		{
			rgb[i]=new Color(x,y,Math.abs(x-y));
		}
		//System.out.println("dziala");
		this.setBackground(Color.white);
		g.setColor(Color.white);
   	 	g.fillRect(0,0,this.getWidth(),this.getHeight());
   	 	g.setColor(rgb[1]);
   	 	g.fillOval(x,y,100,100);
   	 	if(x>=this.getWidth())
   	 	{
   	 		//System.out.println("elo elo");
   	 		x=0;
   	 	}
   	 	else if(y>=this.getHeight())
   	 	{
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
