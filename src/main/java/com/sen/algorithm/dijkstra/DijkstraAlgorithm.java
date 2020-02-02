package com.sen.algorithm.dijkstra;

import java.util.Arrays;

/**
 * @Auther: Sen
 * @Date: 2020/1/31 18:09
 * @Description: 迪杰斯特拉算法：解决最短路径问题
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
        char[] vertexes;
        int[][] matrix;

        public Graph(char[] vertexes, int[][] matrix) {
            this.vertexes = vertexes;
            this.matrix = matrix;
        }

        public void print() {
            for (int[] v : matrix) {
                System.out.println(Arrays.toString(v));
            }
        }

        public void dijkstra(int index) {
            VisitedVertex visitedVertex = new VisitedVertex(vertexes.length, index);
            update(index, visitedVertex);
            for (int i = 1; i < vertexes.length; i++) {
                index = visitedVertex.updateArr();
                update(index, visitedVertex);
            }
            visitedVertex.show();
        }

        /**
         * 更新出发顶点到其他顶点的最短距离和前驱顶点
         *
         * @param index
         */
        private void update(int index, VisitedVertex visitedVertex) {
            for (int i = 0; i < matrix[index].length; i++) {
                int len = visitedVertex.dis[index] + matrix[index][i];
                if (!visitedVertex.isVisited(i) && len < visitedVertex.dis[i]) {
                    //更新最短距离
                    visitedVertex.updateDis(i, len);
                    //更新前驱节点
                    visitedVertex.updatePre(i, index);
                }
            }
        }

    }

    private static class VisitedVertex {
        /**
         * 记录已访问的顶点,0未访问，1已访问
         */
        int[] already_arr;

        /**
         * 每个顶点的下标为对应的值为前一个顶点对应的下标
         */
        int[] pre_visited;

        /**
         * 记录出发顶点到其他顶点的距离,默认为65535，顶点到自身顶点的距离为0；
         */
        int[] dis;

        public VisitedVertex(int length, int index) {
            this.already_arr = new int[length];
            this.pre_visited = new int[length];
            this.dis = new int[length];
            //初始化数组
            Arrays.fill(this.dis, 65535);
            this.already_arr[index] = 1;
            dis[index] = 0;
        }
        /**
         * 继续选择并返回新的访问顶点，比如G顶点访问后的顶点是A顶点最为新的访问顶点（注意不是出发点）
         * @return 返回新访问顶点的下标
         */
        public int updateArr() {
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

        public void show(){
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

        /**
         * 判断index顶点是否被访问过
         *
         * @param index 顶点对应的下标
         * @return 访问过返回true，否则返回false
         */
        public boolean isVisited(int index) {
            return already_arr[index] == 1;
        }

        /**
         * 更新出发顶点到index顶点的距离
         *
         * @param index 下标
         * @param len   更新的而距离
         */
        public void updateDis(int index, int len) {
            dis[index] = len;
        }

        /**
         * 更新pre这个顶点的前驱节点是index这个顶点
         *
         * @param pre   当前顶点
         * @param index 前驱顶点
         */
        public void updatePre(int pre, int index) {
            pre_visited[pre] = index;
        }

        /**
         * 返回出发顶点到index顶点的距离
         *
         * @param index
         * @return
         */
        public int getDis(int index) {
            return dis[index];
        }
    }
}
