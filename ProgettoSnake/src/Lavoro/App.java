package Lavoro;

import javax.swing.JFrame;

public class App {

	public static void main(String[] args) {
		int boardWidth = 600;
		int boardHeight = boardWidth;
		
		JFrame frame = new JFrame("Snake");
		frame.setVisible(true);
		frame.setSize(boardWidth,boardHeight);
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Snake snake = new Snake(boardWidth,boardHeight);
		frame.add(snake);
		frame.pack();
		snake.requestFocus();
		

	}

}
