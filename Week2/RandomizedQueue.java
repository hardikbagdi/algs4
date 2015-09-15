import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item>implements Iterable<Item>  {
	private Item[] items;
	private int size=0;
	private boolean emptyflag=true;
	private int last=-1;
	private class MyIterator implements Iterator<Item> {
		private int i;
		int[] indexs;
		public MyIterator(){
			int i2=0;
		//	Item[] random = items.clone();
			indexs= new int[size];
			for (int i = 0; i2 < indexs.length; i++) {
				indexs[i2]=i2;
			}
		StdRandom.shuffle(indexs);
		}
		public boolean hasNext() {
			return i<size;
		}
		 public void remove() { 
			 throw new UnsupportedOperationException();
			 }
		 public Item next()
		 {
			 if (!hasNext()) throw new NoSuchElementException();
			 return items[indexs[i++]];
		 }
	}
	private void resize(int length) {
		 Item[] temp = (Item[]) new Object[length];
	        for (int i = 0; i < size; i++) {
	            temp[i] = items[i];
	        }
	        items = temp;
	}
	
	 public RandomizedQueue()                 // construct an empty randomized queue
	 {
		 items = (Item[]) new Object[2];
		 size=2;
	 }
	 public boolean isEmpty()                 // is the queue empty?
	 {
		 return size==0;
	 }	 
	 public int size()                        // return the number of items on the queue
	 {
		 return size;
	 }
	
	 public void enqueue(Item item)           // add the item
	 {	emptyflag=false;
		 if(item==null){
			 throw new NullPointerException();
		 }
	        if(size==items.length){
	        	resize(2*items.length);
	        }
	        items[++last]=item;
	        size++;
		 
	 }
	 
	 public Item dequeue()                  
	 {
		 if(size==0 || emptyflag){
			 throw new NoSuchElementException(); 
		 }
		 Item item;
		 int r= StdRandom.uniform(size);
		 item= items[r];
		 items[r]=items[last];
		 items[last]=null;//taking care of loitering
	               last--;
	        size--;
	        
	        if (size > 0 && size == items.length/4) {
	        	resize(items.length/2); 
	        }
	        return item;
	 }
	 public Item sample()                     
	 {
		 if(size==0 || emptyflag){
			 throw new NoSuchElementException();
		}
		 int r= StdRandom.uniform(size);
		 return items[r];
	 }
	 public Iterator<Item> iterator()         // return an independent iterator over items in random order
	 {
		 return new MyIterator();
	 }
	 public static void main(String[] args)   // unit testing
	 {
		 StdOut.print("HelloWorld");
		 
		 
	 }
}

