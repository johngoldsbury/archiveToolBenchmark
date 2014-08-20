package com.benchmark;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.Permission;
import java.util.Collection;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import edu.harvard.hul.ois.jhove.JhoveBase;

import uk.gov.nationalarchives.droid.command.DroidCommandLine;
import uk.gov.nationalarchives.droid.command.action.CommandLineException;

public class RunBenchmark {

	public static void main(String[] args) throws Exception {

		// CaliperMain.main(CaliperBenchmarkTest.class, args);
		CodeControl control = new CodeControl();
		try {
			control.disableSystemExit();
			// invoke the methods and other classes that are not allowed to call
			// System.exit.
			// Object ret = invokeExecute("runTools", runWith, parms);
			runTools(args);

		} finally {
			// finally enable exit
			control.enableSystemExit();
			System.out.println("Hi");
		}

	}

	public static void runTools(String[] args) throws IOException,
			SAXException, TikaException, CommandLineException,
			ClassNotFoundException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException {
		String[] testThing = {};
		String fileName = "";
		TikaBench tika = new TikaBench();

		if (args.length > 0) {
			fileName = new File(args[0]).getAbsolutePath();
			System.out.println(fileName);
		} else {
			System.out.println("Please enter a valid path");
			System.exit(0);
		}

		// DroidCommandLine.main(testThing);
		// tika.parseTika(fileName);

		System.out.println("Done2");

		testThing = new String[] { "-R", "-Nx", "csv", "-Nr", fileName, "-Ns",
				"/home/goldsbjohn/.droid6/signature_files/DROID_SignatureFile_V77.xml" };
		// com.benchmark.DroidCommandLine.main(testThing);
		// Class clazz = Class.forName("java.lang.ApplicationShutdownHooks");
		// Field field = clazz.getDeclaredField("hooks");
		// field.setAccessible(true);
		// Object hooks = field.get(null);
		//
		// IdentityHashMap map = (IdentityHashMap) hooks;
		// for (Iterator iterator = map.keySet().iterator();
		// iterator.hasNext();) {
		//
		// Thread thread = (Thread) iterator.next();
		// Runtime.getRuntime().removeShutdownHook(thread);
		//
		// }
		Runtime.getRuntime().removeShutdownHook(Thread.currentThread());

		// testThing = new String[] { "-c", "conf/jhove.conf", "-s",
		// fileName,"-o","/home/goldsbjohn/test.txt" };
		// JhoveBench.main(testThing);

//		testThing = new String[] {
//				"-R",
//				"-Nr",
//				"/home/goldsbjohn/govdocs",
//				"-Ns",
//				"/home/goldsbjohn/.droid6/signature_files/DROID_SignatureFile_V77.xml"};
		// testThing = new String[]
		// {"-R","-a","/home/goldsbjohn/govdocs/","-p","/home/goldsbjohn/result1.droid"};//"Result",
		// "-t", "DROID Report XML", "-e",
		// "/home/goldsbjohn/govdocs/result1Report.xml"};
//		com.benchmark.DroidCommandLine.main(testThing);
		System.out.println("Done");
		 testThing = new String[] { "-c", "conf/jhove.conf", "-s",
		 fileName,"-o","/home/goldsbjohn/test2.txt" };
		 JhoveBench.main(testThing);

	}

}
