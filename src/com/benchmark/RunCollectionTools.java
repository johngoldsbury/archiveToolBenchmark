package com.benchmark;

import java.io.File;

/**
 * Runs Droid, Jhove and Tika from their commandline interfaces
 * 
 */
public class RunCollectionTools {

	public static void main(String[] args) throws Exception {
		runTools(args);
	}

	/**
	 * Runs Droid, Jhove and Tika from their commandline interfaces Takes one
	 * argument: A path to a file or folder to be passed to each tool
	 */
	public static void runTools(String[] args) throws Exception {
		String[] commandlineArgs = {};
		String filePath = "";

		if (args.length > 0) {
			filePath = new File(args[0]).getAbsolutePath();
			// System.out.println(fileName);
		} else {
			System.out.println("Please enter a valid path");
			System.exit(0);
		}

		// Set Droid command-line arguments including directory/file to process
		// and signature file (for -Nr mode)
		commandlineArgs = new String[] { "-R", "-Nr", filePath, "-Ns",
				"/home/goldsbjohn/.droid6/signature_files/DROID_SignatureFile_V77.xml" };
		// Execute Droid via commandline interface (From within Java)
		DroidCLI.main(commandlineArgs);

		// Set Jhove commandline arguments
		commandlineArgs = new String[] { "-c", "conf/jhove.conf", "-s",
				filePath, "-o", "/home/goldsbjohn/test2.txt" };
		// Execute Jhove via commandline interface (From within Java)
		JhoveCLI.main(commandlineArgs);

		// Execute TikaCLIBench which iterates over files in a folder or a
		// single file in metadata extraction (-m) mode
		TikaCLIBench.parseTikaCLI(filePath);

	}

}
