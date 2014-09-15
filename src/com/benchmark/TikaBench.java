package com.benchmark;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * A set of simple Tika metadata parsers
 * @author John Goldsbury
 *
 */
public class TikaBench {
	
	/**
	 * Invoke Tika autodetect parser on file(s) in a folder, with or without formatting the metadata by MIME type
	 * @param path
	 * @param detectMetaType
	 * @throws IOException
	 * @throws SAXException
	 * @throws TikaException
	 */
	public static void parseTika(String path, Boolean detectMetaType) throws IOException, SAXException,
			TikaException {

		AutoDetectParser parser = new AutoDetectParser();
		ContentHandler handler = new BodyContentHandler(Integer.MAX_VALUE);

		Metadata metadata = new Metadata();
		InputStream input = null;

		File file = new File(path);
		File someFile = null;

		if (file.isDirectory()) {

			Iterator<File> iter = FileUtils.iterateFiles(file, null, true);
			while (iter.hasNext()) {

				metadata = new Metadata();
				handler = new BodyContentHandler(Integer.MAX_VALUE);
				someFile = iter.next();

				System.out.println(someFile.getAbsolutePath() + ": ");
				if (!someFile.isDirectory()) {

					input = new FileInputStream(someFile);
					if(detectMetaType)
					metadata.set(Metadata.CONTENT_TYPE,
							new Tika().detect(someFile));

					parser.parse(input, handler, metadata);
					for (String str : metadata.names()) {
						if (!str.contains("Unknown")) {
							System.out.print("## ");
							System.out.print(str + ": " + metadata.get(str)
									+ " ");
						}
					}

				}

			}
		} else {
			input = new FileInputStream(file);
			parser.parse(input, handler, metadata);
			for (String str : metadata.names()) {
				if (!str.contains("Unknown")) {
					System.out.print("## ");
					System.out.print(str + ": " + metadata.get(str) + " ");
				}
			}
			System.out.println();
		}

		if (input != null) {
			input.close();
		}
	}
	/**
	 * Invoke Tika autodetect parser and output body content of files, with or without formatting the metadata by MIME type
	 * @param path
	 * @param detectMetaType
	 * @throws IOException
	 * @throws SAXException
	 * @throws TikaException
	 */
	public static void parseTikaPrintBody(String path, Boolean detectMetaType)
			throws IOException, SAXException, TikaException {
		AutoDetectParser parser = new AutoDetectParser();
		ContentHandler handler = new BodyContentHandler(Integer.MAX_VALUE);

		Metadata metadata = new Metadata();
		InputStream input = null;

		File file = new File(path);
		File someFile = null;

		if (file.isDirectory()) {

			Iterator<File> iter = FileUtils.iterateFiles(file, null, true);
			while (iter.hasNext()) {

				metadata = new Metadata();

				handler = new BodyContentHandler(Integer.MAX_VALUE);
				someFile = iter.next();
				System.out.println(someFile.getAbsolutePath() + ": ");
				if (!someFile.isDirectory()) {

					input = new FileInputStream(someFile);
					if (detectMetaType)
						metadata.set(Metadata.CONTENT_TYPE,
								new Tika().detect(someFile));

					parser.parse(input, handler, metadata);
					for (String str : metadata.names()) {
						if (!str.contains("Unknown")) {
							System.out.print("## ");
							System.out.print(str + ": " + metadata.get(str)
									+ " ");
						}
					}

					System.out.println(handler.toString());

				}

			}
		} else {
			input = new FileInputStream(file);
			parser.parse(input, handler, metadata);
			for (String str : metadata.names()) {
				if (!str.contains("Unknown")) {
					System.out.print("## ");
					System.out.print(str + ": " + metadata.get(str) + " ");
				}
			}
			System.out.println();
		}

		if (input != null) {
			input.close();
		}
	}

}
