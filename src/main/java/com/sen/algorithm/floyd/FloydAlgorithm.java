package com.sen.algorithm.floyd;

import java.util.Arrays;

/**
 * @Auther: Sen
 * @Date: 2020/2/2 23:45
 * @Description: 弗洛伊德算法：解决最短路径问题（时间复杂度n^3)
 */
public class FloydAlgorithm {

    public static void main(String[] args) {
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        final int N = 65535;
        int[][] matrix = new int[vertexes.length][vertexes.length];
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};

        Graph graph = new Graph(vertexes.length, vertexes, matrix);
        graph.floyd();
        graph.show();
    }

    private static class Graph {
        /**
         * 保存顶点
         */
        char[] vertexes;

        /**
         * 保存各顶点之间的距离，动态变化，最后保证各顶点到其他顶点的最短距离
         */
        int[][] dis;

        /**
         * 保存各顶点的前驱节点（中间节点）
         */
        int[][] pre;

        public Graph(int length, char[] vertexes, int[][] matrix) {
            this.vertexes = vertexes;
            this.dis = matrix;
            this.pre = new int[length][length];
            //初始化pre，默认指定自身
            for (int i = 0; i < length; i++) {
                Arrays.fill(pre[i], i);
            }
        }

        /**
         * 弗洛伊德算法
         */
        public void floyd() {
            //中间顶点
            for (int i = 0; i < vertexes.length; i++) {
                //开始顶点
                for (int j = 0; j < vertexes.length; j++) {
                    //结束顶点
                    for (int k = 0; k < vertexes.length; k++) {
                        int len = dis[j][i] + dis[i][k];
                        if (len < dis[j][k]) {
                            //更新开始顶点到结束顶点的距离
                            dis[j][k] = len;
                            //更新前驱顶点
                            pre[j][k]  = pre[i][k];
                        }
                    }
                }
            }
        }

        public void show() {
            char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
            for (int i = 0; i < vertexes.length; i++) {
                for (int j = 0; j < vertexes.length; j++) {
                    System.out.print(vertexes[pre[i][j]] + " ");
                }
                System.out.println();
                for (int j = 0; j < vertexes.length; j++) {
                    System.out.print(vertexes[i] + "->" + vertexes[j] + "(" + dis[i][j] + ") ");
                }
                System.out.println();
            }
        }
    }
}
