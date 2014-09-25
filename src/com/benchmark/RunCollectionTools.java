package com.benchmark;

import java.io.File;
import uk.gov.nationalarchives.droid.command.action.CommandLineException;


/**
 * Runs Droid, Jhove and Tika from their commandline interfaces
 * @author John Goldsbury
 */
public class RunCollectionTools {

	public static void main(String[] args) {
		runTools(args);
	}

	/**
	 * Runs Droid, Jhove and Tika from their commandline interfaces Takes one
	 * argument: A path to a file or folder to be passed to each tool
	 * @param args
	 */
	public static void runTools(String[] args){
		String[] commandlineArgs = {};
		String filePath = "";
		
		String homeDir = System.getProperty("user.home")+"/";
		String droidSignaturePath = homeDir+".droid6/signature_files/DROID_SignatureFile_V77.xml";
		String resultPath = homeDir+"JhoveResult.txt";

		if (args.length > 0) {
			filePath = new File(args[0]).getAbsolutePath();
			
		}
		else {
			System.out.println("REQUIRED: arg[0] = path to run tools on e.g. /home/{username}/folder or /home/{username}/folder/file.ext \nOPTIONAL: arg[1] = Droid Signature file, default is /home/{username}/.droid6/signature_files/DROID_SignatureFile_V77.xml\nOPTIONAL: arg[2] = results directory and filename for Jhove, default is /home/{username}/JhoveResult.txt");
			System.exit(0);
		}
		if (args.length == 2){
			droidSignaturePath = new File(args[1]).getAbsolutePath();
		}
		if (args.length == 3) {
			resultPath = new File(args[2]).getAbsolutePath();
		}

		// Set Droid command-line arguments including directory/file to process
		// and signature file (for -Nr mode)
		commandlineArgs = new String[] { "-R", "-Nr", filePath, "-Ns",
		droidSignaturePath };
		// Execute Droid via commandline interface (From within Java)
		System.out.println("####### Beginning DROID Run #######");
		try{
			DroidCLI.main(commandlineArgs);				
		}catch (CommandLineException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println("####### DROID run complete #######");
		// Set Jhove commandline arguments
		commandlineArgs = new String[] { "-c", "conf/jhove.conf", "-s",
		filePath, "-o", resultPath,  };
		// Execute Jhove via commandline interface (From within Java)
		System.out.println("####### Beginning Jhove Run #######");
		JhoveCLI.main(commandlineArgs);
		System.out.println("####### Jhove run complete, results at "+filePath+" #######");

		// Execute TikaCLIBench which iterates over files in a folder or a
		// single file in metadata extraction (-m) mode
		System.out.println("####### Beginning Tika run #######");
		TikaCLIBench.parseTikaCLI(filePath);
		System.out.println("####### Tika run complete #######");
		System.out.println("####### DROID cleanup phase begins (automated) #######");


	}

}
