import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int count = 0;

        for (Point pt : s.getPoints()) {
            count += 1;
        }

        return count;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double perim = getPerimeter(s);
        int pointCount = getNumPoints(s);

        double ave = perim / (double) pointCount;

        return ave;
    }

    public double getLargestSide(Shape s) {
        double totalPerim = 0.0;
        Point prevPt = s.getLastPoint();

        Double largestSide = null;

        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);

            if (largestSide == null || largestSide < currDist) {
                largestSide = currDist;
            }
            prevPt = currPt;
        }

        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        Double largestX = null;

        for (Point pt : s.getPoints()) {
            if (largestX == null || largestX < pt.getX()) {
                largestX = (double) pt.getX();
            }
        }

        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        Double largestPerim = null;

        for (File tf : dr.selectedFiles()) {
            FileResource fr = new FileResource(tf);
            Shape s = new Shape(fr);

            if (largestPerim == null || largestPerim < getPerimeter(s)) {
                largestPerim = getPerimeter(s);
            }
        }

        return largestPerim;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code

        DirectoryResource dr = new DirectoryResource();
        Double largestPerim = null;

        for (File tf : dr.selectedFiles()) {
            FileResource fr = new FileResource(tf);
            Shape s = new Shape(fr);

            if (largestPerim == null || largestPerim < getPerimeter(s)) {
                largestPerim = getPerimeter(s);
                temp = tf;
            }
        }

        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int pointsCount = getNumPoints(s);
        double aveLength = getAverageLength(s);
        double largestSide = getLargestSide(s);
        double largestX = getLargestX(s);

        System.out.println("number of points = " + pointsCount);
        System.out.println("perimeter = " + length);
        System.out.println("average length = " + aveLength);
        System.out.println("largest side = " + largestSide);
        System.out.println("largest X = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        // Put code here
        DirectoryResource dr = new DirectoryResource();

        for (File tf : dr.selectedFiles()) {
            FileResource fr = new FileResource(tf);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            int pointsCount = getNumPoints(s);
            double aveLength = getAverageLength(s);
            double largestSide = getLargestSide(s);
            double largestX = getLargestX(s);

            System.out.println("number of points = " + pointsCount);
            System.out.println("perimeter = " + length);
            System.out.println("average length = " + aveLength);
            System.out.println("largest side = " + largestSide);
            System.out.println("largest X = " + largestX);
        }
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        FileResource fr = new FileResource(getFileWithLargestPerimeter());
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int pointsCount = getNumPoints(s);
        double aveLength = getAverageLength(s);
        double largestSide = getLargestSide(s);
        double largestX = getLargestX(s);
        String file = getFileWithLargestPerimeter();

        System.out.println(file);
        System.out.println("number of points = " + pointsCount);
        System.out.println("perimeter = " + length);
        System.out.println("average length = " + aveLength);
        System.out.println("largest side = " + largestSide);
        System.out.println("largest X = " + largestX);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        // pr.testPerimeter();
        // pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
