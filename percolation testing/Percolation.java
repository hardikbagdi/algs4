import edu.princeton.cs.algs4.*;

public class Percolation{
	
	WeightedQuickUnionUF map;
	int N;
   int opened=0;
	boolean[][] opengrid;
	public Percolation(int N) throws java.lang.IllegalArgumentException{
		if (N<0 || N==0){throw new IllegalArgumentException();}
		map = new WeightedQuickUnionUF((N*N)+2);// 2 for the virtual top and virtual bottom, treat 1st as virtual top and 2nd as virtual bottom
		this.N=N;
		opengrid= new boolean[N][N];
		//StdOut.print(opengrid[1][2]);
	}              // create N-by-N grid, with all sites blocked
   public void open(int i, int j)    {
   		//int index= xytoindex(i,j);
   		//validate(i,j);
      StdOut.print("\n \n opening:"+i+"  "+j);
         if(!isOpen(i,j))
         {   opened++;
            
         		opengrid[i-1][j-1]=true;
               if(i==1 && !(map.connected(xytoindex(i,j),N*N))){
                              map.union(xytoindex(i,j),N*N);
                  }
               if(i==N && !(map.connected(xytoindex(i,j),N*N+1))){
                  map.union(xytoindex(i,j),N*N+1);
               }
         		// int actuali= i-1;
         		// int actualj=j-1;
         		//i--;j--;
         		// all opengrid access are 0 based indexes. so -1 common to all. 
         		if(!(i==N)){		//bottom row
                  if(opengrid[i+1-1][j-1]==true && !(map.connected(xytoindex(i,j),xytoindex(i+1,j)))){
                     map.union(xytoindex(i,j),xytoindex(i+1,j));
                     
                  }
                  }
                  
                  if(!(i==1)){ //top row
                  if(opengrid[i-1-1][j-1]==true && !(map.connected(xytoindex(i,j),xytoindex(i-1,j)))){
                     map.union(xytoindex(i,j),xytoindex(i-1,j));
                     
                  }
                  }  
                  if(!(j==1)){ // left edge
                  if(opengrid[i-1][j-1-1]==true && !(map.connected(xytoindex(i,j),xytoindex(i,j-1)))){
                     // StdOut.print(xytoindex(i,j)+"   "+xytoindex(i,j-1));
                     // StdOut.print("Joining\n ");
                     map.union(xytoindex(i,j),xytoindex(i,j-1));

                  }
                  }  
                  if(!(j==(N))){ //right edge
         			if(opengrid[i-1][j+1-1]==true && !(map.connected(xytoindex(i,j),xytoindex(i,j+1)))){
         				map.union(xytoindex(i,j),xytoindex(i,j+1));
         			}
                  }
   		
         }
         

   }      // open site (row i, column j) if it is not open already
   public boolean isOpen(int i, int j)  {
   	
   		validate(i,j);
   		return opengrid[i-1][j-1];
   	
   }   // is site (row i, column j) open?
   public boolean isFull(int i, int j)  {
   	validate(i,j);

   	return map.connected(xytoindex(i,j),N*N);

   }   // is site (row i, column j) full?
   public boolean percolates()      {
   		int size= map.count();
   	return map.connected(N*N,N*N+1);
   	//return false;

   }       // does the system percolate?

   private int xytoindex(int x,int y){
   		int index;
   		index=this.N*(x-1)+y-1;
   		return index;

   }
   private void validate(int i, int j) throws IllegalArgumentException{

   		if(i<=0 || i>N){throw new java.lang.IllegalArgumentException();}
   		if(j<=0 || j>N){throw new java.lang.IllegalArgumentException();}
   

   }
   public static void main(String[] args) throws Exception{
   	StdOut.print("Input size:");
      int x=StdIn.readInt();
      Percolation percolation = new Percolation(x);
   	StdOut.print("\n \n "+percolation.map.count());
      percolation.open(1,1);
      percolation.open(1,2);
      StdOut.print(percolation.map.connected(0,9));
      StdOut.print(percolation.map.connected(1,9));

      percolation.open(2,2);
      percolation.open(3,2);
      
      StdOut.print(percolation.map.connected(4,9));
      StdOut.print(percolation.map.connected(10,9));

      percolation.open(3,2);


      StdOut.print("\n \n "+percolation.map.count());
   }
}
