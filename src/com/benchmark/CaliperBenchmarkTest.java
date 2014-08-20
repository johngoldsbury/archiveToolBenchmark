package com.benchmark;

import uk.gov.nationalarchives.droid.command.DroidCommandLine;
import uk.gov.nationalarchives.droid.command.action.CommandLineException;

import com.google.caliper.Benchmark;
import com.google.caliper.api.Macrobenchmark;

public final class CaliperBenchmarkTest {

	// @Macrobenchmark
	// public void tika() throws Exception {
	//
	// TikaBench tika = new TikaBench();
	// tika.parseTika("/home/goldsbjohn/govdocs");
	// // -Dexec.mainClass="com.google.caliper.runner.CaliperMain"
	// // -Dexec.args="com.benchmark.CaliperBenchmarkTest"
	// }
	
	@Macrobenchmark
	public void tika_CLI() throws Exception{
		TikaCLIBench.parseTikaCLI("/home/goldsbjohn/govdocs/");
	}
	

	 @Macrobenchmark
	 public void tika() throws Exception {
	
	 TikaBench.parseTika("/home/goldsbjohn/govdocs/");
	
	 }
//	 @Macrobenchmark
//	 public void jhoveS_Macro(){
//	 String[] testThing = new String[] { "-c", "conf/jhove.conf", "-s",
//	 "/home/goldsbjohn/govdocs","-o", "/home/goldsbjohn/test1.txt" };
//	 JhoveBench.main(testThing);
//	 }
//	 @Benchmark
//	 public void jhoveS_Micro(){
//	 String[] testThing = new String[] { "-c", "conf/jhove.conf", "-s",
//	 "/home/goldsbjohn/govdocs","-o", "/home/goldsbjohn/test1.txt" };
//	 JhoveBench.main(testThing);
//	 }
//	 @Macrobenchmark
//	 public void jhoveK(){
//	 String[] testThing = new String[] { "-c", "conf/jhove.conf", "-k",
//	 "/home/goldsbjohn/govdocs","-o", "/home/goldsbjohn/test1.txt" };
//	 JhoveBench.main(testThing);
//	 }
//	 
//	 @Macrobenchmark
//	 public void jhoveR(){
//	 String[] testThing = new String[] { "-c", "conf/jhove.conf", "-r",
//	 "/home/goldsbjohn/govdocs","-o", "/home/goldsbjohn/test1.txt" };
//	 JhoveBench.main(testThing);
//	 }
//	@Macrobenchmark
//	public void droid() throws Exception {
//		String[] testThing = new String[] { "-R", "-q", "-Nx", "csv", "-Nr",
//				"/home/goldsbjohn/govdocs", "-Ns",
//				"/home/goldsbjohn/.droid6/signature_files/DROID_SignatureFile_V77.xml", "–r", "/home/goldsbjohn/report.xml"};
//		com.benchmark.DroidCommandLine.main(testThing);
//	}

}
