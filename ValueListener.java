package project;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.*;

public class ValueListener implements ChangeListener {
	
	JLabel label;
	JSlider slider;
	double divider;
	String unit;
	MidPanel mid;
	int actionnumber;//w zaleznosci od tego co ma robic bedzie mialo swoj numerek bo tak to dt konfilktuje z m1 itd
	//1 to masa1, 2 to masa2, 3 to krok calkowy,4 to stala sprezystosci, 5 to mi, 6 i 7 to wsp. zmiany masy, 8 to sila
	//aerocostam
	
	public ValueListener(JLabel l, JSlider s, String u,MidPanel middle,int anumber) {
		actionnumber=anumber;
		mid=middle;
		this.label = l;
		this.slider = s;
		this.divider = 1;
		this.unit = u;
		if(actionnumber==1)
			mid.mass1=slider.getValue()/divider;
		else if(actionnumber==2)
			mid.mass2=slider.getValue()/divider;
		else if(actionnumber==3)
			mid.dt=slider.getValue()/divider;
		else if(actionnumber==4)
			mid.k=slider.getValue()/divider;
		else if(actionnumber==5)
			mid.mi=slider.getValue()/divider;
		else if(actionnumber==6) 
			mid.h[0]=slider.getValue()/divider;
		else if(actionnumber==7) 
			mid.h[1]=slider.getValue()/divider;
		else if(actionnumber==8) 
			mid.eta=slider.getValue()/divider;
		else if(actionnumber==9) 
			mid.h[2]=slider.getValue()/divider;
		String value = String.format("%.3f " + unit, slider.getValue()/divider);
		label.setText(value);

	}
	
	public ValueListener(JLabel l, JSlider s, double d, String u,MidPanel middle,int anumber)
	{
		actionnumber=anumber;
		mid=middle;
		this.label = l;
		this.slider = s;
		this.divider = d;
		this.unit = u;
		if(actionnumber==1)
			mid.mass1=slider.getValue()/divider;
		else if(actionnumber==2)
			mid.mass2=slider.getValue()/divider;
		else if(actionnumber==3)
			mid.dt=slider.getValue()/divider;
		else if(actionnumber==4)
			mid.k=slider.getValue()/divider;
		else if(actionnumber==5)
			mid.mi=slider.getValue()/divider;
		else if(actionnumber==6) 
			mid.h[0]=slider.getValue()/divider;
		else if(actionnumber==7) 
			mid.h[1]=slider.getValue()/divider;
		else if(actionnumber==8) 
			mid.eta=slider.getValue()/divider;
		else if(actionnumber==9) 
			mid.h[2]=slider.getValue()/divider;
		String value = String.format("%.3f " + unit, slider.getValue()/divider);
		label.setText(value);
	
	}

	@Override
	public void stateChanged(ChangeEvent arg0)
	{
		if(actionnumber==1)
			mid.mass1=slider.getValue()/divider;
		else if(actionnumber==2)
			mid.mass2=slider.getValue()/divider;
		else if(actionnumber==3)
			mid.dt=slider.getValue()/divider;
		else if(actionnumber==4)
			mid.k=slider.getValue()/divider;
		else if(actionnumber==5)
			mid.mi=slider.getValue()/divider;
		else if(actionnumber==6) 
			mid.h[0]=slider.getValue()/divider;
		else if(actionnumber==7) 
			mid.h[1]=slider.getValue()/divider;
		else if(actionnumber==8) 
			mid.eta=slider.getValue()/divider;
		else if(actionnumber==9) 
			mid.h[2]=slider.getValue()/divider;
		String value = String.format("%.3f " + unit, slider.getValue()/divider);
		label.setText(value);

	}

}