import java.util.Scanner;
import java.util.Arrays;

class ArraySortDemo {
	public static void main(String[] args) 
{
		Scanner sc = new Scanner(System.in);	// create Scanner object for user input
		
		System.out.println("Enter number of elements of the array: ");
		int size = sc.nextInt();	// Read size of array
		
		int nums[] = new int[size];	// Declare array
		
		// Take input from user
		System.out.println("Enter " + size + " numbers:" );
		for (int i = 0; i < nums.length; i++)
		{
			nums[i] = sc.nextInt();
		}
		
		// Print original array
		System.out.println("Original Array: ");
		for (int i = 0; i < nums.length; i++)
		{
			System.out.print(nums[i] + " ");
		}
		System.out.println();
		
		// Sort the array in accending order 
		Arrays.sort(nums);
		
		// Print sorted array
		System.out.println("Sorted Array in Accending Order : " );
		for (int i = 0; i < nums.length; i++)
		{
			System.out.print(nums[i] + " ");
		}
	}
}