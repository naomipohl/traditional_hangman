package hangman;

import java.util.*;
import java.io.*;

public class DictionaryReader {
	
	/**
	 * Checks whether a given string contains a number;
	 * returns true if so, false otherwise
	 * @param x
	 * @return
	 */
	private boolean containsNumber(String x) {
		// create a character array from the string
		// and iterate through it
	    for (char c : x.toCharArray()) {
	    		// check whether any element is a digit
	        if (Character.isDigit(c)) {
	        	// if so, return true
	      	  	return true;
	        }
	    }
	    // otherwise, return false
	    return false;
	}
	
	/**
	 * Given a file, removes all lines containing characters,
	 * capital letters, spaces or numbers
	 * and returns the remaining lines
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public String checkDictionary(File file) throws IOException {
		  // initialize a new reader
		  BufferedReader in = new BufferedReader(new FileReader(file));
		  
		  int line = 0;
		  
		  // iterate through the file lines
		  for (String x = in.readLine(); x != null; x = in.readLine()) {
			  // increment the line number each iteration
			  line++;
			  // if the line contains any capitals, skip it
	          if (!x.equals(x.toLowerCase())) {
	        	  	line++;
	          }
	          // if the line contains any hyphens, skip it
	          else if (x.indexOf("-") >= 0) {
	        	  	line++;
	          }
	          // if the line contains any periods, skip it
	          else if (x.indexOf(".") >= 0) {
	        	  	line++;
	          }
	          // if the line contains any apostrophes, skip it
	          else if (x.indexOf("'") >= 0) {
	        	  	line++;
	          }
	          // if the line contains any spaces, skip it
	          else if (x.indexOf(" ") >= 0) {
	        	  	line++;
	          }
	          // if the line contains any numbers, skip it
	          else if (containsNumber(x)) {
	        	  	line++;
	          }
	          // otherwise, return the line
	          else {
	        	  	return x;
	          }
		  }
		  // close the reader
		  in.close();
		  // return an empty string so that a string is always returned
		  return "";
	}
	
	/**
	 * Given a file, picks a random line from the file
	 * and returns it
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public String pickRandomWord(File file) throws IOException {
		// initialize a new reader
		BufferedReader in = new BufferedReader(new FileReader(file));
		// create a new array list
		ArrayList<String> array = new ArrayList<>();
		String line;
		// iteratively add all the lines of the file to the array list
		while ((line = in.readLine()) != null) {
			array.add(line);
		}
		// initialize the Random class
		Random rand = new Random();
		// pick a random index of the array list
		int randomIndex = rand.nextInt(array.size());
		// return the word at that index
		return(array.get(randomIndex));

	}
	
}
