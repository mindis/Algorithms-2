import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class PrimeXOR {

	/**
	 * Test case:
	 * Input:
	   1
	   3
	   3511 3671 4153
	   
	   Output:
	   4
	   
	   1
		3
		3511 3671 4153
		
		992
		3511
		3671
		5081
		4153
		7566
		7790
		4
	 * @param args
	 */
	static long mod = 1000000007;
	static boolean[] notPrimes = new boolean[10001];// store primes between 3500-4500
    public static void main(String[] args) {
    	generatePrimeNumbers(10000);
    	Scanner scan = new Scanner(System.in);
    	int q = Integer.parseInt(scan.nextLine());
    	for(int i = 0; i < q; i++){
    		int n = Integer.parseInt(scan.nextLine());
    		String[] arrS = scan.nextLine().split(" ");
    		int[] arr = new int[n];
    		for(int j = 0; j < n; j++){
    			arr[j] = Integer.parseInt(arrS[j]);
    		}
    		/**
    		 * 3500 ~ 4500
    		 * 3500, 3501, 3502, 3503 ...
    		 * building from bottom up
    		 * total:(1)
    		 * 3500 
    		 * 
    		 * total:(2 = 1 + 1)
    		 * 3500, 3501
    		 * 3501
    		 * 
    		 * total:(4 = 1 + 2 + 1)
    		 * 3500, 3502
    		 * 3500, 3501, 3502
    		 * 3501, 3502
    		 * 3502
    		 * 
    		 * total: (8 = 1 + 2 + 4 + 1)
    		 * 3500, 3503
    		 * 3500, 3501, 3503
    		 * 3501, 3053
    		 * 3500, 3502, 3503
    		 * 3500, 3501, 3502, 3503
    		 * 3501, 3502, 3503
    		 * 3502, 3503
    		 * 3503
    		 */
    		
    		/**
    		 * 3500 ~ 4500
    		 * 3500X3, 3501X5, 3502X4, 3503X4 ...
    		 * building from bottom up
    		 * total:(1)
    		 * 3500 x 2
    		 * 
    		 * total:(2 = 1 + 1)
    		 * 3500, 3501 x2x(5/2+1)
    		 * 3501 x(5/2+1)
    		 * 
    		 * total:(4 = 1 + 2 + 1)
    		 * 3500, 3502 x2x(4/2)
    		 * 3500, 3501, 3502 x2x(5/2+1)x(4/2)
    		 * 3501, 3502 x(5/2+1)x(4/2)
    		 * 3502 (4/2)
    		 * 
    		 * total: (8 = 1 + 2 + 4 + 1)
    		 * 3500, 3503
    		 * 3500, 3501, 3503
    		 * 3501, 3053
    		 * 3500, 3502, 3503
    		 * 3500, 3501, 3502, 3503
    		 * 3501, 3502, 3503
    		 * 3502, 3503
    		 * 3503
    		 */
    		
    		/**
    		 * worst case: 1000 * n, because numbers are from 3500-4500
    		 * every time new number came in we just have existing Integer->freq * new number's freq
    		 */
    		/**
    		 * sort and record frequency, use minHeap with node (value, freq)
    		 * or a sorted map search how to use!
    		 * SortedMap<Integer, Integer> map = new SortedMap<Intger, Integer>();
    		 * or we have a frequency map and a sorted list:
    		 */
    		
    		/**
    		 * sort the arr
    		 */
    		Arrays.sort(arr);
    		/**
    		 * Get the frequency map, get a new ArrayList without duplicates
    		 */
    		Map<Integer, Integer> elementMap = new HashMap<Integer, Integer>();
    		List<Integer> list = new ArrayList<Integer>(); 
    		for(int j = 0; j < n; j++){
    			if(elementMap.containsKey(arr[j])){
    				elementMap.put(arr[j], elementMap.get(arr[j])+1);
    			}else{
    				elementMap.put(arr[j], 1);
    				list.add(arr[j]);
    			}
    		}
    		
    		/**
    		 * traverse through the sorted, not duplicated ArrayList and updating a new HashMap
    		 * which has (Combined Number)->freq
    		 */
    		Map<Integer, Long> resultMap = new HashMap<Integer, Long>();
    		for(int j = 0; j < list.size(); j++){
    			int curVal = list.get(j);
    			int curFreq = elementMap.get(curVal);
    			/**
    			 * Have a second map to prevent concurrent modification
    			 */
    			Map<Integer, Long> newResultMap = new HashMap<Integer, Long>();
    			newResultMap.putAll(resultMap);
    			/**
    			 * for each of previously existing vals, append them
    			 */
    			for(Entry<Integer, Long> entry: resultMap.entrySet()){
    				int entryVal = entry.getKey();
    				long entryFreq = entry.getValue();
    				int combinedVal = curVal ^ entryVal;
    				long combinedFreq = curFreq * entryFreq;
    				if(combinedFreq>=mod) combinedFreq %= mod;
    				if(newResultMap.containsKey(combinedVal)){
    					long combinedFreq2 = newResultMap.get(combinedVal)+combinedFreq;
    					if(combinedFreq2>=mod) combinedFreq2 %= mod; 
    					newResultMap.put(combinedVal, combinedFreq2);
    				}else{
    					newResultMap.put(combinedVal, combinedFreq);
    				}
    			}
    			/**
    			 * append current val
    			 */
    			if(newResultMap.containsKey(curVal)){
    				long combinedFreq = newResultMap.get(curVal)+curFreq;
    				if(combinedFreq>=mod) combinedFreq %= mod;
					newResultMap.put(curVal, combinedFreq);
				}else{
					newResultMap.put(curVal, (long) curFreq);
				}
    			/**
    			 * rotate map
    			 */
    			resultMap = newResultMap;
    		}
    		
    		/**
    		 * traverse through the hashmap once and add the freq together if its key is a prime number
    		 */
    		long result = 0;
    		for(Entry<Integer, Long> entry: resultMap.entrySet()){
    			System.out.println(entry.getKey());
    			if(!notPrimes[entry.getKey()]){
    				result += entry.getValue();
    			}
    			if(result>=mod) result %= mod;
    		}
    		System.out.println(result);
    	}
    }
    
	/**
	 * Use Sieve of Eratosthenes to generate Prime numbers between 3500-4500
	 * test: generatePrimeNumbers(4500);
	 */
    static void generatePrimeNumbers(int bound){
    	notPrimes[0] = true; notPrimes[1] = true;
    	int sqrt = (int) Math.sqrt(bound);
    	for(int i = 2; i <= sqrt; i++){
    		if(!notPrimes[i]){
    			for(int j = i * i; j <= bound; j += i){
    				notPrimes[j] = true;
    			}
    		}
    	}
    }
}