import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author: Sen
 * @Date: 2020/1/19 00:13
 * @Description:
 */
public class BinaryTest {

    @Test
    public void testBinary() {
        // System.out.println((byte)Integer.parseInt("10101000",2));
        System.out.println((byte) Integer.parseInt("001100", 2));
        Integer.toBinaryString(12);
    }

    @Test
    public void testStringBuilder() {
        StringBuilder stringBuilder = new StringBuilder("123456");
        System.out.println(stringBuilder.substring(0, 1));
    }

    @Test
    public void testChar() {
        String str = "1111";
        str.charAt(0);
    }

    @Test
    public void testSortArr() {
        int[][] arr = {
                {5, 3},
                {8, 1},
                {6, 4}
        };
        Arrays.sort(arr, Comparator.comparingInt(r -> r[1]));
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i][1] + ",");
        }
        System.out.println();
    }
}
