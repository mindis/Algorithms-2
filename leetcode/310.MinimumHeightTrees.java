/*Gisell 
Reputation:  71
Basically my code starts from the leaf nodes.

For leaf nodes, their degree = 1, which means each of them is only connected to one node.

In our loop, each time we delete the leaf nodes from our graph(just by putting their degrees to 0), and meanwhile we add the new leaf nodes after deleting them(just add their connected nodes with degree as 2) to the queue.

So basically in the end, the nodes in the queue would be connected to no other nodes but each other. They should be the answer.
*/

List<List<Integer>> myGraph = new ArrayList<List<Integer>>();
	List<Integer> res = new ArrayList<Integer>();
	if (n==1) {
		res.add(0);
		return res;
	}
    int[] degree = new int[n];
    for(int i=0; i<n; i++) {
    	myGraph.add(new ArrayList<Integer>());
    }
    for(int i=0; i<edges.length; i++) {
    	myGraph.get(edges[i][0]).add(edges[i][1]);
    	myGraph.get(edges[i][1]).add(edges[i][0]);
    	degree[edges[i][0]]++;
    	degree[edges[i][1]]++;
    }
    Queue<Integer> myQueue = new ArrayDeque<Integer>();
    
    for(int i=0; i<n; i++) 
    	if (degree[i]==0) 
    		return res;
    	else if (degree[i]==1) {
    		myQueue.offer(i);
    	}
    
    while (!myQueue.isEmpty()) {
    	res = new ArrayList<Integer>();
    	int count = myQueue.size();
    	
    	for(int i=0; i<count; i++){
    		int curr = myQueue.poll();
    		res.add(curr);
    		degree[curr]--;
    		for(int k=0; k<myGraph.get(curr).size(); k++) {
    			int next = myGraph.get(curr).get(k);
    			if (degree[next]==0) continue;
    			if (degree[next]==2) {
    				myQueue.offer(next);
    			}
				degree[next]--;
    		}
    	}      	
    }
    return res;
