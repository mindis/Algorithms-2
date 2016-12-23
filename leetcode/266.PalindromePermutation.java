/*

Given a string, determine if a permutation of the string could form a palindrome.

For example,
"code" -> False, "aab" -> True, "carerac" -> True.

Hint:

Consider the palindromes of odd vs even length. What difference do you notice?
Count the frequency of each character.
If each character occurs even number of times, then it must be a palindrome. How about character which occurs odd number of times?
*/

/*

The idea is to iterate over string, adding current character to set if set doesn't contain that character, or removing current character from set if set contains it.
When the iteration is finished, just return set.size()==0 || set.size()==1.

set.size()==0 corresponds to the situation when there are even number of any character in the string, and
set.size()==1 corresponsds to the fact that there are even number of any character except one.
*/

public class Solution {
    public boolean canPermutePalindrome(String s) {
        Set<Character> set=new HashSet<Character>();
        for(int i=0; i<s.length(); ++i){
            if (!set.contains(s.charAt(i)))
                set.add(s.charAt(i));
            else 
                set.remove(s.charAt(i));
        }
        return set.size()==0 || set.size()==1;
    }
}

public boolean canPermutePalindrome(String s) {
    Set<Character> set=new HashSet<Character>();
    for(int i=0; i<s.length(); ++i)
        if (!set.add(s.charAt(i)))
            set.remove(s.charAt(i));
    return set.size()<=1;
}

