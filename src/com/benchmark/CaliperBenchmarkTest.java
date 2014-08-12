package com.benchmark;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;

import uk.gov.nationalarchives.droid.command.DroidCommandLine;
import uk.gov.nationalarchives.droid.command.action.DroidCommand;
import uk.gov.nationalarchives.droid.core.interfaces.DroidCore;
import uk.gov.nationalarchives.droid.core.interfaces.config.DroidGlobalConfig;

import com.google.caliper.Benchmark;

public final class CaliperBenchmarkTest {

	@Benchmark
	public void test() throws Exception {
		
		
		String fileName = "/home/goldsbjohn/caliper/README";
		AutoDetectParser parser = new AutoDetectParser();
		ContentHandler handler = new BodyContentHandler(Integer.MAX_VALUE);
		Metadata metadata = new Metadata();
		InputStream input = null;

		File file = new File(fileName);
		File someFile = null;
//		System.out.println("Opening InputStream");
		if (file.isDirectory()) {
			// System.out.println("A directory");
			Iterator<File> iter = FileUtils.iterateFiles(file, null, true);
			while (iter.hasNext()) {
				// parser = new AutoDetectParser();
				metadata = new Metadata();
				// handler = new BodyContentHandler();
				someFile = iter.next();
				// System.out.println(someFile.getAbsolutePath());
				if (!someFile.isDirectory()) {

					input = new FileInputStream(someFile);
					parser.parse(input, handler, metadata);
					// System.out.print(someFile.getAbsolutePath() + " - ");
					// for (String str : metadata.names()) {
					// if (!str.contains("Unknown")) {
					// System.out.print("## ");
					// System.out.print(str + ": " + metadata.get(str)
					// + " ");
					// }
					//
					// }
					// System.out.println();

					// System.out.println("Title: " + metadata.get("title")
					// + " ## Description: " + metadata.get("description")
					// + " ## file name: " + someFile.getAbsolutePath());

				}

			}
		} else {
//			System.out.println("Just one file");
			input = new FileInputStream(file);
			parser.parse(input, handler, metadata);
//			for (String str : metadata.names()) {
//				if (!str.contains("Unknown")) {
//					System.out.print("## ");
//					System.out.print(str + ": " + metadata.get(str) + " ");
//				}
//
//			}
//			System.out.println();
		}
		if (input != null) {
			input.close();
//			System.out.println("Closed");
		}
		// -Dexec.mainClass="com.google.caliper.runner.CaliperMain"
		// -Dexec.args="com.benchmark.CaliperBenchmarkTest"
	}

}
