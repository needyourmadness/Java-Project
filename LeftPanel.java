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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class LeftPanel extends JPanel
{
	//JButton button1, button2;
	
	GraphPanel panelgraph1x,panelgraph1y,panelgraph2x,panelgraph2y;
	int preferedx,preferedy;
	XYSeries series1x;
	XYSeries series1y;
	XYSeries series2x;
	XYSeries series2y;
	LeftPanel()
	{
		preferedx=300;
		preferedy=230;
		this.setLayout(new GridLayout(4,1));
		this.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		series1x = new XYSeries("x1(t)");
		series1y=new XYSeries("y1(t)");
		series2x=new XYSeries("x2(t)");
		series2y=new XYSeries("y2(t)");
		
		panelgraph1x=new GraphPanel("x1(t)",series1x,preferedx,preferedy);
		panelgraph1y=new GraphPanel("y1(t)",series1y,preferedx,preferedy);
		panelgraph2x=new GraphPanel("x2(t)",series2x,preferedx,preferedy);
		panelgraph2y=new GraphPanel("y2(t)",series2y,preferedx,preferedy);
		
		this.add(panelgraph1x);
		this.add(panelgraph1y);
		this.add(panelgraph2x);
		this.add(panelgraph2y);

	}
}
