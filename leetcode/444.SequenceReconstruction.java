/*After reading a few posts on this topic, I guess most of people over-think about this problem.

IMHO, We don't need to implement any graph theory expressively here; rather, it is sufficient to just check if every two adjacent elements also appears adjacently in the sub-sequences. (and of course, some basic boundary checking is also necessary)
*/

    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        if(seqs.length == 0) return false; 
        int[] pos = new int[org.length+1];
        for(int i=0;i<org.length;++i) pos[org[i]] = i;
        boolean[] flags = new boolean[org.length+1];
        int toMatch = org.length-1;
        for(int[] v : seqs) {
            for(int i=0;i<v.length;++i) {
                if(v[i]<=0 || v[i] > org.length)return false;
                if(i==0)continue;
                int x = v[i-1], y = v[i];
                if(pos[x] >= pos[y])return false;
                if(flags[x] == false && pos[x]+1 == pos[y]) {
                    flags[x] = true;
                    --toMatch;
                }
            }
        }
        return toMatch == 0;
    }

