package com.sen.leetcode.rightSideView;

import java.util.*;

/**
 * @author LingSen
 * @date 2023/6/8 19:06
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * <p>
 * 示例 1:
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1,3,4]
 * <p>
 * 示例 2:
 * 输入: [1,null,3]
 * 输出: [1,3]
 * <p>
 * 示例 3:
 * 输入: []
 * 输出: []
 * <p>
 * 示例 3:
 * 输入: [1,2]
 * 输出: [1,2]
 */
public class RightSideViewDemo {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        root.left = node2;
        root.right = node3;
        node2.right = node5;
        node3.right = node4;
        System.out.println(rightSideView(new TreeNode(1)));
    }

    public static List<Integer> rightSideView(TreeNode root) {
        Map<Integer, Integer> rightDepthMap = new HashMap<>();
        Deque<TreeNode> nodeStack = new LinkedList<>();
        Deque<Integer> depthStack = new LinkedList<>();

        nodeStack.push(root);
        // 高度从0开始
        depthStack.push(0);
        int maxDepth = -1;
        while (!nodeStack.isEmpty()) {
            // 节点出栈
            TreeNode curNode = nodeStack.pop();
            Integer curDepth = depthStack.pop();

            if (curNode != null) {
                if (!rightDepthMap.containsKey(curDepth)) {
                    rightDepthMap.put(curDepth, curNode.val);
                }
                if (curDepth > maxDepth) {
                    maxDepth = curDepth;
                }
                // 左子节点入栈
                nodeStack.push(curNode.left);
                // 右子节点入栈
                nodeStack.push(curNode.right);
                // 左右深度+1
                depthStack.push(curDepth + 1);
                depthStack.push(curDepth + 1);
            }
        }
        List<Integer> ans = new ArrayList<>();
        // 遍历
        for (int i = 0; i <= maxDepth; i++) {
            ans.add(rightDepthMap.get(i));
        }

        return ans;
    }

    static class TreeNode {
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
