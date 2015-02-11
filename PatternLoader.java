package uk.ac.cam.rjm232.tick7;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.LinkedList;

public class PatternLoader {
	
	public static List<Pattern> load (Reader r) throws IOException {
		BufferedReader buff = new BufferedReader(r);
		List<Pattern> patterns = new LinkedList<Pattern>();
		
		String line;
		
		while ((line = buff.readLine()) != null) {
			try {
				Pattern pattern = new Pattern(line);
				patterns.add(new Pattern(line));
			}
			catch (Exception e) {
				continue;
			}
		}
		
		return patterns;
	}
	
	public static List<Pattern> loadFromURL(String url) throws IOException {
 		URL destination = new URL(url);
 		URLConnection conn = destination.openConnection();
 		return load(new InputStreamReader(conn.getInputStream()));
	}
	
	public static List<Pattern> loadFromDisk(String filename) throws IOException {
 		return load(new FileReader(filename));
	}
}