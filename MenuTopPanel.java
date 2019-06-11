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
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
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
	JMenu menu, lngMenu,layMenu;
	JMenuItem menuItemSave,menuItemStop,menuItemExit,menuItemOpen,menuItemStart,menuItemContinue,menuItemEng, menuItemPl,menuItemCol;
	JRadioButtonMenuItem rbMenuItem;
	JCheckBoxMenuItem cbMenuItem;
	MidPanel mid;
	Options optionsPanel;

	boolean cont=false;
	LeftPanel left;
	Window window;
	MenuTopPanel(MidPanel middle,LeftPanel lef,Options rigth,Window wind)
	{
		window=wind;
		optionsPanel=rigth;
		left=lef;
		mid=middle;
		this.setLayout(new GridLayout(1,1));
		
		//Create menu
		menuBar= new JMenuBar();
		//Options menu
		menu=new JMenu(optionsPanel.options.getString("menu"));
		menu.getAccessibleContext().setAccessibleDescription(optionsPanel.options.getString("menu"));
		menuBar.add(menu);
		//Language menu
		lngMenu=new JMenu(optionsPanel.options.getString("lngmenu"));
		lngMenu.getAccessibleContext().setAccessibleDescription(optionsPanel.options.getString("lngmenu"));
		menuBar.add(lngMenu);
		
		layMenu=new JMenu(optionsPanel.options.getString("colmenu"));
		menuBar.add(layMenu);
		//Polish Language option
		menuItemPl= new JMenuItem(optionsPanel.options.getString("polish"));
		menuItemPl.setToolTipText(optionsPanel.options.getString("ttpl"));
		ActionListener polishListener=new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						optionsPanel.options=ResourceBundle.getBundle("MessagesBundle_pl_PL",optionsPanel.locale);
						changeLanguage();
					}
			
				};
		menuItemPl.addActionListener(polishListener);
		
		
		menuItemEng= new JMenuItem(optionsPanel.options.getString("english"));
		menuItemEng.setToolTipText(optionsPanel.options.getString("tteng"));
		ActionListener englishListener=new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						optionsPanel.options=ResourceBundle.getBundle("MessagesBundle_en_GB",optionsPanel.locale);
						changeLanguage();
					}
			
				};
		menuItemEng.addActionListener(englishListener);
		lngMenu.add(menuItemPl);
		lngMenu.add(menuItemEng);
		
		//Save
		menuItemSave = new JMenuItem(optionsPanel.options.getString("save"));
		menuItemSave.getAccessibleContext().setAccessibleDescription(optionsPanel.options.getString("save"));
		ActionListener saveListener=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String filepath;
				JFileChooser filechooser=new JFileChooser();
				filechooser.setCurrentDirectory(new File("/home/me/Documents"));
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
		        	left.panelgraph1x.chart.draw(g2, new Rectangle(0,0,794, 370),new Point(0,0), null);
		        	left.panelgraph1y.chart.draw(g2, new Rectangle(0,370,794, 370),new Point(562,100), null);
		        	left.panelgraph1z.chart.draw(g2, new Rectangle(0,740,794, 370),new Point(562,100), null);
		        	Page page2 = pdfDoc.createPage(new Rectangle(794, 1123));
		           	PDFGraphics2D g2page2 = page2.getGraphics2D();
		        	left.panelgraph2x.chart.draw(g2page2, new Rectangle(0,0,794, 370),new Point(0,0), null);
		        	left.panelgraph2y.chart.draw(g2page2, new Rectangle(0,370,794, 370),new Point(562,100), null);
		        	left.panelgraph2z.chart.draw(g2page2, new Rectangle(0,740,794, 370),new Point(562,100), null);
			    	// Tworzenie pustego pliku 
			    	File file = new File(filechooser.getSelectedFile()+".pdf");
					// zapis do pliku zawartosci dodanej do obiektu pdfDoc
					pdfDoc.writeToFile(file);
					try
					{
						PrintWriter zapis=new PrintWriter(filechooser.getSelectedFile()+".txt");
						zapis.println("#"+"\t"+"Czas t"+"\t"+"x1"+"\t"+"y1"+"\t"+"z1"+"\t"+"x2"+"\t"+"y2"+"\t"+"z2");
						zapis.println("!"+"\t"+"dt:"+"\t"+mid.dt);
						for(int i=0;i<(mid.m1.xn.size()-2);i++)
					    {
							 
							zapis.println((i*mid.dt)+"\t"+mid.m1.xn.get(i)+"\t"+mid.m1.yn.get(i)+"\t"+mid.m1.zn.get(i)+"\t"+mid.m2.xn.get(i)+"\t"+mid.m2.yn.get(i)+"\t"+mid.m2.zn.get(i));
							i++;
					    }
					    zapis.close();
					} 
					catch (FileNotFoundException e1)
					{
			
						e1.printStackTrace();
					}
					
				}
			}
		};	
		menuItemSave.addActionListener(saveListener);
		//Open
		menuItemOpen= new JMenuItem(optionsPanel.options.getString("open"));
		menuItemOpen.getAccessibleContext().setAccessibleDescription(optionsPanel.options.getString("open"));
		ActionListener openListener=new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						String filepath;
						JFileChooser filechooser=new JFileChooser();
						int result=filechooser.showOpenDialog(mid);
						if(result==JFileChooser.APPROVE_OPTION)
						{
							filepath=(filechooser.getSelectedFile().getAbsolutePath());
							if(mid.stop)
							{
								left.series1x.clear();
								left.series1y.clear();
								left.series1z.clear();
								
								left.series2x.clear();
								left.series2y.clear();
								left.series2z.clear();
							}
							mid.stop=false;
							mid.check=false;
							mid.startOpenedAnimation(filepath);
						}
							
						
					}
			
				};
		menuItemOpen.addActionListener(openListener);
		//Exit
		menuItemExit=new JMenuItem(optionsPanel.options.getString("exit"));
		menuItemExit.getAccessibleContext().setAccessibleDescription(optionsPanel.options.getString("exit"));
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

		
		
		menuItemStart=new JMenuItem(optionsPanel.options.getString("start"));
		ActionListener startListener=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				mid.joption=new JOptionPaneMultiInput(optionsPanel.options.getString("mass1"),optionsPanel,1);
				mid.joption=new JOptionPaneMultiInput(optionsPanel.options.getString("mass2"),optionsPanel,2);
				if(mid.check)
				{
					if(mid.stop)
					{
						left.series1x.clear();
						left.series1y.clear();
						left.series2x.clear();
						left.series2y.clear();
						left.series1z.clear();
						left.series2z.clear();
					}
					mid.stop=false;
					mid.check=false;
					mid.startAnimation();
				}
				
			}
		};
		menuItemStart.addActionListener(startListener);
		menuItemStart.getAccessibleContext().setAccessibleDescription(optionsPanel.options.getString("start"));
		
		menuItemStop=new JMenuItem(optionsPanel.options.getString("stop"));
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
		menuItemStop.getAccessibleContext().setAccessibleDescription(optionsPanel.options.getString("stop"));
		
		menuItemContinue=new JMenuItem(optionsPanel.options.getString("continue"));
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
					JOptionPane.showMessageDialog(mid, optionsPanel.options.getString("stopSimEr"));
				}
				else
				{
					JOptionPane.showMessageDialog(mid, optionsPanel.options.getString("startSimFirstEr"));
				}
				
			}
		};
		menuItemCol = new JMenuItem(optionsPanel.options.getString("backcol"));
		menuItemCol.addActionListener( new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color c = JColorChooser.showDialog(null, optionsPanel.options.getString("backcol"), Color.WHITE);
				mid.backCol = c;
				mid.setBackground(c);
				mid.repaint();
			}
			
		});
		layMenu.add(menuItemCol);
		menuItemContinue.addActionListener(continueListener);
		menuItemContinue.getAccessibleContext().setAccessibleDescription(optionsPanel.options.getString("continue"));
		//Tolltips for all components
		menuItemStart.setToolTipText(optionsPanel.options.getString("ttstart"));
		menuItemContinue.setToolTipText(optionsPanel.options.getString("ttcontinue"));
		menuItemStop.setToolTipText(optionsPanel.options.getString("ttstop"));
		menuItemSave.setToolTipText(optionsPanel.options.getString("ttsave"));
		menuItemOpen.setToolTipText(optionsPanel.options.getString("ttopen"));
		menuItemExit.setToolTipText(optionsPanel.options.getString("ttexit"));
		menu.add(menuItemStart);
		menu.add(menuItemContinue);
		menu.add(menuItemStop);
		menu.add(menuItemSave);
		menu.add(menuItemOpen);
		menu.add(menuItemExit);
		this.add(menuBar);
	}
	
	
	
	void changeLanguage()
	{
		//Tolltips for all components
		menuItemCol.setToolTipText(optionsPanel.options.getString("backcol"));
		menuItemEng.setToolTipText(optionsPanel.options.getString("tteng"));
		menuItemPl.setToolTipText(optionsPanel.options.getString("ttpl"));
		menuItemStart.setToolTipText(optionsPanel.options.getString("ttstart"));
		menuItemContinue.setToolTipText(optionsPanel.options.getString("ttcontinue"));
		menuItemStop.setToolTipText(optionsPanel.options.getString("ttstop"));
		menuItemSave.setToolTipText(optionsPanel.options.getString("ttsave"));
		menuItemOpen.setToolTipText(optionsPanel.options.getString("ttopen"));
		menuItemExit.setToolTipText(optionsPanel.options.getString("ttexit"));
		//Texts for all components
		menuItemSave.setText(optionsPanel.options.getString("save"));
		menuItemCol.setText(optionsPanel.options.getString("backcol"));
		layMenu.setText(optionsPanel.options.getString("colmenu"));
		menuItemStop.setText(optionsPanel.options.getString("stop"));
		menuItemExit.setText(optionsPanel.options.getString("exit"));
		menuItemOpen.setText(optionsPanel.options.getString("open"));
		menuItemStart.setText(optionsPanel.options.getString("start"));
		menuItemContinue.setText(optionsPanel.options.getString("continue"));
		menuItemEng.setText(optionsPanel.options.getString("english"));
		menuItemPl.setText(optionsPanel.options.getString("polish"));
		menu.setText(optionsPanel.options.getString("menu"));
		lngMenu.setText(optionsPanel.options.getString("lngmenu"));
		optionsPanel.am.setText(optionsPanel.options.getString("mass1"));
		optionsPanel.bm.setText(optionsPanel.options.getString("mass2"));
		optionsPanel.mi.setText(optionsPanel.options.getString("u"));
		optionsPanel.k.setText(optionsPanel.options.getString("k"));
		optionsPanel.eta.setText(optionsPanel.options.getString("eta"));
		
		optionsPanel.forces.setText(optionsPanel.options.getString("include"));
	}

	

}
