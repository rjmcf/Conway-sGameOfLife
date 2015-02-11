package uk.ac.cam.rjm232.tick7;

public class PackedLong {
	
	/* 
	 * Unpack and return the nth bit from the packed number at index "position"; position 
	 * will count from 0 (representing the least significant bit) to 63 (representing the 
	 * most significant bit).
	 */ 	 
	public static boolean get(long packed, int position) {
		// set "check" to equal 1 if the "position" bit in "packed" is 1.
	 	long check = (packed>>position)&1;
	 	
	 	return (check == 1);
	}
	 
	/*
  	 * Set the nth bit in the packed number to the value given and return the new packed 
  	 * number
  	 */
	public static long set(long packed, int position, boolean value) {
		if (value) {
			return packed | (1l<<position);
		}
		
		else {
			return packed & ~(1l<<position);
		}		
	}
}