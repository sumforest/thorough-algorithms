package com.sen.leetcode;

import javax.swing.tree.TreeNode;

/**
 * @author LingSen
 * @date 2023/6/9 15:18
 * 700. 二叉搜索树中的搜索
 * <p>
 * 给定二叉搜索树（BST）的根节点root和一个整数值val。
 * <p>
 * 你需要在 BST 中找到节点值等于val的节点。 返回以该节点为根的子树。 如果节点不存在，则返回null。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/search-in-a-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 示例 1:
 * 输入：root = [4,2,7,1,3], val = 2
 * 输出：[2,1,3]
 * <p>
 * 示例 2:
 * 输入：root = [4,2,7,1,3], val = 5
 * 输出：[]
 */
public class SearchBSTDemo {

    public static TreeNode searchBST(TreeNode root, int val) {
        //向左递归
        if (root.left != null) {
            TreeNode treeNode = searchBST(root.left, val);
            if (treeNode != null) {
                return treeNode;
            }
        }
        if (root.val == val) {
            return root;
        }
        // 向右递归
        if (root.right != null) {
            return searchBST(root.right, val);
        }
        return null;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
