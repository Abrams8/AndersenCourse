package com.abramchik.taskThreeSortAlgorithms;

public class SortAlgorithms {

    public static int[] bubbleSort(int[] array) {
        boolean shouldSwap = true;
        while (shouldSwap) {
            shouldSwap = false;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                    shouldSwap = true;
                }
            }
        }
        return array;
    }

    public static int[] selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int n = i; n < array.length - 1; n++) {
                if (array[minIndex] > array[n + 1]) {
                    minIndex = n + 1;
                }
            }
            swap(array, minIndex, i);
        }
        return array;
    }

    public static int[] insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int value = array[i];
            int index = i;
            while (index > 0 && array[index - 1] > value) {
                array[index] = array[index - 1];
                index--;
            }
            array[index] = value;
        }
        return array;
    }

    public static int[] shuttleSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                swap(array, i, i - 1);
                int j = i - 1;
                while (j > 0) {
                    if (array[j] < array[j - 1]) {
                        swap(array, j, j - 1);
                    }
                    j--;
                }
            }
        }
        return array;
    }

    public static int[] shellSort(int[] array) {
        int gap = array.length / 2;
        while (gap >= 1) {
            int i = 0;
            while (i < array.length) {
                for (int j = i - gap; j >= 0; j = j - gap) {
                    if (array[j] > array[j + gap]) {
                        swap(array, j, j + gap);
                    }
                }
                i++;
            }
            gap = gap / 2;
        }
        return array;
    }

    public static int[] mergeSort(int[] array) {
        int arrayLength = array.length;
        if (arrayLength < 2) {
            return array;
        }
        int middleIndex = arrayLength / 2;
        int[] leftHalf = new int[middleIndex];
        int[] rightHalf = new int[arrayLength - middleIndex];

        for (int i = 0; i < middleIndex; i++) {
            leftHalf[i] = array[i];
        }
        for (int i = middleIndex; i < arrayLength; i++) {
            rightHalf[i - middleIndex] = array[i];
        }
        mergeSort(leftHalf);
        mergeSort(rightHalf);
        merge(array, leftHalf, rightHalf);
        return array;
    }

    private static void merge(int[] inputArray, int[] leftHalf, int[] rightHalf) {
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;

        int leftIndex = 0;
        int rightIndex = 0;
        int mainArrayIndex = 0;

        while (leftIndex < leftSize && rightIndex < rightSize) {
            if (leftHalf[leftIndex] <= rightHalf[rightIndex]) {
                inputArray[mainArrayIndex] = leftHalf[leftIndex];
                leftIndex++;
            } else {
                inputArray[mainArrayIndex] = rightHalf[rightIndex];
                rightIndex++;
            }
            mainArrayIndex++;
        }
        while (leftIndex < leftSize) {
            inputArray[mainArrayIndex] = leftHalf[leftIndex];
            mainArrayIndex++;
            leftIndex++;
        }
        while (rightIndex < rightSize) {
            inputArray[mainArrayIndex] = rightHalf[rightIndex];
            mainArrayIndex++;
            rightIndex++;
        }
    }

    private static void swap(int[] array, int indexOne, int indexTwo) {
        int temp = array[indexOne];
        array[indexOne] = array[indexTwo];
        array[indexTwo] = temp;
    }

}