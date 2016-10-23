public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<Integer>();
        if(p==null || s == null || p.length() > s.length()) return list;
        int[] freq = new int[26];
        int[] cur = new int[26];
        p = p.toLowerCase();
        s = s.toLowerCase();
        int pLen = p.length();
        for(int i = 0; i < pLen; i++){
            freq[p.charAt(i)-'a']++;
        }
        for(int i=0;i<pLen;i++){
            cur[s.charAt(i)-'a']++;
        }
        if(checkMatch(freq, cur)) list.add(0);
        for(int i=pLen;i<s.length();i++){
            int next = s.charAt(i)-'a';
            cur[next]++;
            int remove = s.charAt(i-pLen)-'a';
            cur[remove]--;
            if(cur[next] == freq[next] && cur[remove] == freq[remove]){
                if(checkMatch(freq, cur)) list.add(i-(pLen-1));
            }
        }
        return list;
    }
    
    private boolean checkMatch(int[] freq, int[] cur){
        for(int i=0;i<26;i++){
            if(freq[i]!=cur[i]) return false;
        }
        return true;
    }
    
}
