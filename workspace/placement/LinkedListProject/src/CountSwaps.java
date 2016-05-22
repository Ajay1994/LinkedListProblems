
public class CountSwaps {
    
    //Using long so that this program can be used to count inversions for the input size more than 100,000 and for that, the inversions are out of Integer range.
    private static long inversions = 0l;
    public static void main(String[] args) {
        int[] ar = {5,3,10,9};
        getInversions(ar, 0, ar.length - 1);
        System.out.println(inversions);
    }
    //I sort the array using merge sort technique.
    public static void getInversions(int[] nums, int left, int right) {
      if (left < right) {
        //Split in half
        int mid = (left + right) / 2;
        //Sort recursively.
        getInversions(nums, left, mid);
        getInversions(nums, mid + 1, right);
        //Merge the two sorted sub arrays.
        merge(nums, left, mid, right);
      }
    }
   
    private static void merge(int[] nums, int left, int mid, int right) {
      int leftLength = mid - left + 1;
      int rightLength = right - mid;
      int[] lAr = new int[leftLength];
      //Just for simplicity, we are creating this right array. 
      //We can use actual nums array with mid and right indexes.
      int[] rAr = new int[rightLength];
      for (int i = 0; i < leftLength; i++) {
        lAr[i] = nums[left + i];
      }
      for (int i = 0; i < rightLength; i++) {
        rAr[i] = nums[mid + 1 + i];
      }
      int i = 0, j = 0, k = left;
      while (i < leftLength && j < rightLength) {
        if (lAr[i] <= rAr[j]) {
          nums[k] = lAr[i];
          inversions += j;
          i++;
        } else {
          nums[k] = rAr[j];
          j++;
        }
        k++;
      }
      //remaining iversions
      inversions += (long) j * (leftLength - i);
      if (i >= leftLength) {
        //copy remaining elements from right
        for (; j < rightLength; j++, k++) {
          nums[k] = rAr[j];
        }
      } else {
        //copy remaining elements from left
        for (; i < leftLength; i++, k++) {
          nums[k] = lAr[i];
        }
      }
    }
}
