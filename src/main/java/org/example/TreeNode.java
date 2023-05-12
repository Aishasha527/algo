package org.example;

import java.util.LinkedList;
import java.util.List;

/**
 * leetcode 99
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<TreeNode> treeNodes = solution.generateTrees(3);
        System.out.println(treeNodes);
    }
}


class Solution {
    public List<TreeNode> generateTrees(int n) {
        return getAns(1, n);
    }
    public List<TreeNode> getAns(int start, int end) {
        List<TreeNode> ans = new LinkedList<>();
        if (start > end) {
            ans.add(null);
            return ans;
        }
        for(int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = getAns(start, i - 1);
            List<TreeNode> rightTrees = getAns(i + 1, end);
            for(TreeNode leftNode: leftTrees) {
                for(TreeNode rightNode: rightTrees) {
                    TreeNode head = new TreeNode(i);
                    head.left = leftNode;
                    head.right = rightNode;
                    ans.add(head);
                }
            }
        }

        return ans;
    }
}