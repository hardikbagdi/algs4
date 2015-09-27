import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
  
	private Point[] points;
	private LineSegment[] answer;
	private int numberOfSegments;
	public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
   {
	   if (points==null) {
		throw new NullPointerException();
	}
	   else
	   {
		   for(int i=0;i<points.length;i++){
			   if(points[i]==null)
			   throw new NullPointerException();
		   }
				   
	   }
	   
	   for (int i = 0; i < points.length; i++) {
		 for (int j = i+1; j < points.length; j++) {
			if(points[i].compareTo(points[j])==0)
				{StdOut.println(points[i]);
			StdOut.println(points[j]);
				throw new IllegalArgumentException();
				}
		}
	}
	   
	   this.points = points;
	   //compute();
   }
	
	   
	   
	   
   private class tuple{
	   double slope;Point point;
	   public tuple(double d,Point p1) {
		// TODO Auto-generated constructor stub
		   slope=d;point=p1;
	   }
   }
	
   public int numberOfSegments()        // the number of line segments
   {
	
	   return numberOfSegments;   
   }
   
   
   public LineSegment[] segments(){
	 //  boolean addedflag=false;
	 //  System.out.println("Start of segment()");
	   Point[] points2 =points.clone();
	   ArrayList<LineSegment> answer = new ArrayList<LineSegment>();
	   ArrayList<tuple> added = new ArrayList<tuple>();
	   Point p=null;
	 for (int i = 0; i < points.length; i++) { // uncomment loop to consider each point
		p = points[i];
		
		Arrays.sort(points2,(Comparator<Point>)p.slopeOrder());
		
	
	//		System.out.println("\n\nprinting slope order"+p);
//		for (int j1 = 0; j1 < points2.length; j1++) {
//			System.out.println(points2[j1]);
//		}
		//int j=2; //j=i+1;
	//	System.out.println(points2.length);
		for (int i1 = 0; i1 < points2.length-3; i1++) {
	//		System.out.println("\n\n\n new point");
			if( 	points2[i1+1].slopeTo(p)==points2[i1+2].slopeTo(p) &&
					points2[i1+2].slopeTo(p)==points2[i1+3].slopeTo(p) ){
			
				Point[] temp = {p,points2[i1+1],points2[i1+2],points2[i1+3]};
				Arrays.sort(temp);
				
				
				//add to answer
				if(answer.isEmpty())
				{
	//				System.out.println("first time add to answer");
					added.add(new tuple(points2[i1+1].slopeTo(p), temp[temp.length-1]));
				answer.add(new LineSegment(temp[0], temp[temp.length-1]));continue;}
			int iterl=0;
			boolean found=false;
				for (tuple t : added) {
					iterl++;
			//	System.out.println(iterl+"comparing"+added.size());
			//	System.out.println(t.slope+"\t"+temp[0].slopeTo(temp[temp.length-1]));
				if((t.slope==temp[0].slopeTo(temp[temp.length-1]) && t.slope== p.slopeTo(temp[temp.length-1])) || temp[temp.length-1].slopeTo(p)==Double.NEGATIVE_INFINITY){
			//		System.out.println("broken");
					found=true;
					
					
					
					break;
					
				}
				else {
					
				
				}
			}
			if (!found) {
			//	System.out.println(p);
			//	System.out.println("tempaeeay======================");
//				
//				for (Point point : temp) {
//					System.out.println(point);
//				}
				LineSegment l =new LineSegment(temp[0], temp[temp.length-1]);
			//	System.out.println("Added"+l);
				answer.add(new LineSegment(temp[0], temp[temp.length-1]));
				//addedflag=true;
			
			added.add(new tuple(points2[i1+1].slopeTo(p), temp[temp.length-1]));
		//	addedflag=false;
			}	
			i1=i1+3;
			}
			else {
				i1++;
			}
		}
		
		
		
	 }
	
	   LineSegment[] returnrarray= new LineSegment[answer.size()];
		 // answer = new LineSegment[linesegments.size()];
		   answer.toArray(returnrarray);   
		  return returnrarray;
	   
		}
   
   
//   public LineSegment[] segments()                // the line segments
//   {
//	   ArrayList<Double> recordslope = new ArrayList<Double>();
//	   ArrayList<LineSegment> linesegments= new ArrayList<>();
//	 //  Merge.sort(points);
//	   Merge.sort(points);
//	  
//	   
//	  // boolean[] mark = new boolean[points.length];
//	   
//	   for (int i = 0; i < points.length; i++) {
//		 //  StdOut.println("i updated:"+i);
//		for (int j = i+1; j < points.length; j++) {
////			StdOut.println("j updated:"+j);
//			for (int k = j+1; k < points.length; k++) {
////				StdOut.println("k updated:"+k);
//				
//				double slope1=points[i].slopeTo(points[j]);
//				double slope2=points[j].slopeTo(points[k]);
//				if(slope1==slope2){
//				
//								
//					for (int l = k+1; l < points.length; l++) {
////									StdOut.println("l updated: "+l);
////									StdOut.println("Length: "+points.length);
//					double slope3=points[k].slopeTo(points[l]);
//								if(slope1==slope3){
//									
//									if (recordslope.isEmpty()) {
//										StdOut.println(points[i] + " -> " + points[l]);
//										linesegments.add(new LineSegment(points[i], points[l]));
//										recordslope.add(slope1);
//									}
//									
//									for (Iterator<Double> iterator = recordslope.iterator(); iterator.hasNext();) {
//										Double sl = (Double) iterator.next();
//										if(!(sl==slope3)){
////											StdOut.println("break called:");
//											StdOut.println("adding from insider iterator loop:");
//											StdOut.println(points[i] + " -> " + points[l]);
//											linesegments.add(new LineSegment(points[i], points[l]));
////											
////											break;
//											}
////										StdOut.println(points[i] + " -> " + points[l]);
////										linesegments.add(new LineSegment(points[i], points[l]));
//									}
//									
//								//	if(!mark[i] && !mark[j] && !mark[k] && !mark[l]){
//									
//								//	}
//									//ArrayList<Point> newlist= new ArrayList<Point>(Arrays.asList(points));
//									//newlist.remove(points[i]);
//									//newlist.remove(points[j]);
//									//newlist.remove(points[k]);
//									//newlist.remove(points[l]);
////												mark[i]=true;
////												mark[j]=true;
////												mark[k]=true;
////												mark[l]=true;
//									
//									//StdOut.println(points[i] + " -> " + points[l]);
//									//points = new Point[newlist.size()];
//									//newlist.toArray(points);
//									
//								}	
//											
//											
//								
//								
//								
//							}
//				
//				}
//			}
//		}
//	}
//	   
//	 //  linesegments.s
//	   answer = new LineSegment[linesegments.size()];
//	   linesegments.toArray(answer);   
//	  numberOfSegments=answer.length;
//	  return answer;
//   }


   public static void main(String[] args) {

	    // read the N points from a file
	    In in = new In(args[0]);
	   //In in = new StdIn(); 
	   int N = in.readInt();
	    Point[] points = new Point[N];
	    for (int i = 0; i < N; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }

	    // draw the points
	  //  StdDraw.show(0);
	 //   StdDraw.setYscale(0, 32768);
	//    for (Point p : points) {
	//        p.draw();
	//    }
	   // StdDraw.show();

	    // print and draw the line segments
	    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	    LineSegment[] a = collinear.segments();
	//    StdOut.println("Printing from main:");
	    for (LineSegment segment : a) {
	        StdOut.println(segment);
	        //segment.draw();
	    }
	}


}