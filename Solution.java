import java.util.Random;

/*
 * Find missing number in monotonically ascending order array.
 * The array starts with value zero (0).
 * Problem 17.4
 */
public class Solution {

	/*
	 * Fetch jth bit from arr[i]
	 */
	static int fetchBit(int[] arr, int i, int j) {
		
		// **** (we should really throw an exception) ****
		if (i >= arr.length)
			return 0;
		
		// **** (we should really throw an exception) ****
		if ((j < 0) || (j >= 32))
			return 0;
		
		// **** mask for jth bit ***
		int mask = 1;
		for (int k = 0; k < j; k++)
			mask <<= 1;

		// **** return the requested bit value ****
		return arr[i] & mask;
	}
	
	/*
	 * Find missing value in O(n).
	 * arr[i] & 0x01 is equivalent to fetch the 0th bit (LSB) from arr[i].
	 */
	static int findMissing(int[] arr) {
		
		// **** ****
		int missing = 0;
		
		// **** traverse array looking for missing value ****
		for (int i = 0; i < arr.length; i++) {
			if ((i % 2) != fetchBit(arr, i, 0)) {
				missing = i;
				break;
			}
		}

		// **** ****
		return missing;
	}
	
	/*
	 * Test scaffolding.
	 * random.nextInt(max - min + 1) + min
	 */
	public static void main(String[] args) {

		// **** ****
		final int MAX_LENGTH = 19;
		final int MIN_LENGTH = 2;
		
		// **** determine n ****
		Random rand = new Random(System.currentTimeMillis());
		int n = Math.abs(rand.nextInt(MAX_LENGTH - MIN_LENGTH + 1)) + MIN_LENGTH;
		
		// ???? ????
		System.out.println("main <<<   n: " + n);
		
		// **** array holding missing number ****
		int[] arr = new int[n];

		// **** determine missing value ****
		int m = Math.abs(rand.nextInt((n - 1) - 0 + 1) + 0);

		// ???? ????
		System.out.println("main <<<   m: " + m);

		// **** populate array without the missing value ****
		int j = 0;
		for (int i = 0; i < arr.length; i++) {
			
			// **** skip this value ****
			if (i == m)
				j++;
			
			// **** populate array with this value ****
			arr[i] = j++;
		}
		
		// ???? ????
		System.out.print("main <<< arr: [");
		for (int i = 0; i < arr.length; i++) {
			if (i < arr.length - 1)
				System.out.print(arr[i] + ", ");
			else 
				System.out.print(arr[i]);			
		}
		System.out.println("] arr.length: " + arr.length);
		
		// **** find missing value ****
		int missing = findMissing(arr);

		// **** check if we found the proper missing value ****
		System.out.println();
		if (missing == m)
			System.out.println("main <<< missing: " + missing + " == m: " + m);
		else
			System.err.println("main <<< missing: " + missing + " != m: " + m);
	}

}
