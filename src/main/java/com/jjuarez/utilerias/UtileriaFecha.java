package com.jjuarez.utilerias;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtileriaFecha {
	
	public static Date dateFromString(String strDate) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = formatter.parse(strDate);
			return date;
		} catch (ParseException e) {
			throw new Exception();
		}
	}

//	public static void main(String[] args) {
//		try {
//			Date date = UtileriaFecha.dateFromString("1");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
