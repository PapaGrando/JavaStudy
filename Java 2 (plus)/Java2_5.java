import java.util.ArrayList;

class Scratch {
    public static void main(String[] args) {
        final int size = 10000000;
        final int halfArray = size / 2;
        float[] arr = new float[size];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }

        //способ 1
        long startTimer1 = System.currentTimeMillis();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) *
                    Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long stopTimer1 = System.currentTimeMillis();

        System.out.println("method 1 - " + (stopTimer1 - startTimer1) + " last val: " + arr[arr.length - 1]);

        //способ 2
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }

        long startTimer2 = System.currentTimeMillis();

        float[] hArr1 = new float[size / 2];
        float[] hArr2 = new float[size / 2];

        System.arraycopy(arr, 0, hArr1, 0, halfArray);
        System.arraycopy(arr, halfArray, hArr2, 0, halfArray);

        ArrayCounter ac1 = new ArrayCounter(hArr1);
        ArrayCounter ac2 = new ArrayCounter(hArr2);

        Thread t1 = new Thread(ac1);
        Thread t2 = new Thread(ac2);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(ac1.getArr(), 0, arr, 0, halfArray);
        System.arraycopy(ac2.getArr(), 0, arr, halfArray, halfArray);

        long stopTimer2 = System.currentTimeMillis();

        System.out.println("method 2 - " + (stopTimer2 - startTimer2) + " last val: " + arr[arr.length - 1]);
    }
}

class ArrayCounter implements Runnable {
    float[] arr;

    public ArrayCounter(float[] arr) {
        this.arr = arr;
    }

    public float[] getArr() {
        return arr;
    }

    @Override
    public void run() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) *
                    Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("ended");
    }

}