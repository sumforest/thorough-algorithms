package com.sen.data.structure.avl;

/**
 * @Auther: Sen
 * @Date: 2020/1/23 23:21
 * @Description: 平衡二叉树（前提二叉搜索树）
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        // int[] arr = {4, 3, 6, 5, 7, 8};
        // int[] arr = {10, 12, 8, 9, 7, 6};
        int[] arr = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        for (int value : arr) {
            avlTree.add(new Node(value));
        }
        System.out.println("创建的二叉排序树");
        avlTree.print();
        // System.out.println("左旋转调整后树的高度：" + avlTree.height());
        // System.out.println("旋转前的左子树的高度：" + avlTree.leftHeight());
        // System.out.println("旋转后的右子树的高度：" + avlTree.rightHeight());
        // System.out.println("根节点为：" + avlTree.root);

        System.out.println("双旋转调整后树的高度：" + avlTree.height());
        System.out.println("旋转后的左子树的高度：" + avlTree.leftHeight());
        System.out.println("旋转后的右子树的高度：" + avlTree.rightHeight());
        System.out.println("根节点为：" + avlTree.root);
        avlTree.print();
    }

    private static class AVLTree {
        Node root;

        public void print() {
            if (root == null) {
                throw new RuntimeException("Current Tree is Empty!");
            }
            root.infixOrderPrint();
        }

        /**
         * @return 返回AVL树的高度
         */
        public int height() {
            if (root == null) {
                return 0;
            }
            return root.height();
        }

        /**
         * @return 返回左子树的高度
         */
        public int leftHeight() {
            if (root.left == null) {
                return 0;
            }
            return root.left.height();
        }

        /**
         * @return 返回右子树的高度
         */
        public int rightHeight() {
            if (root.right == null) {
                return 0;
            }
            return root.right.height();
        }

        /**
         * 创建二叉排序树
         *
         * @param node
         */
        public void add(Node node) {
            if (root == null) {
                //如果根节点为空，则添加的节点指向根节点
                root = node;
            } else {
                root.add(node);
            }

        }
    }

    private static class Node {
        int value;

        Node left;

        Node right;

        public Node(int value) {
            this.value = value;
        }

        /**
         * 返回当前树的左子树的高度
         *
         * @return
         */
        public int leftHeight() {
            if (this.left == null) {
                return 0;
            }
            return this.left.height();
        }

        /**
         * 返回当前树的右子树的高度
         *
         * @return
         */
        public int rightHeight() {
            if (this.right == null) {
                return 0;
            }
            return this.right.height();
        }

        /**
         * 反回当前节点的树的高度
         *
         * @return 树的高度
         */
        public int height() {
            return Math.max(this.left == null ? 0 : this.left.height(), this.right == null ? 0 : this.right.height()) + 1;
        }

        /**
         * 平衡二叉树左旋转
         */
        public void leftRotate() {
            //以当前节点的值创建一个新节点
            Node newNode = new Node(this.value);
            //把当前节点的左子节点作为新节点的左子节点
            newNode.left = this.left;
            //把当前节点的右子节点的左子节点作为新节点的右子节点
            newNode.right = this.right.left;
            //把当前节点的右子节点的值作为当前节点的值
            this.value = this.right.value;
            //把当前节点的右子树的右子节点作为当前节点的右子节点
            this.right = this.right.right;
            //把新节点作为当前节点的左子节点
            this.left = newNode;
        }

        /**
         * 平衡二叉树右旋转
         */
        public void rightRotate() {
            Node newNode = new Node(this.value);
            //把新节点的右子树设置位当前节点的右子树
            newNode.right = this.right;
            //当前节点的左子树的右子节点作为新节点的左子节点
            newNode.left = this.left.right;
            //把当前节点的值替换成当前节点的左子节点的值
            this.value = this.left.value;
            //把当前节点的左子节点设置为当前节点的左子树的左子节点
            this.left = this.left.left;
            //把新节点设置为当前节点的右子节点
            this.right = newNode;
        }

        /**
         * 中序遍历AVL树
         */
        public void infixOrderPrint() {
            if (this.left != null) {
                this.left.infixOrderPrint();
            }
            System.out.println(this);
            if (this.right != null) {
                this.right.infixOrderPrint();
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }

        /**
         * 创建二叉排序树
         *
         * @param node
         */
        public void add(Node node) {
            if (node == null) {
                return;
            }
            if (node.value < this.value) {
                //新添加的节点的值比当前节点的小
                if (this.left == null) {
                    //当前节点的左节点为空
                    this.left = node;
                } else {
                    //当前节点的左节点不为空查找到二叉树的左边添加
                    this.left.add(node);
                }
            } else {
                //新添加的节点的值等于或大于当前节点的值
                if (this.right == null) {
                    //当前节点的右节点为空
                    this.right = node;
                } else {
                    //当前节点的右节点的不为空递归找到二叉树的右边添加
                    this.right.add(node);
                }
            }
            //每添加一个节点检查是否需要进行旋转
            if (rightHeight() - leftHeight() > 1) {
                if (this.right.leftHeight() > this.right.rightHeight()) {
                    //满足左旋转条件时判断当前节点的右子节点的左子树高度是否大于右子树高度
                    //满足对当前节点的右子树进行右旋转
                    this.right.rightRotate();
                    //在对当前节点左旋转
                    leftRotate();
                }else{
                    //当前节点的右子树高度-当前节点的左子树高度大于1
                    leftRotate();
                }
                return;
            }
            if (leftHeight() - rightHeight() > 1) {
                if (this.left.rightHeight() > this.left.leftHeight()) {
                    //满足右旋转条件时判断左子节点的右子树高度是否大于左子树高度，满足条件对当前节点的左子树进行左旋转
                    this.left.leftRotate();
                    //对当前节点进行右旋转
                    rightRotate();
                }
                else {
                    //当前节点的左子树-当前节点的右子树高于大于1
                    rightRotate();
                }
            }
        }
    }
}
