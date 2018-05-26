package program;

/**
 * Throw Delivery Exception
 * 
 * @author Ashley Cottrell
 * @author Radhimas Djan
 */
@SuppressWarnings("serial")
public class DeliveryException extends Exception {

	/**
	 * Throw Delivery Exception Error
	 */
	public DeliveryException() {

	}

	/**
	 * Throw Delivery Exception Error with message
	 * 
	 * @param message
	 */
	public DeliveryException(String message) {
		super(message);

	}

	/**
	 * Throw Delivery Exception Error with Throwable cause
	 * 
	 * @param cause
	 */
	public DeliveryException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Throw Delivery Exception Error with message and Throwable cause
	 * 
	 * @param message
	 * @param cause
	 */
	public DeliveryException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Throw Delivery Exception Error with message, Throwable cause, enable
	 * Suppression and writable Stack Trace;
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public DeliveryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
