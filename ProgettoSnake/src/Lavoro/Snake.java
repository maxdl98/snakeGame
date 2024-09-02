package Lavoro;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.random.*;
import javax.swing.*;

public class Snake extends JPanel implements  KeyListener, ActionListener {
	private class Tile{
		int x;
		int y;
		
		Tile(int x, int y){
			this.x = x;
			this.y = y;
			
		}
	}
int boardWidth;
int boardHeight;
int TileSize = 25;
private boolean gameOver = false;


Tile snakeHead;
ArrayList<Tile> snakeBody;

Tile food;
Random random ;

Timer gameLoop;
int velocityX;
int velocityY;


public Snake(int boardWidth, int boardHeight) {
	super();
	this.boardWidth = boardWidth;
	this.boardHeight = boardHeight;
	setPreferredSize(new Dimension(this.boardWidth,this.boardHeight));
	setBackground(Color.black);
	
	snakeHead = new Tile(5,5);
	snakeBody = new ArrayList<Tile>();
	
	food = new Tile(10,10);
	random = new Random();
	placeFood();
	gameLoop = new Timer(100,this);
	gameLoop.start();
	addKeyListener(this);
	setFocusable(true);
	velocityX = 0;
	velocityY = 0;
   
	
	
	
}

public void paintComponent(Graphics g) {
	super.paintComponent(g);
	draw(g);
}

public boolean collision(Tile tile1, Tile tile2) {

	return tile1.x  == tile2.x && tile1.y == tile2.y;
	
	
}

public void draw(Graphics g) {
    // Imposta il colore per il corpo del serpente
    g.setColor(Color.green);

    // Disegna il corpo del serpente
    for (int i = 0; i < snakeBody.size(); i++) {
        Tile snakePart = snakeBody.get(i);
        g.fill3DRect(snakePart.x * TileSize, snakePart.y * TileSize, TileSize, TileSize, true);
    }

    // Disegna la testa del serpente (continua a usare il colore verde)
    g.fill3DRect(snakeHead.x * TileSize, snakeHead.y * TileSize, TileSize, TileSize, true);

    // Disegna il cibo
    g.setColor(Color.red);
    g.fillRect(food.x * TileSize, food.y * TileSize, TileSize, TileSize);

    // Disegna il punteggio
    g.setFont(new Font("Arial", Font.PLAIN, 16));
    if (gameOver) {
        g.setColor(Color.red);
        g.drawString("GameOver " + String.valueOf(snakeBody.size()), TileSize - 16, TileSize);
    } else {
        g.setColor(Color.white);
        g.drawString("Score:  " + String.valueOf(snakeBody.size()), TileSize - 16, TileSize);
    }
}

public void placeFood() {
	food.x = random.nextInt(boardWidth/TileSize) ;
	food.y = random.nextInt(boardHeight/TileSize) ;
	
	
}




@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyPressed(KeyEvent e) {
  if(e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1) {
	  velocityX = 0;
	  velocityY = -1;
	  
  }
  else if(e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
	  velocityX = 0;
	  velocityY = 1;
	  
  }
  else if(e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1) {
	  velocityX = -1;
	  velocityY = 0;
	  
  }
  else if(e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1) {
	  velocityX = 1;
	  velocityY = 0;
	  
  }
  
    	
    }
    


@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

public void move() {
	
	if(collision(snakeHead,food)) {
		snakeBody.add(new Tile(food.x, food.y));
		placeFood();
	}
	
	
	
	for(int i = snakeBody.size() - 1; i>=0; i--) {
		Tile snakePart = snakeBody.get(i);
		if(i == 0) {
			
			snakePart.x = snakeHead.x;
			snakePart.y = snakeHead.y;
			
		}
		else {
				Tile prevSnakePart = snakeBody.get(i-1);
				snakePart.x = prevSnakePart.x;
				snakePart.y = prevSnakePart.y;
				
		}
	}
	
	
	
	
	snakeHead.x += velocityX;
	snakeHead.y += velocityY;
	
	for(int i = 0; i<snakeBody.size(); i++) {
		Tile SnakePart = snakeBody.get(i);
		
		if(collision(snakeHead,SnakePart)) {
			gameOver = true;
			
		}
		if(snakeHead.x * TileSize <=0 || snakeHead.x * TileSize >= boardWidth || snakeHead.y * TileSize <=0 || snakeHead.y * TileSize >= boardHeight) {
			gameOver = true;
			
		}
	}
}

@Override
public void actionPerformed(ActionEvent e) {
	move();
	repaint();
	if(gameOver) {
		gameLoop.stop();
	}
}



}
