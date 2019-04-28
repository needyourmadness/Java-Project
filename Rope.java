package project;

public class Rope
{

	double[] l;
	double alfa;
	double l0;
	double r;
	Object m1,m2;
	static boolean secoperationx=false;
	static boolean secoperationy=false;
	Rope(Object mm1, Object mm2 )
	{
		l=new double[3];
		m1=mm1;
		m2=mm2;
		l[0]=Math.abs(m1.x-m2.x);
		l[1]=Math.abs(m1.y-m2.y);
		l[2]=Math.abs(m1.z-m2.z);
	
		l0=Math.sqrt(Math.pow(l[0],2)+Math.pow(l[1],2)+Math.pow(l[2],2));
		
		//System.out.println(Math.toRadians(180));
		//System.out.println("l="+l);
	}
	void calculateAlpha(Object mm1,Object mm2)
	{
		
		//r=Math.sqrt(Math.pow((mm2.x-mm1.x), 2)+Math.pow((mm2.y-mm1.y),2));
		//System.out.println(r);
		alfa=(Math.atan2((mm2.y-mm1.y),(mm2.x-mm1.x)));
		//System.out.println(alfa*180/Math.PI);
		/*if(Math.abs(mm1.x-mm2.x)==0)
		{
			alfa=-Math.PI/2;
		}
		else if((mm2.y-mm1.y)>=0 && (mm2.x-mm1.x)>=0)
		{
			alfa=(Math.atan((mm2.y-mm1.y)/(mm2.x-mm1.x)));
			System.out.println("alfa1: "+ alfa*180/Math.PI);
		}
		else if((mm2.y-mm1.y)>0 && (mm2.x-mm1.x)<0)
		{
			alfa=(Math.atan((mm2.y-mm1.y)/(mm2.x-mm1.x)))+Math.PI;
			System.out.println("alfa2: "+ alfa*180/Math.PI);
			
		}
		else if((mm2.y-mm1.y)<=0 && (mm2.x-mm1.x)<=0)
		{
			alfa=Math.abs(Math.atan((mm2.y-mm1.y)/(mm2.x-mm1.x)))+Math.PI;
			System.out.println("alfa3: "+ alfa*180/Math.PI);
			
		}
		else if((mm2.y-mm1.y)<0 && (mm2.x-mm1.x)>0)
		{
			alfa=(Math.atan((mm2.y-mm1.y)/(mm2.x-mm1.x)))+2*Math.PI;
			System.out.println("alfa4: "+ alfa*180/Math.PI);
			
			
		}*/

		
	}
	double calculateDeltax(Object mm1,Object mm2)//oblicza rozciagniecie sprezyny w kierunku x
	{
		
		if(!secoperationx)
		{
			secoperationx=true;
			//l[0]=l0*(mm2.x-mm1.x)/r;
			l[0]=l0*(Math.cos(alfa));
			

		}
		//System.out.println("cosfi: "+l[0]/l0);

			
		
		return (l[0]-Math.abs(mm1.x-mm2.x));
		
	}
	double calculateDeltay(Object mm1,Object mm2)//oblicza rociagniecie sprezyzny w kierunku y
	{
		if(!secoperationy)
		{
			secoperationy=true;
			//System.out.println("alfay: "+ alfa);
			//l[1]=l0*(mm2.y-mm1.y)/r;
			l[1]=l0*(Math.sin(alfa));
			
				
				
		}
		//System.out.println("sinfi: "+l[1]/l0);
		//System.out.println("jedynka tryg: "+(Math.pow(l[0]/l0,2)+Math.pow(l[1]/l0, 2)));
		
		return (l[1]-Math.abs(mm1.y-mm2.y));
		
	}
	double calculateDeltaz(Object mm1,Object mm2)
	{
		double previouslz=l[2];
		l[2]=Math.sqrt(Math.pow(l0,2)-Math.pow(l[0],2)-Math.pow(l[1],2));
		return (previouslz-Math.abs(mm1.z-mm2.z));
		
	}
}
