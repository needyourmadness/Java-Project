package project;

import java.awt.Color;

import project.MidPanel;

public class RunAnimation implements Runnable
{
	MidPanel middle;
	double K;
	double Mi;
	double dt;
	double Eta;
	double[] h;
	LeftPanel left;
	double correction;
	static double stoptime=0;
	double F1, F2;
	int observationPointSpeed=3000;
	
	RunAnimation(MidPanel mid,LeftPanel leftt)
	{
		left=leftt;
		middle=mid;
		dt=0.001;//middle.dt
		K=middle.k;
		Mi=middle.mi;
		h=middle.h;
		Eta=middle.eta;
		correction=middle.getHeight()/2;
		
	}
	@Override
	public void run() //w¹tek s³u¿¹cy do uruchamiania animacji
	{
		double t=stoptime;
		//System.out.println("m1 :"+middle.m1.mass+"m2 :"+middle.m2.mass+"dt :"+middle.dt);
		for(;t<100; t+=dt)
		{
			
			if(middle.stop)
			{
				stoptime=t;
				//System.out.println("stoptime:"+ stoptime);
				break;
			}	
			middle.l1.calculateLength();
			calculateAnimationx(dt);
			left.series1x.add(t,middle.m1.x);
			left.series2x.add(t,middle.m2.x);
			
			calculateAnimationy(dt);
			left.series1y.add(t,middle.m1.y-correction);
			left.series2y.add(t,middle.m2.y-correction);
			
			calculateAnimationz(dt);
			left.series1z.add(t,middle.m1.z);
			left.series2z.add(t,middle.m2.z);
			
			
			calculateParameters(dt);
			
			middle.repaint();
			left.repaint();
			try
			{
				Thread.sleep((long) (dt*10));
			}
			catch (InterruptedException e) 
			{
				
				e.printStackTrace();
			}
			//System.out.println("Energia :"+((middle.m1.mass*(middle.m1.vx*middle.m1.vx+middle.m1.vy*middle.m1.vy)+middle.m2.mass*(middle.m2.vx*middle.m2.vx+middle.m2.vy*middle.m2.vy))/2+K*(middle.l1.l[0]-middle.l1.l0)*(middle.l1.l[0]-middle.l1.l0)));
		
		}
		middle.check=true;
		middle.stop=true;
		
	}
	//Wszystko co dzieje siê na osi z
	void calculateAnimationx(double dt) //oblicza os x
	{
		F1=-K*middle.l1.calculateDeltax();
		F2=K*middle.l1.calculateDeltax();
		if (middle.change[3]) {
			F1-=Mi*middle.m1.vx;
			F2-=Mi*middle.m2.vx;
		}
		if (middle.change[4]) {
			F1-=Eta*Math.abs(middle.m1.vx)*middle.m1.vx;
			F2-=Eta*Math.abs(middle.m2.vx)*middle.m2.vx;
		}
		middle.m1.ax=F1/(middle.m1.mass);
		middle.m2.ax=F2/(middle.m2.mass);
		
		middle.m1.vx+=middle.m1.ax*dt;
		middle.m2.vx+=middle.m2.ax*dt;
		
		middle.m1.x+=middle.m1.vx*dt;
		middle.m2.x+=middle.m2.vx*dt;
		
		middle.m1.xn.add(middle.m1.x);
		middle.m2.xn.add(middle.m2.x);
		
		
	}
	//Wszystko co dzieje sie na osi y
	void calculateAnimationy(double dt) //oblicza os y
	{
		F1=-K*middle.l1.calculateDeltay();
		F2=K*middle.l1.calculateDeltay();
		if (middle.change[3]) {
			F1-=Mi*middle.m1.vy;
			F2-=Mi*middle.m2.vy;
		}
		if (middle.change[4]) {
			F1-=Eta*Math.abs(middle.m1.vy)*middle.m1.vy;
			F2-=Eta*Math.abs(middle.m2.vy)*middle.m2.vy;
		}
		middle.m1.ay=F1/(middle.m1.mass);
		middle.m2.ay=F2/(middle.m2.mass);
		middle.m1.y+=middle.m1.vy*dt;
		middle.m2.y+=middle.m2.vy*dt;
		middle.m1.vy+=middle.m1.ay*dt;
		middle.m2.vy+=middle.m2.ay*dt;
		middle.m1.yn.add(middle.m1.y);
		middle.m2.yn.add(middle.m2.y);
	}
	//Wszystko co dzieje siê na osi z
	void calculateAnimationz(double dt) //oblicza os z
	{
		
		F1=-K*middle.l1.calculateDeltaz();
		F2=K*middle.l1.calculateDeltaz();
		if (middle.change[3]) {
			F1-=Mi*middle.m1.vz;
			F2-=Mi*middle.m2.vz;
		}
		if (middle.change[4]) {
			F1-=Eta*Math.abs(middle.m1.vz)*middle.m1.vz;
			F2-=Eta*Math.abs(middle.m2.vz)*middle.m2.vz;
		}
		middle.m1.az=F1/(middle.m1.mass);
		middle.m2.az=F2/(middle.m2.mass);
		middle.m1.z+=middle.m1.vz*dt;
		middle.m2.z+=middle.m2.vz*dt;
		middle.m1.vz+=middle.m1.az*dt;
		middle.m2.vz+=middle.m2.az*dt;
		
		middle.m1.zn.add(middle.m1.z);
		middle.m2.zn.add(middle.m2.z);
		//skalowanie wymiarow kulek
		middle.m1.sizex=(int) (30-middle.m1.z/observationPointSpeed*middle.m1.sizex);
		middle.m1.sizey=(int) (30-middle.m1.z/observationPointSpeed*middle.m1.sizey);
		
		middle.m2.sizex=(int) (30-middle.m2.z/observationPointSpeed*middle.m2.sizex);
		middle.m2.sizey=(int) (30-middle.m2.z/observationPointSpeed*middle.m2.sizey);
	}
	void calculateParameters(double dt) {
		if (middle.change[0]) middle.m1.mass *= Math.exp(-h[0]*dt);
		if (middle.change[1]) middle.m2.mass *= Math.exp(-h[1]*dt);
	}
}
