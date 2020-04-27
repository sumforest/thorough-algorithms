package com.sen.data.structure.binary.sort.tree;

/**
 * @Author: Sen
 * @Date: 2020/1/20 10:16
 * @Description: 二叉排序树（二叉搜索树）
 */
public class BinarySortTreeDemo {

    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i : arr) {
            binarySortTree.add(new Node(i));
        }
        // binarySortTree.infixOrderPrint();

        //删除根节点
        // binarySortTree.deleteNode(7);
        // binarySortTree.infixOrderPrint();

        //删除叶子节点
        // binarySortTree.deleteNode(2);
        // binarySortTree.deleteNode(5);
        // binarySortTree.deleteNode(9);
        // binarySortTree.deleteNode(12);
        // binarySortTree.deleteNode(1);

        binarySortTree.deleteNode(3);
        binarySortTree.deleteNode(10);
        binarySortTree.infixOrderPrint();
    }

    private static class BinarySortTree {
        Node root;

        public void add(Node node) {
            if (root == null) {
                //根节点位空直接挂到根节点
                root = node;
            } else {
                root.add(node);
            }
        }

        public Node findTargetNode(int value) {
            if (root == null) {
                return null;
            }
            if (root.value == value) {
                return root;
            }
            return root.findTargetNode(value);
        }

        public Node findParentNode(int value) {
            if (root == null) {
                return null;
            }
            return root.findParentNode(value);
        }

        /**
         * 删除二叉排序树（BST）节点
         *
         * @param value
         */
        public void deleteNode(int value) {
            Node targetNode = findTargetNode(value);
            //要删除的节点不存在
            if (targetNode == null) {
                return;
            }
            Node parentNode = findParentNode(value);
            if (parentNode == null) {
                //要删除的节点是该BST的根节点
                root = null;
                return;
            }
            //删除的节点是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                //要删除的节点是parent的左子节点
                if (parentNode.left != null && parentNode.left.value == value) {
                    parentNode.left = null;
                    return;
                }
                //要删除的点点是parent的右子节点
                if (parentNode.right != null && parentNode.right.value == value) {
                    parentNode.right = null;
                }
            }
            //删除的节点是有两个子树
            else if (targetNode.left != null && targetNode.right != null) {
                //查找当前节点子树的最小值
                int min = findMinValue(targetNode.right);
                //把当前得点的父节点的值用最小值替换
                targetNode.value = min;
            }
            //删除的节点有一个子树
            else {
                //要删除的节点有左子树
                if (targetNode.left != null) {
                    //要删除的节点是parent的左子树
                    if (parentNode.left.value == value) {
                        parentNode.left = targetNode.left;
                        return;
                    }
                    //要删除的的节点是parent的右子树
                    if (parentNode.right.value == value) {
                        parentNode.right = targetNode.left;
                        return;
                    }
                }
                //要删除的节点有右子树
                if (targetNode.right != null) {
                    //要删除的节点是parent的左子树
                    if (parentNode.left.value == value) {
                        parentNode.left = targetNode.right;
                        return;
                    }
                    //要删除的节点是parent的右子节点
                    if (parentNode.right.value == value) {
                        parentNode.right = targetNode.right;
                    }
                }
            }
        }

        /**
         * 查找要删除节点的右子树的最小值，并删除最小节点
         *
         * @param node 要查找子树的根节点
         * @return 要删除子树的最小值
         */
        private int findMinValue(Node node) {
            //向左寻找最小节点的值
            while (node.left != null) {
                node = node.left;
            }
            int temp = node.value;
            deleteNode(temp);
            return temp;
        }

        public void infixOrderPrint() {
            if (root == null) {
                System.err.println("Current Tree is Empty");
            } else {
                root.preOrder();
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

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }

        /**
         * 查找要删除的节点
         *
         * @param value 要删除节点的值
         * @return
         */
        public Node findTargetNode(int value) {
            if (this.value == value) {
                //找到了当前节点就是要删除的节点
                return this;
            }
            //要删除节点的值比当前节点小向右递归查找
            if (this.value > value && this.left != null) {
                return this.left.findTargetNode(value);
            }
            //要删除的节点的值比当前节点的值小，并且当前节点的左节点为空，则不存在该节点
            if (this.value > value && this.left == null) {
                return null;
            }
            //要删除的节点的值大于等于当前节点，并且当前节点的右节点不为空
            if (this.value < value && this.right != null) {
                return this.right.findTargetNode(value);
            }
            //要删除的节点的值大于当前节点，并且当前节点的右节点为空，则不存在该节点
            return null;
        }

        /**
         * 查找要删除节点的父节点
         *
         * @param value 要删除的值
         * @return
         */
        public Node findParentNode(int value) {
            //找到了父节点
            if (this.left != null && this.left.value == value || this.right != null && this.right.value == value) {
                return this;
            }
            if (this.left != null && this.value > value) {
                //向左递归查找要删除的父节点
                return this.left.findParentNode(value);
            }
            if (this.right != null && this.value <= value) {
                //向右递归查找要删除的父节点
                return this.right.findParentNode(value);
            }
            return null;
        }

        /**
         * 根据二叉排序树：根节点比左子树的都大，根节点比右子树的都小；
         * 特别地如果左子树存在和根节点的值相等统一放到右子树
         *
         * @param node
         */
        public void add(Node node) {
            if (node == null) {
                return;
            }
            if (node.value < this.value) {
                //当前节点的值比当前子树根节点的值小，挂在左边
                if (this.left == null) {
                    this.left = node;
                } else {
                    //当前子树根节点的值不为空继续递归往下查找位置
                    this.left.add(node);
                }
            } else {
                //当前节点的值比当前子树根节点的值大或者等于，挂在左边
                if (this.right == null) {
                    this.right = node;
                } else {
                    this.right.add(node);
                }
            }
        }

        /**
         * 前序遍历BST二叉搜索树
         */
        public void preOrder() {
            if (this.left != null) {
                this.left.preOrder();
            }
            System.out.println(this);
            if (this.right != null) {
                this.right.preOrder();
            }
        }
    }
}
