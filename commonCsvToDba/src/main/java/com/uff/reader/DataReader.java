package com.uff.reader;

import java.io.InputStream;

/**
 * 
 * @author abdelbaki_mahmoudi
 *
 */
public interface DataReader {

    public void read(InputStream is) throws DataReaderException;
}
