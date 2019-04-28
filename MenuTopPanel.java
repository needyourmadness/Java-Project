package project;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeriesCollection;

import com.orsonpdf.PDFDocument;
import com.orsonpdf.PDFGraphics2D;
import com.orsonpdf.Page;

public class MenuTopPanel extends JPanel 
{
	
	JMenuBar menuBar;
	JMenu menu, submenu;
	JMenuItem menuItemSave,menuItemStop,menuItemExit,menuItemOpen,menuItemStart,menuItemContinue;
	JRadioButtonMenuItem rbMenuItem;
	JCheckBoxMenuItem cbMenuItem;
	MidPanel mid;

	boolean cont=false;
	LeftPanel left;
	MenuTopPanel(MidPanel middle,LeftPanel lef)
	{
		left=lef;
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
		ActionListener saveListener=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String filepath;
				JFileChooser filechooser=new JFileChooser();
				filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result=filechooser.showSaveDialog(mid);
				if(result==JFileChooser.APPROVE_OPTION)
				{
					filepath=(filechooser.getSelectedFile().getAbsolutePath());
					
					
		    		// Tworzenie nowej klasy reprezentujacej dokument PDF
					PDFDocument pdfDoc = new PDFDocument();
		        
		        	// opcjonalne ustawianie tytulu i autora PDF:
		        	pdfDoc.setTitle("Uzyskane Wyniki");
		        	
		        
		        	// dodawanie nowej strony do klasy reprezentujacej PDF
		        	Page page = pdfDoc.createPage(new Rectangle(794, 1123));
		        	PDFGraphics2D g2 = page.getGraphics2D();
		        	left.panelgraph1x.chart.draw(g2, new Rectangle(0,0,794, 562),new Point(0,0), null);
		        	left.panelgraph1y.chart.draw(g2, new Rectangle(0,562,794, 562),new Point(562,100), null);
		        
		        	Page page2 = pdfDoc.createPage(new Rectangle(794, 1123));
		           	PDFGraphics2D g2page2 = page2.getGraphics2D();
		        	left.panelgraph2x.chart.draw(g2page2, new Rectangle(0,0,794, 562),new Point(0,0), null);
		        	left.panelgraph2y.chart.draw(g2page2, new Rectangle(0,562,794, 562),new Point(562,100), null);
			    	
			    	// Tworzenie pustego pliku 
			    	File file = new File(filepath+"/Dane.pdf");
					// zapis do pliku zawartosci dodanej do obiektu pdfDoc
					pdfDoc.writeToFile(file);
					try
					{
						PrintWriter zapis=new PrintWriter(filepath+"/Wyniki.txt");
						int i=0;
						zapis.println("#"+"\t"+"Czas t"+"\t"+"x1"+"\t"+"y1"+"\t"+"x2"+"\t"+"y2");
						for(double w:mid.m1.xn)
					    {
							 
							zapis.println((i*mid.dt)+"\t"+mid.m1.xn.get(i)+"\t"+mid.m1.yn.get(i)+"\t"+mid.m2.xn.get(i)+"\t"+mid.m2.yn.get(i));
							i++;
					    }
					    zapis.close();
					} 
					catch (FileNotFoundException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		};	
		menuItemSave.addActionListener(saveListener);
		//Open
		menuItemOpen= new JMenuItem("Open");
		menuItemOpen.getAccessibleContext().setAccessibleDescription("Open");
		
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
				if(mid.check)
				{
					if(mid.stop)
					{
						left.series1x.clear();
						left.series1y.clear();
						left.series2x.clear();
						left.series2y.clear();
					}
					mid.stop=false;
					mid.check=false;
					mid.startAnimation();
				}
				
			}
		};
		menuItemStart.addActionListener(startListener);
		menuItemStart.getAccessibleContext().setAccessibleDescription("Start");
		
		menuItemStop=new JMenuItem("Stop");
		ActionListener stopListener=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				mid.check=true;
				cont=true;
				mid.stop=true;
				mid.start=false;
				
			}
		};
		menuItemStop.addActionListener(stopListener);
		menuItemStop.getAccessibleContext().setAccessibleDescription("Stop");
		
		menuItemContinue=new JMenuItem("Continue");
		ActionListener continueListener=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(cont)
				{
					mid.stop=false;
					mid.check=false;
					mid.continueAnimation();
					cont=false;
				}
				else if(!mid.stop)
				{
					JOptionPane.showMessageDialog(mid, "You have to stop simulation first");
				}
				else
				{
					JOptionPane.showMessageDialog(mid, "You haven't started simulation yet !!!!");
				}
				
			}
		};
		menuItemContinue.addActionListener(continueListener);
		menuItemContinue.getAccessibleContext().setAccessibleDescription("Continue");
		menu.add(menuItemStart);
		menu.add(menuItemContinue);
		menu.add(menuItemStop);
		menu.add(menuItemSave);
		menu.add(menuItemOpen);
		menu.add(menuItemExit);
		this.add(menuBar);
	}

	

}
