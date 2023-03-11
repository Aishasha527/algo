package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        // test merge sort
        for (int i = 0; i < 5000; i++) {
            int[] array = ArrayComparator.generateRandomArray(500, 9999);
            int[] testArray = ArrayComparator.copyArray(array);
            Arrays.sort(array);
            int[] ans = Sort.mergeSort(testArray);//归并排序
            if (!ArrayComparator.isEqual(array,ans)) {
                System.out.println("something went wrong");
                System.out.println("correct sorted array");
                ArrayComparator.printArray(array);
                System.out.println("your answer");
                ArrayComparator.printArray(ans);
                System.out.println("origin array");
                ArrayComparator.printArray(testArray);
            }
        }

        for (int i = 0; i < 5000; i++) {
            int[] array = ArrayComparator.generateRandomArray(80, 9999);
            int[] testArray = ArrayComparator.copyArray(array);
            Arrays.sort(array);
            Sort.quickSort(testArray);//随机快排
            if (!ArrayComparator.isEqual(array,testArray)) {
                System.out.println("something went wrong");
                System.out.println("correct sorted array");
                ArrayComparator.printArray(array);
                System.out.println("your answer");
                ArrayComparator.printArray(testArray);
            }
        }
    }
}