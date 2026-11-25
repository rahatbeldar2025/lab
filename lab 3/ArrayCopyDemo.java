class ArrayCopyDemo {
  public static void main(String[] args)
  {
    // Orginal array
    int array1[] = {2, 3, 4, 5, 8, 9};
    // Create a new Array with same length
    int array2[] = new int[array1.length];

    // Copy elements from array1 to array2
    for (int i = 0; i < array1.length; i++)
    {
      array2[i] =array1[i];
    }

    // Print original array1
    System.out.println("Original Arrayy (array1): ");
    for (int i = 0; i < array1.length; i++)
      {
        System.out.print(array1[i] + " ");
      }
    	
    	System.out.println(); /// Next Line
    	
    	
    // Print copied array2
    System.out.println("Copaid Arrayy (array2):");A
    for (int i = 0; i < array2.length; i++)
      {
        System.out.print(array2[i] + " ");
      } 
      
      
  }
}