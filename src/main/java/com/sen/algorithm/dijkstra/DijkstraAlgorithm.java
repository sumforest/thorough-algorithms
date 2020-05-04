package com.sen.algorithm.dijkstra;

import java.util.Arrays;

/**
 * @Author: Sen
 * @Date: 2020/1/31 18:09
 * @Description: 迪杰斯特拉算法：解决最短路径问题
 * 思想：
 * 1.起始顶点开始遍历所有可达的顶点并且把权值保存到数组中
 * 2.在起始顶点可达的顶点中选择权值（距离）最小的顶点作为途径顶点，遍历所有经过途径顶点可达的顶点，当前通过途径顶点可达
 * 的顶点的权值比起始顶点直接可达的权值小则更新其为最小权值，并且把可到顶点的前驱节点记为途径顶点
 * 3.如此循环直到所有节点遍历完，就得到连通网格中的最小生成树。
 */
public class DijkstraAlgorithm {

    public static void main(String[] args) {
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        final int N = 65535;
        int[][] matrix = new int[vertexes.length][vertexes.length];
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        Graph graph = new Graph(vertexes, matrix);
        graph.print();
        graph.dijkstra(6);
    }

    private static class Graph {

        /**
         * 顶点
         */
        char[] vertexes;

        /**
         * 邻接矩阵
         */
        int[][] matrix;

        public Graph(char[] vertexes, int[][] matrix) {
            this.vertexes = vertexes;
            this.matrix = matrix;
        }

        /**
         * 输出邻接矩阵
         */
        public void print() {
            for (int[] v : matrix) {
                for (int value : v) {
                    System.out.printf("%8d", value);
                }
                System.out.println();
            }
        }

        /**
         * 迪杰斯特拉算法
         *
         * @param index 起始下标
         */
        public void dijkstra(int index) {
            VisitedVertex visitedVertex = new VisitedVertex(vertexes.length, index);
            // 以起始顶点为下标更新已访问顶点最短距离和前驱顶点
            update(index, visitedVertex);
             /* 邻接矩阵
                   65535       5       7   65535   65535   65535       2
                       5   65535   65535       9   65535   65535       3
                       7   65535   65535   65535       8   65535   65535
                   65535       9   65535   65535   65535       4   65535
                   65535   65535       8   65535   65535       5       4
                   65535   65535   65535       4       5   65535       6
                       2       3   65535   65535       4       6   65535
             */
            // 排除起始顶点，遍历剩下的顶点
            for (int i = 1; i < vertexes.length; i++) {
                // 选择未访问的，距离最短的顶点同时把该顶点标记为已访问
                index = visitedVertex.getNextVertex();
                // 以顶点为访问顶点，更新最短距离和前驱顶点
                update(index, visitedVertex);
            }
            visitedVertex.show();
        }

        /**
         * 更新下标为index顶点到其他顶点的最短距离和前驱顶点
         *
         * @param index         顶点下标
         * @param visitedVertex 已访问过的顶点对应的描述类
         */
        private void update(int index, VisitedVertex visitedVertex) {
            for (int i = 0; i < matrix[index].length; i++) {
                /* 邻接矩阵
                   65535       5       7   65535   65535   65535       2
                       5   65535   65535       9   65535   65535       3
                       7   65535   65535   65535       8   65535   65535
                   65535       9   65535   65535   65535       4   65535
                   65535   65535       8   65535   65535       5       4
                   65535   65535   65535       4       5   65535       6
                       2       3   65535   65535       4       6   65535
                 */
                int len = visitedVertex.dis[index] + matrix[index][i];
                // 更新当前
                if (visitedVertex.already_arr[i] == 0 && len < visitedVertex.dis[i]) {
                    //更新最短距离
                    visitedVertex.dis[i] = len;
                    //更新前驱节点
                    visitedVertex.pre_visited[i] = index;
                }
            }
        }

    }

    /**
     * 描述已访问顶点的类
     */
    private static class VisitedVertex {
        /**
         * 记录已访问的顶点,0未访问，1已访问
         */
        int[] already_arr;

        /**
         * 每个顶点的下标为对应的值为前一个顶点对应的下标
         * pre_visited[index]表示下标为index这个顶点的前驱节点下标为pre_visited[index]
         * 特别地起始顶点的前驱节点为它自己
         */
        int[] pre_visited;

        /**
         * 记录出发顶点到其他顶点的距离,默认为65535，顶点到自身顶点的距离为0；
         * dis[index]表示到index这个顶点的距离为dis[index]
         */
        int[] dis;

        public VisitedVertex(int length, int index) {
            this.already_arr = new int[length];
            this.pre_visited = new int[length];
            this.dis = new int[length];
            //初始化数组
            Arrays.fill(this.dis, 65535);
            this.already_arr[index] = 1;
            // 初始化当前顶点距离为0
            dis[index] = 0;
            // 初始化起始顶点的前驱节点
            pre_visited[index] = index;
        }

        /**
         * 继续选择并返回距离最短的未访问顶点，比如G顶点访问后的顶点是A顶点为新的访问顶点（注意不是出发点）
         *
         * @return 返回新访问顶点的下标
         */
        public int getNextVertex() {
            int min = 65535;
            int index = 0;
            for (int i = 0; i < already_arr.length; i++) {
                if (already_arr[i] == 0 && dis[i] < min) {
                    min = dis[i];
                    index = i;
                }
            }
            //把新访问顶点标记为已访问
            already_arr[index] = 1;
            return index;
        }

        public void show() {
            System.out.println(Arrays.toString(already_arr));
            System.out.println(Arrays.toString(pre_visited));
            char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
            int index = 0;
            for (int di : dis) {
                if (di != 65535) {
                    System.out.print(vertexes[index] + " (" + di + ") ");
                } else {
                    System.out.print(vertexes[index] + " (N) ");
                }
                index++;
            }
        }
    }
}
