package com.sen.leetcode.nearestExit;

import sun.security.provider.certpath.Vertex;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author LingSen
 * @date 2023/6/9 18:58
 * <p>
 * 图-广度优先搜索
 * 1926. 迷宫中离入口最近的出口
 * <p>
 * 给你一个m x n的迷宫矩阵maze（下标从 0 开始），矩阵中有空格子（用'.'表示）和墙（用'+'表示）。同时给你迷宫的入口entrance，用entrance = [entrancerow, entrancecol]表示你一开始所在格子的行和列。
 * 每一步操作，你可以往 上，下，左 或者 右移动一个格子。你不能进入墙所在的格子，你也不能离开迷宫。你的目标是找到离entrance最近的出口。出口的含义是maze边界上的空格子。entrance格子不算出口。
 * 请你返回从 entrance到最近出口的最短路径的 步数，如果不存在这样的路径，请你返回 -1。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/nearest-exit-from-entrance-in-maze
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NearestExitDemo {

    public static void main(String[] args) {
//        char[][] maze = {{ '+', '.', '+'}, { '.', '.', '+'}, {'+', '+', '+'}};
//        char[][] maze = {{'.', '+'}};
        char[][] maze = {{'.', '+'}};
//        int[] entrance = {1, 0};
//        int[] entrance = {0, 0};
        int[] entrance = {0, 0};
        System.out.println(nearestExit(maze, entrance));
    }

    public static int nearestExit(char[][] maze, int[] entrance) {
        // 记录点是否被访问；下标表示行，val-列
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        // 使用队列存储顶点
        Deque<Vertex> queue = new LinkedList<>();
        Deque<Integer> distanceQueue = new LinkedList<>();

        queue.offer(new Vertex(entrance[0], entrance[1]));
        distanceQueue.offer(0);
        int minDistance = 0;
        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();
            Integer distance = distanceQueue.poll();
            if (!visited[vertex.x][vertex.y]) {
                visited[vertex.x][vertex.y] = true;
                if (vertex.x == 0 || vertex.x == maze.length - 1 || vertex.y == 0 || vertex.y == maze[0].length - 1) {
                    if (minDistance == 0) {
                        minDistance = distance;
                    } else {
                        minDistance = Math.min(minDistance, distance);
                    }
                }
                // 可向上
                if (vertex.x - 1 >= 0 && !visited[vertex.x - 1][vertex.y] && '.' == maze[vertex.x - 1][vertex.y]) {
                    queue.offer(new Vertex(vertex.x - 1, vertex.y));
                    // 广度+1
                    distanceQueue.offer(distance + 1);
                }
                // 可向下
                if (vertex.x + 1 <= maze.length - 1 && !visited[vertex.x + 1][vertex.y] && '.' == maze[vertex.x + 1][vertex.y]) {
                    queue.offer(new Vertex(vertex.x + 1, vertex.y));
                    distanceQueue.offer(distance + 1);
                }
                // 可向左
                if (vertex.y - 1 >= 0 && !visited[vertex.x][vertex.y - 1] && '.' == maze[vertex.x][vertex.y - 1]) {
                    queue.offer(new Vertex(vertex.x, vertex.y - 1));
                    distanceQueue.offer(distance + 1);
                }
                // 可向右
                if (vertex.y + 1 <= maze[0].length - 1 && !visited[vertex.x][vertex.y + 1] && '.' == maze[vertex.x][vertex.y + 1]) {
                    queue.offer(new Vertex(vertex.x, vertex.y + 1));
                    distanceQueue.offer(distance + 1);
                }
            }
        }
        return minDistance == 0 ? -1 : minDistance;
    }

    static class Vertex {
        private int x;

        private int y;

        public Vertex() {
        }

        public Vertex(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
