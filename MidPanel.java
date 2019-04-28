package project;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.Stroke;
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

import org.jfree.data.xy.XYSeries;

public class MidPanel extends JPanel
{
	int x=0;
	Random rand;
	int y=0;
	Object m1,m2;
	Rope l1;
	boolean start=false;
	boolean stop=false;
	double dt;
	double k;
	double mass1,mass2;
	LeftPanel left;
	boolean check=true;
	CartesianPanel background;
	MidPanel(LeftPanel leftt)
	{
		left=leftt;
		this.setMinimumSize(new Dimension(1000,1000));
		this.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.setBackground(Color.white);
		
	}
	public void paintComponent(Graphics g)
    { 
		super.paintComponent(g);
		
		if(start)
		{
			
			
			Graphics2D g2d = (Graphics2D) g;
			
			Object paintm1,paintm2;
			paintm1=new Object(m1.x,m1.y,m1.z,m1.vx,m1.vy,m1.vz,m1.mass);
			paintm2=new Object(m2.x,m2.y,m2.z,m2.vx,m2.vy,m2.vz,m2.mass);
			int w=(int) (paintm1.x/this.getWidth());
			if(paintm1.x>=this.getWidth() )
	   	 	{
	   	 		paintm1.x=paintm1.x-k*this.getWidth();
	   	 		paintm2.x=paintm2.x-k*this.getWidth();
	   	 	}
	   	 		
	   	 	else if (paintm2.x>=this.getWidth())
	   	 	{
	   	 		paintm1.x=paintm1.x-this.getWidth()/2;
	   	 		paintm2.x=paintm2.x-this.getWidth()/2;
	   	 	}
	   	 	else if(paintm1.y>=this.getHeight())
	   	 	{
	   	 		paintm1.y=paintm1.y-this.getHeight()/2;
	   	 		paintm2.y=paintm2.y-this.getHeight()/2;
	   	 	}
	   	 	else if(paintm2.y>=this.getHeight())
	   	 	{
	   	 		paintm1.y=paintm1.y-this.getHeight()/2;
	   	 		paintm2.y=paintm2.y-this.getHeight()/2;
	   	 	}
			g2d.setStroke(new BasicStroke());
			//System.out.println("dziala");
			g2d.setColor(Color.WHITE);
	   	 	g2d.fillRect(0,0,this.getWidth(),this.getHeight());
	   	 	
	   	 	Stroke stroke = new BasicStroke(2f);
	   	 	g2d.setColor(Color.BLACK);
	   	 	g2d.setStroke(stroke);
	   	 	g2d.drawLine((int) paintm1.x,(int) paintm1.y,(int) paintm2.x,(int) paintm2.y);
	   		
	   	  	g2d.setColor(Color.GREEN);
	   	 	g2d.fillOval((int) paintm1.x,(int) paintm1.y,30,30);
	   	 	g2d.setColor(Color.RED);
	   	 	g2d.fillOval((int) paintm2.x,(int) paintm2.y,30,30);

		}
		else if(stop)
		{
			Graphics2D g2d = (Graphics2D) g;
			Object paintm1,paintm2;
			paintm1=new Object(m1.x,m1.y,m1.z,m1.vx,m1.vy,m1.vz,m1.mass);
			paintm2=new Object(m2.x,m2.y,m2.z,m2.vx,m2.vy,m2.vz,m2.mass);
			
			g2d.setColor(Color.WHITE);
	   	 	g2d.fillRect(0,0,this.getWidth(),this.getHeight());
	   	 	g2d.setColor(Color.BLACK);
	   		g2d.drawLine((int) paintm1.x,(int) paintm1.y,(int) paintm2.x,(int) paintm2.y);
	   	 	g2d.setColor(Color.GREEN);
	   	 	g2d.fillOval((int) paintm1.x,(int) paintm1.y,30,30);
	   	 	g2d.setColor(Color.RED);
	   	 	g2d.fillOval((int) paintm2.x,(int) paintm2.y,30,30);
		}
	
    }
	
	
	void startAnimation()
	{
		start=true;
		m1=new Object(0,this.getHeight()/2,0,0,0,0,mass1);
		m2=new Object(100,this.getHeight()/2 ,0,20,10,0,mass2);
		l1=new Rope(m1,m2);
		this.repaint();
		ExecutorService ex=Executors.newFixedThreadPool(1);
		ex.submit(new RunAnimation(this,left));
	}
	void continueAnimation()
	{
		start=true;
		this.repaint();
		ExecutorService ex=Executors.newFixedThreadPool(1);
		ex.submit(new RunAnimation(this,left));
	}

}
