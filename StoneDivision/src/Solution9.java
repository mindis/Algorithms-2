import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution9 {
  /*
  int[][] image = {
  {1, 1, 1, 1, 1, 1, 1},
  {1, 1, 1, 1, 1, 1, 1},
  {1, 1, 1, 0, 0, 0, 1},
  {1, 1, 1, 0, 0, 0, 1},
  {1, 1, 1, 1, 1, 1, 1},
};*/
  
  static class Point{
    int x;
    int y;
    Point(int x, int y){
      this.x = x;
      this.y = y;
    }
    public String printString(){
      return ("(p: x:"+x + ","+y+")");
    }
  }
  
  public static void main(String[] args) {
    
    int[][] image = {
      {1, 1, 1, 1, 1, 1, 1},
      {1, 1, 1, 1, 1, 1, 1},
      {1, 1, 1, 0, 0, 0, 0},
      {1, 1, 1, 0, 0, 0, 0},
      {1, 1, 1, 1, 1, 1, 1},
    };
    Point[] ret = getRect(image);
    System.out.println(ret[0].printString());
    System.out.println(ret[1].printString());
  }
  
  public static Point[] getRect(int[][] matrix){
    
    Point[] pArr = new Point[2];
  
    for(int i = 0; i < matrix.length; i++){
      //find first point
      if(pArr[0]==null){
          for(int j = 0; j < matrix[0].length;j++){
            if(matrix[i][j]==0){
              pArr[0] = new Point(j, i);
              break;
            }
          }
        }else{
        	break;
        }
     }
    
    
    //find second point
    int i = pArr[0].x; int j = pArr[0].y;
    for(; i < matrix.length;i++){
    	if(pArr[1]==null){
	    	for(; j < matrix[0].length;j++){
		    	if(matrix[i][j]==1){
		    		pArr[1] = new Point(j-1, i-1);
		    		break;
		    	}
	    	}
    	}else{
			break;
		}
    }
    return pArr;
  }
}


/* 
Your previous Markdown content is preserved below:

Imagine we have an image where every pixel is white or black. We’ll represent this image as a simple 2D array (0 = black, 1 = white). The image you get is known to have a single black rectangle on a white background. Write a function that takes in the image and returns the coordinates of the rectangle -- either top-left and bottom-right; or top-left, width, and height.


Here are sample images using JavaScript and Java. Feel free to rewrite in your language of choice:
//Java
int[][] image = {
  {1, 1, 1, 1, 1, 1, 1},
  {1, 1, 1, 1, 1, 1, 1},
  {1, 1, 1, 0, 0, 0, 1},
  {1, 1, 1, 0, 0, 0, 1},
  {1, 1, 1, 1, 1, 1, 1},
};
 */