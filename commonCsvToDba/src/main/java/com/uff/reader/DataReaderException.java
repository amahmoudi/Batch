package com.uff.reader;

/**
 * 
 * @author abdelbaki_mahmoudi
 *
 */
public class DataReaderException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataReaderException(String message) {
        super(message);
    }

    public DataReaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
