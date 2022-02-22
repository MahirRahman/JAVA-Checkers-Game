import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.MouseInputAdapter;
/************************************************
Author:     Mahir Rahman
Class:      CSE271
Date:       10/31/2021
Assignment: Project - 4
Compiler:   Eclipse
 ************************************************/
/**
 * @author Mahir Rahman
 * Description: This creates a GUI which represents a checker board with pieces
 *
 */
public class CheckerGame extends JFrame {

	//================================================================Instance Properties
	private static char[][] boardStatus = new char[][] { 
		{'e', 'b', 'e', 'b', 'e', 'b', 'e', 'b'}, 
		{'b', 'e', 'b', 'e', 'b', 'e', 'b', 'e'}, 
		{'e', 'b', 'e', 'b', 'e', 'b', 'e', 'b'}, 
		{'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'}, 
		{'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'}, 
		{'r', 'e', 'r', 'e', 'r', 'e', 'r', 'e'}, 
		{'e', 'r', 'e', 'r', 'e', 'r', 'e', 'r'}, 
		{'r', 'e', 'r', 'e', 'r', 'e', 'r', 'e'}
	};
	private static JFrame frame;
	final URI uri = new URI("https://www.wikihow.com/Play-Checkers");
	
	//=================================================================Constructors
	/**
	 * The constructor creates JPanel, adds menu bar, menus, and items. 
	 * It also sets some frame properties
	 */
	public CheckerGame() throws URISyntaxException {

		this.boardStatus = boardStatus;

		//Creates the panel for the checker board
		JPanel panel;
		panel = new CheckerBoard(boardStatus);

		//This section helps with the status panel
		int redCount = 0;
		int blackCount = 0;

		for (int i = 0; i < boardStatus.length; i++) {
			for (int j = 0; j < boardStatus[0].length; j++) {
				if (boardStatus[i][j] == 'r') {
					redCount++;
				}
				else if (boardStatus[i][j] == 'b') {
					blackCount++;
				}
			}
		}

		//Creates the status panel
		JPanel status = new JPanel();
		status.setLayout(new BorderLayout());
		JLabel label1 = new JLabel("The number of red pieces are: " + redCount + " and the number of black pieces are: " + blackCount, SwingConstants.CENTER);
		JLabel label2 = new JLabel("This game was developed by Mahir Rahman");
		status.add(label1, BorderLayout.NORTH);
		status.add(label2, BorderLayout.CENTER);
		add(status, BorderLayout.SOUTH);

		//Creates the menu bar, menu, and menu items
		//Adds actionListeners to menu items
		JMenuBar menuBar = new JMenuBar();
		JMenu menu1 = new JMenu("Game");
		JMenu menu2 = new JMenu("Help");
		JMenuItem newItem = new JMenuItem("New");
		newItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				boardStatus = new char[][] { 
					{'e', 'b', 'e', 'b', 'e', 'b', 'e', 'b'}, 
					{'b', 'e', 'b', 'e', 'b', 'e', 'b', 'e'}, 
					{'e', 'b', 'e', 'b', 'e', 'b', 'e', 'b'}, 
					{'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'}, 
					{'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'}, 
					{'r', 'e', 'r', 'e', 'r', 'e', 'r', 'e'}, 
					{'e', 'r', 'e', 'r', 'e', 'r', 'e', 'r'}, 
					{'r', 'e', 'r', 'e', 'r', 'e', 'r', 'e'}
				};
				((CheckerBoard)panel).setBoardStatus(boardStatus);
			}

		});
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				System.exit(0);
			}
		});
		JMenuItem rulesItem = new JMenuItem("Checker Game Rules");
		rulesItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JEditorPane ep = new JEditorPane("text/html", "<html><body\">" //
						+ "<a href=\"https://www.wikihow.com/Play-Checkers\">https://www.wikihow.com/Play-Checkers</a>" //
						+ "</body></html>");

				// handle link events
				ep.addHyperlinkListener(new HyperlinkListener()
				{
					@Override
					public void hyperlinkUpdate(HyperlinkEvent e)
					{
						if (e.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED))
							try {
								Desktop.getDesktop().browse(uri);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}
				});
				ep.setEditable(false);

				JOptionPane.showMessageDialog(frame, ep);
			}
		});
		JMenuItem aboutItem = new JMenuItem("About Checker Game App");
		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(frame, "Creator: Mahir Rahman \n Email: rahmanm7@miamioh.edu \n University: Miami University");
			}
		});
		menu1.add(newItem);
		menu1.add(exitItem);
		menu2.add(rulesItem);
		menu2.add(aboutItem);

		menuBar.add(menu1);
		menuBar.add(menu2);
		setJMenuBar(menuBar);
		add(panel, BorderLayout.CENTER);


		//Sets some frame properties
		final int FRAME_WIDTH = 480;
		final int FRAME_HEIGHT = 530;

		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Checker Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	//=================================================================================Main method
	public static void main (String[] args) {

		try {
			frame = new CheckerGame();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Once again, always remember to set the visibility to true in order to actually
		// see your GUI.
		frame.setVisible(true);
	}
}
