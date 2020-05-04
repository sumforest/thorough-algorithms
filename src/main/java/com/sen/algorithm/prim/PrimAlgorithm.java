package com.sen.algorithm.prim;

/**
 * @Author: Sen
 * @Date: 2020/1/29 14:05
 * @Description: 普里姆算法，解决修路最短问题
 */
public class PrimAlgorithm {

    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int count = data.length;
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},
        };

        Graph graph = new Graph(count);
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, data, weight);
        minTree.printGraph(graph);
        minTree.prim(graph, 0);
    }

    /**
     * 最小生成树
     */
    private static class MinTree {

        /**
         * 创建图，初始化各个村庄
         *
         * @param graph  图对象
         * @param data   每个村庄的字符表示数组
         * @param weight 每个村庄的权重即每个村庄的距离
         */
        public void createGraph(Graph graph, char[] data, int[][] weight) {
            if (graph.vertexes >= 0) {
                System.arraycopy(data, 0, graph.data, 0, graph.vertexes);
                System.arraycopy(weight, 0, graph.weight, 0, graph.vertexes);
            }
        }

        public void printGraph(Graph graph) {
            for (int[] ints : graph.weight) {
                for (int anInt : ints) {
                    System.out.print(anInt + " ");
                }
                System.out.println();
            }
        }

        /**
         * 普里姆算法
         *
         * @param graph 用于最小生成树的图
         * @param index 开始生成最小生成树的顶点对应的下标
         */
        public void prim(Graph graph, int index) {
            //定义一个数组数组标记顶点是否已经被访问
            int[] flag = new int[graph.vertexes];
            //把当前顶点标记为已访问
            flag[index] = 1;
            //假定最小的权值为较大值
            int minWeight = 10000;
            //已遍历过的顶点下标
            int h1 = -1;
            //已遍历过的顶点到未遍历过顶点中最小权值的顶点的下标
            int h2 = -1;
            /*
                <A,G> 权值：2
                <G,B> 权值：3
                <G,E> 权值：4
                <E,F> 权值：5
                <F,D> 权值：4
                <A,C> 权值：7
             */
            //普里姆算法：n个顶点生成n-1条边，构建最小生成树所有边
            for (int i = 1; i < graph.vertexes; i++) {
                // 遍历已访问过的顶点，以其为起始点寻找能到达顶点中权值最小的顶点
                for (int j = 0; j < graph.vertexes; j++) {
                    // 获取j顶点的所有邻接顶点
                    for (int k = 0; k < graph.vertexes; k++) {
                        int temp = graph.weight[j][k];
                        // 顶点j已经被访问，k未被访问，并且存在最小权值
                        if (flag[j] == 1 && flag[k] == 0 && minWeight > temp) {
                            //交换最小的权值
                            minWeight = temp;
                            // 记录当前顶点的下标
                            h1 = j;
                            // 记录能到达的权值最小的顶点下标
                            h2 = k;
                        }
                    }
                }
                // 每次遍历完之后得到一条边
                System.out.println("<" + graph.data[h1] + "," + graph.data[h2] + ">" + " 权值：" + minWeight);
                // 并且把当前的顶点的邻接顶点标记为已访问
                flag[h2] = 1;
                // 把最小的权值重置
                minWeight = 10000;
            }
        }
    }

    private static class Graph {
        /**
         * 顶点的总数
         */
        int vertexes;

        /**
         * 存放顶点的值
         */
        char[] data;

        /**
         * 每条边之间的权重
         */
        int[][] weight;

        public Graph(int vertexes) {
            this.vertexes = vertexes;
            this.data = new char[vertexes];
            this.weight = new int[vertexes][vertexes];
        }
    }
}
