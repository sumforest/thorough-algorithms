package com.sen.data.structure.graph;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @Auther: Sen
 * @Date: 2020/1/25 14:51
 * @Description: 图（邻接矩阵）
 */
public class Graph {

    /**
     * 图的顶点
     */
    private ArrayList<String> vertexes;

    /**
     * 记录图的边的总数
     */
    private int numbersOfEdges;

    /**
     * 描述顶点相邻关系的顶点
     */
    private int[][] edges;

    /**
     * 标记节点是否被访问，访问后标记为true
     */
    private boolean[] isVisited;

    /**
     * @param n 顶点的个数
     */
    public Graph(int n) {
        this.vertexes = new ArrayList<>(n);
        this.edges = new int[n][n];
        this.isVisited = new boolean[n];
    }

    /**
     * 添加顶点
     *
     * @param vertex
     */
    public void addVertex(String vertex) {
        vertexes.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     一个顶点的下标如 A->0;B->1
     * @param v2     另一个顶点的下标
     * @param weight 边的权重，这里默认为0，1表示两个顶点连通
     */
    public void addEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        this.numbersOfEdges++;
    }

    /**
     * 获取边的个数
     *
     * @return
     */
    public int getNumbersOfEdges() {
        return this.numbersOfEdges;
    }

    /**
     * 获取顶点的个数
     *
     * @return
     */
    public int getCountOfVertex() {
        return this.vertexes.size();
    }

    /**
     * 获取顶点
     *
     * @param index 顶点所在集合的下标
     * @return 顶点
     */
    public String getVertex(int index) {
        return this.vertexes.get(index);
    }

    /**
     * 打印顶点
     */
    public void print() {
        for (int[] edge : edges) {
            for (int vertex : edge) {
                System.out.print(vertex + " ");
            }
            System.out.println();
        }
    }

    /**
     * 获取当前节点的邻接的下标
     *
     * @param index 当前节点的下标
     * @return 存在返回下标，不存在返回-1
     */
    private int getFistNeighbor(int index) {
        for (int i = 0; i < vertexes.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取当前节点的下一个邻接节点
     *
     * @param v1 当前节点
     * @param v2 当前节点的邻接节点
     * @return 存在返回对应的下标，不存在返回-1
     */
    private int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexes.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 图的深度优先遍历算法
     *
     * @param isVisited 用于标记顶点是否被访问
     * @param i         开始遍历顶点的下标
     */
    private void dfs(boolean[] isVisited, int i) {
        //输出当前顶点
        String str = vertexes.get(i);
        System.out.print(str + "->");
        //把当前顶点标记为已访问
        isVisited[i] = true;
        //以当前顶点的邻接节点作为起始继续遍历
        int w = getFistNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                //邻接节点没有被访问继续深度优先遍历
                dfs(isVisited, w);
            }
            //获取下一个邻接节点作为深度遍历的起始下标
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * 重载用于遍历当前顶点的邻接节点
     */
    public void dfs() {
        for (int i = 0; i < vertexes.size(); i++) {
            if (!isVisited[i]) {
                //以当前顶点作为深度优先遍历算法的起始位置
                //回溯到当前节点的下一个邻接节点
                dfs(isVisited, i);
            }
        }
    }

    /**
     * 广度优先遍历算法
     *
     * @param isVisited 标记当前顶点是否被访问过
     * @param i         第几个顶点
     */
    private void bfs(boolean[] isVisited, int i) {
        //输出当前顶点
        String vertex = getVertex(i);
        System.out.print(vertex + "->");
        //把当前顶点标记为已访问
        isVisited[i] = true;
        //把当前已访问的顶点入队
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(i);

        while (!queue.isEmpty()) {
            //出队
            int u = queue.removeFirst();
            //获取顶点u的邻接点
            int w = getFistNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    //输出当前节点
                    System.out.print(getVertex(w) + "->");
                    //把当前顶点标记为已访问
                    isVisited[w] = true;
                    //把当前节点入队
                    queue.addLast(w);
                }
                //获取下一个邻接点
                w = getNextNeighbor(u, w);
            }
        }
    }

    /**
     * 每一个顶点都经行广度遍历算法
     */
    public void bfs(){
        for (int i = 0; i < vertexes.size(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(8);

        //添加顶点
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("3");
        graph.addVertex("4");
        graph.addVertex("5");
        graph.addVertex("6");
        graph.addVertex("7");
        graph.addVertex("8");

        //添加边
        // A-B A-C B-C B-D B-E
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(1, 4, 1);
        graph.addEdge(3, 7, 1);
        graph.addEdge(4, 7, 1);
        graph.addEdge(2, 5, 1);
        graph.addEdge(2, 6, 1);
        graph.addEdge(5, 6, 1);

        graph.print();

        System.out.println("深度优先搜索算法");
        graph.dfs();

        // System.out.println("广度优先搜索算法");
        // graph.bfs();
    }
}
