import java.awt.*;

final class RSFrame extends Frame {
	
	public RSFrame(RSApplet RSApplet_, int i, int j) {
		rsApplet = RSApplet_;
		setTitle(Config.FrameTitle);
		setResizable(true);
		setVisible(true);
		toFront();
		setSize(i + 8, j + 28);//28 33
		setLocationRelativeTo(null);
	}
	
	public Graphics getGraphics() {
		Graphics g = super.getGraphics();
		g.translate(4, 24);//24 29
		return g;
	}

	public void update(Graphics g) {
		rsApplet.update(g);
	}

	public void paint(Graphics g) {
		rsApplet.paint(g);
	}

	private final RSApplet rsApplet;
	public static final long serialVersionUID = 24362462L;//Serialization
}
