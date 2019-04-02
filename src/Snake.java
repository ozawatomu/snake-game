import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Snake {
	private XYPair pos;
	private XYPair vel;
	private int length;
	private boolean hasMovedSinceKey;
	ArrayList<XYPair> body;
	ArrayList<Integer> moves;

	public Snake(Field field, int blockSize) {
		pos = new XYPair(field.xSize / 3, field.ySize / 2);
		vel = new XYPair(1, 0);
		length = 2;
		body = new ArrayList<XYPair>();
		body.add(new XYPair(pos.x - 1, pos.y));
		body.add(pos.clone());
		hasMovedSinceKey = true;
		moves = new ArrayList<Integer>();
	}

	public void draw(Graphics g, int blockSize, Field field) {
		g.setColor(new Color(11, 17, 11));
		int drawBlockSize = blockSize - 2;
		for (XYPair bodyPos : body) {
			g.fillRect(field.x + bodyPos.x * blockSize + 1, field.y + bodyPos.y * blockSize + 1, drawBlockSize,
					drawBlockSize);
		}
	}

	public boolean move(Field field) {
		if (isKO(field)) {
			return true;
		} else {
			pos.x += vel.x;
			pos.y += vel.y;
			body.add(pos.clone());
			while (length < body.size()) {
				body.remove(0);
			}
			hasMovedSinceKey = true;
			if (moves.size() != 0) {
				switch (moves.get(0)) {
				case 0:
					up();
					break;
				case 1:
					down();
					break;
				case 2:
					left();
					break;
				case 3:
					right();
					break;
				}
				moves.remove(0);
			}
			return false;
		}
	}

	public boolean isKO(Field field) {
		XYPair nextPos = new XYPair(pos.x + vel.x, pos.y + vel.y);
		boolean isKO = false;
		for (XYPair bodyPos : body) {
			// System.out.println(bodyPos.toString() + " " + nextPos.toString());
			if (nextPos.x == bodyPos.x && nextPos.y == bodyPos.y) {
				isKO = true;
			}
		}
		if (nextPos.x < 0 || nextPos.y < 0 || nextPos.x >= field.xSize || nextPos.y >= field.ySize) {
			isKO = true;
		}
		return isKO;
	}

	public boolean isBody(XYPair pair) {
		for (XYPair bodyPos : body) {
			if (pair.x == bodyPos.x && pair.y == bodyPos.y) {
				return true;
			}
		}
		return false;
	}

	public void grow() {
		length += 1;
	}

	public void reset(Field field, int blockSize) {
		pos = new XYPair(field.xSize / 3, field.ySize / 2);
		vel = new XYPair(1, 0);
		length = 2;
		body = new ArrayList<XYPair>();
		body.add(new XYPair(pos.x - 1, pos.y));
		body.add(pos.clone());
	}

	public void up() {
		if (vel.y != 1) {
			if (hasMovedSinceKey) {
				vel.x = 0;
				vel.y = -1;
				hasMovedSinceKey = false;
			} else {
				moves.add(0);
			}
		}
	}

	public void down() {
		if (vel.y != -1) {
			if (hasMovedSinceKey) {
				vel.x = 0;
				vel.y = 1;
				hasMovedSinceKey = false;
			} else {
				moves.add(1);
			}
		}
	}

	public void left() {
		if (vel.x != 1) {
			if (hasMovedSinceKey) {
				vel.x = -1;
				vel.y = 0;
				hasMovedSinceKey = false;
			} else {
				moves.add(2);
			}
		}
	}

	public void right() {
		if (vel.x != -1) {
			if (hasMovedSinceKey) {
				vel.x = 1;
				vel.y = 0;
				hasMovedSinceKey = false;
			} else {
				moves.add(3);
			}
		}
	}
}
