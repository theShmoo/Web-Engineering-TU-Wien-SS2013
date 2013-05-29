package tuwien.big.formel0.controller;

/**
 * ServiceException
 *
 * @author David Pfahler
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -8138544283872805363L;

	/**
	 * Default empty constructor.
	 */
	public ServiceException() {
		super();
	}

	/**
	 * Constructs a new instance with the specified describing message.
	 * 
	 * @param msg
	 *            the description of the occurred exception.
	 */
	public ServiceException(String msg) {
		super(msg);
	}

	/**
	 * Constructs a new instance with the specified cause.
	 * 
	 * @param cause
	 *            the <code>throwable</code> instance which generated this
	 *            exception.
	 */
	public ServiceException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new instance with the specified describing message and
	 * cause.
	 * 
	 * @param msg
	 *            the description of the occurred exception.
	 * @param cause
	 *            the <code>throwable</code> instance which generated this
	 *            exception.
	 */
	public ServiceException(String msg, Throwable cause) {
		super(msg, cause);
	}

	/**
	 * Constructs a new runtime exception with the specified detail message,
	 * cause, suppression enabled or disabled, and writable stack trace enabled
	 * or disabled.
	 * 
	 * @param msg
	 *            the detail message.
	 * @param cause
	 *            the cause. (A {@code null} value is permitted, and indicates
	 *            that the cause is nonexistent or unknown.)
	 * @param enableSuppression
	 *            whether or not suppression is enabled or disabled
	 * @param writableStackTrace
	 *            whether or not the stack trace should be writable
	 */
	public ServiceException(String msg, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(msg, cause, enableSuppression, writableStackTrace);
	} 

}
