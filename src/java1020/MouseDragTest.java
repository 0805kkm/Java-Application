package java1020;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

	public class MouseDragTest extends JFrame {

		int x1, x2, y1, y2;

		
		public MouseDragTest() {
			setTitle("드래그로 사각형 그리기 ");
			/*
			 * JPanel p = new JPanel() { public void paintComponent(Graphics g) {
			 * super.paintComponent(g); g.setColor(color); int px = Math.min(x1, x2); int py
			 * = Math.min(y1, y2); int w = Math.abs(x1-x2); int h = Math.abs(y1-y2);
			 * g.drawRect(px, py, w, h); } };
			 */
			JPanel p1 = new JPanel() {
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					int px = Math.min(x1, x2); 
					int py = Math.min(y1, y2);
					int w = Math.abs(x1-x2);
					int h = Math.abs(y1-y2);
					g.drawLine(x1, y1, x2, y2);
					g.drawRect(px, py, w, h);
					
				}
			};


			JPanel p2 = new JPanel();
			JLabel status = new JLabel();
			p2.add(status);

			add("Center", p1);
			add("South", p2);

			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(400, 250);
			setVisible(true);

			class MyMouseListener implements MouseListener, MouseMotionListener {

				@Override
				public void mousePressed(MouseEvent e) {
					x1 = e.getX();
					y1 = e.getY();
					status.setText("(" + x1 + "," + y1 + ")");
					
				}

				@Override
				public void mouseDragged(MouseEvent e) {
					repaint();
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					x2 = e.getX();
					y2 = e.getY();
					status.setText("(" + x1 + "," + y1 + ")(" + x2 + "," + y2 + ")");
					repaint();
				}

				public void mouseExited(MouseEvent e) {
				}

				public void mouseEntered(MouseEvent e) {
				}

				public void mouseClicked(MouseEvent e) {
				}

				public void mouseMoved(MouseEvent e) {
				}

			}
			MyMouseListener listener = new MyMouseListener();
			p1.addMouseListener(listener);
			p1.addMouseMotionListener(listener);

		}

		public static void main(String[] args) {
			new MouseDragTest();
		}
	}
