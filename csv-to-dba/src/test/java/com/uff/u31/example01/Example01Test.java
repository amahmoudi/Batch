package com.baki.batch.example01;

import java.util.List;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;

import com.uff.CSVDao;
import com.uff.CSVDaoFactory;
import com.uff.logger.Message;
import com.baki.batch.model.CRE5A001;
import com.baki.batch.model.CRE5A010;

import junit.framework.Assert;
/**
 * 
 * @author abdelbaki_mahmoudi
 *
 */
public class Example01Test {

    private static CSVDao dao;

    @BeforeClass
    public static void setup() {
    	Properties properties = new Properties();
	     properties.put("date.csvFile", "20170628");
        CSVDaoFactory factory = new CSVDaoFactory("/csv-config.xml");
        dao = new CSVDao(factory,properties);
    }


    @Test
    public void find_allEntriesCRE5A001() {
        List<CRE5A001> cre5a001 = dao.find(CRE5A001.class);
        Assert.assertNotNull(cre5a001);
    }
    
    @Test
    public void find_allEntriesCRE5A010() {
        List<CRE5A010> cre5a010 = dao.find(CRE5A010.class);
        Assert.assertNotNull(cre5a010);
    }
    
    @Test
    public void find_allMessagesCRE5A001() {
        List<Message> findMessages = dao.findMessages(CRE5A001.class);
        Assert.assertNotNull(findMessages);
    }
    
    @Test
    public void find_allMessagesCRE5A010() {
    	List<Message> findMessages = dao.findMessages(CRE5A010.class);
        Assert.assertNotNull(findMessages);
    }
    

}
