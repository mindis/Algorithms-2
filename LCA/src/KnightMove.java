import java.util.LinkedList;
import java.util.Queue;

class Position{
	public int x;
	public int y;
	public int level;
	public Position(int x, int y, int level){
		this.x = x;
		this.y = y;
		this.level = level;
	}
	public boolean equals(Position b){
		if(x == b.x && y == b.y){
			return true;
		} else {
			return false;
		}
	}
}

public class KnightMove {
    public static boolean[][] visited;
    
	public static int minSteps(Position start, Position end){
		visited = new boolean[8][8];
		Queue<Position> queue = new LinkedList<Position>();
		queue.add(start);
		while(!queue.isEmpty()){
			
			Position current = queue.poll();
			if(current.x < 0 || current.x > 7 || current.y < 0 || current.y > 7){
				continue;
			}
			int level = current.level;
			if(current.equals(end)){
				return level;
			}
			if(visited[current.x][current.y]){
				continue;
			}
			visited[current.x][current.y] = true;

			queue.add(new Position(current.x - 2, current.y - 1, level + 1));
			queue.add(new Position(current.x - 2, current.y + 1, level + 1));
			queue.add(new Position(current.x - 1, current.y - 2, level + 1));
			queue.add(new Position(current.x - 1, current.y + 2, level + 1));
			queue.add(new Position(current.x + 1, current.y - 2, level + 1));
			queue.add(new Position(current.x + 1, current.y + 2, level + 1));
			queue.add(new Position(current.x + 2, current.y - 1, level + 1));
			queue.add(new Position(current.x + 2, current.y + 1, level + 1));
		}
		return -1;
	}
	
	public static void main(String[] args){
		System.out.println(minSteps(new Position(0,0,0),new Position(1,2,0)));
		System.out.println(minSteps(new Position(0,0,0),new Position(7,7,0)));
		System.out.println(minSteps(new Position(0,0,0),new Position(1,3,0)));
		System.out.println(minSteps(new Position(0,0,0),new Position(1,7,0)));
	}
}
