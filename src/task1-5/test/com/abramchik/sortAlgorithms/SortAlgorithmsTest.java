package com.abramchik.sortAlgorithms;

import com.abramchik.taskThreeSortAlgorithms.SortAlgorithms;
import org.junit.Assert;
import org.junit.Test;

public class SortAlgorithmsTest {

    private int[] testArray = {2, 5, 7, 96, 4, 6, 0, 1, 6, 8, 10, 46, 2, 85};
    private int[] result = {0, 1, 2, 2, 4, 5, 6, 6, 7, 8, 10, 46, 85, 96};

    @Test
    public void bubbleSortTest() {
        SortAlgorithms.bubbleSort(testArray);
        Assert.assertArrayEquals(result, testArray);
    }

    @Test
    public void selectionSortTest() {
        SortAlgorithms.selectionSort(testArray);
        Assert.assertArrayEquals(result, testArray);
    }

    @Test
    public void insertionSortTest() {
        SortAlgorithms.insertionSort(testArray);
        Assert.assertArrayEquals(result, testArray);
    }

    @Test
    public void shuttleSortTest() {
        SortAlgorithms.shuttleSort(testArray);
        Assert.assertArrayEquals(result, testArray);
    }

    @Test
    public void shellSortTest() {
        SortAlgorithms.shellSort(testArray);
        Assert.assertArrayEquals(result, testArray);
    }

    @Test
    public void mergeSortTest() {
        SortAlgorithms.mergeSort(testArray);
        Assert.assertArrayEquals(result, testArray);
    }
}


