
public class XYPair {
	public int x;
	public int y;

	public XYPair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public XYPair clone() {
		XYPair clone = new XYPair(this.x, this.y);
		return clone;
	}

	public String toString() {
		return "[" + x + ", " + y + "]";
	}
}
