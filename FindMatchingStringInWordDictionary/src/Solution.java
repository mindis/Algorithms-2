import java.util.List;

/*Given a String and a dictionary, 
 * return all words in dictionary that are using some letters contained in the
 * given string.
 * e.g. Given String Aabdtc and dictionary contains the following:
 * {Bat,dag,cad,ab,at,date,calos}
 * you should return {Bat,cad,ab,at}
 */
public class Solution {
	class TrieNode {

	    // R links to node children
	    private TrieNode[] links;

	    private final int R = 26;

	    private boolean isEnd;

	    public TrieNode() {
	        links = new TrieNode[R];
	    }

	    public boolean containsKey(char ch) {
	        return links[ch -'a'] != null;
	    }
	    public TrieNode get(char ch) {
	        return links[ch -'a'];
	    }
	    public void put(char ch, TrieNode node) {
	        links[ch -'a'] = node;
	    }
	    public void setEnd() {
	        isEnd = true;
	    }
	    public boolean isEnd() {
	        return isEnd;
	    }
	}
	class Trie {
	    private TrieNode root;

	    public Trie() {
	        root = new TrieNode();
	    }

	    // Inserts a word into the trie.
	    public void insert(String word) {
	        TrieNode node = root;
	        for (int i = 0; i < word.length(); i++) {
	            char currentChar = word.charAt(i);
	            if (!node.containsKey(currentChar)) {
	                node.put(currentChar, new TrieNode());
	            }
	            node = node.get(currentChar);
	        }
	        node.setEnd();
	    }
	}
	
//	public List<String> find() {
		//sort each word, store in TrieTreeNode
		//sort substring, find
//	}
	
	public static void main(String[] args){
		String input = "Aabdtc";
		//{Bat,dag,cad,ab,at,date,calos}
		//{Bat,cad,ab,at}
	}

}
