package com.uff.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.uff.processing.TextProcess;

/**
 * 
 * @author abdelbaki_mahmoudi
 *
 */
public class TextFileDataReader implements DataReader {

    private List<TextProcess> lstText = new ArrayList<TextProcess>();

    public void register(TextProcess ttxt) {
        this.lstText.add(ttxt);
    }

    public void read(InputStream is) throws DataReaderException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
            	if(line.split(";").length >0){
            		notifyProcesses(line);
            	}
            }
        } catch (IOException e) {
            throw new DataReaderException("probleme de lecture de texte ", e);
        } finally {
            close(is);
        }
    }

    private void notifyProcesses(String line) {
        for (int i = 0; i < lstText.size(); i++) {
            TextProcess textHandler = lstText.get(i);
            textHandler.processLine(line);
        }
    }

    private void close(InputStream inputStream) {
        try {
            inputStream.close();
        } catch (IOException e) {
            //
        }
    }
}

