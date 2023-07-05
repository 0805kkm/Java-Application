package Meta;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


class HowToPlay extends JFrame implements ActionListener{

	JButton blackJack = new JButton("ºí·¢Àè");
	JButton sudda = new JButton("¼¸´Ù");
	JButton jokbo = new JButton("¼¸´ÙÁ·º¸");
	JButton quit = new JButton("³ª°¡±â");
	JPanel panel;
	Board board;
	JLabel label;

	public HowToPlay() {
		setTitle("How To Play");
		setSize(400,700);

		label = new JLabel();
		panel = new JPanel(new GridLayout(0,4));
		
		blackJack.addActionListener(this);
		sudda.addActionListener(this);
		jokbo.addActionListener(this);
		quit.addActionListener(this);
		
		panel.add(blackJack);
		panel.add(sudda);
		panel.add(jokbo);
		panel.add(quit);
		
		add(panel,"South");
		board = new Board();
		board.add(label);
		add(board,"Center");

		setVisible(true);
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==blackJack){
			ImageIcon bj = new ImageIcon("img/brule.png");
			label.setIcon(bj);
		}
		else if(e.getSource()==sudda){
			ImageIcon sudda = new ImageIcon("img/suddamm.png");
			label.setIcon(sudda);
		}
		else if(e.getSource()==jokbo){
			ImageIcon jokbo = new ImageIcon("img/jokbo.png");
			label.setIcon(jokbo);
		}
		else if(e.getActionCommand().equals("³ª°¡±â")){
			dispose();
		}
	}

	class Board extends JPanel{
		CardLayout layout = new CardLayout();
		public Board() {
			setLayout(layout);
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Image img=null;
			g.drawImage(img, 0, 0,  null);
		}
	}
}