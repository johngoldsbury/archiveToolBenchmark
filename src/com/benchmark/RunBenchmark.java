package com.benchmark;

import java.io.File;
import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import uk.gov.nationalarchives.droid.command.DroidCommandLine;

import com.google.caliper.runner.CaliperMain;

public class RunBenchmark{

	
	public static void main(String[] args) throws Exception {
		
//		CaliperMain.main(CaliperBenchmarkTest.class, args);  
//		String[] testThing = {"-h","/home/goldsbjohn/caliper"};

		TikaBench tika = new TikaBench();
		if (args[0].length()<1 && args!= null){
			String fileName = new File(args[0]).getName();
			System.out.println(fileName);
		}
		else{
			tika.parseTika(args[0]);
		}
		
		
//		DroidCommandLine.main(testThing);		
//		testThing = new String[] {"/home/goldsbjohn/caliper"};
//		JhoveBench.main(testThing);
		
	}
	

}
