package project;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class Graph extends JPanel
{
	int x;
	int y;
	Graph()
	{
		this.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		XYSeries series = new XYSeries("Nazwa serii");
		series.add(1, 1);
		series.add(1, 2);
		series.add(2, 4);
		series.add(6, 10);
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		//Tworzymy wykres XY
		JFreeChart chart = ChartFactory.createXYLineChart(
		"Wykres XY",//Tytul
		"Opis osi X", // opisy osi
		"Opis osi Y",
		dataset, // Dane
		PlotOrientation.VERTICAL, // Orjentacja wykresu
		false, // legenda
		false, // tooltips
		false
		
		);

		ChartPanel chartPanel=new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(300,300 ));
		JPanel paintpanel=new JPanel();
		this.add(chartPanel);
		
	}

	public void play()
	   {	
			this.setBackground(Color.white);
	        x++;
	        y++;
	        this.repaint();//tells the panel to redraw itself so we can see the circle in new location
	        try
	        {
	          	Thread.sleep(25);
	        }catch(Exception e)
	        {}
	   }

}
