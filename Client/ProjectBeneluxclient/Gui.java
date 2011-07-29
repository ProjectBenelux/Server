import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.lang.String;
import java.lang.reflect.Method;
import java.net.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;


public class Gui extends client implements ActionListener, FocusListener {

	public static Gui instance;
	private static JMenuItem menuItem;
	public JPanel gamePanel;
	public static final long serialVersionUID = 24362462L;//Serialization
	public String FSlookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
	public String imgDIR = signlink.findcachedir()+"/User/Frame/Img.PNG";
	
	public Gui(String args[]) {
		super();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			signlink.startpriv(InetAddress.getByName(Config.serverName));
			System.out.println("Operating System: " + System.getProperty("os.name") + " " + System.getProperty("os.version"));
			initUI();
			init();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void initUI() {
		try {
			JFrame.setDefaultLookAndFeelDecorated(true);
			JPopupMenu.setDefaultLightWeightPopupEnabled(true);
			frame = new JFrame(Config.FrameTitle);
			frame.setIconImage(new ImageIcon(imgDIR).getImage());
			frame.setLayout(new BorderLayout());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gamePanel = new JPanel();
			gamePanel.setLayout(new BorderLayout());
			gamePanel.add(this);
			clientWidth = 795; clientHeight = 553;
			gamePanel.setPreferredSize(new Dimension(765, 503));//iono
			initMenubar();
			frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
			frame.pack();
			frame.setVisible(true);
			frame.setResizable(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initMenubar() {
		/*JMenu fileMenu = new JMenu("File");
		String[] mainButtons = new String[] { "Exit" };
		String[] forumNames = new String[] { };
		for (String name : mainButtons) {
			JMenuItem menuItem = new JMenuItem(name);
			if (name.equalsIgnoreCase("-")) {
				fileMenu.addSeparator();
			} else {
				menuItem.addActionListener(this);
				fileMenu.add(menuItem);
			}
		}
		JMenuBar menuBar = new JMenuBar();
		JMenuBar jMenubar = new JMenuBar();
		frame.add(jMenubar);
		menuBar.add(fileMenu);
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);*/
	}
	
	public void chooseDetail() {
		
	}

	public URL getCodeBase() {
		try {
			return new URL("http://" + Config.serverName + "/cache");
		} catch (Exception e) {
			return super.getCodeBase();
		}
	}

	public URL getDocumentBase() {
		return getCodeBase();
	}

	public void loadError(String s) {
		System.out.println("loadError: " + s);
	}

	public String getParameter(String key) {
		return "";
	}

	private static void openURL(String url) {
		Desktop d = Desktop.getDesktop();
		try {
			d.browse(new URI(url)); 	
		} catch (Exception e) {
		}
	}

	public void actionPerformed(ActionEvent evt) {
		String cmd = evt.getActionCommand();
		try {
			if(cmd != null) {
				if (cmd.equalsIgnoreCase("Exit")) {
					int pane;
					if((pane = JOptionPane.showConfirmDialog(this, "Do you really want to exit the client?")) == 0)
						System.exit(0);
					return;
				}
			}
		} catch (Exception e) {
		}
	}
}