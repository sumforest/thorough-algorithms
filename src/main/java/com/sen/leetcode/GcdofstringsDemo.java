package com.sen.leetcode;

/**
 * @author LingSen
 * @date 2023/6/5 19:22
 * 对于字符串s 和t，只有在s = t + ... + t（t 自身连接 1 次或多次）时，我们才认定“t 能除尽 s”。
 * <p>
 * 给定两个字符串str1和str2。返回 最长字符串x，要求满足x 能除尽 str1 且 x 能除尽 str2 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/greatest-common-divisor-of-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GcdofstringsDemo {

    public static void main(String[] args) {
        String str1 = "ABCABC", str2 = "ABC";
//        String str1 = "ABABAB", str2 = "ABAB";
//        String str1 = "LEET", str2 = "CODE";
        String divisor = gcdOfStrings(str1, str2);
        System.out.println(divisor);
    }

    public static String gcdOfStrings(String str1, String str2) {
        String divisor;
        if (str1.length() > str2.length()) {
            divisor = str2;
        } else {
            divisor = str1;
        }

        boolean hasDivisior = false;

        // divisor自我拼接比较
        StringBuilder stringBuilder1 = new StringBuilder(divisor);
        StringBuilder stringBuilder2 = new StringBuilder(divisor);
        String res = "";
        for (int i = divisor.length(); i > 0; i--) {
            res = divisor.substring(0, i);
            boolean hasSubDivisior1 = false;
            boolean hasSubDivisior2 = false;

            while (stringBuilder1.length() < str1.length()) {
                stringBuilder1.append(res);
            }
            if (str1.contentEquals(stringBuilder1)) {
                // 有最大公因子
                hasSubDivisior1 = true;
            }
            while (stringBuilder2.length() < str2.length()) {
                stringBuilder2.append(res);
            }
            if (str2.contentEquals(stringBuilder2)) {
                // 有最大公因子
                hasSubDivisior2 = true;
            }
            stringBuilder1.setLength(0);
            stringBuilder2.setLength(0);
            if (hasSubDivisior1 && hasSubDivisior2) {
                hasDivisior = true;
                break;
            }
        }
        if (hasDivisior) {
            return res;
        }
        return "";
    }
}
