package com.hzyc.hzycpos.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileConversion {

	
	public static byte[] PictureConversion(File f) throws Exception{
		
		byte [] buffer = null;
		InputStream is = null;
		ByteArrayOutputStream bos = null;
		
		if(f != null && f.length() > 0 ){
			try{
				is = new FileInputStream(f);  
				
			    bos = new ByteArrayOutputStream(1000);  
			    
			    byte[] b = new byte[1000];  
		        int n;  
		            
	            while ((n = is.read(b)) != -1) {  
	               bos.write(b, 0, n);  
	            }  
	            buffer = bos.toByteArray();  
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				is.close();
				bos.close();
			}
	       
		}
		return buffer;
	}
	
	
}
