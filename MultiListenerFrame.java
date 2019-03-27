package project;
import java.awt.*;
import javax.swing.*; 
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MultiListenerFrame extends JFrame 
{
	
	Options prawy;
	MidPanel mid;
	LeftPanel left;
	BottomPanel bottom;
	TopPanel topPanel;
	JSlider slider;
	JLabel label;
	Color bgcolor;
	JButton button;
	JTextField field;
	int colorNumber;
	int wartoscslajderka;
	// Menu testy
	
	JRadioButton r1,r2,r3;
	MultiListenerFrame()
	{
		this.setSize(1000,1440);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout(10,10));
		//gorny panel ze sliderem
		
		topPanel=new TopPanel();
		
		//dolny panel z guziczkiem
		bottom=new BottomPanel();
		
		
		//lewy panel
		left= new LeftPanel();

		
		prawy=new Options();
		
		mid = new MidPanel();
		
		this.add(topPanel,BorderLayout.PAGE_START);
		this.add(bottom,BorderLayout.PAGE_END);
		this.add(left,BorderLayout.LINE_START);
		this.add(mid,BorderLayout.CENTER);
		this.add(prawy,BorderLayout.LINE_END);
	}
	public static void main(String[] args)
	{
		
		MultiListenerFrame frame=new MultiListenerFrame();
		frame.setVisible(true);
		while(true)
		{
			frame.mid.play();
			frame.left.panelgraph1x.graph.play();
			frame.left.panelgraph1y.graph.play();
			frame.left.panelgraph2x.graph.play();
			frame.left.panelgraph2y.graph.play();
		}
	}

	

}
