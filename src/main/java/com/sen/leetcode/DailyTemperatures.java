package com.sen.leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author LingSen
 * @date 2023/6/12 22:59
 * 739. 每日温度(单调栈-中等)
 * 给定一个整数数组temperatures，表示每天的温度，返回一个数组answer，其中answer[i}是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用0 来代替。
 * <p>
 * 示例 1:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * <p>
 * 示例 2:
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * <p>
 * 示例 3:
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/daily-temperatures
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DailyTemperatures {

    public static void main(String[] args) {
        // int[] temperatures = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        // int[] temperatures = new int[]{30,40,50,60};
        // int[] temperatures = new int[]{30,60,90};
        int[] temperatures = new int[]{89,62,70,58,47,47,46,76,100,70};
        System.out.println(Arrays.toString(dailyTemperatures(temperatures)));
    }


    public static int[] dailyTemperatures(int[] temperatures) {
        /*
         * [73,74,75,71,69,72,76,73]
         * [1 ,1 ,4 ,2 ,1 ,1 ,0 ,0]
         *  0 ,1 ,2 ,3 ,4 ,5 ,6 ,7
         *
         * [89,62,70,58,47,47,46,76,100,70]
         * [8 ,1 ,5 ,4 ,3 ,2 ,1 ,1 ,0  , 0] 预期
         * [8 ,1 ,5 ,4 ,3 ,0 ,1 ,1 ,0  , 0] 错误输出
         *  0 ,1 ,2 ,3 ,4 ,5 ,6 ,7 ,8 , 9
         * */
        int[] answer = new int[temperatures.length];
        Deque<Integer> tpStack = new LinkedList<>();
        Deque<Integer> indexStack = new LinkedList<>();
        for (int i = 0; i < temperatures.length; i++) {
            // 当前要入栈元素大于栈顶元素出栈
            while (!tpStack.isEmpty() && temperatures[i] > tpStack.peek()) {
                tpStack.pop();
                Integer index = indexStack.pop();
                answer[index] = i-index;
            }
            // 栈为空||者栈顶元素>=当前元素，入栈
            if (tpStack.isEmpty() || tpStack.peek() >= temperatures[i]) {
                tpStack.push(temperatures[i]);
                indexStack.push(i);
            }
        }
        return answer;
    }
}
