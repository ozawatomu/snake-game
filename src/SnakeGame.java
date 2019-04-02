import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {
	static int fieldSize = 20;
	static int blockSize = 800 / fieldSize;
	int difficulty = 2; // >= 0
	static Field field = new Field(200, 200, fieldSize, fieldSize);
	Timer timer = new Timer(250 / difficulty, this);
	int highScore = 0;
	int score = 0;
	static Snake snake;
	static Food food;

	public static void main(String[] args) {
		JFrame jframe = new JFrame();
		jframe.setTitle("Snake");
		jframe.setVisible(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		SnakeGame snakeGame = new SnakeGame();
		snakeGame.setPreferredSize(new Dimension(1200, 1200));
		jframe.addKeyListener(snakeGame);
		jframe.add(snakeGame);
		jframe.pack();

		snake = new Snake(field, blockSize);
		food = new Food(snake, field);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(154, 204, 153));
		g.fillRect(0, 0, 1200, 1200);
		g.setColor(new Color(11, 17, 11));
		g.setFont(new Font("Courier", Font.PLAIN, 50)); 
		g.drawString("High Score: " + highScore, 200, 90);
		g.drawString("Score: " + score, 200, 150);
		field.draw(g, blockSize);
		food.draw(g, blockSize, field);
		snake.draw(g, blockSize, field);
		timer.start();
	}

	public void actionPerformed(ActionEvent arg0) {
		if (food.checkEaten(snake, field)) {
			snake.grow();
			score += 1;
			if (score > highScore) {
				highScore = score;
			}
		}
		if (snake.move(field)) {
			// timer.stop();
			snake.reset(field, blockSize);
			food.reset(snake, field);
			score = 0;
		}
		repaint();
	}

	public void keyPressed(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_UP:
			snake.up();
			break;
		case KeyEvent.VK_DOWN:
			snake.down();
			break;
		case KeyEvent.VK_LEFT:
			snake.left();
			break;
		case KeyEvent.VK_RIGHT:
			snake.right();
			break;
		}
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
