package com.benchmark;

import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import uk.gov.nationalarchives.droid.command.DroidCommandLine;
import uk.gov.nationalarchives.droid.command.action.CommandLineException;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import com.google.caliper.api.Macrobenchmark;
import com.google.caliper.api.VmOptions;

/**
 * Runs all annotated benchmarks using the Google Caliper runner
 * @code mvn exec:java -Dexec.mainClass=com.google.caliper.runner.CaliperMain -Dexec.args="com.benchmark.CaliperBenchmark -Dpath='' -Dsig='' -Dresult='' "
 */
public final class CaliperBenchmark {

	@Param({ "/home/goldsbjohn/govdocs/" })
	String path;
	@Param({ "/home/goldsbjohn/.droid6/signature_files/DROID_SignatureFile_V77.xml" })
	String sig;
	@Param({ "/home/goldsbjohn/JhoveResult.txt" })
	String result;

	// To change any of the above parameters, change command line argument and
	// add -Dname=/pathname/path/file.ext etc
	// e.g for maven: ...
	// -Dexec.args="com.benchmark.CaliperBenchmark -Dpath=/home/goldsbjohn/govdocs"
	
	
	
	
	@Macrobenchmark
	public void tika_CLI() {
		try{
			TikaCLIBench.parseTikaCLI(path);
		}catch (Exception e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Macrobenchmark
	public void tikaMetaDetect() {
		try{
			TikaBench.parseTika(path,true);
		}catch (Exception e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Macrobenchmark
	public void tikaNoMetaDetect() {
		try{
			TikaBench.parseTika(path,false);
		}catch (Exception e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Macrobenchmark
	public void tikaBody() {
		try{
			TikaBench.parseTikaPrintBody(path, true);
		}catch (Exception e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Macrobenchmark
	public void tikaBodyNoMetaDetect() {
		try{
			TikaBench.parseTikaPrintBody(path, false);
		}catch (Exception e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Macrobenchmark
	public void jhoveS_Macro() {
		try{
			JhoveCLI.main(new String[] { "-c", "conf/jhove.conf", "-s",	path, "-o", result});
		}catch (Exception e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Macrobenchmark
	public void jhoveK() {
		try{
			JhoveCLI.main(new String[] { "-c", "conf/jhove.conf", "-k",
				path, "-o", result });
		}catch (Exception e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	@Macrobenchmark
	public void jhoveR() {
		try{
			JhoveCLI.main(new String[] { "-c", "conf/jhove.conf", "-r",
				path, "-o", result });
		}catch (Exception e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// @Benchmark
	// public void signatureDownload() throws CommandLineException {
	// 	try{
	// 		DroidCLI.main(new String[] { "-d" });
	// 	}catch (Exception e) {
	// 			// TODO Auto-generated catch block
	// 		e.printStackTrace();
	// 	}
	// }

	@Macrobenchmark
	public void droid() throws Exception {
		try{
			DroidCLI.main(new String[] { "-R", "-Nr",
				path, "-Ns",
				sig });
		}catch (Exception e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
