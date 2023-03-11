package org.example;

public class Sort {
    public static int[] mergeSort(int[] arr) { //归并排序
        if (arr == null || arr.length < 2) {
            return arr;
        }
        int[] ans = helpMergeSort(arr, 0, arr.length - 1);
        return ans;

    }

    public static int[] helpMergeSort(int[] arr, int l, int r) {
        int[] ans = new int[r - l + 1];
        if (l == r) {
            ans[0] = arr[l];
            return ans;
        }
        int mid = ((r - l) >> 1) + l;
        int[] left = helpMergeSort(arr, l, mid);
        int[] right = helpMergeSort(arr, mid + 1, r);
        mergeTwoSortedArray(left, right, ans);

        return ans;
    }

    public static void mergeTwoSortedArray(int[] left, int[] right, int[] ans) {
        int ansIdx = 0;
        int leftIdx = 0;
        int rightIdx = 0;
        while (ansIdx < ans.length) {
            if (leftIdx >= left.length & rightIdx < right.length) {
                ans[ansIdx] = right[rightIdx];
                ansIdx++;
                rightIdx++;
            } else if (rightIdx >= right.length & leftIdx < left.length) {
                ans[ansIdx] = left[leftIdx];
                ansIdx++;
                leftIdx++;
            } else {
                if (left[leftIdx] <= right[rightIdx]) {
                    ans[ansIdx] = left[leftIdx];
                    ansIdx++;
                    leftIdx++;
                } else {
                    ans[ansIdx] = right[rightIdx];
                    ansIdx++;
                    rightIdx++;
                }
            }
        }
    }


    static class MergeSort {
        // Merges two subarrays of arr[].
        // First subarray is arr[l..m]
        // Second subarray is arr[m+1..r]
        void merge(int arr[], int l, int m, int r) {
            // Find sizes of two subarrays to be merged
            int n1 = m - l + 1;
            int n2 = r - m;

            /* Create temp arrays */
            int L[] = new int[n1];
            int R[] = new int[n2];

            /*Copy data to temp arrays*/
            for (int i = 0; i < n1; ++i)
                L[i] = arr[l + i];
            for (int j = 0; j < n2; ++j)
                R[j] = arr[m + 1 + j];

            /* Merge the temp arrays */

            // Initial indexes of first and second subarrays
            int i = 0, j = 0;

            // Initial index of merged subarray array
            int k = l;
            while (i < n1 && j < n2) {
                if (L[i] <= R[j]) {
                    arr[k] = L[i];
                    i++;
                } else {
                    arr[k] = R[j];
                    j++;
                }
                k++;
            }

            /* Copy remaining elements of L[] if any */
            while (i < n1) {
                arr[k] = L[i];
                i++;
                k++;
            }

            /* Copy remaining elements of R[] if any */
            while (j < n2) {
                arr[k] = R[j];
                j++;
                k++;
            }
        }

        // Main function that sorts arr[l..r] using
        // merge()
        void sort(int arr[], int l, int r) {
            if (l < r) {
                // Find the middle point
                int m = l + (r - l) / 2;

                // Sort first and second halves
                sort(arr, l, m);
                sort(arr, m + 1, r);

                // Merge the sorted halves
                merge(arr, l, m, r);
            }
        }

        /* A utility function to print array of size n */
        static void printArray(int arr[]) {
            int n = arr.length;
            for (int i = 0; i < n; ++i)
                System.out.print(arr[i] + " ");
            System.out.println();
        }
    }

    public static void partition(int[] arr, int val) {//让arr中小于等于val的数都在左边，大于val的数都在右边
        if (arr.length < 2) {
            return;
        }
        int boundary = -1;//下标小于等于boundary的数都符合条件
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= val) {
                swap(arr, boundary + 1, i);
                boundary++;
            }
        }
    }

    public static int[] netherlandFlag(int[] arr, int val) {//荷兰国旗问题：让arr中小于val的数在最左边，等于val的数在中间，大于val的数在最右侧
        if (arr.length < 2 || arr == null) {
            return new int[] {-1, -1};
        }
        int smallerBoundary = -1;//下标小于等于boundary的数都符合条件
        int greaterBoundary = arr.length;//下标大于等于boundary的数都符合条件
        int idx = 0;
        while(idx < greaterBoundary) {
            if (arr[idx] < val) {
                swap(arr, idx, smallerBoundary + 1);
                smallerBoundary++;
                idx++;
            } else if (arr[idx] > val) {
                swap(arr, idx, greaterBoundary - 1);
                greaterBoundary--;
            } else {
                idx++;
            }
        }
        return new int[] {smallerBoundary, greaterBoundary};
    }

    public static int[] netherlandFlagWithRange(int[] arr, int left, int right) {
        if (right == left) {
            return new int[] {left, right};
        }
        int smallerBoundary = left - 1;
        int greaterBoundary = right + 1;
        int idx = left;
        int val = arr[right];
        while(idx < greaterBoundary) {
            if (arr[idx] < val) {
                swap(arr, idx, smallerBoundary + 1);
                smallerBoundary++;
                idx++;
            } else if (arr[idx] > val) {
                swap(arr, idx, greaterBoundary - 1);
                greaterBoundary--;
            } else {
                idx++;
            }
        }
        return new int[] {smallerBoundary, greaterBoundary};
    }
    public static void quickSort(int[] arr) {//快排的本质是递归调用荷兰国旗问题
        helpQuickSort(arr, 0, arr.length - 1);
    }

    public static void helpQuickSort(int[] arr, int left, int right) {
        if (left >= right || arr.length == 0) {
            return;
        }
        int[] range = netherlandFlagWithRange(arr, left, right);
        helpQuickSort(arr, left, range[0]);
        helpQuickSort(arr,range[1], right);
    }

    public static void swap(int[] arr, int leftIdx, int rightIdx) {
        int temp = 0;
        temp = arr[leftIdx];
        arr[leftIdx] = arr[rightIdx];
        arr[rightIdx] = temp;
    }

    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            int[] array = ArrayComparator.generateRandomArray(20, 10);
//            if (array.length > 2) {
//                System.out.println("array[array.length - 1] is "+array[array.length - 1]);
//                netherlandFlagWithRange(array, 0, array.length - 1);
//            }
//
//            ArrayComparator.printArray(array);
//        }
        int[] arr = new int[] {-5, -4, -4, -4, -2, 8, 5, 0, 3, 6, 2, 10, 4};
        quickSort(arr);
        ArrayComparator.printArray(arr);
    }
}