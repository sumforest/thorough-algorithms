package com.sen.data.structure.huffman.tree;

import java.util.ArrayList;
import java.util.Collections;
/**
 * @Auther: Sen
 * @Date: 2020/1/18 15:22
 * @Description: 实现赫夫曼编码
 */
public class HuffmanTree {

    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node huffNode = createHuffmanTree(arr);
        huffNode.preOrderPrint();
    }

    /**
     * 创建赫夫曼树
     *
     * @param arr 用于创建赫夫曼编码的数组
     * @return 返回赫夫曼树的根节点
     */
    private static Node createHuffmanTree(int[] arr) {
        ArrayList<Node> nodes = new ArrayList<>();
        //1.把数组转换成集合操作方便
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        //当集合中只有一个元素时退出
        while (nodes.size() > 1) {
            //把集合升序排序
            Collections.sort(nodes);
            //取出并删除最小的元素
            Node left = nodes.remove(0);
            //取出并删除第二小的元素
            Node right = nodes.remove(0);
            //新建一个根节点
            Node parent = new Node(left.value + right.value);
            //把较小的元素加到parent的左边
            parent.left = left;
            //把较大的元素加到parent的右边
            parent.right = right;
            //把parent加到集合中
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    /**
     * 实现comparable接口用于{@link java.util.stream.Collectors} 的sort()排序
     */
    public static class Node implements Comparable<Node> {

        int value;

        Node left;

        Node right;

        public Node(int value) {
            this.value = value;
        }

        public void preOrderPrint() {
            System.out.println(this);
            if (this.left != null) {
                this.left.preOrderPrint();
            }
            if (this.right != null) {
                this.right.preOrderPrint();
            }
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
}
