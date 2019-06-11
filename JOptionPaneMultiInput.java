package project;

import java.awt.GridLayout;

import javax.swing.*;

public class JOptionPaneMultiInput
{
	Options options;
  JOptionPaneMultiInput(String name,Options opt,int n)
  {
	  options=opt;
	  JPanel myPanel = new JPanel();
      myPanel.setLayout(new GridLayout(2,3,10,10));
      
      JTextField xField = new JTextField(5);
      JTextField yField = new JTextField(5);
      JTextField zField = new JTextField(5);
      xField.setText("0");
      yField.setText("0");
      zField.setText("0");
      
      myPanel.add(new JLabel("vx:"));
      myPanel.add(new JLabel("vy:"));
      myPanel.add(new JLabel("vz:"));
      myPanel.add(xField);
      myPanel.add(yField);
      myPanel.add(zField);
      int result = JOptionPane.showConfirmDialog(null, myPanel, 
    		  options.options.getString("insertvelocity")+" "+name, JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
    	if(n==1)
    	{
    		options.middle.v01x=Double.parseDouble(xField.getText());
    		options.middle.v01y=Double.parseDouble(yField.getText());
    		options.middle.v01z=Double.parseDouble(zField.getText());
    	}
    	if(n==2)
    	{
    		options.middle.v02x=Double.parseDouble(xField.getText());
    		options.middle.v02y=Double.parseDouble(yField.getText());
    		options.middle.v02z=Double.parseDouble(zField.getText());
    	}
        System.out.println("x value: " + xField.getText());
        System.out.println("y value: " + yField.getText());
        System.out.println("z value: " + zField.getText());
        }
   }
}