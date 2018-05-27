package program;

/**
 * Throw Stock Exception
 * 
 * @author Ashley Cottrell
 * @author Radhimas Djan
 *
 */
public class StockException extends Exception {

	/**
	 * Throw Stock Exception Error
	 */
	public StockException() {

	}

	/**
	 * Throw Stock Exception Error with Message
	 * 
	 * @param message
	 */
	public StockException(String message) {
		super(message);

	}

	/**
	 * Throw Stock Exception Error with a throwable cause
	 * 
	 * @param cause
	 */
	public StockException(Throwable cause) {
		super(cause);

	}

	/**
	 * Throw Stock Exception Error with message and throwable cause
	 * 
	 * @param message
	 * @param cause
	 */
	public StockException(String message, Throwable cause) {
		super(message, cause);

	}

	/**
	 * Throw Stock Exception Error with message, throwable cause, Enable supperssion
	 * and a writable stack trace
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public StockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
