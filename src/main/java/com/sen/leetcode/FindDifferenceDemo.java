package com.sen.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author LingSen
 * @date 2023/6/8 14:37
 * 给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，请你返回一个长度为 2 的列表 answer ，其中：
 * <p>
 * answer[0] 是 nums1 中所有 不 存在于 nums2 中的 不同 整数组成的列表。
 * answer[1] 是 nums2 中所有 不 存在于 nums1 中的 不同 整数组成的列表。
 * 注意：列表中的整数可以按 任意 顺序返回。
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,2,3], nums2 = [2,4,6]
 * 输出：[[1,3],[4,6]]
 * <p>
 * 示例 2：
 * 输入：nums1 = [1,2,3,3], nums2 = [1,1,2,2]
 * 输出：[[3],[]]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-the-difference-of-two-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindDifferenceDemo {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,3};
        int[] nums2 = new int[]{1,1,2,2};
        System.out.println(findDifference(nums1, nums2));
    }

    public static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> answer1 = new ArrayList<>();
        List<Integer> answer2 = new ArrayList<>();
        answer.add(answer1);
        answer.add(answer2);

        for (int i = 0; i < nums1.length; i++) {
            int temp = nums1[i];
            boolean contain = false;
            for (int j = 0; j < nums2.length; j++) {
                if (temp == nums2[j]) {
                    contain = true;
                    break;
                }
            }
            if (!contain && !answer1.contains(temp)) {
                answer1.add(temp);
            }
        }

        for (int i = 0; i < nums2.length; i++) {
            int temp = nums2[i];
            boolean contain = false;
            for (int j = 0; j < nums1.length; j++) {
                if (temp == nums1[j]) {
                    contain=true;
                    break;
                }
            }
            if (!contain && !answer2.contains(temp)) {
                answer2.add(temp);
            }
        }

        return answer;
    }
}
