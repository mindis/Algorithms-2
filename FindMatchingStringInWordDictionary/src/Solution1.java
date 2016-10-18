public class Solution1 {
    public int minCost(int[][] costs) {
        //{[15, 5, 2], [1, 8, 20], [5, 10, 15]...}
        int len = costs.length;
        //house 1, house 2, house 3, house 4
        //cost[ithHouse][0thColor] = cost[i-1thHouse][1thColor] + cost[i-1thHouse][2ndColor]
        //cost[ithHouse][1thColor] = cost[i-1thHouse][0thColor] + cost[i-1thHouse][2ndColor];
        //cost[ithHouse][2thColor] = cost[i-1thHouse][0thColor] + cost[i-1thHouse][1ndColor];
        
        int[][] cost = new int[len+1][3];
        for(int i = 1; i <= costs.length; i++){
            cost[i][0] = Math.min(cost[i-1][1], cost[i-1][2]) + costs[i-1][0];//current
            cost[i][1] = Math.min(cost[i-1][0], cost[i-1][2]) + costs[i-1][1];
            cost[i][2] = Math.min(cost[i-1][0], cost[i-1][1]) + costs[i-1][2];
        }
        return Math.min(Math.min(cost[len+1][0], cost[len+1][1]), cost[len+1][2]);
    }
}