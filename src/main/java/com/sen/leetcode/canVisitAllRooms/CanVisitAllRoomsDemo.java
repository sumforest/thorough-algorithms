package com.sen.leetcode.canVisitAllRooms;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author LingSen
 * @date 2023/6/9 17:21
 * <p>
 * 图-深度优先
 * 841. 钥匙和房间
 * <p>
 * 有 n 个房间，房间按从 0 到 n - 1 编号。最初，除 0 号房间外的其余所有房间都被锁住。你的目标是进入所有的房间。然而，你不能在没有获得钥匙的时候进入锁住的房间。
 * 当你进入一个房间，你可能会在里面找到一套不同的钥匙，每把钥匙上都有对应的房间号，即表示钥匙可以打开的房间。你可以拿上所有钥匙去解锁其他房间。
 * 给你一个数组 rooms 其中 rooms[i] 是你进入 i 号房间可以获得的钥匙集合。如果能进入 所有 房间返回 true，否则返回 false。
 * 示例 1：
 * 输入：rooms = [[1],[2],[3],[]]
 * 输出：true
 * 解释：
 * 我们从 0 号房间开始，拿到钥匙 1。
 * 之后我们去 1 号房间，拿到钥匙 2。
 * 然后我们去 2 号房间，拿到钥匙 3。
 * 最后我们去了 3 号房间。
 * 由于我们能够进入每个房间，我们返回 true。
 * <p>
 * 示例 2：
 * 输入：rooms = [[1,3],[3,0,1],[2],[0]]
 * 输出：false
 * 解释：我们不能进入 2 号房间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/keys-and-rooms
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CanVisitAllRoomsDemo {

    public static void main(String[] args) {
        List<List<Integer>> rooms = new ArrayList<>();
        List<Integer> room0 = new ArrayList<>();
        room0.add(1);
        List<Integer> room1 = new ArrayList<>();
        room0.add(2);
        List<Integer> room2 = new ArrayList<>();
        room0.add(3);
        List<Integer> room3 = new ArrayList<>();
        rooms.add(room0);
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);

        System.out.println(canVisitAllRooms(rooms));
    }

    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        // 记录已经访问的房间，index-房间号；false未被访问
        boolean[] visited = new boolean[rooms.size()];
        // 记录房间号
        Deque<Integer> roomStack = new LinkedList<>();
        rooms.get(0).forEach(roomStack::push);
        visited[0] = true;
        while (!roomStack.isEmpty()) {
            Integer vertex = roomStack.pop();
            if (!visited[vertex]) {
                visited[vertex] = true;
                rooms.get(vertex).forEach(roomStack::push);
            }
        }
        for (boolean accessed : visited) {
            if (!accessed) {
                return false;
            }
        }
        return true;
    }
}
