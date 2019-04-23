package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
	Object m1,m2;
	Rope l1;
	boolean start=false;
	double dt;
	double k;
	double mass1,mass2;
	MidPanel()
	{

	
		this.setMinimumSize(new Dimension(1000,1000));
		this.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.setBackground(Color.white);
		
	}
	public void paintComponent(Graphics g)
    { 
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		if(start)
		{
			//System.out.println("dziala");
			g2d.setColor(Color.WHITE);
	   	 	g2d.fillRect(0,0,this.getWidth(),this.getHeight());
	   	 	
	   	 	g2d.setColor(Color.GREEN);
	   	 	g2d.fillOval((int) m1.x,(int) m1.y,30,30);
	   	 	g2d.setColor(Color.RED);
	   	 	g2d.fillOval((int) m2.x,(int) m2.y,30,30);
	   	 	if(m1.x>=this.getWidth() )
	   	 	{
	   	 		m1.x=m1.x-this.getWidth()/2;
	   	 		m2.x=m2.x-this.getWidth()/2;
	   	 	}
	   	 		
	   	 	else if (m2.x>=this.getWidth())
	   	 	{
	   	 		m1.x=m1.x-this.getWidth()/2;
	   	 		m2.x=m2.x-this.getWidth()/2;
	   	 	}
	   	 	else if(m1.y>=this.getHeight())
	   	 	{
	   	 		m1.y=m1.y-this.getHeight()/2;
	   	 		m2.y=m2.y-this.getHeight()/2;
	   	 	}
	   	 	else if(m2.y>=this.getHeight())
	   	 	{
	   	 		m1.y=m1.y-this.getHeight()/2;
	   	 		m2.y=m2.y-this.getHeight()/2;
	   	 	}
		}
	
    }
	
	
	void startAnimation()
	{
		start=true;
		m1=new Object(0,this.getHeight()/2,0,0,0,0,mass1);
		m2=new Object(100,this.getHeight()/2 ,0,20,0,0,mass2);
		l1=new Rope(m1,m2);
		ExecutorService ex=Executors.newFixedThreadPool(1);
		ex.submit(new RunAnimation(this));
	}

}
