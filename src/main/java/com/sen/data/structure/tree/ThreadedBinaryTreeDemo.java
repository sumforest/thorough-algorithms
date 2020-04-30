package com.sen.data.structure.tree;

import javax.xml.soap.Node;

/**
 * @Author: Sen
 * @Date: 2020/1/16 17:29
 * @Description: 线索化二叉树
 */
public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "Tom");
        HeroNode node1 = new HeroNode(3, "Bob");
        HeroNode node2 = new HeroNode(6, "Shell");
        HeroNode node3 = new HeroNode(8, "JetBrains");
        HeroNode node4 = new HeroNode(10, "PC");
        HeroNode node5 = new HeroNode(14, "Logitech");
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(root);
        // threadedBinaryTree.infixOrderCreateThreadedBinaryTree();
        // System.out.println(node4);
        //
        // System.out.println("中序线索二叉树遍历：");
        // threadedBinaryTree.print();

        // System.out.println("前序线索二叉树");
        // threadedBinaryTree.preOrderThreadedBinaryTree();
        // System.out.println(node5);

        System.out.println("后序序线索二叉树");
        threadedBinaryTree.postOrderThreadedBinaryTree();
        System.out.println(node5);
    }

    /**
     * 线索二叉树
     */
    private static class ThreadedBinaryTree {
        HeroNode root;

        /**
         * 用于记录前驱节点线索化二叉树
         */
        HeroNode pre;

        HeroNode temp = new HeroNode(-1, "");

        public ThreadedBinaryTree(HeroNode root) {
            this.root = root;
        }

        public void infixOrderCreateThreadedBinaryTree() {
            infixOrderThreadedBinaryTree(root);
        }

        public void postOrderThreadedBinaryTree() {
            postOrderThreadedBinaryTree(root);
        }

        /**
         * 后序线索化二叉树
         *
         * @param node
         */
        private void postOrderThreadedBinaryTree(HeroNode node) {
            if (node == null) {
                return;
            }
            //向左线索左子树
            postOrderThreadedBinaryTree(node.left);
            //向右线索右子树
            postOrderThreadedBinaryTree(node.right);
            //线索当节点
            //当前节点的左指针为空，线索前驱节点
            if (node.left == null) {
                node.left = pre;
                node.leftType = 1;
            }
            //前驱节点的不为空，并且前驱节点的右指针为空
            if (pre != null && pre.right == null) {
                pre.right = node;
                node.rightType = 1;
            }
            //跟新前驱节点
            pre = node;
        }

        /**
         * 后序遍历线索二叉树
         */
        /*public void postOrderPrint(){
            HeroNode cur = root;
            while (cur != null) {
                //当leftType == 0说明不是线索节点，还没到输出位置
                while (cur.leftType == 0) {
                    cur = cur.left;
                }
                //输出当前节点
                System.out.println(cur);
                while (cur.leftType == 1) {
                    System.out.println(cur);
                    cur = cur.right;
                }
                //
            }
        }*/
        public void preOrderThreadedBinaryTree() {
            preOrderThreadedBinaryTree(root);
        }

        /**
         * 前序线索化二叉树
         *
         * @param node
         */
        public void preOrderThreadedBinaryTree(HeroNode node) {
            //如果当前节点为空直接反回
            if (node == null || node == temp) {
                return;
            }
            //当前节点的做指针为空，线索化前驱节点
            if (node.left == null) {
                node.left = pre;
                temp = pre;
                node.leftType = 1;
            }
            //当前节点的前驱节点不为空并且右指针为空并且，线索化前驱节点的后继节点
            if (pre != null && pre.right == null && pre.left != node) {
                pre.right = node;
                pre.rightType = 1;
            }
            //更新前驱节点
            pre = node;
            //线索化左子树
            preOrderThreadedBinaryTree(node.left);
            //线索化右子树
            preOrderThreadedBinaryTree(node.right);
        }


        /**
         * 中序线索化二叉树
         *
         * @param node 根节点
         */
        public void infixOrderThreadedBinaryTree(HeroNode node) {
            //当前节点为返回，结束线索化
            if (node == null) {
                return;
            }
            //线索化左子树
            infixOrderThreadedBinaryTree(node.left);
            /*
                                   1
                                /     \
                               3        6
                              /  \      /
                             8   10    14
            线索化当前节点
            只有在当前节点的左指针为空时才线索化前驱节点
            */
            if (node.left == null) {
                //把当前节点的左指针指向前驱节点
                node.left = pre;
                //修改做指针类型
                node.leftType = 1;
            }
            //在前节点不为空时并且前驱节点的右指针为空，线索化当前节点的前驱节点的后继节点
            if (pre != null && pre.right == null) {
                //把当前节点的指针指向前驱节点的后继节点
                pre.right = node;
                //修改指针类型
                pre.rightType = 1;
            }
            //移动前驱节点
            pre = node;
            //线索化右子树
            infixOrderThreadedBinaryTree(node.right);
        }

        /**
         * 遍历中序线索化二叉树
         */
        public void print() {
            HeroNode cur = root;
            /*
                     1
                  /     \
                 3        6
                /  \      /
               8   10    14
              / \
             4  7
             */
            while (cur != null) {
                //查找当前节点的左子树，直到leftType = 1
                while (cur.leftType == 0) {
                    cur = cur.left;
                }
                //输出当前节点
                System.out.println(cur);
                //继续输出当前节点的后继节点
                while (cur.rightType == 1) {
                    //把当前节点指向后去节点
                    cur = cur.right;
                    //输出后继节点
                    System.out.println(cur);
                }
                // 当前节点没有后继节点时，把当前的节点指向当前节点的右节点
                cur = cur.right;
            }
        }
    }

    private static class HeroNode {

        int no;

        String name;

        /**
         * 表示当前节点的左子节点,默认为null
         */
        HeroNode left;

        /**
         * 0->该节点的左指针存储的是左子节点；1->该节点的左指针存储的是前驱节点
         */
        int leftType;

        /**
         * 表示当前节点的右子节点，默认为null
         */
        HeroNode right;

        /**
         * 0->该节点的右指针存储的是右子节点；1->该节点的右指针存储的是后继节点
         */
        int rightType;

        public HeroNode(int no, String name) {
            this.no = no;
            this.name = name;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", leftType=" + leftType +
                    ", rightType=" + rightType +
                    '}';
        }
    }
}
