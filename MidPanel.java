
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
	int y=0;
	Object m1,m2;
	Rope l1;
	boolean start=false;
	boolean stop=false;
	double dt;
	double k;
	double mi;
	double eta;
	double[] h;
	double mass1,mass2;
	LeftPanel left;
	boolean check=true;
	boolean[] change; //1 - czy m1 sie zmienia; 2 - to samo dla m2; 3 - czy dziala sila lepkosci; 4 - sila aleblablaniczna
	JOptionPaneMultiInput joption;
	double v01x,v01y,v01z,v02x,v02y,v02z;
	Toolkit toolkit;
	final BasicStroke dashed;
	Stroke rope;
	Color backCol, m1Col, m2Col;
	
	MidPanel(LeftPanel leftt)
	{
		left=leftt;
		v01x=0;
		v01y=0;
		v01z=0;
		v02x=0;
		v02y=0;
		v02z=0;
		toolkit=Toolkit.getDefaultToolkit();
		this.setMinimumSize(new Dimension(1000,1000));
		this.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.setBackground(Color.white);
		change = new boolean[5];
		h = new double[3];
		for (int i = 0; i<5; i++) change[i] = false;
		final float dash1[] = {10.0f};
    	dashed =
        new BasicStroke(2.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, dash1, 0.0f);
		rope=new ZigzagStroke(new BasicStroke(), 3, 3);
		backCol = Color.WHITE;
		m1Col = Color.GREEN;
		m2Col = Color.RED;
		
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
			g2d.setColor(Color.WHITE);
	   	 	g2d.fillRect(0,0,this.getWidth(),this.getHeight());
	   	 	
	   	 	
	   	 	Stroke stroke = dashed;
	   	 	g2d.setColor(Color.BLACK);
	   	 	g2d.setStroke(rope);
	   	 	g2d.drawLine((int) (paintm1.x+10),(int) (paintm1.y+10),(int) (paintm2.x+10),(int) (paintm2.y+10));
	   	 	
	   	 	g2d.setStroke(new BasicStroke(2));
	   	 	
	   	 	if(m1.z>m2.z)
	   	 	{
	   	 	g2d.setColor(m1Col);
	   	 	g2d.fillOval((int) paintm1.x,(int) paintm1.y,m1.sizex,m1.sizey);
	   		g2d.setColor(m2Col);
	   	 	g2d.fillOval((int) paintm2.x,(int) paintm2.y,m2.sizex,m2.sizey);
	   	 	}
	   	 	else
	   	 	{
	   	 	g2d.setColor(m1Col);
	   	 	g2d.fillOval((int) paintm2.x,(int) paintm2.y,m2.sizex,m2.sizey);
	   		g2d.setColor(m2Col);
	   	 	g2d.fillOval((int) paintm1.x,(int) paintm1.y,m1.sizex,m1.sizey);
	   	 	}
	   	  	

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
	   	 	g2d.setStroke(new ZigzagStroke(new BasicStroke(), 3, 3));
	   	 	g2d.drawLine((int) (paintm1.x+10),(int) (paintm1.y+10),(int) (paintm2.x+10),(int) (paintm2.y+10));
	   	 	
	   	 	g2d.setStroke(new BasicStroke(2));
	   		if(m1.z>m2.z)
	   	 	{
	   		g2d.setColor(m1Col);
	   	 	g2d.fillOval((int) paintm1.x,(int) paintm1.y,m1.sizex,m1.sizey);
	   		g2d.setColor(m2Col);
	   	 	g2d.fillOval((int) paintm2.x,(int) paintm2.y,m2.sizex,m2.sizey);
	   	 	}
	   	 	else
	   	 	{
	   	 	g2d.setColor(m1Col);
	   	 	g2d.fillOval((int) paintm2.x,(int) paintm2.y,m2.sizex,m2.sizey);
	   		g2d.setColor(m2Col);
	   	 	g2d.fillOval((int) paintm1.x,(int) paintm1.y,m1.sizex,m1.sizey);
	   	 	}
	   	}
	
    }
	
	
	void startAnimation()
	{
		start=true;
		m1=new Object(0,this.getHeight()/2,10,v01x,v01y,v01z,mass1);
		m2=new Object(100,this.getHeight()/2 ,0,v02x,v02y,v02z,mass2);
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
	void startOpenedAnimation(String filepath)
	{
		start=true;
		m1=new Object(0,this.getHeight()/2,0,0,0,0,mass1);
		m2=new Object(100,this.getHeight()/2 ,0,0,0,0,mass2);
		l1=new Rope(m1,m2);
		ExecutorService ex=Executors.newFixedThreadPool(1);
		ex.submit(new RunOpenedAnimation(left,this,filepath));
	}
}


