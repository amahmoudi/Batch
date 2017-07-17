package com.uff.util;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * @author abdelbaki_mahmoudi
 *
 */
public class DateUtils {

	private DateUtils() {
	}
	
	public static String FormatToYYYMMDD(Date myDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String formattedDate = sdf.format(myDate);
		return formattedDate;
		
	}

}
