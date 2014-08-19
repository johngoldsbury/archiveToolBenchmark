package com.benchmark;

import java.io.File;
import java.security.Permission;

import uk.gov.nationalarchives.droid.command.DroidCommandLine;

public class RunBenchmark {
	private static class ExitTrappedException extends SecurityException {
	}

	private static void forbidSystemExitCall() {
		final SecurityManager securityManager = new SecurityManager() {
			public void checkPermission(Permission permission) {
				if ("exitVM".equals(permission.getName())) {
					throw new ExitTrappedException();
				}
			}
		};
		System.setSecurityManager(securityManager);
	}

	private static void enableSystemExitCall() {
		System.setSecurityManager(null);
	}

	public static void main(String[] args) throws Exception {

		// CaliperMain.main(CaliperBenchmarkTest.class, args);

		String[] testThing = {};
		String fileName = "";
		TikaBench tika = new TikaBench();
		forbidSystemExitCall();
		if (args.length > 0) {
			fileName = new File(args[0]).getAbsolutePath();
			System.out.println(fileName);
		} else {
			System.out.println("Please enter a valid path");
			System.exit(0);
		}

		// DroidCommandLine.main(testThing);
		tika.parseTika(fileName);

		testThing = new String[] { "-c", "conf/jhove.conf", "-s", fileName };
		JhoveBench.main(testThing);
		
		JhoveBench.main(testThing);
		testThing = new String[] { "-R", "-Nx", "csv", "-Nr", fileName, "-Ns",
				"/home/goldsbjohn/.droid6/signature_files/DROID_SignatureFile_V77.xml" };
//		DroidCommandLine.main(testThing);


	}

}
