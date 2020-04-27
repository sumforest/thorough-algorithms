package com.sen.algorithm.kmp;

/**
 * @Author: Sen
 * @Date: 2020/1/28 11:28
 * @Description: 字符串匹配暴力匹配算法
 */
public class Violence {
    public static void main(String[] args) {
        String str1 = "硅硅谷尚硅谷 你 尚硅尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
        int index = violenceMatch(str1, str2);
        System.out.println(index);
    }

    public static int violenceMatch(String str1, String str2) {
        char[] target = str1.toCharArray();
        char[] source = str2.toCharArray();
        int i = 0;
        int j = 0;
        while (i < target.length && j < source.length) {
            if (target[i] == source[j]) {
                //匹配
                i++;
                j++;
            }
            else{
                //当不匹配时把回溯i往后移动一位
                i = i - (j - 1);
                //重新匹配要匹配字符串，j置为0
                j= 0;
            }
        }
        if (j == str2.length()) {
            //匹配成功
            return i-j;
        }
        return  -1;
    }
}
