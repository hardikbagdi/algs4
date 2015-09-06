import edu.princeton.cs.algs4.*;

class test {

public static void main(String[] args){
QuickFindUF q = new QuickFindUF(10);
StdOut.print(q.count());
q.union(0,9);
q.union(10,3);
StdOut.print(q.connected(0,3));
StdOut.print(q.connected(0,2));
StdOut.print(q.connected(0,9));

}
}

