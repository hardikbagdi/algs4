import edu.princeton.cs.algs4.*;


public class PercolationStats {
   double[] results;
   int size;
   int NoOfExp=0;
   double mean,stddev;
   Percolation percolation;

   private double performpercolation(){
   	// StdOut.print("\n \n inside performpercolation");
   		while (!percolation.percolates()) {
            // StdOut.print("\n \n inside while");
   			percolation.open(StdRandom.uniform(size)+1,StdRandom.uniform(size)+1);
   		}
   		// StdOut.print("\n \n Opened:"+percolation.opened);
   		return ((double)(percolation.opened))/((double)size*size);



   } 
   public PercolationStats(int N, int T) throws Exception   {
   	// StdOut.print("\n \n inside  constructor");
   			if(N<=0 || T<=0){throw new java.lang.IllegalArgumentException();}
				results= new double[T];
				size=N;
				NoOfExp=T;
				for (int i=0;i<T ;i++ ) {
					
				percolation = new Percolation(N);
				results[i]=performpercolation();
				// StdOut.print("\n \n Result:"+results[i]);
				}
				// StdOut.print("\n \n end of Constructor");

   }  // perform T independent experiments on an N-by-N grid
   public double mean(){

   	return StdStats.mean(results);
   }                      // sample mean of percolation threshold
   public double stddev(){
   	if(NoOfExp==1){return  Double.NaN;}
      stddev=StdStats.stddev(results);
   	return stddev;

   }                    // sample standard deviation of percolation threshold
   public double confidenceLo(){



      return (double)(mean-((double)(1.96*stddev)/(double)Math.sqrt(NoOfExp)));
   }              // low  endpoint of 95% confidence interval
   public double confidenceHi()  {
   	return mean+((1.96*stddev)/Math.sqrt(NoOfExp));


   }            // high endpoint of 95% confidence interval

   public static void main(String[] args) throws Exception{
   	PercolationStats ps= new PercolationStats(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
   		StdOut.print("\n \n Mean:\t"+ps.mean());
   		StdOut.print("\n \n Standard Deviation:\t"+ps.stddev());
   		StdOut.print("\n \n 95% confidence interval:\t"+ps.confidenceLo()+" , "+ps.confidenceHi());

   		

   }    // test client (described below)
}
