package project;

public class Rope
{

	double[] l;
	double lc;
	static double l0;
	double r;
	Object m1,m2;
	Rope(Object mm1, Object mm2 )
	{
		l=new double[3];
		m1=mm1;
		m2=mm2;
		l[0]=m1.x-m2.x;
		l[1]=m1.y-m2.y;
		l[2]=m1.z-m2.z;
		l0=Math.sqrt(Math.pow(l[0],2)+Math.pow(l[1],2)+Math.pow(l[2],2));
		lc=l0;
		
	}
	void calculateLength() //Oblicza ca³k. d³ugoœæ
	{
		
		lc = Math.sqrt(Math.pow(l[0],2)+Math.pow(l[1],2)+Math.pow(l[2],2));
		
	}
	double calculateDeltax()//oblicza rozciagniecie sprezyny w kierunku x
	{
		l[0]=m1.x-m2.x;
		return (l[0]*(lc-l0)/l0);
	}
	double calculateDeltay()//oblicza rociagniecie sprezyzny w kierunku y
	{
		l[1]=m1.y-m2.y;
		return  (l[1]*(lc-l0)/l0);
		
	}
	double calculateDeltaz()//oblicza rozciagniecie sprezyny w kierunku z
	{
		l[2]=m1.z-m2.z;
		return  (l[2]*(lc-l0)/l0);
		
	}
}
