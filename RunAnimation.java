package project;

import java.awt.Color;

import project.MidPanel;

public class RunAnimation implements Runnable
{
	MidPanel middle;
	double K;
	double dt;
	RunAnimation(MidPanel mid)
	{
		
		middle=mid;
		dt=middle.dt;
		K=middle.k;
		
	}
	@Override
	public void run() //w¹tek s³u¿¹cy do uruchamiania animacji
	{
		System.out.println("m1 :"+middle.m1.mass+"m2 :"+middle.m2.mass+"dt :"+middle.dt);
		for(double t=0;t<100; t+=dt)
		{
			calculateAnimationx(dt);
			calculateAnimationy(dt);
			middle.repaint();
			try
			{
				Thread.sleep((long) (dt*1000));
			}
			catch (InterruptedException e) 
			{
				
				e.printStackTrace();
			}
			
		}
		
	}
	void calculateAnimationx(double dt) //oblicza os x tutaj chyba trzeba dac przypadki wyboru
	{
		middle.l1.calculateAlpha(middle.m1, middle.m2);
		middle.m1.ax=-K*middle.l1.calculateDeltax(middle.m1, middle.m2)/(middle.m1.mass);
		middle.m2.ax=K*middle.l1.calculateDeltax(middle.m1, middle.m2)/(middle.m2.mass);
		middle.l1.secoperationx=false;
		middle.m1.vx+=middle.m1.ax*dt;
		middle.m2.vx+=middle.m2.ax*dt;
		middle.m1.x+=middle.m1.vx*dt;
		middle.m2.x+=middle.m2.vx*dt;
		
	}
	void calculateAnimationy(double dt) //oblicza os y tutaj chyba trzeba dac przypadki wyboru
	{
		middle.m1.ay=K*middle.l1.calculateDeltay(middle.m1, middle.m2)/(middle.m1.mass);
		middle.m2.ay=-K*middle.l1.calculateDeltay(middle.m1, middle.m2)/(middle.m2.mass);
		middle.l1.secoperationy=false;
		//System.out.println(middle.l1.calculateDeltay(middle.m1, middle.m2));
		middle.m1.vy+=middle.m1.ay*dt;
		middle.m2.vy+=middle.m2.ay*dt;
		middle.m1.y+=middle.m1.vy*dt;
		middle.m2.y+=middle.m2.vy*dt;
	}
}
