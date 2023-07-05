package java1020;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Rectangle extends JFrame {
	int count=0;
	public Rectangle(){
		MyPanel p1 = new MyPanel();
		JPanel p2 = new JPanel();
		JLabel status = new JLabel();
		p2.add(status);
		status.setText(Integer.toString(count));
		add("Center",p1);
		add("South", p2);

		setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	class MyPanel extends JPanel{
		Point start,end;
		public MyPanel(){
			this.addMouseListener(new MyMouseListener());
			this.addMouseMotionListener( new MyMouseListener());
		}
		
		class MyMouseListener extends MouseAdapter{
			public void mousePressed(MouseEvent e){
				start=e.getPoint();
			}
			public void mouseReleased(MouseEvent e){
				end=e.getPoint();
				
			}
			public void mouseDragged(MouseEvent e){
				end=e.getPoint();
				Graphics g = getGraphics();
				int px = Math.min(start.x, end.x); 
				int py = Math.min(start.y, end.y);
				int w = Math.abs(start.x- end.x);
				int h = Math.abs(start.y- end.y);
				g.drawLine(start.x, start.y, end.x,end.y);
				g.drawRect(px, py, w, h);
			}

		}
		
	}
	
	public static void main(String[] args) {
		new Rectangle();
	}
}

