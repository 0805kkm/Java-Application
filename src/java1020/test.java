package java1020;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class test extends JFrame implements ActionListener {
	JButton b1;
	JButton b2;
	JFileChooser fc;
	JPanel p1;
	
	public test(){
		p1 = new MyPanel();
		JPanel p2 = new JPanel();
		b1 =new JButton("Load");
		b2 =new JButton("Save");
		p2.add(b1);
		p2.add(b2);
		add("Center",p1);
		add("South", p2);
		
		
		//fc.setFileFilter();
		b1.addActionListener(this);
		b2.addActionListener(this);
		setTitle("Draw Rectangle With a Drag");
		setSize(500,500);
		setLocation(500,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== b1) {
			JFileChooser jfc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("data file", "dat");
			jfc.setFileFilter(filter);
			//jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int j = jfc.showOpenDialog(this);
			switch(j)
			{
			case JFileChooser.APPROVE_OPTION : 
				File x = jfc.getSelectedFile();
				try {
					FileInputStream in = new FileInputStream(x);
					BufferedInputStream b = new BufferedInputStream(in);
					ObjectInputStream o = new ObjectInputStream(b);
					//p1.add((Component) o.readObject());
					in.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case JFileChooser.CANCEL_OPTION :
				break;
			}
		}
		else{
			File x= null;
			JFileChooser jfc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("data file", "dat");
			jfc.setFileFilter(filter);
			int j = jfc.showSaveDialog(null);
			ObjectOutputStream out = null;
			switch(j)
			{
				case JFileChooser.APPROVE_OPTION : 
					x= jfc.getSelectedFile();
					try {
						out = new ObjectOutputStream(new FileOutputStream(x.toString()+".dat",true));
						out.writeObject(p1);
						out.flush();
						out.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					break;
				case JFileChooser.CANCEL_OPTION : 
					return;	
			}
		}
	}
	class MyPanel extends JPanel{
		Point startP,endP;
		public MyPanel(){
			this.addMouseListener( new MyMouseListener()); 
			this.addMouseMotionListener( new MyMouseListener());
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g); 
			if(startP != null) {
			int px = Math.min(startP.x, endP.x); 
			int py = Math.min(startP.y, endP.y);
			int w = Math.abs(startP.x- endP.x);
			int h = Math.abs(startP.y- endP.y);
			g.drawRect(px,py,w,h);				
			}
		}
		class MyMouseListener extends MouseAdapter {
			public void mousePressed(MouseEvent e){
				startP = e.getPoint();
			}
			public void mouseReleased(MouseEvent e){
				endP = e.getPoint();
				repaint(); 
			}
			public void mouseDragged(MouseEvent e){
				endP = e.getPoint();
				repaint();
			}
		}
	}
	public static void main(String[] args) {
		new test();
	}
}
