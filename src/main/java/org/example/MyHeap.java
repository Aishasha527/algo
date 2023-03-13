package org.example;

import java.util.Objects;

public class MyHeap {
    public static void sort(int[] arr) {

        //把arr创建成大根堆
        buildMaxHeap(arr);
        //把最大的数和arr最后的数swap 堆大小减一
        //把最开始的数heapify直到循环到第一个数
        int size = arr.length;
        for (int i = arr.length - 1; i > 0; i--) {
            Sort.swap(arr, 0, i);
            size--;
            heapify(arr, 0, size);
        }

    }

    private static void buildMaxHeap(int[] arr) {
        //就当arr中每个数都是新加进堆中的
        int size = 0;
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, size, arr[i]);
            size++;
        }
    }

    private static void heapInsert(int[] arr, int size, int val) {
        //在堆最后的位置添加元素
        arr[size] = val;
        //一路和父对比
        int idx = size;
        while (arr[idx] > arr[(idx - 1) / 2]) {
            Sort.swap(arr, (idx - 1) / 2, idx);
            idx = (idx - 1) / 2;
        }
    }

    private static void heapify(int[] arr, int idx, int size) {//arr中堆的大小为size 以idx为起点进行堆化
        //当前arr[idx]有没有左孩子
        //如果没有说明已经沉底
        //如果有，判断有没有右孩子
        //如果孩子中有比自己大的，swap并且重复
        //如果自己最大就返回
        int leftIdx = idx * 2 + 1;
        while (leftIdx < size) {
            int largest = leftIdx + 1 < size && arr[leftIdx] < arr[leftIdx + 1] ? leftIdx + 1 : leftIdx;
            largest = arr[largest] < arr[idx] ? idx : largest;
            if (largest == idx) {
                break;
            }
            Sort.swap(arr, largest, idx);
            idx = largest;
            leftIdx = idx * 2 + 1;
        }
    }

    public static void main(String[] args) {
        int[] arr = {-34, 19, 15, -2, 16};
        sort(arr);
        ArrayComparator.printArray(arr);
    }
}
