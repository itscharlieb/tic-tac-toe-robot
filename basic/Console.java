package basic;

public class Console {
	/**
	 * Print a prompt on the console but don't print a newline
	 * 
	 * @param prompt
	 *            the prompt string to display
	 */

	public static void printPrompt(final String prompt) {
		System.out.println(prompt + " ");
		System.out.flush();
	}

	/**
	 * Read a string from console. The string is terminated by a newline
	 * 
	 * @return the input string (without the newline)
	 */

	public static String readLine() {
		int ch;
		String r = "";
		boolean done = false;
		while (!done) {
			try {
				ch = System.in.read();
				if ((ch < 0) || ((char) ch == '\n')) {
					done = true;
				} else if ((char) ch != '\r') {
					r = r + (char) ch;
				}
			} catch (final java.io.IOException e) {
				done = true;
			}
		}
		return r;
	}

	/**
	 * Read a string from the console. The string is terminated by a newline
	 * 
	 * @param prompt
	 *            the prompt string to display
	 * @return the input string (without the newline)
	 */
	public static String readLine(final String prompt) {
		printPrompt(prompt);
		return readLine();
	}

	/**
	 * Read a char from the console. The char is terminated by a newline
	 * 
	 * @return The input char (without the newline)
	 */
	public static char readChar(final String prompt) {
		while (true) {
			printPrompt(prompt);
			try {
				return readLine().trim().toCharArray()[0];
			} catch (final NumberFormatException e) {
				System.out.println("Not an integer. Please try again!");
			}
		}
	}

	/**
	 * Read an integer from the console. The input is terminated by a newline
	 * 
	 * @param prompt
	 *            the prompt string to display
	 * @return the input value as an int
	 * @exception NumberFormatException
	 *                if bad input
	 */
	public static int readInt(final String prompt) {
		while (true) {
			printPrompt(prompt);
			try {
				return Integer.valueOf(readLine().trim()).intValue();
			} catch (final NumberFormatException e) {
				System.out.println("Not an integer. Please try again!");
			}
		}
	}

	/**
	 * Read a floating point number from the console. The input is terminated by
	 * a newline
	 * 
	 * @param prompt
	 *            the prompt string to display
	 * @return the input value as a double
	 * @exception NumberFormatException
	 *                if bad input
	 */
	public static double readDouble(final String prompt) {
		while (true) {
			printPrompt(prompt);
			try {
				return Double.parseDouble(readLine().trim());
			} catch (final NumberFormatException e) {
				System.out.println("Not a floating point number. Please try again!");
			}
		}
	}

	/**
	 * Read a floating point number from the console. The input is terminated by
	 * a newline
	 * 
	 * @param prompt
	 *            the prompt string to display
	 * @return the input value as a double
	 * @exception NumberFormatException
	 *                if bad input Added by Joey Burtini on January 6th, 2006
	 */
	public static double readPositiveDouble(final String prompt) {
		double retval;
		while (true) {
			printPrompt(prompt);
			try {
				retval = Double.parseDouble(readLine().trim());
				if (retval < 0) {
					System.out.println("Not a positive number. Please try again!");
				} else {
					return retval;
				}
			} catch (final NumberFormatException e) {
				System.out.println("Not a floating point number. Please try again!");
			}
		}
	}
}
