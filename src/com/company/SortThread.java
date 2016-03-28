package com.company;

/**
 * Created by User on 24.03.2016.
 */
public class SortThread extends Thread {

    int[] array;

    public SortThread(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        int step;
        for (step = array.length / 2; step >= 1; step /= 2) {
            for (int i = step; i < array.length; i++) {
                int k = i;
                int temp = array[i];
                while (k >= step && temp < array[k - step]) {
                    array[k] = array[k - step];
                    k -= step;
                }
                array[k] = temp;
            }
        }
    }
}
