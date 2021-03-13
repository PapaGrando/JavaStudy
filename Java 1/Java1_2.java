class Scratch {
    public static void main(String[] args) {

        MySecondClass reference = new MySecondClass();

        mPrintArray(reference.ex1());
        mPrintArray(reference.ex2());
        mPrintArray(reference.ex3());
        mPrintArray(reference.ex4());
        mPrintArray(reference.ex5());
        System.out.println(reference.ex6(new int[]{1, 5, 3, 9}));
        //mPrintArray(reference.ex7(new int[]{1, 5, 3, 2, 11, 4, 7, 2, 4, 8, 9, 1}, 5));
    }

    private static void mPrintArray(int[] array) {
        for (int val : array) System.out.print(val + " ");

        System.out.println('\n');
    }

    private static void mPrintArray(int[][] array) {
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++)
                System.out.print(array[i][j] + " ");
            System.out.print('\n');
        }
        System.out.println('\n');
    }
}

class MySecondClass {

    public int[] ex1() {
        int[] array = {1, 0, 1, 1, 0, 1};

        int[] result = new int[array.length];
        for (int i = 0; array.length > i; i++) {
            result[i] = Math.abs(array[i] - 1); // kek
        }

        return result;
    }

    public int[] ex2() {
        int[] array = new int[8];

        for (int i = 0, j = 0; j < array.length; i += 3, j++) {
            array[j] = i;
        }
        return array;
    }

    public int[] ex3() {
        int[] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        for (int val : array) {
            val = val < 6 ? val * 2 : val;
        }

        return array;
    }

    public int[][] ex4() {
        int[][] array = new int[8][8];

        for (int i = 0; 8 > i; i++) {
            for (int j = 0; 8 > j; j++) {
                if (i == j) {
                    array[i][j] = 1;
                } else {
                    array[i][j] = 0;
                }
            }
        }

        return array;
    }

    public int[] ex5() {
        int[] array = {8, 3, -1, 5, 3, 5, 7, 6, 7, 6, 5, 4, 74, 7,};

        int max = 0, min = 0;
        for (int val : array) {
            if (val > max) max = val;
            else if (val < min) min = val;
        }

        return new int[]{max, min};
    }

    public String ex6(int[] array) {
        if (array.length == 0) return "Array empty";

        for (int i = 0; array.length > i; i++) {
            int leftSum = 0, rightSum = 0;

            for (int j = 0; i >= j; j++)
                leftSum += array[j];

            for (int j = array.length - 1; i < j; j--)
                rightSum += array[j];

            if (rightSum == leftSum && i != array.length - 1) return "true";

        }
        return "false";
    }

 /*   public int[] ex7(int[] array, int n) {
        if (array.length == 0) return array;

        int startIndex, bufferValue;
        startIndex = n < 0 ? array.length - 1 - n : n;

        for (int i = 0; Math.abs(n) > i; i++) {

            if (n > 0) {

                bufferValue = array[i + n];

                for (int j = i; j <= array.length - 1; j = j + n) {
                    bufferValue = array[j + n];
                    array[j] = a
                }
            }
            if (i + n < 0 || i + n > array.length - 1) continue;

            array[i + n] = array[i];
        }
        return array;
    }
}*/