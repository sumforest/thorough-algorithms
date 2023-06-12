package com.sen.leetcode.binarySearch;

/**
 * @author LingSen
 * @date 2023/6/8 0:24
 * 猜数字游戏的规则如下：
 * 每轮游戏，我都会从1到n 随机选择一个数字。 请你猜选出的是哪个数字。
 * 如果你猜错了，我会告诉你，你猜测的数字比我选出的数字是大了还是小了。
 * 你可以通过调用一个预先定义好的接口 int guess(int num) 来获取猜测结果，返回值一共有 3 种可能的情况（-1，1 或 0）：
 * -1：我选出的数字比你猜的数字小 pick < num
 * 1：我选出的数字比你猜的数字大 pick > num
 * 0：我选出的数字和你猜的数字一样。恭喜！你猜对了！pick == num
 * 来源：力扣（LeetCode）
 * 链接：h<a href="ttps://leetcode.cn/problems/guess-number-higher-or-lower
 * ">...</a> * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GuessNumberDemo {
    /**
     * -1：我选出的数字比你猜的数字小 pick < num
     * 1：我选出的数字比你猜的数字大 pick > num
     * 0：我选出的数字和你猜的数字一样。恭喜！你猜对了！pick == num
     *
     * @param num
     * @return
     */
    public static int guess(int num) {
        int target = 2;
        if (num < target) {
            return 1;
        }
        if (num > target) {
            return -1;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(guessNumber(2));
    }

    public static int guessNumber(int n) {
        int left = 1;
        int right = n;
        // 防止溢出等同于 pick=(left+right)/2
        int pick = left + (right - left) / 2;
        int res = guess(pick);
        while (res != 0) {
            if (res == -1) {
                // 猜大了，向左查询
                right = pick-1;

            }
            if (res == 1) {
                // 猜小了，向右查询
                left = pick + 1;

            }
            pick = left + (right - left) / 2;
            res = guess(pick);
        }
        return pick;
    }
}
