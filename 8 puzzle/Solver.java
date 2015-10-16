import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
	private int totalMoves;
	private boolean solvable=false;
	private boolean twinsolvable=false;
	private Board initBoard=null,twinBoard=null;
	private MinPQ<Node> q=new MinPQ<Node>();
	private Node solvednode=null;
	private MinPQ<Node> twinq=new MinPQ<Node>();
	
	//node class begin
	private class Node implements Comparable<Node>{
		
		int moves;
		Board currentBoard;
		Node parent=null;
		
		public Node(Board current,Node previous) {
			this.parent= previous;
			currentBoard=current;
			if(previous==null){
				this.moves=0;
			}
			else{
				this.moves=previous.moves+1;
			}
		}

		@Override
		public int compareTo(Node o) {
			int thispriority = this.currentBoard.manhattan()+this.moves;
			int thatpriorirty= o.currentBoard.manhattan()+o.moves;
			// TODO Auto-generated method stub
			return thispriority-thatpriorirty;
		}
	}
	//node class end
	
	
	public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
	{
	initBoard=initial;	
	twinBoard=initBoard.twin();
	q.insert(new Node(initBoard, null));
	twinq.insert(new Node(twinBoard, null));
	while (!solvable && !twinsolvable) {
		solvable=solve(q);
		twinsolvable=solve(twinq);
		
	}
	
		
	}
	
	private boolean solve(MinPQ<Node> q){
		if(q.isEmpty()) return false;
		Node current= q.delMin();
		if(current==null) return false;
		if(current.currentBoard.isGoal()) {
			solvednode= current;
			totalMoves=current.moves;
			return true;
		}
		for (Board neighbour: current.currentBoard.neighbors()) {
			if(current.parent==null || !neighbour.equals(current.parent.currentBoard)){
				q.insert(new Node(neighbour, current));
			
			}
		}
		return false;
	
	}
	
	
	
	public boolean isSolvable()            // is the initial board solvable?
	{
		return solvable;
		
	}
	public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
	{
		if(!solvable) return -1;
		return totalMoves;
	}
	public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
	{
		if (!solvable) {
			return null;
		}
		
		Stack<Board> solution = new Stack<Board>();
		Node current = solvednode;
		solution.push(current.currentBoard);
		while (current.parent!=null) {
			solution.push(current.parent.currentBoard);
			current=current.parent;
		}
		
		return solution;
		
	}    
	public static void main(String[] args) // solve a slider puzzle (given below)
	{}
}
