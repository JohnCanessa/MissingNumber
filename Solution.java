import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


/**
 * Find missing number in monotonically ascending order array.
 * 
 * CORRECTION: Numbers are NOT in sorted order in the array.
 * 
 * The array starts with value zero (0).
 * Problem 17.4
 */
public class Solution {


	/**
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
	

	/**
	 * Find missing value in O(n).
	 * arr[i] & 0x01 is equivalent to fetch the 0th bit (LSB) from arr[i].
	 */
	static int findMissingSorted(int[] arr) {
		
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


	/**
	 * Converts array to array list.
	 * Start recursion.
	 */
	static int findMissing(int[] arr) {

		// **** ****
		ArrayList<Integer> al = new ArrayList<Integer>();;

		// **** populate array list ****
		for (int i = 0; i < arr.length; i++)
			al.add(arr[i]);

		// **** start recursion with LSB ****
		return findMissing(al, 0);
	}


	/**
	 * Recursive call.
	 */
	static int findMissing(ArrayList<Integer> al, int lsb) {

		// **** end / stop condition ****
		if (lsb >= Integer.SIZE)
			return 0;

		// **** ****
		ArrayList<Integer> oneBits = new ArrayList<Integer>();
		ArrayList<Integer> zeroBits = new ArrayList<Integer>();

		// **** set mask ****
		int mask = 1;
		mask <<= lsb;

		// **** populate array lists ****
		for (Integer i : al) {
			if ((i & mask) == 0) {
				zeroBits.add(i);
			} else {
				oneBits.add(i);
			}
		}

		// **** recursive call ****
		if (zeroBits.size() <= oneBits.size()) {
			int v = findMissing(zeroBits, lsb + 1);
			return (v << 1) | 0;
		} else {
			int v = findMissing(oneBits, lsb + 1);
			return (v << 1) | 1;
		}
	}


	/**
	 * Test scaffolding.
	 * random.nextInt(max - min + 1) + min
	 */
	public static void main(String[] args) {

		// **** ****
		final int MAX_LENGTH = 19;
		final int MIN_LENGTH = 2;
		
		// **** ****
		Random rand = new Random(System.currentTimeMillis());

		// **** determine n ****
		int n = Math.abs(rand.nextInt(MAX_LENGTH - MIN_LENGTH + 1)) + MIN_LENGTH;
		
		// ???? ????
		System.out.println("main <<<   n: " + n);

		// **** determine the missing value ****
		int m = Math.abs(rand.nextInt((n - 1) - 0 + 1) + 0);

		// ???? ????
		System.out.println("main <<<   m: " + m);

		// **** declare array list ****
		ArrayList<Integer> al = new ArrayList<Integer>();

		// **** populate array list with a missing number ****
		int j = 0;
		for (int i = 0; i < n; i++) {
			
			// **** skip this value ****
			if (i == m)
				j++;
			
			// **** populate array with this value ****
			al.add(j++);
		}

		// **** populate array with the contents of the array list ****
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = al.get(i);
			
		// ???? ????
		System.out.println("main <<< arr: " + Arrays.toString(arr));

		// **** missing number ****
		int missing;

		// **** find the missing number ****
		missing = findMissingSorted(arr);

		// **** check if we found the proper missing number ****
		if (missing == m)
			System.out.println("main <<< missing: " + missing + " == m: " + m);
		else
			System.err.println("main <<< missing: " + missing + " != m: " + m + " :o(");

		// **** shuffle the array list ****
		Collections.shuffle(al);

		// **** populate array with the contents of the array list ****
		arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = al.get(i);
			
		// ???? display the shuffled array ????
		System.out.println("main <<< arr: " + Arrays.toString(arr));

		// **** find the missing number ****
		missing = findMissing(arr);

		// **** check if we found the proper missing number ****
		if (missing == m)
			System.out.println("main <<< missing: " + missing + " == m: " + m);
		else
			System.err.println("main <<< missing: " + missing + " != m: " + m + " :o(");
	}
}
