package com.sen.data.structure.tree;

/**
 * @Author: Sen
 * @Date: 2020/1/16 16:20
 * @Description: 顺序存储二叉树
 */
public class ArrayBinaryTreeDemo {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        System.out.println("前序遍历");
        arrayBinaryTree.preOrder();

        System.out.println("中序遍历");
        arrayBinaryTree.infixOrder();

        System.out.println("后序遍历");
        arrayBinaryTree.postOrder();
    }

    private static class ArrayBinaryTree {
        private int[] arr;

        public ArrayBinaryTree(int[] arr) {
            this.arr = arr;
        }

        public void preOrder() {
            preOrder(0);
        }
        /**
         * 顺序存储二叉树前序遍历
         * @param index 根节点的下标
         */
        public void preOrder(int index) {
            //当前表示二叉树的数组为空则结束遍历
            if (arr == null || arr.length == 0) {
                System.err.println("Current ArrayBinaryTree is Empty!");
                return;
            }
            //打印当前节点
            System.out.println(arr[index]);
            // 判断是否存在左节点-->左节点存在的下标是否越界
            if (2 * index + 1 < arr.length) {
                preOrder(2 * index + 1);
            }
            //判断是否存在右节点-->右节点的下标是否越界
            if (2 * index + 2 < arr.length) {
                preOrder(2 * index + 2);
            }
        }

        public void infixOrder() {
            infixOrder(0);
        }

        /**
         * 顺序存储二叉树中序遍历
         * @param index 根节点下标
         */
        public void infixOrder(int index) {
            //当前表示二叉树的数组为空则结束遍历
            if (arr == null || arr.length == 0) {
                System.err.println("Current ArrayBinaryTree is Empty!");
                return;
            }
            //打印当前节点的左节点
            if (2 * index + 1 < arr.length) {
                infixOrder(2 * index + 1);
            }
            //打印当前节点
            System.out.println(arr[index]);
            if (2 * index + 2 < arr.length) {
                infixOrder(2 * index + 2);
            }
        }


        public void postOrder() {
            postOrder(0);
        }

        /**
         * 顺序二叉树后序遍历
         * @param index 根节点下标
         */
        public void postOrder(int index) {
            //当前表示二叉树的数组为空则结束遍历
            if (arr == null || arr.length == 0) {
                System.err.println("Current ArrayBinaryTree is Empty!");
                return;
            }
            //打印当前节点的左节点
            if (2 * index + 1<arr.length) {
                postOrder(2 * index + 1);
            }
            //打印当前节点的右节点
            if (2 * index + 2 < arr.length) {
                postOrder(2 * index + 2);
            }
            //打印当前节点
            System.out.println(arr[index]);
        }
    }
}
