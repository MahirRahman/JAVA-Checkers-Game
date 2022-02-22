import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

/**
 * Description: This class represents a square in the checkerboard that extends the JComponent 
 *	class. It draws a square if it is empty (e) or a square with black (b) or red (r) circle depending on 
 *	if a checker piece is in the square.
 * @author Mahir Rahman
 * 26 October 2021
 */
public class CheckerPiece extends JComponent {

	//===============================================================Instance Properties
	private char status;
	private int row;
	private int column;
	private final int SQUARE_LENGTH = 60;
	private final int CIRCLE_DIAMETER = 40;
	private int SQUARE_X = 0;
	private int SQUARE_Y = 0;
	private int CIRCLE_X = 10;
	private int CIRCLE_Y = 10;


	//================================================================================Constructors
	/**
	 * The workhorse constructor passes parameter values to instance properties. It also catches exceptions in this case
	 * @param row The row number of the piece to be painted
	 * @param column The column number of the piece to be painted
	 * @param status The status of the piece to be painted
	 * @throws IllegalCheckerboardArgumentException 
	 */
	public CheckerPiece(int row, int column, char status) throws IllegalCheckerboardArgumentException  {

		this.row = row;
		this.column= column;
		this.status = status;

		//Exception Handling
		if (row < 0 || row > 7) {
			throw new IllegalCheckerboardArgumentException("The row number must be between 0 and 7 inclusive");
		}
		else if (column < 0 || column > 7) {
			throw new IllegalCheckerboardArgumentException("The column number must be between 0 and 7 inclusive");
		}
		else if (!(status == 'r' || status == 'b' || status == 'e')) {
			throw new IllegalCheckerboardArgumentException("The status must be r, b, or e");
		}
		else if (column % 2 == 1 && row % 2 == 1 && status != 'e')
			throw new IllegalCheckerboardArgumentException("No pieces can be placed on white square");
		else if (column % 2 == 0 && row % 2 == 0 && status != 'e')
			throw new IllegalCheckerboardArgumentException("No pieces can be placed on white square");

		
	}
	
	/**
	 * @return the status
	 */
	public char getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(char status) {
		this.status = status;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}

	//================================================================================Methods
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void paintComponent(Graphics g) {
		//The following loops paint green square and checker pieces if condition is true
		//If not true, then paints it white
		if (column % 2 == 0) {
			if (row % 2 == 1) {
				g.setColor(Color.GREEN);
				g.fillRect(SQUARE_X, SQUARE_Y, SQUARE_LENGTH, SQUARE_LENGTH);
				if (status == 'b') {
					g.setColor(Color.BLACK);
					g.fillOval(CIRCLE_X, CIRCLE_Y, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
				}
				else if (status == 'r') {
					g.setColor(Color.RED);
					g.fillOval(CIRCLE_X, CIRCLE_Y, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
				}
			}
			else {
				g.setColor(Color.WHITE);
				g.fillRect(SQUARE_X, SQUARE_Y, SQUARE_LENGTH, SQUARE_LENGTH);
			}
		}
		else {
			if (row % 2 == 0) {
				g.setColor(Color.GREEN);
				g.fillRect(SQUARE_X, SQUARE_Y, SQUARE_LENGTH, SQUARE_LENGTH);
				if (status == 'b') {
					g.setColor(Color.BLACK);
					g.fillOval(CIRCLE_X, CIRCLE_Y, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
				}
				else if (status == 'r') {
					g.setColor(Color.RED);
					g.fillOval(CIRCLE_X, CIRCLE_Y, CIRCLE_DIAMETER, CIRCLE_DIAMETER);

				}
			}
			else {
				g.setColor(Color.WHITE);
				g.fillRect(SQUARE_X, SQUARE_Y, SQUARE_LENGTH, SQUARE_LENGTH);
			}
		}
	}
}
