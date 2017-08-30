import java.util.Arrays;

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
		
		// Proof that Java passes arrays by "reference"
		int[] nums = {3, 4, 5}; 
		System.out.println("nums before: " + Arrays.toString(nums)); 
		m.checkArray(nums); 
		System.out.println(" nums after: " + Arrays.toString(nums));
		
		// Check palindrome permutation
		String str3 = "Tact Cza"; 
		System.out.println("Is \"" + str3 + "\" a palindrome permutation?: " + m.checkPalindromePermutation(str3));
		
		// Check palindrome permutation with Hash
		System.out.println("Is \"" + str3 + "\" a palindrome permutation (hash)?: " + m.checkPalindromePermutationHash(str3));
		
		// Show that Character.getNumbericValue() is case insensitive. 
		char a = 'a'; 
		char A = 'T'; 
		char b = '\"'; // Show that non-letter characters map to -1
		System.out.println("a: " + Character.getNumericValue(a) + ", A: " + Character.getNumericValue(A));
		System.out.println("non-letter: " + Character.getNumericValue(b));
		System.out.println("z - a = " + (Character.getNumericValue('z') - Character.getNumericValue('a')));
		
		// Show if two strings are "one away"
		String str4 = "palz"; 
		String str5 = "pal e";
		
		System.out.println("Is \"" + str4 + "\" one away from \"" + str5 + "\"? " + m.oneAway(str4, str5));
		
	}
	
	private boolean oneAway(String s1, String s2)
	{
		// Is this case sensitive? 
		// New strat: hash the longer one, 
		// then check if hash contains
		// more than one fail, means false
		
		// Should be within 1 char of each other.
		int diff = s2.length() - s1.length();
		if ( diff < -1 || diff > 1)
		{
			return false; 
		}		
		
		// Set the shorter/larger string
		String shorter;
		String larger; 
		if (diff >= 0)
		{
			shorter = s1; 
			larger = s2; 			
		}
		else // diff < 0
		{
			shorter = s2; 
			larger = s1; 
		}
		
		// Check insert/remove 		
		// we don't have to do this if strings are same size
		if (diff != 0) 
		{
			for (int k = 0, largerIndex = 0, diffCount = 0; k < shorter.length(); k++)
			{	
				while (shorter.charAt(k) != larger.charAt(largerIndex))
				{
					diffCount++; 
					if (diffCount > 1) return false; 
					largerIndex++; 
				}
				largerIndex++; 
			}
		} // passed insert test
		else 			
		{
			// Check replace
			for (int k = 0, diffCount = 0; k < shorter.length(); k++)
			{				
				if (shorter.charAt(k) != larger.charAt(k))
				{
					diffCount++; 
					if (diffCount > 1) return false; 
				}
			}
		}
		
		return true; 
	}
	
	private boolean checkPalindromePermutationHash(String str)
	{
		// Allocate a Hash Table for all 26 letters in the alphabet 
		int[] hashTable = new int[26]; 
		
		// Hash everything using the "hash function" getNumericValue()
		for (int k = 0; k < str.length(); k++)
		{
			int val = Character.getNumericValue(str.charAt(k)) - 10;
			if (val < 0)
			{
				continue; // ignore not letter chars
			}			
			hashTable[val]++; 			
		}
		
		// Check hash table 
		int numOdds = 0; 
		for (int k = 0; k < hashTable.length; k++)
		{
			if (hashTable[k] % 2 == 1)
			{
				numOdds++; 
				if (numOdds > 1)
				{
					return false; 
				}
			}
		}
		
		return true; 
	}
	
	private boolean checkPalindromePermutation(String str) 
	{
		// ignore case
		str = str.toLowerCase(); 
		
		// Array to store counts 
		int[] arr = new int[128]; // default values will be 0
		
		// Letter counts 
		for (int k = 0; k < str.length(); k++)
		{
			
			if (str.charAt(k) == ' ') // ignore spaces
			{
				continue; 
			}
			else // add count to array
			{
				arr[str.charAt(k)]++; 
			}
		}
		
		// Check counts. If more than 1 odd, return false. 
		int numOdd = 0; 
		for (int k = 0; k < str.length(); k++)
		{
			// ignore spaces
			if (str.charAt(k) == ' ')
			{
				continue; 
			}
			
			if (arr[str.charAt(k)] % 2 == 1) // check if odd
			{
				numOdd++;
				if (numOdd > 1) // if more than 1 odd, return false
					return false; 
			}
		}
		return true; 
	}
	
	private void checkArray(int[] arr) 
	{
		arr[0] = 45; 
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
