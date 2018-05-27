package program;

/**
 * Throw CSV Format Exception Error
 * @author Ashley Cottrell
 * @author Radhimas Djan
 *
 */
public class CSVFormatException extends Exception {

	/**
	 * Throw CSV Format Exception Error
	 */
	public CSVFormatException() {

	}

	/**
	 * Throw CSV Format Exception with message
	 * 
	 * @param arg0
	 *            message
	 */
	public CSVFormatException(String arg0) {
		super(arg0);

	}

	/**
	 * Throw CSV Format Exception with throwable cause
	 * 
	 * @param arg0
	 *            Throwable cause
	 */
	public CSVFormatException(Throwable arg0) {
		super(arg0);

	}

	/**
	 * Throw CSV Format Exception with message and throwable cause
	 * 
	 * @param arg0
	 *            Message
	 * @param arg1
	 *            Throwable cause
	 */
	public CSVFormatException(String arg0, Throwable arg1) {
		super(arg0, arg1);

	}

	/**
	 * Throw CSV Format Exception with message, throwable cause, Enable supperssion
	 * and a writable stack trace
	 * 
	 * @param arg0
	 *            message
	 * @param arg1
	 *            throwable cause
	 * @param arg2
	 *            Enable supperssion
	 * @param arg3
	 *            writable stack trace
	 */
	public CSVFormatException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);

	}

}
