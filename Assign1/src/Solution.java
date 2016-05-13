import java.util.HashMap;

// Definition for a binary tree node.
public class Solution {
    
    public static void main(String[] arg) {
        int[] preorder = {1, 2, 4, 5, 3, 6, 7};
        int[] inorder = {4, 2, 5, 1, 6, 3, 7};
        buildTree(preorder, inorder);
    }
    
      public static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }
     
    public static TreeNode buildTree(int[] preorder, int[] inorder) { // preorder find root, 在inorder里面找到root的值，可以找到左子树和右子树元素
        int len = preorder.length;
        if (len < 1) return null;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < len; i++) {
            map.put(inorder[i], i);
        }
        return buildSubTree(preorder, 0, 0, len - 1, map);
    }
    private static TreeNode buildSubTree(int[] preorder, int preCurr, int inStart, int inEnd, HashMap<Integer, Integer> map) {
        TreeNode root = new TreeNode(preorder[preCurr]);
        if (inStart < inEnd) {
            int mid = map.get(preorder[preCurr]);
            if (mid > inStart) {
                root.left = buildSubTree(preorder, preCurr + 1, inStart, mid - 1, map);
            }
            if (mid < inEnd) {
                root.right = buildSubTree(preorder, preCurr + mid - inStart + 1, mid + 1, inEnd, map);
            }
        }
        return root;
    }
}