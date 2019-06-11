package project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RunOpenedAnimation implements Runnable
{
	String file;
	MidPanel middle;
	LeftPanel left;
	double correction;
	double stoptime=0;
	private volatile boolean exit = false;
	double dt;
	
	RunOpenedAnimation(LeftPanel lef,MidPanel mid,String filepath)
	{
		
		left=lef;
		middle=mid;
		file=filepath;
		correction=middle.getHeight()/2;
		exit = false;
		
	}
	@Override
	public void run() //w¹tek s³u¿¹cy do uruchamiania animacji
	{
		BufferedReader br;
		try
		{
			br = new BufferedReader(new FileReader(file));
			String st; 
			try
			{
				while ((st = br.readLine()) != null)
				{
					
					String[] arguments=st.split("\t");
					if(arguments[0].equals("#"))
					{
						continue;
					}
					else if(arguments[0].equals("!"))
					{
						dt=Double.parseDouble(arguments[2]);
						System.out.println(dt);
					}
					else
					{
						
						double[] values=new double[7];
						for(int i=0;i<7;i++)
						{
							values[i]=Double.parseDouble(arguments[i]);
						}
						double t=values[0];
						if(middle.stop)
						{
							stoptime=t;
							//System.out.println("stoptime:"+ stoptime);
							break;
						}
						middle.m1.x=values[1];
						middle.m1.y=values[2];
						middle.m1.z=values[3];
						
						middle.m2.x=values[4];
						middle.m2.y=values[5];
						middle.m2.z=values[6];
						
						left.series1x.add(t,middle.m1.x);
						left.series1y.add(t,middle.m1.y-correction);
						left.series1z.add(t,middle.m1.z);
						
						left.series2x.add(t,middle.m2.x);
						left.series2y.add(t,middle.m2.y-correction);
						left.series2z.add(t,middle.m2.z);
						
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
						
					}
				
				
				}
				exit=true;

				
				middle.check=true;
				middle.stop=true;
			} catch (NumberFormatException | IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
	}
}