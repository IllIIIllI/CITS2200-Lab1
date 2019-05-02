import CITS2200.Sort;

/**
 * A class to give a comaprative view of common sorting algorithms.
 * The maintains a private static variable that counts the number
 * of array assignments that are performed (as an approximate measure
 * of the complexity of the algorithm.
 * @author Tim French.
 **/

public class Sorter implements Sort
{
	private int count;

	/**
	 * Returns the number of array assignment operations
	 * performed by this class since the count variable was rest.
	 * @return the number of assignments
	 **/
	public int getCount(){
		return count;
	}

	/**
	 *Resets the counter variable to 0
	 **/
	public void reset(){
		count = 0;
	}

	/**
	 * Executes the insertion sort algorithm sorting the argument array.
	 * There is no return as the parameter is to be mutated.
	 * @param a the array of long integers to be sorted
	 **/
	public void insertionSort(long[] a)
	{
		int i, j, n;
		long key;
		n = a.length;

		for (j = 1; j < n; j++) {
			key = a[j];
			// Insert a[j] (key) into the sorted sequence a[i...j-1]
			i = j - 1;
			// Checks whether the number before key is greater than key.
			// If it is, checks towards the start of the array for a number smaller than key.
            // When it finds one, shifts the numbers right and inserts the key in.
			while (i >= 0 && a[i] > key) {
				a[i + 1] = a[i]; count++;
				i--;
			}
			a[i+1] = key; count++;
		}
	}

	/**
	 * Executes the quick sort algorithm sorting the argument array.
	 * There is no return as the parameter is to be mutated.
	 * @param a the array of long integers to be sorted
	 **/
	public void quickSort(long[] a){
		//insert your code here.
		//you will also need to provide some private methods
        QS(a, 0, a.length - 1);
	}
	private void QS(long[] a, long p, long r) {
		if (p < r) {
			long q = partition(a, p, r);
			QS(a, p, q - 1);
			QS(a, q + 1, r);
		}
	}

	/*
	 * Moves numbers lower than the pivot point to the start of the partition.
	 * Moves numbers higher than the pivot point to the end of the position.
	 * Moves the pivot point between them.
	 * Returns the location of the pivot point.
	 */
	private long partition (long[] a, long p, long r) {
		long x = a[(int)r];
		long i = p - 1;
		for (int j = (int)p; j < r; j++) {
			if (a[j] <= x) {
				i = i + 1;
				long temp = a[(int)i];
				a[(int)i] = a[j]; count++;
				a[j] = temp; count++;
			}
		}
		long temp = a[(int)i + 1];
		a[(int)i + 1] = a[(int)r]; count++;
		a[(int)r] = temp; count++;
		return i + 1;
	}

	/**
	 * Executes the merge sort algorithm sorting the argument array.
	 * There is no return as the parameter is to be mutated.
	 * @param a the array of long integers to be sorted
	 **/
	public void mergeSort(long[] a){
		mergeSort(a, 0, a.length-1);
	}

	/**
	 *A private method to merge the elements in the array between p and r.
	 *the array a, between the indices p and r, inclusive.
	 *Given the array is sorted between p and q and q+1 and r
	 *sorts the array between p and r.
	 *@param a the array to be sorted, which is mutated by the method
	 *@param p the lower index of the range to be partitioned
	 *@param q the midpoint of the two sorted sections.
	 *@param r the upper index of the range to be paritioned
	 *@return the index of the point of partition
	 **/
	private void merge(long[] a, int p, int q, int r)
	{
		int n = q-p+1;
		int m = r-q;
		long[] an = new long[n];
		long[] am = new long[m];
		for(int i = 0; i<n; i++) {
			an[i] = a[p+i];
			count++;
		}
		for(int i = 0; i<m; i++){
			am[i] = a[q+i+1];
			count++;
		}
		int i = 0;
		int j = 0;
		for(int k = p; k<=r; k++){
			if(i==n) a[k] = am[j++];
			else if(j==m || an[i]<am[j]) a[k] = an[i++];
			else a[k] = am[j++];
			count++;
		}
	}

	/**
	 *Overloads the mergeSort method with parameters to set the
	 *range to be sorted.
	 **/
	private void mergeSort(long[] a, int p, int r)
	{
		if(p<r){
			int i = (p+r)/2;
			mergeSort(a,p,i);
			mergeSort(a,i+1,r);
			merge(a, p,i,r);
		}
	}




}
