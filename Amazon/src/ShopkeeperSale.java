import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author mukhe
 *
Shopkeeper Sale
A shopkeeper has a sale to complete and has arranged the items being sold in a list. Starting from the left, the shop keeper rings up each item at its full price less the price of the first lower or equally priced item to its right. If there is no item to the right that costs less than or equal to the current item's price the current item is sold at full price.

For example, assume there are items priced [2, 3, 1, 2, 4, 2].
The first and second items would each be discounted by 1 unit, the first equal or lower price to the right.
The item priced 1 unit would sell at a full price.
The next item, at 2 units, would be discounted 2 units as would the 4 unit item.
The sixth and final item must be purchased at full price.
The total cost is 1+2+1+0+2+2 = 8 units.

Print total cost of all items on the first line.
On the second line print a space separated list of integers representing the indexes of the non- discounted items in ascending index order.

1 <= size(prices) <= 100000
1 <= prices <= 1000000

Output:
8
2 5

Input 2: [5,1,3,4,6,2]
Output:
14
1 5

Input 3: [1,3,3,2,5]
Output:
9
0 3 4
 */
public class ShopkeeperSale {

	List<Integer> nonDisc = new LinkedList<>();
	public int findSalePrice(int[] arr) {
		int res = 0;
		nonDisc.clear();
		for(int i = 0; i < arr.length; i++) {
			int j = i+1;
			for(; j < arr.length && arr[j] > arr[i]; j++);
			if(j < arr.length)
				res += arr[i] - arr[j];
			else {
				res += arr[i];
				nonDisc.add(i);
			}
		}
		return res;
	}
	public static void main(String[] args) {
		ShopkeeperSale ss = new ShopkeeperSale();
		System.out.println(ss.findSalePrice(new int[] {2, 3, 1, 2, 4, 2}));
		System.out.println(ss.nonDisc);
		System.out.println(ss.findSalePrice(new int[] {5,1,3,4,6,2}));
		System.out.println(ss.nonDisc);
		System.out.println(ss.findSalePrice(new int[] {1,3,3,2,5}));
		System.out.println(ss.nonDisc);
	}

}
