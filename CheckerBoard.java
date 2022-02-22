import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Description: The class designs the panel to be added to the frame
 * @author Mahir Rahman
 * 31 Oct 2021
 */
public class CheckerBoard extends JPanel {

	//===============================================================Instance Properties
	private char[][] board;
	//private static CheckerBoard panel;
	private JComponent jc;
	public CheckerPiece[][] comp = new CheckerPiece[8][8];
	private boolean clicked = false;
	private int round = 0;
	private char color;
	private int tempRow;
	private int tempCol;

	//===============================================================Constants
	private final int ROWS = 8;
	private final int COLUMNS = 8;

	//=============================================================================Constructors
	/**
	 * The work horse constructor passes parameter variables to instance properties.
	 * It also designs the layout of the panel
	 * @param boardStatus The state of the pieces on the board
	 */
	public CheckerBoard(char[][] boardStatus) {
		this.board = boardStatus;



		setLayout(new GridLayout(ROWS, COLUMNS));
		for (int i = 0; i < boardStatus.length; i++) {
			for (int j = 0; j < boardStatus[i].length; j++) {
				try {
					jc = new CheckerPiece(i, j, boardStatus[i][j]);
					comp[i][j] = (CheckerPiece) jc;
					add(jc);
				} catch (IllegalCheckerboardArgumentException e) {
					e.printStackTrace();
					System.exit(0);
				}

			}
		}
		/**
		 * Description: The functioning part of the game. Listens to mouse clicks and moves
		 * pieces accordingly
		 * @author Mahir Rahman 8 Nov 2021
		 *
		 */
		class PanelListener implements MouseListener {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public void mouseClicked(MouseEvent event) {
				if (clicked == false) {
					CheckerPiece tempComp = (CheckerPiece) getComponentAt(event.getX(), event.getY());
					if (tempComp.getStatus() != 'e') {
						clicked = true;
						tempRow = tempComp.getRow();
						tempCol = tempComp.getColumn();
						color = tempComp.getStatus();
					}
				}
				else {
					CheckerPiece tempComp = (CheckerPiece) getComponentAt(event.getX(), event.getY());
					if (tempComp.getStatus() == 'e') {
						clicked = false;
						if ((tempComp.getColumn() % 2 == 0 && tempComp.getRow() % 2 == 0) ||  (tempComp.getColumn() % 2 == 1 && tempComp.getRow() % 2 == 1))
						{}
						else {
							if (round % 2 == 0 && color == 'b') {
								if (Math.abs(tempComp.getColumn() - tempCol) == 1 && Math.abs(tempComp.getRow() - tempRow) == 1) {
									setCheckerPiece(tempRow, tempCol, 'e');
									setCheckerPiece(tempComp.getRow(), tempComp.getColumn(), 'b');
									round++;
								}

							}
							else if (round % 2 == 1 && color == 'r') {
								if (Math.abs(tempComp.getColumn() - tempCol) >= 1 && Math.abs(tempComp.getRow() - tempRow) == 1) {
									setCheckerPiece(tempRow, tempCol, 'e');
									setCheckerPiece(tempComp.getRow(), tempComp.getColumn(), 'r');
									round++;
								}
							}
						}
					}
				}
			}


			/**
			 * {@inheritDoc}
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		}
		addMouseListener(new PanelListener());
	}




	//==================================================================================Setters
	/**
	 * A  setter  method  that  sets the  boardStatus  array  using  the  received  parameter. 
	 * @param boardStatus the boardStatus array to set
	 */
	public void setBoardStatus(char[][] boardStatus) {
		board = boardStatus;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				setCheckerPiece(i, j, boardStatus[i][j]);
			}
		}

	}

	/**
	 * A  setter method  that  sets  the  status  in  boardStatus  for  a  
	 * specific  square  on  the  board based on the row and column. 
	 * 
	 * @param row The row of the square to be changed
	 * @param column The column of the square to be changed
	 * @param status The status of the piece on the board
	 */
	public  void  setCheckerPiece(int  row,  int  column,  char  status) {
		board[row][column] = status;
		comp[row][column].setStatus(status);
		repaint();

	}
}
