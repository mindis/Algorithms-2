import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrintInIndexOrder {
	/**
	 * Running time: O(n) Space: O(n) one hashMap, one List
	 * @param args
	 */
	public static void main(String[] args){
		int[] arr = new int[]{3, 1, 1, 3, 2, 5, 1};
		int[] ret = sortInIndexOrder(arr);
		for(int i = 0; i < ret.length; i++){
			System.out.print(ret[i]+" ");
		}
	}
	public static int[] sortInIndexOrder(int[] arr){
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < arr.length; i++){
			if(map.containsKey(arr[i])){
				map.put(arr[i], map.get(arr[i])+1);
			}else{
				map.put(arr[i], 1);
				list.add(arr[i]);
			}
		}
		int count = 0;
		for(int i = 0; i < list.size(); i++){
			int curV = list.get(i);
			int curN = map.get(curV);
			for(int j = 0; j < curN; j++){
				arr[count++] = curV;
			}
		}
		return arr;
	}
}
