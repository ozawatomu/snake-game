import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Food {
	public XYPair pos;
	private Random r;

	public Food(Snake snake, Field field) {
		r = new Random();
		XYPair testPos = new XYPair(r.nextInt(field.xSize), r.nextInt(field.ySize));
		while (snake.isBody(testPos)) {
			testPos = new XYPair(r.nextInt(field.xSize), r.nextInt(field.ySize));
		}
		pos = testPos;
	}

	public void draw(Graphics g, int blockSize, Field field) {
		g.setColor(new Color(11, 17, 11));
		int drawBlockSize = blockSize - 2;
		/*
		 * g.fillOval(field.x + pos.x * blockSize + 1, field.y + pos.y * blockSize + 1,
		 * drawBlockSize, drawBlockSize);
		 */
		g.fillRect(field.x + pos.x * blockSize + 1 + drawBlockSize / 3, field.y + pos.y * blockSize + 1,
				drawBlockSize / 3, drawBlockSize / 3);
		g.fillRect(field.x + pos.x * blockSize + 1, field.y + pos.y * blockSize + 1 + drawBlockSize / 3,
				drawBlockSize / 3, drawBlockSize / 3);
		g.fillRect(field.x + pos.x * blockSize + 1 + 2 * drawBlockSize / 3,
				field.y + pos.y * blockSize + 1 + drawBlockSize / 3, drawBlockSize / 3, drawBlockSize / 3);
		g.fillRect(field.x + pos.x * blockSize + 1 + drawBlockSize / 3,
				field.y + pos.y * blockSize + 1 + 2 * drawBlockSize / 3, drawBlockSize / 3, drawBlockSize / 3);
	}

	public void move(Snake snake, Field field) {
		XYPair testPos = new XYPair(r.nextInt(field.xSize), r.nextInt(field.ySize));
		while (snake.isBody(testPos)) {
			testPos = new XYPair(r.nextInt(field.xSize), r.nextInt(field.ySize));
		}
		pos = testPos;
	}

	public boolean checkEaten(Snake snake, Field field) {
		if (snake.isBody(pos)) {
			move(snake, field);
			return true;
		}
		return false;
	}

	public void reset(Snake snake, Field field) {
		XYPair testPos = new XYPair(r.nextInt(field.xSize), r.nextInt(field.ySize));
		while (snake.isBody(testPos)) {
			testPos = new XYPair(r.nextInt(field.xSize), r.nextInt(field.ySize));
		}
		pos = testPos;
	}
}
