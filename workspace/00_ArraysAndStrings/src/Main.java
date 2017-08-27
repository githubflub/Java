
public class Main {
	public static void main (String [] args) 
	{
		// Use this to access non-static methods within the Main class, from the static main method. 
		Main m = new Main(); 
		
		// Make some strings
		String myString = "qwertyuiop"; 		
		String str1 = "panama";
		String str2 = "amanaj"; 
		String strURL = "aa b  "; // truLen = 4
		
		System.out.println("Is the string \"" + myString + "\" unique? " + m.isUnique(myString));
		System.out.println("Is \"" + str1 + "\" a permutation of \"" + str2 + "\"? " + m.checkPermutation(str1, str2));
		System.out.println("URLify \"" + strURL + "\": \"" + m.urlify(strURL, 4) + "\"");
	}
	
	private String urlify( String str, int trueLength)
	{
		// problem constraints require me to use a character array
		char[] arr = str.toCharArray(); 
		
		// Turn trueLength into an index
		trueLength--; 
		
		// assume the array contains enough spaces at the end
		// for the extra chars added by all the %20	
		int spaces = 0; 
		for (int k = 0; k <= trueLength; k++) 
		{			
			if (arr[k] == ' ') 
			{				
				spaces = spaces + 2; 
			}
		}		
		
		int a = trueLength, b = trueLength + spaces;		
		// add terminating null if necessary
		if (arr.length > b + 1) 
		{
			arr[b+1] = '\0';
		}
		while (b > a) 
		{
			if (arr[a] == ' ')
			{
				arr[b] = '0'; 
				arr[b-1] = '2';
				arr[b-2] = '%'; 
				b = b - 3; 
				a--; 
			}
			else  
			{
				arr[b--] = arr[a--];				
			}			
		}
		return new String(arr); 
	}
	
	private boolean isUnique(String arg)
	{
		boolean[] char_set = new boolean[128]; 
			// If you're not allowed to use a separate data structure, 
			// then you can only do this in nlog(n) time.
			// Java boolean arrays use false as default value
		
		for (int k = 0; k < arg.length(); k++)
		{
			if (char_set[arg.charAt(k)])
				// If you try to access an array element by inserting a character as an index,
				// Java will translate that char into its ASCII number and use that as the index. 
			{
				return false; 
			}
			char_set[arg.charAt(k)] = true; 
		}		
		return true; 
	}
	
	private boolean checkPermutation(String arg1, String arg2)
	{
		// Check size
		if (arg1.length() != arg2.length()) return false; 
		
		int k; // counter
		int[] char_counts = new int[128]; // Java int arrays default to 0
		
		// get initial counts 
		for (k = 0; k < arg1.length(); k++)
		{
			char_counts[arg1.charAt(k)]++; 
		}
		
		// compare counts 
		for (k = 0; k < arg2.length(); k++)
		{
			char_counts[arg2.charAt(k)]--; 
			
			if (char_counts[arg2.charAt(k)] < 0) {
				return false; 
			}
		}
		return true; 
	}
}
