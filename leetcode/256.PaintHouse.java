public class Solution {
    public int minCost(int[][] costs) {
        //{[15, 5, 2], [1, 8, 20], [5, 10, 15]...}
        // int len = costs.length;
        //house 1, house 2, house 3, house 4
        //cost[ithHouse][0thColor] = cost[i-1thHouse][1thColor] + cost[i-1thHouse][2ndColor]
        //cost[ithHouse][1thColor] = cost[i-1thHouse][0thColor] + cost[i-1thHouse][2ndColor];
        //cost[ithHouse][2thColor] = cost[i-1thHouse][0thColor] + cost[i-1thHouse][1ndColor];
        
        // int[][] cost = new int[len+1][3];
        // for(int i = 1; i <= costs.length; i++){
        //     cost[i][0] = Math.min(cost[i-1][1], cost[i-1][2]) + costs[i-1][0];//current
        //     cost[i][1] = Math.min(cost[i-1][0], cost[i-1][2]) + costs[i-1][1];
        //     cost[i][2] = Math.min(cost[i-1][0], cost[i-1][1]) + costs[i-1][2];
        // }
        // return Math.min(Math.min(cost[len][0], cost[len][1]), cost[len][2]);
        
        if(costs== null || costs.length == 0) return 0;
        int len = costs.length;
        int cost0=costs[0][0];
        int cost1=costs[0][1];
        int cost2=costs[0][2];
        for(int i = 1; i < costs.length; i++){
            int cur0 = Math.min(cost1, cost2) + costs[i][0];//current
            int cur1 = Math.min(cost0, cost2) + costs[i][1];
            int cur2 = Math.min(cost0, cost1) + costs[i][2];
            cost0 = cur0;
            cost1 = cur1;
            cost2 = cur2;
        }
        return Math.min(Math.min(cost0,cost1),cost2);
        
    }
}
