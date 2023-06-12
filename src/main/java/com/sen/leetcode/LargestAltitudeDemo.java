package com.sen.leetcode;

/**
 * @author LingSen
 * @date 2023/6/8 13:57
 * 有一个自行车手打算进行一场公路骑行，这条路线总共由n + 1个不同海拔的点组成。自行车手从海拔为 0的点0开始骑行。
 * <p>
 * 给你一个长度为 n的整数数组gain，其中 gain[i]是点 i和点 i + 1的 净海拔高度差（0 <= i < n）。请你返回 最高点的海拔 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-the-highest-altitude
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 示例 1：
 * 输入：gain = [-5,1,5,0,-7]
 * 输出：1
 * 解释：海拔高度依次为 [0,-5,-4,1,1,-6] 。最高海拔为 1 。
 * <p>
 * 示例 2：
 * 输入：gain = [-4,-3,-2,-1,4,3,2]
 * 输出：0
 * 解释：海拔高度依次为 [0,-4,-7,-9,-10,-6,-3,-1] 。最高海拔为 0 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-the-highest-altitude
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LargestAltitudeDemo {

    public static void main(String[] args) {
        System.out.println(largestAltitude(new int[]{-4,-3,-2,-1,4,3,2}));
    }

    public static int largestAltitude(int[] gain) {
        // altitudes[i+1]-altitudes[i]=gain[i]
        int[] altitudes = new int[gain.length + 1];
        altitudes[0] = 0;
        int maxAltitude = 0;
        for (int i = 0; i < gain.length; i++) {
            altitudes[i + 1] = gain[i] + altitudes[i];
            if (altitudes[i + 1] > maxAltitude) {
                maxAltitude = altitudes[i + 1];
            }
        }
        return maxAltitude;
    }
}
