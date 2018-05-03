import java.util.Scanner;
import java.util.Arrays;

public class MergeSort
{
    public int[] mergeSort(int[] arr)
    {
        int n = arr.length;
        
        // If length is 1 then simply return
        if (n == 1) 
        {
            return arr;
        }

        // Find the middle element of the array and split the original array
        int mid = n / 2;
        int[] lA = new int[mid];
        int[] rA = new int[n - mid];

        System.arraycopy(arr, 0, lA, 0, lA.length);
        System.arraycopy(arr, lA.length, rA, 0, rA.length);

        // Sort each side recursively, dividing the arrays in half till only pairs
        mergeSort(lA);
        mergeSort(rA);
        merge(lA, rA, arr);

        return arr;
    }

    public void merge(int[] lA, int[] rA, int[] arr)
    {
        int lALength = lA.length;
        int rALength = rA.length;
        int i = 0, j = 0, k = 0;

        while(i < lALength && j < rALength) 
        {
            if(lA[i] < rA[j]) 
            {
                arr[k] = lA[i];
                i++;
            } 
            else 
            {
                arr[k] = rA[j];
                j++;
            }

            k++;
        }

        // Copy the rest of the first half if there is something left over 
        while(i < lALength) 
        {
            arr[k] = lA[i];
            i++;
            k++;
        }

        // Copy the rest of the second half if there is something left over 
        while(j < rALength) 
        {
            arr[k] = rA[j];
            j++;
            k++;
        }
    }

    // This will merge all of the sorted arrays together at the end
    public int[] mergeKWay(int[][] arr, int length) 
    {
        // Create result array
        int n = 0;
        
        for (int[] a : arr)
        {
            n += a.length;
        }
        
        int[] mergedArray = new int[n];

        // Start at index 0 in each source array
        int[] currentPosition = new int[arr.length];

        // Merge source arrays into result array
        for (int i = 0; i < n; i++) 
        {
            // Find smallest value
            int minPos = -1, minVal = 0;
            
            for (int j = 0; j < arr.length; j++) 
            {
                // Find smallest value from each source array, then add to result array
                if (currentPosition[j] < arr[j].length) 
                {
                    int val = arr[j][currentPosition[j]];
                    
                    if (minPos == -1 || val < minVal) 
                    {
                        minPos = j;
                        minVal = val;
                    }
                }
            }

            // Add to result array and step forward in appropriate source array
            mergedArray[i] = minVal;
            currentPosition[minPos]++;
        }

        return mergedArray;
    }
}