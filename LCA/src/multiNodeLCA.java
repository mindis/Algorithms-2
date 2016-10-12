
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.List;

class TreeNodeMulti {
	public int value;
	public List<TreeNodeMulti> children;
}

//public class TreeNodeMulti {
//	public int value;
//	public List<TreeNodeMulti> children;
//}

public class multiNodeLCA{
	public TreeNodeMulti lowestCommonAncestor(TreeNodeMulti root, TreeNodeMulti a, TreeNodeMulti b){
        if (root == null || root == a || root == b) {
            return root;
        }
      
        TreeNodeMulti n0 = null;
        TreeNodeMulti n1 = null;
        
        for(int i = 0; i < root.children.size(); i ++){
        	  // Divide
        	TreeNodeMulti temp = lowestCommonAncestor(root.children.get(i), a, b);
        	if(temp != null){
        		if(n0 == null) {
        			n0 = temp;
        		} else {
        			n1 = temp;
        			break;
        		}
        	}
        }
        // Conquer
        if (n0 != null && n1 != null) {
            return root;
        } 
        if (n0 != null) {
            return n0;
        }
        if (n1 != null) {
            return n1;
        }
        return null;
	    }
	}