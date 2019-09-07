import java.util.Stack;

/**
 * 
 * @author mukhe
Given x1 -> x2 ->x3…-> y3 -> y2 -> y1
Reorder it to x1 -> y1 -> x2 -> y2 -> x3 -> y3 …..

e.g.
Given 1 -> 2 -> 3 -> 4 -> 5
Return 1 -> 5 -> 2 -> 4 -> 3

 */
public class ReorderLinkedList {
	public LinkedNode reorder(LinkedNode head) {
		if(head == null)
			return head;
		LinkedNode ptr1 = head;
		LinkedNode ptr2 = head.next;
		while(ptr2 != null) {
			ptr1 = ptr1.next;
			if(ptr2.next == null)
				break;
			ptr2 = ptr2.next.next;
			
		}
		Stack<LinkedNode> stack = new Stack<>();
		LinkedNode mid = ptr1;
		while(ptr1 != null) {
			stack.push(ptr1);
			ptr1 = ptr1.next;
		}
		LinkedNode start = head;
		while(start != mid && !stack.isEmpty()) {
			LinkedNode next = stack.pop();
			LinkedNode nextAfterStart = start.next;
			start.next = next;
			next.next = nextAfterStart;
			start = nextAfterStart;
		}
		if(!stack.isEmpty())
			start.next = stack.pop();
		start.next = null;
		return head;
	}
	public static void main(String[] args) {
		ReorderLinkedList rll = new ReorderLinkedList();
		LinkedNode one = new LinkedNode(1);
		one.next = new LinkedNode(2);
		one.next.next = new LinkedNode(3);
		one.next.next.next = new LinkedNode(4);
		one.next.next.next.next = new LinkedNode(5);
		//one.next.next.next.next.next = new LinkedNode(6);
		rll.printNode(rll.reorder(one));
	}
	public void printNode(LinkedNode node) {
		System.out.println();
		while(node != null) {
			System.out.print(node.val + " ");
			node = node.next;
		}
		System.out.println();
	}
}
class LinkedNode {
	int val;
	LinkedNode next;
	public LinkedNode(int val) {
		this.val = val;
	}
}