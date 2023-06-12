package com.sen.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author LingSen
 * @date 2023/6/12 22:03
 * 435. 无重叠区间(贪心算法-中等)
 * 给定一个区间的集合intervals，其中 intervals[i] = [starti, endi]。返回 需要移除区间的最小数量，使剩余区间互不重叠。
 * <p>
 * 示例 1:
 * 输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * <p>
 * 示例 2:
 * <p>
 * 输入: intervals = [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * <p>
 * 示例 3:
 * <p>
 * 输入: intervals = [ [1,2], [2,3] ]
 * 输出: 0
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/non-overlapping-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class EraseOverlapIntervals {
    public static void main(String[] args) {
        // int[][] intervals = new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        // int[][] intervals = new int[][]{{1, 2}, {1, 2}, {1, 2}, {1, 2}};
        int[][] intervals = new int[][]{{1, 2}, {2, 3}};
        System.out.println(eraseOverlapIntervals(intervals));
    }
    public static int eraseOverlapIntervals(int[][] intervals) {
        // 把问题转换为：使用尽量多的区间，是他们不重合
        // [[1,2],[2,3],[3,4],[1,3]]
        // 根据右区间进行排序
        // [[1,2],[2,3],[1,3],[1,4][2,4]]
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparingInt(r -> r[1]));
        // 第一个右边界
        int right = intervals[0][1];
        int notRemove = 1;
        // 遍历排序好的区间
        for (int i = 1; i < intervals.length; i++) {
            // 前一个区间的右边界 <= 下一个区间的左边界,符合要求
            if (intervals[i][0] >= right) {
                notRemove++;
                right = intervals[i][1];
            }
        }
        return intervals.length - notRemove;
    }
}
