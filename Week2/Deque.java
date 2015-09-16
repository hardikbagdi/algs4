import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.*;


public class Deque<Item> implements Iterable<Item> {
	
	private Node first=null;
	private Node last=null;
	private int size=0;
	private class Node{
		public Item item;
		public Node next=null;
		public Node(Item item){this.item=item;}
	}
	
	
	
	private class MyIterator implements Iterator<Item> {
			private Node current = first;
			public boolean hasNext() { return current != null; }
			 public void remove() { 
				 throw new UnsupportedOperationException();
				 }
			 public Item next()
			 {
				 if(!hasNext()){
					 throw new NoSuchElementException();
				 }
			 Item item = current.item;
			 current = current.next;
			 return item;
			 }
		}
	public Iterator<Item> iterator()         // return an iterator over items in order from front to end
	   {
		   return new MyIterator();
	   }
	
	 public Deque()                           // construct an empty deque
	 {
		first=null;
		last=null;
		size=0;
		}  
	 public boolean isEmpty()                 // is the deque empty?
	   {
		 return size==0;
		 
	   }
	   public int size()                        // return the number of items on the deque
	   {
		   return size;
	   }
	   public void addFirst(Item item)  throws NullPointerException        // add the item to the front
	   {
		   if(item==null){
			   throw new NullPointerException();
		   }
		   Node newnode= new Node(item);
		   
		   newnode.next=first;
		   first=newnode;
		   size++;
		   if (size==1) {
			last=first;
		}
	   }
	   public void addLast(Item item)           // add the item to the end
	   {
		   if(item==null){
			   throw new NullPointerException();
		   }
		   Node newnode = new Node(item);
		   if(size==0){
			   first=last=newnode;
			   size++;
			   return;
		   }
		   Node oldlast = last;
		   
		   oldlast.next=newnode;
		   last=newnode;
		   size++;
		  
		   
	   }
	   public Item removeFirst()                // remove and return the item from the front
	   {
		   if(size==0){
			   throw new NoSuchElementException();
		   }
		   Node returnnode= first;
		   first=first.next;
		   size--;
		   
		   return returnnode.item;
	   }
	   public Item removeLast()                 // remove and return the item from the end
	   {
		   if(size==0){
			   throw new NoSuchElementException();
		   }
		   if(size==1){
			   Node ret=first;
			   first=last=null;
			   size--;
			   return ret.item;
		   }
		   Node secondlast=first;
			   while (secondlast.next.next!=null) {
				secondlast=secondlast.next;
			   }
		  
		   Node returnednode=last;
		   last=secondlast;
		   secondlast.next=null;
		   size--;
		   return returnednode.item;
		   
	   }
	   
	   public static void main(String[] args)   // unit testing
	   {
		   StdOut.print("HelloWorld");
		   Deque<Integer> deck=new Deque<>();
		   deck.addFirst(0);
	         deck.addFirst(1);
	         for (Integer integer : deck) {
	 			StdOut.print(integer);
	 		}
	         deck.isEmpty();
	         deck.removeLast()    ; 
	         deck.removeLast();
		   //deck.addLast(null);
		  // deck.addLast(2);
		   for (Integer integer : deck) {
			StdOut.print(integer);
		}
		   //Iterator i = deck.iterator();
		   //i.remove();
		   StdOut.print("\n");
	//	   deck.removeFirst();deck.removeLast();
		   for (Integer integer : deck) {
				StdOut.print(integer);
			}
		   StdOut.print("Adding 999 \n \n");
		   deck.addLast(999);
		   for (Integer integer : deck) {
				StdOut.print("\n"+integer);
			}
	   }
}
