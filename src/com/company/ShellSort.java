package com.company;

import java.util.Random;

public class ShellSort {

    final int N = 4194304;

    public void StartSort() {
        Random random = new Random();
        int[] array = new int[N];
        for (int i = 0; i < array.length; i++) {
            array[i] = N-i;
        }
        long start = System.currentTimeMillis();
        //sort(array);
        array = parallelSort(array);
        long stop = System.currentTimeMillis();
        System.out.println("parallel: " + (stop - start) + " ms");
        start = System.currentTimeMillis();
        //sort(array);
        //parallelSort(array);
        //stop = System.currentTimeMillis();
        System.out.println("Consistent: " + (stop - start) + " ms");
        //parallelSort(array);
//        for (int x : array) {
//            System.out.print(x + " ");
//
//        }
    }

    public void ssort(int[] array){
        for(int i = 1;i<array.length;i++){
            int tmp = array[i];
            int k = i;
            while (k>0 && tmp < array[k-1]){
                array[k] = array[k-1];
                k--;
            }
            array[k] = tmp;
        }
    }

    public int[] mergeSort(int[] a, int[] b){
        int[] array = new int[a.length+b.length];
        int aCount = 0;
        int bCount = 0;
        for (int i = 0; i < array.length; i++) {
            if(a[aCount]>b[bCount]){
                array[i] = b[bCount++];
                if(bCount==b.length){
                    break;
                }
            } else {
                array[i] = a[aCount++];
                if(bCount==b.length){
                    break;
                }
            }
        }
        if(aCount==a.length){
            for (int i = aCount+bCount;i<array.length;i++){
                array[i]=b[bCount++];
            }
        } else {
            for (int i = aCount+bCount;i<array.length;i++){
                array[i]=a[aCount++];
            }
        }
        return array;
    }
    public void sort(int[] array) {
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

    public int[] parallelSort(int[] array) {
        int k = 2;
        int[][] temp = new int[k][array.length/k];
        for (int i = 0; i < k; i++) {
            //int[] tmp = new int[array.length / k];
            System.arraycopy(array, array.length / k * i, temp[i], 0, array.length / k);
            new SortThread(temp[i]).run();
        }
        //ssort(array);
        //sort(array);
        return mergeSort(temp[0],temp[1]);

    }



}
