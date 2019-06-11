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
	
	JSlider sliderm1;			//slidery do mas i wspó³czynników
	JSlider sliderm2;
	JSlider sliderK;
	JSlider sliderMi;
	JSlider sliderEta;
	
	JSlider sliderm1Change;
	JSlider sliderm2Change;
	JSlider sliderKChange;
	
	JLabel amEcho;				//Etykietki wyœwietlaj¹ wartoœci ustawione na suwakach
	JLabel bmEcho;
	JLabel kEcho;
	JLabel miEcho;
	JLabel h1Echo;
	JLabel h2Echo;
	JLabel h3Echo;
	JLabel etaEcho;
	
	//Wszystkie CheckBoxy
	//Dwa do zmiany mas, 3 do uwzglêdnianych si³
	JCheckBox fn;
	JCheckBox fs;
	JCheckBox fl;
	
	JComboBox<String> mass1Function;	//Wybór funkcji zmiany parametrów w czasie
	JComboBox<String> mass2Function;
	JComboBox<String> kFunction;

	Locale locale;
	JPanel[][] pos;				//Tablica paneli do ogarniêcia layoutu
	ResourceBundle options;
	
	ParamPanel[] param;
	
	public Options(MidPanel mid) 
	{

		locale = new Locale("PL", "POL");
		
		options= ResourceBundle.getBundle("MessagesBundle_en_GB",locale);
		
		middle=mid;
		this.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.setLayout(new GridLayout(6,2));	//Rozmieszczam panele
		Border b1 = BorderFactory.createLoweredBevelBorder();
		Border b2 = BorderFactory.createLineBorder(Color.GRAY);
		Border b0;
		b0 = BorderFactory.createEmptyBorder();
		Border panelbord = BorderFactory.createCompoundBorder(b1, b2);
		pos = new JPanel[6][2];
		for (int i = 0; i<6; i++) {
			for (int j = 0; j < 2; j++) {
				pos[i][j] = new JPanel();
				pos[i][j].setBorder(panelbord);
				this.add(pos[i][j]);
			}
		}
		pos[5][0].setLayout(new FlowLayout(FlowLayout.RIGHT));
		pos[0][0].setLayout(new BorderLayout());
		pos[0][1].setLayout(new BorderLayout());
		pos[3][0].setLayout(new BorderLayout());
		pos[4][0].setLayout(new BorderLayout());
		pos[4][1].setLayout(new BorderLayout());
		
		pos[2][0].setBorder(b0);
		pos[2][1].setBorder(b0);
		
		param = new ParamPanel[3];
		for (int i = 0; i<3; i++) param[i] = new ParamPanel();
		
		am = new JLabel(options.getString("mass1"));
		bm = new JLabel(options.getString("mass2"));
		k = new JLabel(options.getString("k"));
		mi = new JLabel(options.getString("u"));
		forces = new JLabel(options.getString("include"));
		eta = new JLabel(options.getString("eta"));
		
		
		sliderm1 = new JSlider(JSlider.HORIZONTAL, 0, 10000, 1000);
		sliderm2 = new JSlider(JSlider.HORIZONTAL, 0, 10000, 1000);
		sliderK = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
		sliderMi = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
		sliderEta = new JSlider(JSlider.HORIZONTAL, 0, 10000, 1000);
		
		sliderm1.setMajorTickSpacing(5000);
		sliderm1.setMinorTickSpacing(500);
		sliderm1.setPaintTicks(true);
		sliderm2.setMajorTickSpacing(5000);
		sliderm2.setMinorTickSpacing(500);
		sliderm2.setPaintTicks(true);
		sliderK.setMajorTickSpacing(50);
		sliderK.setMinorTickSpacing(5);
		sliderK.setPaintTicks(true);
		sliderMi.setMajorTickSpacing(50);
		sliderMi.setMinorTickSpacing(5);
		sliderMi.setPaintTicks(true);
		sliderEta.setMajorTickSpacing(5000);
		sliderEta.setMinorTickSpacing(500);
		sliderEta.setPaintTicks(true);
		
		sliderm1Change = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
		sliderm2Change = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
		sliderKChange = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		
		sliderm1Change.setMajorTickSpacing(50);
		sliderm1Change.setMinorTickSpacing(5);
		sliderm1Change.setPaintTicks(true);
		sliderm2Change.setMajorTickSpacing(50);
		sliderm2Change.setMinorTickSpacing(5);
		sliderm2Change.setPaintTicks(true);
		sliderKChange.setMajorTickSpacing(50);
		sliderKChange.setMinorTickSpacing(5);
		sliderKChange.setPaintTicks(true);
		
		amEcho = new JLabel(String.format("%.2f kg", (double) sliderm1.getValue()));
		bmEcho = new JLabel(String.format("%.2f kg", (double) sliderm2.getValue()));
		kEcho = new JLabel(String.format("%.2f N/m", (double) sliderK.getValue()));
		miEcho = new JLabel(String.format("%.2f kg*m/s", (double) sliderMi.getValue()));
		h1Echo = new JLabel(String.format("%.2f", (double) sliderm1Change.getValue()));
		h2Echo = new JLabel(String.format("%.2f", (double) sliderm2Change.getValue()));
		h3Echo = new JLabel(String.format("%.2f", (double) sliderKChange.getValue()));
		etaEcho = new JLabel(String.format("%.2f", (double) sliderEta.getValue()));
		Border b = BorderFactory.createLineBorder(Color.BLACK, 1);
		amEcho.setBorder(b);
		bmEcho.setBorder(b);
		kEcho.setBorder(b);
		miEcho.setBorder(b);
		etaEcho.setBorder(b);
		
		
		sliderm1.addChangeListener(new ValueListener(amEcho, sliderm1, 1000, "kg",middle,1));		
		sliderm2.addChangeListener(new ValueListener(bmEcho, sliderm2, 1000, "kg",middle,2));
		sliderK.addChangeListener(new ValueListener(kEcho, sliderK, 100, "N/m",middle,4));
		sliderMi.addChangeListener(new ValueListener(miEcho, sliderMi, 500, "kg*m/s",middle,5));
		sliderm1Change.addChangeListener(new ValueListener(h1Echo, sliderm1Change, 100, "", middle, 6));
		sliderm2Change.addChangeListener(new ValueListener(h2Echo, sliderm2Change, 100, "", middle, 7));
		sliderEta.addChangeListener(new ValueListener(etaEcho, sliderEta, 500000, "kg/m", middle, 8));
		sliderKChange.addChangeListener(new ValueListener(h3Echo, sliderKChange, 100, "", middle, 9));
		
		String[] functions = {"m(t) = m", "m(t) = m*e^(-xt)"};
		mass1Function = new JComboBox<String>(functions);
		mass2Function = new JComboBox<String>(functions);
		mass2Function.setSelectedIndex(0);
		mass1Function.setSelectedIndex(0);
		
		String[] functions2 = {"k(t) = k", "k(t) = k*change*(1+x)"};
		kFunction = new JComboBox<String>(functions2);
		kFunction.setSelectedIndex(0);
		fn = new JCheckBox("Fahd", false);
		fn.setToolTipText(options.getString("fahd"));
		fs = new JCheckBox("Fs", true);
		fs.setToolTipText(options.getString("fs"));
		fl = new JCheckBox("Fl", false);
		fl.setToolTipText(options.getString("fl"));
		
		fl.addActionListener(new ForceListener(3, mid, fl));
		fn.addActionListener(new ForceListener(4, mid, fn));
		
		mass1Function.addItemListener(new CheckboxListener(this, middle, mass1Function, 0, sliderm1Change, h1Echo));
		mass2Function.addItemListener(new CheckboxListener(this, middle, mass2Function, 1, sliderm2Change, h2Echo));
		kFunction.addItemListener(new CheckboxListener(this, middle, kFunction, 2, sliderKChange, h3Echo));

		pos[0][0].add(am, BorderLayout.PAGE_START);
		pos[0][0].add(amEcho, BorderLayout.PAGE_END);
		pos[0][1].add(bm, BorderLayout.PAGE_START);
		pos[0][1].add(bmEcho, BorderLayout.PAGE_END);
		pos[0][0].add(sliderm1, BorderLayout.CENTER);
		pos[0][1].add(sliderm2, BorderLayout.CENTER);
		param[0].up.add(mass1Function);
		param[1].up.add(mass2Function);
		pos[1][0].add(param[0]);
		pos[1][1].add(param[1]);
		pos[3][0].add(k, BorderLayout.PAGE_START);
		pos[3][0].add(kEcho, BorderLayout.PAGE_END);
		pos[3][0].add(sliderK, BorderLayout.CENTER);
		param[2].up.add(kFunction);
		pos[3][1].add(param[2]);
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

