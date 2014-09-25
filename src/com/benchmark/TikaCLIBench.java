package com.benchmark;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import org.apache.tika.exception.TikaException;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.apache.tika.mime.MimeTypeException;

import org.apache.commons.io.FileUtils;

public class TikaCLIBench {

	/**
	 * Execute TikaCLIBench which iterates over files in a folder or a single
	 * file in metadata extraction (-m) mode
	 * 
	 * @param path
	 * @author John Goldsbury
	 * @throws Exception
	 * @see TikaCli
	 * 
	 * 
	 */
	public static void parseTikaCLI(String path){

		InputStream input = null;
		File file = new File(path);
		File someFile = null;
		int numExceptions = 0;
		String exceptionFiles = "";

		if (file.isDirectory()) {

			Iterator<File> iter = FileUtils.iterateFiles(file, null, true);
			while (iter.hasNext()) {

				someFile = iter.next();

				if (!someFile.isDirectory()) {
					System.out.println("Analysing new file: "+someFile.getAbsolutePath());
					try{
						input = new FileInputStream(someFile);
						TikaCLI.main(new String[] { "-m", "file://" + someFile });
					
					}catch (Exception e) {
				// TODO Auto-generated catch block
						e.printStackTrace();
						numExceptions++;
						exceptionFiles += numExceptions+": "+someFile.getAbsolutePath()+"\n";
					}
				}

			}
		} else {
			try{
				TikaCLI.main(new String[] { "-m", "file://" + someFile });
			}catch(Exception e){
			// TODO Auto-generated catch block
				e.printStackTrace();
				numExceptions++;
			}

		}
		if (input != null) {
			try{
				input.close();
			}catch(IOException e){
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(numExceptions>0){
			System.out.println("#### Number of caught exceptions: "+numExceptions+" ####");
			System.out.println("#### Files at fault are: "+exceptionFiles+" ####");
		}	
	}
}
