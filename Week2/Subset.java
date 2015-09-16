import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
    public static void main(String[] args){
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq= new RandomizedQueue<String>();
        //StdOut.print("\n"+k);
        String s;
        while (!StdIn.isEmpty()) {
        s=StdIn.readString();
        rq.enqueue(s);
        }
        //for (String string : rq) {
        //    StdOut.print("\n\n"+string);
        //}
        //    StdOut.print("\n now printing random k: \n");
        s=null;
        int i=0;
        while (i<k) {
            StdOut.print("\n"+rq.dequeue());
            i++;
        }
    }
}
