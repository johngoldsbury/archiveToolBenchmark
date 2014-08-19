package com.benchmark;

import uk.gov.nationalarchives.droid.command.DroidCommandLine;
import uk.gov.nationalarchives.droid.command.action.CommandLineException;

import com.google.caliper.Benchmark;
import com.google.caliper.api.Macrobenchmark;

public final class CaliperBenchmarkTest {
	
	

	@Macrobenchmark
	public void tika() throws Exception {

		TikaBench tika = new TikaBench();
		tika.parseTika("/home/goldsbjohn/govdocs");
		// -Dexec.mainClass="com.google.caliper.runner.CaliperMain"
		// -Dexec.args="com.benchmark.CaliperBenchmarkTest"
	}

//	@Benchmark
//	public void micro() throws Exception {
//
//		TikaBench tika = new TikaBench();
//		tika.parseTika("/home/goldsbjohn/govdocs");
//		
//	}
	@Macrobenchmark
	public void jhove(){
		String[] testThing = new String[] { "-c", "conf/jhove.conf", "-s",
				"/home/goldsbjohn/govdocs" };
		 JhoveBench.main(testThing);
	}
	@Macrobenchmark
	public void droid() throws Exception{
		String[] testThing = new String[] { "-R", "-Nx", "csv", "-Nr", "/home/goldsbjohn/govdocs",
				"-Ns",
				"/home/goldsbjohn/.droid6/signature_files/DROID_SignatureFile_V77.xml" };
		 DroidCommandLine.main(testThing);
	}

}
