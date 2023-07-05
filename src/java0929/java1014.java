package java0929;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class java1014 extends JFrame{
		int count=0;
	public void StringInNumber() {
	
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(600, 300));
		frame.setLocation(500, 100);
		Container c = frame.getContentPane();
		frame.setDefaultCloseOperation(JFrame .EXIT_ON_CLOSE);
		JTextField t1 = new JTextField(100);
		JButton b1 = new JButton("<");
		JButton b2 = new JButton(">");
		JTextField t2 = new JTextField();
		JPanel p = new JPanel();
		b1.setPreferredSize(new Dimension(130,100));
		b2.setPreferredSize(new Dimension(130,100));
		p.add(b1);
		p.add(b2);
		c.setLayout(new BorderLayout(0,50));
		c.add(t1,  BorderLayout.NORTH);
		c.add(p,  BorderLayout.CENTER);
		c.add(t2, BorderLayout.SOUTH);
		
		b1.addActionListener(new ActionListener() {            
			public void actionPerformed(ActionEvent e) {
				t2.setText(" ");
				String n = "";
				char ch,ch2 = 0;
				int l=1;
				String[] s =new String[10];
				count--;
				String str = t1.getText();
				if(str.equals("")) {
					JOptionPane.showMessageDialog(b1, "error-empty");
					count++;
				}
				else if(count<0) {
					JOptionPane.showMessageDialog(b1, "error - wrong button");
					count++;
				}
				else if(count==0) {
					JOptionPane.showMessageDialog(b1, "first number");
					count++;
				}
				for (int i=0; i < str.length(); i++) {
				     ch = str.charAt(i);
				 	if(i!=str.length()-1)
						ch2 = str.charAt(i+1);
				    if (48 <= ch && ch <= 57) {
				    	 n += ch ; 
				    	 if(i==str.length()-1)
			    			 s[l] = n;	
				    	 if(48 <= ch2 && ch2 <= 57) { 
				    	 }
				    	 else {
				    			 s[l] =n;
				    			 n="";
				    			 l++; }}}
			t2.setText(s[count]);
			}});
		b2.addActionListener(new ActionListener() {            
			public void actionPerformed(ActionEvent e) {
				t2.setText(" ");
				String n = "";
				char ch,ch2 = 0;
				int l=1;
				String[] s =new String[10];
				count++;
				String str = t1.getText();
				for (int i=0; i < str.length(); i++) {
				     ch = str.charAt(i);
				 	if(i!=str.length()-1)
						ch2 = str.charAt(i+1);
				    if (48 <= ch && ch <= 57) {
				    	 n += ch ; 
				    	 if(i==str.length()-1)
			    			 s[l] = n;	
				    	 if(48 <= ch2 && ch2 <= 57) { 
				    	 }
				    	 else {
				    			 s[l] =n;
				    			 n="";
				    			 l++;
				    		 }
				    }
				}
				if(str.equals("")) {
					JOptionPane.showMessageDialog(b2, "error-empty");
					count--;
				}
				else if(s[count]==null) {
					JOptionPane.showMessageDialog(b2, "Last number");
					count--;
				}
				t2.setText(s[count]);
			}});
		frame.pack();
		frame.setVisible(true);		}}
