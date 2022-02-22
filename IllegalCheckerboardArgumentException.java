/**
 * Description: Catches specfic illegal arguments like invalid columns, rows, and status
 * @author Mahir Rahman
 * 31 October 2021
 */
public class IllegalCheckerboardArgumentException extends Exception{
	
	//===================================================================Constructors
	/**
	 * The constructor calls the the default constructor of the exception class
	 */
	public IllegalCheckerboardArgumentException() {
		super();
	}
	
	/**
	 * The constructor calls the the partial workhorse constructor of the exception class
	 * @param message The message to be displayed
	 */
	public IllegalCheckerboardArgumentException(String message) {
		super(message);
	}
}
