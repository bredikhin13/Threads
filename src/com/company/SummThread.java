package com.company;

public class SummThread extends Thread {
    public int[] a;
    public int[] b;
    public static int sum;
    public SummThread(int[] x, int[] y){
        this.a = x;
        this.b = y;
        sum = 0;
    }

    @Override
    public void run() {
        for (int i = 0; i < a.length; i++) {
            sum+=a[i]*b[i];
        }
        a[0] = sum;
    }
}
