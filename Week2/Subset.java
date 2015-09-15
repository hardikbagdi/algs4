import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
	public static void main(String[] args){
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> rq= new RandomizedQueue<String>();
		//StdOut.print("\n"+k);
		String s;
		while (!StdIn.hasNextChar()) {
		s=StdIn.readString();
		rq.enqueue(s);
		}
		int i=0;
		while (i<k) {
			StdOut.print("\n"+rq.dequeue());
		}
	}
}
