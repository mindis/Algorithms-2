


// This is the text editor interface. 
// Anything you type or change here will be seen by the other person in real time.
//Queue, getMax()
/*e.g. 4,5,1,2,1,*/
/* 5,4,2,1*/
/* 1,1,1,2*/
//ArrayList, store queue element
//HashMap<Value,Freq>
//MaxHeap
//Node
/* 4,5,1,2,1*/
/* 4, 
   5,4
   5,4,1
   5,4,2,1*/
 
//LinkedList Descending 
/*
    4
    4->5->1->2
//  5->5->2->2->1
  
    4

    5->2
    remove(4) 5->2
    remove(5) 2*/
    /* 5->1*/
    
    
    
        
class CustomizedQueue{
    private List<Integer> list = new ArrayList<Integer>();
    private Queue<Integer> queue = new LinkedList<Integer>();
    public push(int in){
        queue.push(in);
        if(list.size() ==0) list.add(in);
     
        for(int i = list.size()-1; i>=0;i--){
            if(list.get(i) < in){//now 4, replace 4 with 5
                list.set(i,in);
                break;
            }else if(list.get(i)>in){// now 5 and 2 comes in
                list.add(i,in);
                break;
            }
        }      
    }
    
    public int pop(){
        int cur = queue.poll();
        if(list.size()!=0){
            if(list.get(0)==cur){
                list.remove(0);
            }
        }
        return cur;
    }
    
    public int getMax(){
        if(list.size() == 0) return null;
        return list.get(0);
    }
}