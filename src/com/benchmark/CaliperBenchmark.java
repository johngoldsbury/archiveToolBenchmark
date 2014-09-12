package com.benchmark;

import uk.gov.nationalarchives.droid.command.DroidCommandLine;
import uk.gov.nationalarchives.droid.command.action.CommandLineException;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import com.google.caliper.api.Macrobenchmark;

/**
 * Runs all annotated benchmarks using the Google Caliper runner
 * 
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
	public void tika_CLI() throws Exception {
		TikaCLIBench.parseTikaCLI(path);
	}

	@Macrobenchmark
	public void tika() throws Exception {

		TikaBench.parseTika(path);

	}

//	@Macrobenchmark
//	public void jhoveS_Macro() {
//		String[] testThing = new String[] { "-c", "conf/jhove.conf", "-s",
//				path, "-o", result };
//		JhoveCLI.main(testThing);
//	}

//	@Macrobenchmark
//	public void jhoveK() {
//		String[] testThing = new String[] { "-c", "conf/jhove.conf", "-k",
//				path, "-o", result };
//		JhoveCLI.main(testThing);
//	}

	
//	@Macrobenchmark
//	public void jhoveR() {
//		String[] testThing = new String[] { "-c", "conf/jhove.conf", "-r",
//				path, "-o", result };
//		JhoveCLI.main(testThing);
//	}

	// @BeforeExperiment
	// public void downloadSig() throws CommandLineException {
	// DroidCLI.main(new String[] { "-d" });
	// }
	// @Benchmark
	// public void jhoveS_Micro(){
	// String[] testThing = new String[] { "-c", "conf/jhove.conf", "-s",
	// path,"-o", result };
	// JhoveCLI.main(testThing);
	// }
	// @Benchmark
	// public void signatureDownload() throws CommandLineException {
	// DroidCLI.main(new String[] { "-d" });
	//
	// }

	// @Macrobenchmark
	// public void droid() throws Exception {
	// String[] testThing = new String[] { "-R", "-Nr",
	// path, "-Ns",
	// sig };
	// DroidCLI.main(testThing);
	// }

}
