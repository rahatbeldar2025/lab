import java.util.Scanner; // Import Scanner class 

class PalindromeDemo
{
	public static void main (String[] args)
	{
		Scanner sc = new  Scanner(System.in); 	// create Scanner object for user input  
		
		System.out.println("Enter a string : ");
		String originalString = sc.nextLine(); 	//Read string from user 
		
		// Use StringBuffer to reverse the string
		StringBuffer sb1 = new StringBuffer(originalString);
		String reverseString = sb1.reverse().toString();
		
		// Compare origonal and reversed string
		if (originalString.equals(reverseString))
			{
				System.out.println("Entered String is Palindrome!!");
			}
			
		
		else {
				System.out.println("Entered String NOT a  Palindrome!!");
			}
			
	}	
}