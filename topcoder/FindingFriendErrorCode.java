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
        Arrays.sort(leaders);
        boolean continueRoomDiffRoomSize = true;
        
        for(int i= 0; i<leaders.length; i++){
            //if friend = 1
            int cur = leaders[i];
            
            
            if(friendPlace == cur){
            	return 1;
            }
            

            
            if(i!=0){
                if(cur - leaders[i-1] == roomSize && continueRoomDiffRoomSize){
                    if(friendPlace<=cur){
                        return 1;
                    } else{
                        continue;
                    }
                } else{
                    continueRoomDiffRoomSize = false;
                }
            }
                       
            if(friendPlace<cur){
                break;
            }
            
        	if(friendPlace>cur){
            	count++;
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
