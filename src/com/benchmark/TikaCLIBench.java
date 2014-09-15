package com.benchmark;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;

public class TikaCLIBench {

	/**
	 * Execute TikaCLIBench which iterates over files in a folder or a single
	 * file in metadata extraction (-m) mode
	 * 
	 * @param path
	 * @author John Goldsbury
	 * @throws Exception
	 * @see TikaCli
	 * 
	 * 
	 */
	public static void parseTikaCLI(String path) throws Exception {

		InputStream input = null;
		File file = new File(path);
		File someFile = null;

		if (file.isDirectory()) {

			Iterator<File> iter = FileUtils.iterateFiles(file, null, true);
			while (iter.hasNext()) {

				someFile = iter.next();

				if (!someFile.isDirectory()) {

					input = new FileInputStream(someFile);
					TikaCLI.main(new String[] { "-m", "file://" + someFile });

				}

			}
		} else {

			TikaCLI.main(new String[] { "-m", "file://" + someFile });

		}
		if (input != null) {
			input.close();
		}
	}
}
