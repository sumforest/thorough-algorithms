import org.junit.Test;

/**
 * @Auther: Sen
 * @Date: 2020/1/19 00:13
 * @Description:
 */
public class BinaryTest {

    @Test
    public void testBinary(){
        // System.out.println((byte)Integer.parseInt("10101000",2));
        System.out.println((byte)Integer.parseInt("001100",2));
        Integer.toBinaryString(12);
    }

    @Test
    public void testStringBuilder(){
        StringBuilder stringBuilder = new StringBuilder("123456");
        System.out.println(stringBuilder.substring(0, 1));
    }
}
