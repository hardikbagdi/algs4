import java.util.TreeSet;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;

public class PointSET {
	private TreeSet<Point2D>  tree;
		   public  PointSET()                               // construct an empty set of points 
		   {
			   tree = new TreeSet<Point2D>();
		   }
		   public boolean isEmpty()                      // is the set empty? 
		   {
			return tree.isEmpty();
			   
		   }
		   public int size()                         // number of points in the set 
		   {
			return tree.size();}
		   public void insert(Point2D p)              // add the point to the set (if it is not already in the set)
		   {
			   if(p==null)throw new NullPointerException();
			   tree.add(p);
		   }
		   public boolean contains(Point2D p)            // does the set contain point p? 
		   {
			   if(p==null)throw new NullPointerException();
			return tree.contains(p);
			}
		   public void draw()                         // draw all points to standard draw 
		   {
			   for (Point2D point2d : tree) {
				point2d.draw();
			}
		   }
		   public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle 
		   
		   {
			   if(rect==null)throw new NullPointerException();
			   Stack<Point2D> inrange= new Stack<Point2D>();
			  for (Point2D point2d : tree) {
				if(rect.contains(point2d)){
					inrange.push(point2d);
				}
				
				
			} 
			return inrange;
			}
		   public Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty 
		   {
			   if(p==null)throw new NullPointerException();
			double mindistance= Double.MAX_VALUE;
			Point2D nearest=null;
				   for (Point2D point2d : tree) {
						if(point2d.distanceTo(p)<mindistance) {
							mindistance= point2d.distanceTo(p);
							nearest=point2d;
						}
				}
			   
			   
			return nearest;
			}
		   public static void main(String[] args)                  // unit testing of the methods (optional) 
		   {}
	

}
