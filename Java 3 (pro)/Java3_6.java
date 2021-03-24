import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

class Scratch {
    public static void main(String[] args) {

    }
}

class MyArray {

    public int[] testArray1(int[] testArray) {

        int startIndex = Integer.MIN_VALUE;

        for (int i = testArray.length - 2; i >= 0; i--) {
            if (testArray[i] != 4) continue;
            else {
                startIndex = testArray[i];
                break;
            }
        }
        if (startIndex == Integer.MIN_VALUE) throw new RuntimeException("Array without value 4");

        int[] out = new int[testArray.length - startIndex];
        System.arraycopy(testArray, startIndex, out, 0, out.length - 1);

        return out;
    }

    public boolean testArray2(int[] testArray) {
        boolean out1 = false, out2 = false;
        for (int i = 0; i < testArray.length; i++) {
            if (testArray[i] == 1) {
                out1 = true;
                continue;
            } else if (testArray[i] == 4) {
                out2 = true;
                continue;
            } else {
                return false;
            }
        }
        return (out1 && out2);
    }
}

@RunWith(Parameterized.class)
class Array1Test {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new int[][][]{
                {{1, 5, 4, 6}, {6}},
                {{4, 2, 3, 5}, {2, 3, 5}},
                {{4, 4, 0, 1}, {0, 1}},
                {{5, 5, 5, 4}, {}},
                {{4, 7, 5, 4}, {}},
                {{5, 5, 4, 5,}, {5}},
        });
    }

    private int x[];
    private int result[];
    private static MyArray arr;

    public Array1Test(int x[], int result[]) {
        this.x = x;
        this.result = result;
    }

    @Before
    public void init() {
        System.out.println("init Method");
        arr = new MyArray();
    }

    @Test
    public void massTestAdd() {
        Assert.assertArrayEquals(result, arr.testArray1(x));
    }
}

class MethodTwoTest {

    private static MyArray myArray;

    @Before
    public void init() {
        System.out.println("init Method");
        myArray = new MyArray();
    }

    @Test
    public void test1() {
        Assert.assertTrue(myArray.testArray2(new int[]{1, 5, 4, 6}));
    }

    @Test
    public void test2() {
        Assert.assertTrue(myArray.testArray2(new int[]{4, 2, 3, 5}));
    }

    @Test
    public void test3() {
        Assert.assertTrue(myArray.testArray2(new int[]{2, 2, 0, 1}));
    }

    @Test
    public void test4() {
        Assert.assertFalse(myArray.testArray2(new int[]{5, 5, 5, 5}));
    }

    @Test
    public void test5() {
        Assert.assertFalse(myArray.testArray2(new int[]{3, 8, 5, 9}));
    }

}
