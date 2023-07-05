package game;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class tictactoe extends JPanel implements ActionListener {
	
		JButton[][] buttons = new JButton[3][3];
		char[][] board = new char[3][3];
		private char turn = 'X';
		int count=0;
		
		public tictactoe() {
			setLayout(new GridLayout(3,3,5,5));
			Font f = new Font("Dialog",Font.ITALIC,50);
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					buttons[i][j]= new JButton(" ");
					buttons[i][j].setFont(f);
					buttons[i][j].addActionListener(this);
					add(buttons[i][j]);
				}
			}
		}
		
		public int gameover() {
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					board[i][j] = buttons[i][j].getText().charAt(0);
					if(board[i][0]==board[i][1]&&board[i][1]==board[i][2]&& board[i][0] !=' ')
						return 1;
					if(board[0][j]==board[1][j]&&board[1][j]==board[2][j]&& board[0][j] !=' ')
						return 1;
				}
			}
			if(board[0][0]==board[1][1]&&board[1][1]==board[2][2]&& board[0][0] !=' ')
				return 1;
			if(board[0][2]==board[1][1]&&board[1][1]==board[2][0]&& board[0][2] !=' ')
				return 1;
			if(count==9)
				return 2;
			return 0;
		}
		
		public void actionPerformed(ActionEvent e) {
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					if(e.getSource()==buttons[i][j] && buttons[i][j].getText().equals(" ")== true) {
						if(turn=='X') {
							buttons[i][j].setText("X");
							if(gameover()==1) 
								JOptionPane.showMessageDialog(buttons[i][j], "X win");
							turn = 'O';
							count++;
						}
						else {
							buttons[i][j].setText("O");
							if(gameover()==1) 
								JOptionPane.showMessageDialog(buttons[i][j], "O win");
							turn ='X';
							count++;
						}
						if(gameover()==2)
							JOptionPane.showMessageDialog(buttons[i][j], "Draw");
					}
				}
			}
		}
		
		public static void main(String args[]) {
			JFrame f = new JFrame();
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.add(new tictactoe());
			f.setSize(300,300);
			f.setVisible(true);
		}
		
	}

