package nebula.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.text.StyledEditorKit.BoldAction;

public class SmartFile {

	public static String readAllLine(String textPath) {
		String result = null;// save read result

		final StringBuilder sb = new StringBuilder();
		// read method
		InputStream is = null;
		try {
			is = new FileInputStream(textPath);
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 512);
			// read one line，save string to sb
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				line = line.trim();
				sb.append(line);
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
					is = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		result = sb.toString();
		return result;
	}

	/*
	 * bufferedWriteAndFileWriter
	 * WriteStringToFile
	 * 
	 */
	public static Boolean WriteStringToFile(String filePath, String str) {
		File f = new File(filePath);
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(f);
			bw = new BufferedWriter(fw);
			bw.write(str);
			bw.flush();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

}
