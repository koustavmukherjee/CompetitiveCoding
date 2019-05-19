/**
 * 
 * @author mukhe
	Given a binary tree, find the subtree with maximum average. Return the root of the subtree.
	Example Given a binary tree:
	
	
	       2 ​ 
	     /   \ 
	  -2       14 
	  / \      / \ 
	-1  1     5  -1
	return the node 14.
 
 */
public class MaximumAverageNode {
	static int maxAvgNode = 0;
	static double maxAvg = 0.0;
	public static int[] maxAverageNode(Node root) {
		if(root == null) return new int[] {0, 0};
		else if(root.left == null && root.right == null) {
			if(root.val > maxAvg) {
				maxAvg = root.val;
				maxAvgNode = root.val;
			}
			return new int[] {root.val, 1};
		}
		else {
			int[] left = maxAverageNode(root.left);
			int[] right = maxAverageNode(root.right);
			float newAvg = (root.val + left[0] + right[0]) / (1 + left[1] + right[1]);
			if(newAvg > maxAvg) {
				maxAvg = newAvg;
				maxAvgNode = root.val;
			}
			return new int[] { (root.val + left[0] + right[0]) , (1 + left[1] + right[1]) };
		}
	}
	
	public static void main(String[] args) {
		Node root = new Node();
		root.val = 2;
		
		root.left = new Node();
		root.right = new Node();
		root.left.val = -2;
		root.right.val = 14;
		
		root.left.left = new Node();
		root.left.right = new Node();
		root.left.left.val = -1;
		root.left.right.val = 1;
		
		root.right.left = new Node();
		root.right.right = new Node();
		root.right.left.val = 5;
		root.right.right.val = -1;
		
		maxAverageNode(root);
		
		System.out.println(maxAvgNode);
		System.out.println(maxAvg);

	}

}
class Node {
	Node left;
	Node right;
	int val;
}