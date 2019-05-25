package project;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class Options extends JPanel
{
	MidPanel middle;

	
	JLabel am;					//Wszystkie etykietki
	JLabel bm;
	JLabel k;
	JLabel mi;
	JLabel forces;
	JLabel eta;
	
	JSlider sliderm1;			//slidery do mas i wsp�czynnik�w
	JSlider sliderm2;
	JSlider sliderK;
	JSlider sliderMi;
	JSlider sliderEta;
	
	JSlider sliderm1Change;
	JSlider sliderm2Change;
	
	JLabel amEcho;				//Etykietki wy�wietlaj� warto�ci ustawione na suwakach
	JLabel bmEcho;
	JLabel kEcho;
	JLabel miEcho;
	JLabel h1Echo;
	JLabel h2Echo;
	JLabel etaEcho;
	
	//Wszystkie CheckBoxy
	//Dwa do zmiany mas, 3 do uwzgl�dnianych si�
	JCheckBox fn;
	JCheckBox fs;
	JCheckBox fl;
	
	JComboBox<String> mass1Function;	//Wyb�r funkcji zmiany parametr�w w czasie
	JComboBox<String> mass2Function;
	JComboBox<String> kFunction;

	Locale locale;
	JPanel[][] pos;				//Tablica paneli do ogarni�cia layoutu
	ResourceBundle options;
	
	public Options(MidPanel mid) 
	{

		locale = new Locale("PL", "POL");
		
		options= ResourceBundle.getBundle("MessagesBundle_en_GB",locale);
		
		middle=mid;
		this.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.setLayout(new GridLayout(6,2));	//Rozmieszczam panele
		pos = new JPanel[6][2];
		for (int i = 0; i<6; i++) {
			for (int j = 0; j < 2; j++) {
				pos[i][j] = new JPanel();
				this.add(pos[i][j]);
			}
		}
		pos[5][0].setLayout(new FlowLayout(FlowLayout.RIGHT));
		pos[0][0].setLayout(new BorderLayout());
		pos[0][1].setLayout(new BorderLayout());
		pos[3][0].setLayout(new BorderLayout());
		pos[4][0].setLayout(new BorderLayout());
		pos[4][1].setLayout(new BorderLayout());
		
		
		am = new JLabel(options.getString("mass1"));
		bm = new JLabel(options.getString("mass2"));
		k = new JLabel(options.getString("k"));
		mi = new JLabel(options.getString("u"));
		forces = new JLabel(options.getString("include"));
		eta = new JLabel(options.getString("eta"));
		
		
		sliderm1 = new JSlider(JSlider.HORIZONTAL, 0, 100, 1);
		sliderm2 = new JSlider(JSlider.HORIZONTAL, 0, 100, 1);
		sliderK = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
		sliderMi = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
		sliderEta = new JSlider(JSlider.HORIZONTAL, 0, 10000, 1000);
		
		sliderm1Change = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
		sliderm2Change = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
		
		amEcho = new JLabel(String.format("%.2f kg", (double) sliderm1.getValue()));
		bmEcho = new JLabel(String.format("%.2f kg", (double) sliderm2.getValue()));
		kEcho = new JLabel(String.format("%.2f N/m", (double) sliderK.getValue()));
		miEcho = new JLabel(String.format("%.2f kg*m/s", (double) sliderMi.getValue()));
		h1Echo = new JLabel(String.format("%.2f", (double) sliderm1Change.getValue()));
		h2Echo = new JLabel(String.format("%.2f", (double) sliderm2Change.getValue()));
		etaEcho = new JLabel(String.format("%.2f", (double) sliderEta.getValue()));
		Border b = BorderFactory.createLineBorder(Color.BLACK, 1);
		amEcho.setBorder(b);
		bmEcho.setBorder(b);
		kEcho.setBorder(b);
		miEcho.setBorder(b);
		etaEcho.setBorder(b);
		
		
		sliderm1.addChangeListener(new ValueListener(amEcho, sliderm1, 10, "kg",middle,1));
				
		sliderm2.addChangeListener(new ValueListener(bmEcho, sliderm2, 10, "kg",middle,2));
		sliderK.addChangeListener(new ValueListener(kEcho, sliderK, 20, "N/m",middle,4));
		sliderMi.addChangeListener(new ValueListener(miEcho, sliderMi, 500, "kg*m/s",middle,5));
		sliderm1Change.addChangeListener(new ValueListener(h1Echo, sliderm1Change, 100, "", middle, 6));
		sliderm2Change.addChangeListener(new ValueListener(h2Echo, sliderm2Change, 100, "", middle, 7));
		sliderEta.addChangeListener(new ValueListener(etaEcho, sliderEta, 500000, "kg/m", middle, 8));
		
		String[] functions = {"m(t) = m", "m(t) = m*e^(-xt)"};
		mass1Function = new JComboBox<String>(functions);
		mass2Function = new JComboBox<String>(functions);
		mass2Function.setSelectedIndex(0);
		mass1Function.setSelectedIndex(0);
		
		String[] functions2 = {"k(t) = k", "k(t) = k*(1+l)"};
		kFunction = new JComboBox<String>(functions2);
		kFunction.setSelectedIndex(0);
		fn = new JCheckBox("Fn", false);
		fs = new JCheckBox("Fs", true);
		fl = new JCheckBox("Fl", false);
		
		fl.addActionListener(new ForceListener(3, mid, fl));
		fn.addActionListener(new ForceListener(4, mid, fn));
		
		mass1Function.addItemListener(new CheckboxListener(this, middle, mass1Function, 0, sliderm1Change, h1Echo));
		mass2Function.addItemListener(new CheckboxListener(this, middle, mass2Function, 1, sliderm2Change, h2Echo));

		pos[0][0].add(am, BorderLayout.PAGE_START);
		pos[0][0].add(amEcho, BorderLayout.PAGE_END);
		pos[0][1].add(bm, BorderLayout.PAGE_START);
		pos[0][1].add(bmEcho, BorderLayout.PAGE_END);
		pos[0][0].add(sliderm1, BorderLayout.CENTER);
		pos[0][1].add(sliderm2, BorderLayout.CENTER);
		pos[1][0].add(mass1Function);
		pos[1][1].add(mass2Function);
		pos[3][0].add(k, BorderLayout.PAGE_START);
		pos[3][0].add(kEcho, BorderLayout.PAGE_END);
		pos[3][0].add(sliderK, BorderLayout.CENTER);
		pos[3][1].add(kFunction);
		pos[4][0].add(mi, BorderLayout.PAGE_START);
		pos[4][0].add(miEcho, BorderLayout.PAGE_END);
		pos[4][0].add(sliderMi, BorderLayout.CENTER);
		pos[4][1].add(eta, BorderLayout.PAGE_START);
		pos[4][1].add(etaEcho, BorderLayout.PAGE_END);
		pos[4][1].add(sliderEta, BorderLayout.CENTER);
		pos[5][0].add(forces);
		pos[5][1].add(fn);
		pos[5][1].add(fs);
		pos[5][1].add(fl);
	}
}

