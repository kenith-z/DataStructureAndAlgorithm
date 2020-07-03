package xyz.hcworld.leetcode;

/**
 * 力扣108. 将有序数组转换为二叉搜索树
 *
 * @ClassName: TransformOrderedArrayIntoBinarySearchTree
 * @Author: 张红尘
 * @Date: 2020/7/3 21:03
 * @Version： 1.0
 */
public class TransformOrderedArrayIntoBinarySearchTree {
    /**
     * 图解连接
     * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/solution/you-xu-shu-zu-zhuan-er-cha-shu-by-kenith-zhang/
     * @param nums 要做树的有序数组
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode temp=go(0, nums.length - 1, nums);
        return temp;
    }

    /**
     * 递归主体
     */
    public TreeNode go(int left, int right, int[] nums) {
        if (left > right) {
            return null;
        }
        int median = (left + right + 1) >> 1;
        //median = (left + right) / 2;
        TreeNode root = new TreeNode(nums[median]);
        //进入左子树的递归
        root.left = go(left, median - 1, nums);
        //进入右子树的递归
        root.right = go(median + 1, right, nums);
        return root;
    }

    public static void main(String[] args) {
        TransformOrderedArrayIntoBinarySearchTree solution = new TransformOrderedArrayIntoBinarySearchTree();
        int[] nums = {-10, -3, 0, 5, 9};
        System.out.println(solution.sortedArrayToBST(nums).toString());
        ;
    }
}

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
