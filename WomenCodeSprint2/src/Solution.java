class Solution {
    /**
     * @param x: An integer
     * @return: The sqrt of x
     */
    public double sqrt(int x) {
        // find the last number which square of it <= x
        double start = 1, end = x;
        while (start + 1 < end) {
            double mid = start + (end - start) / 2;
            if (Math.abs(mid * mid - x) <= 0.000000001) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (end * end <= x) {
            return end;
        }
        return start;
    }
}