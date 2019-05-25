package project;

import java.util.ArrayList;

public class Object
{
	double mass;
	double x;
	double y;
	double z;
	double vx;
	double vy;
	double vz;
	double ax;
	double ay;
	double az;
	ArrayList<Double> xn;
	ArrayList<Double> yn;
	ArrayList<Double> zn;
	int sizex;
	int sizey;
	
	Object(double xx,double yy,double zz,double vvx,double vvy,double vvz,double m)
	{
		x=xx;
		y=yy;
		z=zz;
		vx=vvx;
		vy=vvy;
		vz=vvz;
		mass=m;
		sizex=30;
		sizey=30;
		xn=new ArrayList<Double>();
		yn=new ArrayList<Double>();
		zn=new ArrayList<Double>();
		//System.out.println(vx);
	}

}
