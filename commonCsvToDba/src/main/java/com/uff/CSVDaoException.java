package com.uff;

/**
 * 
 * @author abdelbaki_mahmoudi
 *CSVDaoException
 */
public class CSVDaoException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CSVDaoException(String message) {
        super(message);
    }

    public CSVDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
