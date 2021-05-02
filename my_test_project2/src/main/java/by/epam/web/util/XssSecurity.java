package by.epam.web.util;

/*
 * This class represents xss security from script tags.
 * */
public class XssSecurity {
	
	private static final String REGEX_SCRIPT = "</?script>";
	private static final String EMPTY_LINE = "";
	
	
	/*
	 * This method checks the string and if it contains script tags, replace all tags with an empty value
	 * @param line the string to check
	 * @return secure line for a web application
	 * */
	public static String xssSecurity(String line) {
		
		String secureLine = null;
		
		if(line != null) {
			secureLine = line.replaceAll(REGEX_SCRIPT, EMPTY_LINE);
		}
		
		return secureLine;
	}
}
