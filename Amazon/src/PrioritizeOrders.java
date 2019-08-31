import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 
 * @author mukhe
Amazon is planning to release a new order prioritization algorithm to optimize fulfilling Prime deliveries on time. All orders (Prime or otherwise) are given an alphanumeric ID code. However, Prime orders are given additional metadata that consists of a space delimited list of lowercase English letters, whereas non-Prime orders are given metadata that consists only Of a space delimited string Of positive integers. Each order is therefore defined as their alphanumeric id code, followed by a space, followed by their space delimited metadata. 

You have been tasked with sorting a list of all orders in the order queue to assist in prioritization of fulfillment. They should be sorted according to the following order: 
1. The Prime orders should be returned first, sorted by lexicographic sort of the alphabetic metadata. 
2. Only in the case of ties, the identifier should be used as a backup sort. 
3. The remaining non-Prime orders must all come after, in the original order they were given in the input. 
Write a function or method to sort the orders according to this system. 

Input:
The input to the function/method consists of two arguments: 
numOrders, an integer representing the number of orders. 
orderl_ist, a list Of strings representing all Of the orders.

Output: Return a list of strings representing the correctly prioritized orders. 

Note The order identifier consists of only lower case English character and numbers.

Examples
Input:
numOrders = 6
orderList 
[Zid 93 12]
[fp kindle book]
[IOa echo show]
[17g 12 256] 
[abl kindle book] 
[125 echo dot second generation] 

Output: 
[125 echo dot second generation]
[IOa echo show] [abl kindle book]
[fp kindle book] 
[Zid 93 12] 
[17g 12 256] 

 */
public class PrioritizeOrders {

	public List<String> prioritizeOrders(String[] orders) {
		Queue<Order> primeOrders = new PriorityQueue<>(5, new Comparator<Order>() {
			@Override
			public int compare(Order o1, Order o2) {
				if(o1.metadata.equals(o2.metadata)) {
					if(o1.id.compareTo(o2.id) < 0)
						return 1;
					else
						return -1;
				}
				if(o1.metadata.compareTo(o2.metadata) < 0)
					return 1;
				else
					return -1;
			}
		});
		List<String> result = new LinkedList<>();
		for(String order : orders) {
			String[] orderParts = order.split(" ", 2);
			String id = orderParts[0];
			String metaData = orderParts[1];
			if(metaData.length() > 0 && Character.isAlphabetic(metaData.charAt(0))) {
				Order po = new Order(id, metaData);
				primeOrders.offer(po);
			}
			else {
				result.add(order);
			}
		}
		int size = primeOrders.size();
		for(int i = 0; i < size; i++) {
			Order cur = primeOrders.poll();
			result.add(0, cur.id + " " + cur.metadata);
		}
		return result;
	}
	public class Order {
		String id;
		String metadata;
		public Order(String id, String metadata) {
			this.id = id;
			this.metadata = metadata;
		}
	}
	public static void main(String[] args) {
		PrioritizeOrders po = new PrioritizeOrders();
		System.out.println(po.prioritizeOrders(new String[] {"Zid 93 12","fp kindle book","IOa echo show","17g 12 256","abl kindle book","125 echo dot second generation"}));
	}

}
