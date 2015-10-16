

public class Board {
	private int[][] board;
	private int N;
	private int zi,zj;
	public Board(int[][] blocks)           // construct a board from an N-by-N array of blocks
	 {										// (where blocks[i][j] = block in row i, column j)	
	 N=blocks.length;
//	 System.out.println(N);
	 board= new int[N][N];
	 for (int i = 0; i < blocks.length; i++) {
		for (int j = 0; j < blocks.length; j++) {
			if(blocks[i][j]==0){
				zi=i;zj=j;
			}
			board[i][j]= blocks[i][j];
			}
		}
	 }
	 public int dimension()                 // board dimension N
	 {
		return N;
	 }
	 public int hamming()                   // number of blocks out of place
	 {
		 
		 int iter=N*N-1;
		 int outOfOrder=0;
			for (int i = 0; i < iter; i++) {
				int r= i/N;
				int c= i%N;
				if(board[r][c]-1!=i)  outOfOrder++;
				
			}
			return outOfOrder;
		
	 }
	 public int manhattan()                 // sum of Manhattan distances between blocks and goal
	 {
		int distance=0;
		 for (int i = 0; i < N*N; i++) {
			int r=i/N;
			int c=i%N;
			int element=board[r][c];
			if(element==0) continue;
			if(element-1==i){continue;}
			else {
				//System.out.println("out of order\t"+r+"\t"+c);
				int correctRow= (element-1)/N;
				int correctCol= (element-1)%N;
				int horizontal= Math.abs(correctCol-c);
				int vertical = Math.abs(correctRow-r);
				distance+=(horizontal+vertical);
			}
			
		}
		return distance;
	 }
	 public boolean isGoal()                // is this board the goal board?
	 {
		 int iter=N*N-1;
		for (int i = 0; i < iter; i++) {
			int r= i/N;
			int c= i%N;
			if(board[r][c]-1!=i) return false;
			
		}
		if(board[N-1][N-1]==0) return true;
		return true;
	}
	 public Board twin()                    // a board that is obtained by exchanging any pair of blocks
	 {
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length-1; j++) {
				
				if(board[i][j]!=0 && board[i][j+1]!=0){
					int temp=board[i][j];
					board[i][j]=board[i][j+1];
					board[i][j+1]=temp;
					Board twinBoard = new Board(this.board);
					// swapping back
					temp=board[i][j];
					board[i][j]=board[i][j+1];
					board[i][j+1]=temp;
					 return twinBoard;
				}
			}
		}
		return null;
	 }
	 public boolean equals(Object y)        // does this board equal y?
	 {	
		 if (y == this) return true;
	     if (y == null) return false;
	     if (y.getClass() != this.getClass()) return false;
		 Board that = (Board)y;
		 if(this.dimension()!=that.dimension())return false;
		 
		 return this.toString().equals(that.toString());
	}
	 private void swap(int a, int b, int c, int d) {
		 int temp=board[a][b];
		 board[a][b]=board[c][d];
		 board[c][d]=temp;
		
	}
	 public Iterable<Board> neighbors()     // all neighboring boards
	 {
		 java.util.Stack<Board> neighbours = new java.util.Stack<Board>();
		 
		 if(zi!=0 ){
			 swap(zi,zj,zi-1,zj);
			 neighbours.push(new Board(board));
			 swap(zi,zj,zi-1,zj);
		 }
		 if(zi!=N-1){
			 swap(zi,zj,zi+1,zj);
			 neighbours.push(new Board(board));
			 swap(zi,zj,zi+1,zj);
		 }
		 if(zj!=0){
			 swap(zi,zj,zi,zj-1);
			 neighbours.push(new Board(board));
			 swap(zi,zj,zi,zj-1);
		 }
		 if(zj!=N-1){
			 swap(zi,zj,zi,zj+1);
			 neighbours.push(new Board(board));
			 swap(zi,zj,zi,zj+1);
		 }
		 
		 
		 return neighbours;
		
	 }
	 public String toString() {
		    StringBuilder s = new StringBuilder();
		    s.append(N + "\n");
		    for (int i = 0; i < N; i++) {
		        for (int j = 0; j < N; j++) {
		            s.append(String.format("%2d ", board[i][j]));
		        }
		        s.append("\n");
		    }
		    return s.toString();
		
	 }
	

	 public static void main(String[] args) // unit tests (not graded)
	 {
//		 int n=3;
//		 int[][] a= new int[n][n];
//		 for (int i = 0; i < n*n; i++) {
//				int r= i/n;
//				int c = i%n;
//					a[r][c]=i+1; 
//					}
//				
//		// System.out.println(a);
//		 int[][] a2= new int[n][n];
//		 for (int i = 0; i < n*n; i++) {
//				int r= i/n;
//				int c = i%n;
//					a2[r][c]=i+1; 
//					}
//		 a[2][2]=0;
////		 a[2][2]=0;a[2][1]=7;
////		 a[0][2]=5;
//		
//		 //Board b = new Board(a);Board b2 = new Board(a2);
//		 System.out.println(b.toString());
//		 Iterable<Board> neighbours= b.neighbors();
//		 for (Board board : neighbours) {
//			System.out.println(board);
//		}
////		 System.out.println("hamming\t"+ b.hamming());
////		 System.out.println("manhattan\t"+ b.manhattan());
////		 System.out.println(b.twin());
//	/*	 System.out.println(b.equals(b2));
//		 
//		 System.out.println(b.toString());*/
	 }
}
