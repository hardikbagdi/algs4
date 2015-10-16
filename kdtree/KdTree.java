

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
	
	//1 means left right, 0 means abovebelow
	//private int orientation;
	
	
	private int size=0;
	private Node root=null;;
	private static class Node {
		   private Point2D p;      // the point
		  
		   private RectHV rect;    // the axis-aligned rectangle corresponding to this node
		  
		   private Node lb;        // the left/bottom subtree
		  
		   private Node rt;        // the right/top subtree
		
		   public Node(Point2D p) {
			   this.p=p;
			 
			// TODO Auto-generated constructor stub
		}
	}

	
	 public  KdTree()                               // construct an empty set of points 
	   {
		 
	   }
	   public boolean isEmpty()                      // is the set empty? 
	   {
		return size==0;
		   
	   }
	   public int size()                         // number of points in the set 
	   {
		return size;
		}
	   public void insert(Point2D p)              // add the point to the set (if it is not already in the set)
	   {
		  if(p==null) throw new NullPointerException();
		//  System.out.println("inserting fn"+p);
		  if(root==null){
			//  System.out.println("root null true"+p);
			  Node n= new Node(p);
			  root=n;
			  root.rect= new RectHV(0, 0, 1, 1);
			  ++size;
			  return;
		  }
		  else {
			insert(root,p,1);
		}
	   
	   }
	   private Node insert(Node root, Point2D p, int currentorientation) {
		   //System.out.println("qww");
		   if(root==null){
			 //  System.out.println("this should  print"+p);
			   ++size;
			    return new Node(p);			   
		   }
		   if(p.equals(root.p)){return root;}
		   int nextOrientation= currentorientation^1;
		   
		   int compare= compare(p,root.p, currentorientation);
		   //System.out.println("compare "+compare);
		 if(compare<0){
			
			 //System.out.println("compare less");
			 root.lb=insert(root.lb, p, nextOrientation);
			 //update recthv
			 if(root.lb.rect==null){
				// System.err.println("inside rect"+currentorientation);
				 if(currentorientation==1){
					//left-right partition 
					// System.out.println("inserting "+p);
					 root.lb.rect= new RectHV(root.rect.xmin(),root.rect.ymin(),root.p.x(),root.rect.ymax());
				//	 System.err.println(root.lb.rect);
				 }
				 else {
					 root.lb.rect= new RectHV(root.rect.xmin(),root.rect.ymin(),root.rect.xmax(),root.p.y());
					//above below partition
				}
			 }
		 }
		 else {
			 root.rt=insert(root.rt, p, nextOrientation);
			 if(root.rt.rect==null){
				 //System.err.println("inside rect"+currentorientation);
				 if(currentorientation==1){
					//left-right partition 
					// System.out.println("inserting "+p);
					 root.rt.rect= new RectHV(root.p.x(),root.rect.ymin(),root.rect.xmax(),root.rect.ymax());
					 //System.err.println(root.rt.rect);
				 }
				 else {
					 root.rt.rect= new RectHV(root.rect.xmin(),root.p.y(),root.rect.xmax(),root.rect.ymax());
					//above below partition
				}
			 }
			 
			 
		 }
		   
		   
		 return root;
	}
	   
	   
	   public boolean contains(Point2D p)            // does the set contain point p? 
	   {
		   if(p==null) throw new NullPointerException();
		   
		   
		   boolean flag= containsHelper(root, p, 1);
		return flag;
		}
	   private boolean containsHelper(Node root, Point2D p, int currentOrientation){
		   if(root==null) return false;
		   if(root.p.equals(p)) return true;
		   int nextOrientation = currentOrientation^1;
		   int compareflag= compare(p,root.p,currentOrientation);
//		  / System.out.println(compareflag);
		   if(compareflag<0){
			   return containsHelper(root.lb, p, nextOrientation);
		   }
		   else {
			   return containsHelper(root.rt, p, nextOrientation);
		}
		   
	  }
	   
	   private int compare(Point2D p, Point2D p2, int currentOrientation) {
		// TODO Auto-generated method stub
		if(currentOrientation==1){
			
			//compare x
			return Double.compare(p.x(), p2.x());
		
		}
		else {
			return Double.compare(p.y(),p2.y());
			//compare y;
		}
	}
	   public void draw()                         // draw all points to standard draw 
	   {
		   if(root ==null) return;
		   draw(root,1);
	   }
	   
	   private void draw(Node root, int orientation) {
		   if(root==null) return;
		   int nextOrientation = orientation^1;
		   
		   StdDraw.setPenColor(StdDraw.BLACK);
		   StdDraw.setPenRadius(0.01);
		   StdDraw.point(root.p.x(), root.p.y());
		   if(orientation==1){
			   StdDraw.setPenColor(StdDraw.RED);
			   StdDraw.setPenRadius(0.001);
			   StdDraw.line(root.p.x(),root.rect.ymin(), root.p.x(),root.rect.ymax());
		   }
		   else {
			   StdDraw.setPenColor(StdDraw.BLUE);
			   StdDraw.setPenRadius(0.001);
			   StdDraw.line(root.rect.xmin(),root.p.y(),root.rect.xmax(),root.p.y());
		}
		   draw(root.lb,nextOrientation);
		   draw(root.rt, nextOrientation);
		
		
	}
	   
	    public Iterable<Point2D> range(RectHV rect) {
	        
	    	if(rect==null) throw new NullPointerException();
	    	if(root==null) return new Stack<Point2D>();
	    	Stack<Point2D> stack = new Stack<Point2D>();
	        range(stack, rect, root);
	        
	        return stack;
	    }

	    private void range(Stack<Point2D> stack, RectHV rect, Node root) {
	        if (!rect.intersects(root.rect)) {
	            return;
	        }
	        if (rect.contains(root.p)) stack.push(root.p);
	        
	        if (root.lb != null) range(stack, rect, root.lb);
	        
	        if (root.rt != null)  range(stack, rect, root.rt);
	        
	    }
	
	public Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty 
	   {
		if(p==null) throw new NullPointerException();
		if(root==null) return null;
		return nearest(root,p,root.p,Double.MAX_VALUE,1);
		}
			private Point2D nearest(Node x, Point2D p, Point2D nearest,double nearestDistance1, int orientation) {
	        if (x == null) {
	            return nearest;
	        }
	       
	        double nearestDistance2 = nearestDistance1;
	        double distance = x.p.distanceSquaredTo(p);
	        if (distance < nearestDistance1) {
	            nearest = x.p;
	            nearestDistance2 = distance;
	        }
	        Node first, second;
	      first=x.lb;second=x.rt;
	        int nextOrientation = orientation^1;
	        if (first != null && first.rect.distanceSquaredTo(p) < nearestDistance2) {
	        	nearest = nearest(first, p, nearest, nearestDistance2,
	                    nextOrientation);
	            nearestDistance2 = nearest.distanceSquaredTo(p);
	        }
	        if (second != null
	                && second.rect.distanceSquaredTo(p) < nearestDistance2) {
	        	nearest = nearest(second, p, nearest, nearestDistance2,
	                    nextOrientation);
	        }

	        return nearest;
	    }
	
	public static void main(String[] args)                  // unit testing of the methods (optional) 
	   {
		   KdTree  tree= new KdTree();
		   tree.insert(new Point2D(0.5, 0.5));
		   tree.insert(new Point2D(0.75, 0.25));
		   tree.insert(new Point2D(0.75, 0.75));
		 System.out.println(  tree.nearest(new Point2D(0.75, 0.76)));
//		   System.out.println(tree.contains(new Point2D(0.75, 0.5)));
//		   System.out.println(tree.contains(new Point2D(0.75, 0.75)));
		   tree.draw();
//		   int i=1;
//		   int j=i^1;
//		   System.out.println(j);
	   }
}
