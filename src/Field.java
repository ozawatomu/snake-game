import java.awt.Color;
import java.awt.Graphics;

public class Field {
	public int x;
	public int y;
	public int xSize;
	public int ySize;

	public Field(int x, int y, int xSize, int ySize) {
		this.x = x;
		this.y = y;
		this.xSize = xSize;
		this.ySize = ySize;
	}

	public void draw(Graphics g, int blockSize) {
		g.setColor(new Color(11, 17, 11));
		g.drawRect(x - 1, y - 1, xSize * blockSize + 2, ySize * blockSize + 2);
	}
}
