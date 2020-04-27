package com.sen.algorithm.kruskal;

import java.util.Arrays;

/**
 * @Author: Sen
 * @Date: 2020/1/29 22:49
 * @Description: 克鲁斯卡尔算法解决公交问题
 */
public class KruskalCase {

    private static final int INF = Integer.MAX_VALUE;

    /**
     * 表示边的数量
     */
    private int edges;

    /**
     * 顶点的个数
     */
    private int vertexesLen;

    /**
     * 保存顶点
     */
    private char[] vertexes;

    /**
     * 邻接矩阵
     */
    private int[][] matrix;

    public KruskalCase(char[] vertexes, int[][] matrix) {
        this.vertexes = vertexes;
        this.matrix = matrix;
        this.vertexesLen = vertexes.length;
        for (int i = 0; i < vertexesLen; i++) {
            //只统计邻接矩阵的上半部分三角形，即不统计重复的边
            for (int j = i + 1; j < vertexesLen; j++) {
                if (matrix[i][j] != Integer.MAX_VALUE) {
                    edges++;
                }
            }
        }
    }

    public void print() {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.printf("%15d", anInt);
            }
            System.out.println();
        }
    }

    public void sortEdata(EData[] eDatas) {
        //比较的轮数
        for (int i = 0; i < eDatas.length - 1; i++) {
            for (int j = 0; j < eDatas.length - 1 - i; j++) {
                if (eDatas[j].weight > eDatas[j + 1].weight) {
                    EData temp = eDatas[j];
                    eDatas[j] = eDatas[j + 1];
                    eDatas[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 获取顶点在顶点数组种的位置
     *
     * @param vertex 顶点
     * @return 反回顶点的下标, 不存在返回-1
     */
    public int getPosition(char vertex) {
        for (int i = 0; i < vertexes.length; i++) {
            if (vertex == vertexes[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 创建边
     *
     * @return
     */
    public EData[] createEdges() {
        EData[] eDatas = new EData[edges];
        int index = 0;
        for (int i = 0; i < vertexesLen; i++) {
            //只统计邻接矩阵的上半部分三角形，即不统计重复的边
            for (int j = i + 1; j < vertexesLen; j++) {
                int temp = this.matrix[i][j];
                if (temp != Integer.MAX_VALUE) {
                    eDatas[index++] = new EData(vertexes[i], vertexes[j], temp);
                }
            }
        }
        return eDatas;
    }

    /**
     * 获取顶点下标为i的终点，用于后面判断两个顶点的终点是否相同
     *
     * @param ends 记录了各个顶点对应的重点，在遍历过程种逐步变价
     * @param i    顶点的下标
     * @return 终点的下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    /**
     * 克鲁斯卡尔算法
     */
    public void kruskal() {
        //记录每个顶点的终点
        int[] ends = new int[edges];
        EData[] result = new EData[edges];
        int index = 0;
        EData[] eData = createEdges();
        //排序
        sortEdata(eData);
        for (int i = 0; i < this.edges; i++) {
            //对应边的第一个顶点
            int p1 = getPosition(eData[i].start);
            //对应边的第二个顶点
            int p2 = getPosition(eData[i].end);

            //第一个顶点的终点
            int m = getEnd(ends, p1);
            //第二个顶点的终点
            int n = getEnd(ends, p2);

            //第一个顶点的终点和第二个顶点的终点不重合，即新添加的边与已有的边不形成回路
            if (m != n) {
                result[index++] = eData[i];
                //设置m在已有生成树中的终点为n
                ends[m] = n;
            }
        }
        System.out.println(Arrays.toString(result));
    }

    /**
     * 描述边的类
     */
    private static class EData {
        /**
         * 边的起始顶点
         */
        char start;
        /**
         * 边的结束顶点
         */
        char end;
        /**
         * 边的权重
         */
        int weight;

        public EData(char start, char end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "EData{" +
                    "<" + start +
                    ", " + end +
                    ">, weight=" + weight +
                    '}';
        }
    }

    public static void main(String[] args) {
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}};
        KruskalCase kruskalCase = new KruskalCase(vertexes, matrix);
        kruskalCase.print();
        EData[] edges = kruskalCase.createEdges();
        // kruskalCase.sortEdata(edges);
        // System.out.println(Arrays.toString(edges));
        kruskalCase.kruskal();
    }
}
