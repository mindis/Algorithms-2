/*
*case 1: 3 [1, 4, 7, 10, 13], 8: 1
*case 2: 2, [1,2,3,4], 4 :
*case 3: 3, [1,2,4,7]. 6
*case 4: 3 [1, 4, 5, 8], 6: 1
*case 5: 3 [1, 2, 5, 8], 6: 
* gready insert to cove,
continue crime, no good, break crime, always good person
*/
import java.util.*;
public class FindingFriend{
	public int find(int roomSize, int[] leaders, int friendPlace){
        int count = 0;
        if(roomSize == 1 || leaders.length ==1) return 1;
        Arrays.sort(leaders);
        Set<Integer> set = new HashSet<Integer>();
        set.add(friendPlace);
        for(int i= 0; i<leaders.length; i++){
            //if friend = 1 
            if(friendPlace == leaders[i]){
            	return 1;
            }
            set.add(leaders[i]);
        }
        int N = roomSize*leaders.length;
        for(int i=0;i<leaders.length;i++){
            if(friendPlace<leaders[i]) break;
            int cur = 1; int curRoomSize = 1; int curRoom = 0; int friendRoom = i;
            
            //insert friend to curRoom
            if(curRoom==friendRoom){
                if(curRoomSize == roomSize){
                   continue;
                } else {
                    curRoomSize++;
                }
            }
            
            while(cur <=N){
                //check if room full before insertion
                //System.out.print("cur:" +cur);
                if(curRoomSize == roomSize) {
                    curRoom++;
                    curRoomSize=1;
                    if(curRoom>leaders.length-1){
                    	break;
                    }
                }
                if(set.contains(cur)){
                    cur++;
                    continue;
                }
                if(cur < leaders[curRoom]){
                    break;
                } else {
                   // System.out.print("cur:" +cur+ "curRoom"+curRoom);
                    curRoomSize++;
                    cur++;
                   
                }
             }
            System.out.println();
            if(cur == N){
                count++;
                
            }else{
                if(cur < leaders[curRoom]){
                }else{
                    cur = cur + 1;
                }
                boolean token = true;
                for(int j = cur; j<=N;j++){
                    if(!set.contains(j)){
                        token = false;
                        break;
                    }
                }
                if(token) count++;
            }
        }
        
        return count;
    }
}

/*
Method:
find
Parameters:
int, int[], int
Returns:
int
Method signature:
int find(int roomSize, int[] leaders, int friendPlace)
(be sure your method is public)*/
