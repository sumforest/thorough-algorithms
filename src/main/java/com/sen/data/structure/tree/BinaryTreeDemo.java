package com.sen.data.structure.tree;

/**
 * @Auther: Sen
 * @Date: 2020/1/16 00:49
 * @Description: 二叉树
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node1 = new HeroNode(2, "吴用");
        HeroNode node2 = new HeroNode(3, "卢俊义");
        HeroNode node3 = new HeroNode(4, "林冲");
        HeroNode node4 = new HeroNode(5, "关胜");
        root.left = node1;
        root.right = node2;
        node2.right = node3;
        node2.left = node4;
        BinaryTree binaryTree = new BinaryTree(root);
        // System.out.println("前序遍历");
        // binaryTree.preOrder();
        //
        // System.out.println("中序遍历");
        // binaryTree.infixOrder();
        //
        // System.out.println("后续遍历");
        // binaryTree.postOrder();

        // System.out.println("前序查找");
        // System.out.println(binaryTree.preOrderSearch(15));

        // System.out.println("中序查找");
        // System.out.println(binaryTree.infixOrderSearch(5));

        System.out.println("后序查找");
        System.out.println(binaryTree.postOrderSearch(5));
    }

    private static class BinaryTree{
        HeroNode root;

        public BinaryTree(HeroNode root) {
            this.root = root;
        }

        public void preOrder() {
            if (root == null) {
                System.err.println("Current BinaryTree is Empty");
                return;
            }
            root.preOrder();
        }

        public void infixOrder() {
            if (root == null) {
                System.err.println("Current BinaryTree is Empty");
                return;
            }
            root.infixOrder();
        }

        public void postOrder(){
            if (root == null) {
                System.err.println("Current BinaryTree is Empty");
                return;
            }
            root.postOrder();
        }

        public HeroNode preOrderSearch(int no) {
            if (root == null) {
                return null;
            }
            return root.preOrderSearch(no);
        }

        public HeroNode infixOrderSearch(int no) {
            if (root == null) {
                return null;
            }
            return root.infixOrderSearch(no);
        }

        public HeroNode postOrderSearch(int no) {
            if (root == null) {
                return null;
            }
            return root.postOrderSearch(no);
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
         * 表示当前节点的右子节点，默认为null
         */
        HeroNode right;

        public HeroNode(int no, String name) {
            this.no = no;
            this.name = name;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    '}';
        }

        /**
         * 二叉树前序遍历
         */
        public void preOrder(){
            System.out.println(this);
            if (this.left != null) {
                //向左递归遍历
                this.left.preOrder();
            }
            if (this.right != null) {
                //向右递归遍历
                this.right.preOrder();
            }
        }

        /**
         * 二叉树中序遍历
         */
        public void infixOrder() {
            if (this.left != null) {
                //向左递归
                this.left.infixOrder();
            }
            System.out.println(this);
            if (this.right != null) {
                //向右递归
                this.right.infixOrder();
            }
        }

        /**
         * 二叉树后续遍历
         */
        public void postOrder(){
            if (this.left != null) {
                // 向左递归
                this.left.postOrder();
            }
            if (this.right != null) {
                //向右递归
                this.right.postOrder();
            }
            System.out.println(this);
        }

        /**
         * 前序遍历查找
         * @param no
         * @return
         */
        public HeroNode preOrderSearch(int no) {
            //判断是否相等，相等则找到
            if (this.no == no) {
                return this;
            }
            //存放查找结果
            HeroNode result = null;
            if (this.left != null) {
                //递归向左查找
                result = this.left.preOrderSearch(no);
            }
            //左边子树递归查找后是否找到
            if (result != null) {
                return result;
            }
            // System.out.println("-----------");
            if (this.right != null) {
                //右子树没找到递归向右查找
                result = this.right.preOrderSearch(no);
            }
            //最后不管有没找到都返回
            return result;
        }

        /**
         * 中序遍历查找
         * @param no
         * @return
         */
        public HeroNode infixOrderSearch(int no) {
            //存放查找结果
            HeroNode result = null;
            if (this.left != null) {
                result = this.left.infixOrderSearch(no);
            }
            //左子树找到则返回
            if (result != null) {
                return result;
            }
            System.out.println("---------------");
            //比较当前节点是否是目标值
            if (this.no == no) {
                return this;
            }
            if (this.right != null) {
                //左子树没找到继续往当前节点的右子树寻找
                result = this.right.infixOrderSearch(no);
            }
            //最后返回结果
            return result;
        }

        /**
         * 后续遍历查找
         *
         * @param no
         * @return
         */
        public HeroNode postOrderSearch(int no) {
            HeroNode result = null;
            if (this.left != null) {
                result = this.left.postOrderSearch(no);
            }
            if (result != null) {
                return result;
            }
            if (this.right != null) {
                result = this.right.postOrderSearch(no);
            }
            if (result != null) {
                return result;
            }
            System.out.println("---------------");
            if (this.no == no) {
                return this;
            }
            return null;
        }
    }
}
