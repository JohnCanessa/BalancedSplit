import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * 
 */
public class BalancedSplit {

    /**
     * Balanced split function.
     * Time complexity: O(n)
     */
    static boolean balancedSplitExists(int[] arr) {

        // **** sanity check(s) ****
        if (arr == null || arr.length <= 2)
            return false;

        // **** initialization ****
        int i       = -1;
        int j       = arr.length;
        int lastA   = Integer.MIN_VALUE;
        int lastB   = Integer.MAX_VALUE;       
        int sumA    = 0;
        int sumB    = 0;

        // **** sort the array O(n log(n) ****
        Arrays.sort(arr);

        // **** select numbers from outside to inside O(n) ****
        while (i + 1 < j && lastA != lastB) {

            // **** increment sumA or sumB ****
            if (sumA > sumB) {
                sumB += arr[--j];
                lastB = arr[j];
            } else if (sumA < sumB) {
                sumA += arr[++i];
                lastA = arr[i];
            } else {
                sumA += arr[++i];
                lastA = arr[i];
            }
        }

        // **** ****
        return (sumA == sumB && lastA != lastB);
    }

    /**
     * Balanced split function.
     * Time complexity: 
     */
    static boolean balancedSplitExists1(int[] arr) {

        // **** sanity check(s) ****
        if (arr == null || arr.length <= 2)
            return false;

        // **** initialization ****     
        int sumA    = 0;
        int sumB    = 0;
        int i       = 0;

        // **** compute sumA O(n) ****
        for (i = 0; i < arr.length; i++)
            sumA += arr[i];

        // **** sort the array O(n log(n) ****
        Arrays.sort(arr);

        // **** decrement sumA while incrementing sumB O(n) ****
        for (i = arr.length - 1; sumA > sumB; i--) {
            sumA -= arr[i];
            sumB += arr[i];
        }

        // **** ****
        return (sumA == sumB && arr[i] != arr[i + 1]);
    }

    /**
     * https://leetcode.com/discuss/interview-question/718692/facebook-training-balanced-split
     */
    static boolean balancedSplitExists2(int[] arr) {

        // **** initialization ****
        int leftSum     = 0;
        int rightSum    = 0;

        // **** sort the array O(n log(n) ****
        Arrays.sort(arr);
    
        // **** ****
        for (int i = 0; i < arr.length; i++)
            leftSum += arr[i];

        // **** ****
	    for (int i = arr.length - 1; i >= 0; i--) {

            // **** ****
            leftSum  -= arr[i];
            rightSum += arr[i];

            // **** ****
		    if (leftSum == rightSum) {
                try {
                    if (arr[i - 1] < arr[i])
				    return true;
                } catch (Exception e) {
                    System.out.println("balancedSplitExists2 <<< EXCEPTION e: " + e.toString());
                    System.exit(-1);
                }

		    }
	    }

        // **** ****
        return false;
    }

    /**
     * Test scaffolding
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // **** read String[] array ****
        String[] strArr = br.readLine().trim().split(", ");

        // **** close buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<< strArr: " + Arrays.toString(strArr));

        // **** create and populate int[] (if needed) ****
        int[] arr = {};
        if (!strArr[0].equals("")) {
            arr = new int[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                if (strArr[i].equals(""))
                    continue;
                arr[i] = Integer.parseInt(strArr[i]);
            }
        }

        // ???? ????
        System.out.println("main <<<    arr: " + Arrays.toString(arr));

        // **** generate and display result ****
        System.out.println("main <<< output: " + balancedSplitExists(arr));

        // **** generate and display result ****
        System.out.println("main <<< output: " + balancedSplitExists1(arr));

        // **** generate and display result ****
        System.out.println("main <<< output: " + balancedSplitExists2(arr));
    }
}