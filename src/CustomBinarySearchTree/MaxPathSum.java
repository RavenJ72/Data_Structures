package CustomBinarySearchTree;

public class MaxPathSum {
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(AbstractBinarySearchTree.Node root) {
        findMaxPathSumHelper(root);
        return maxSum;
    }

    private int findMaxPathSumHelper(AbstractBinarySearchTree.Node node) {
        if (node == null) {
            return 0;
        }

        int leftMax = Math.max(findMaxPathSumHelper(node.leftChild), 0);
        int rightMax = Math.max(findMaxPathSumHelper(node.rightChild), 0);
        int nodeValue = Integer.parseInt(node.value.toString());

        maxSum = Math.max(maxSum, nodeValue + leftMax + rightMax);

        return nodeValue + Math.max(leftMax, rightMax);
    }
}