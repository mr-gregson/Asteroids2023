package adam.gregson.asteroids;

import java.util.LinkedList;
import java.util.ArrayList;

class PolygonCollider extends Collider {
    public LinkedList<double[]> vertices;
    private double angleRotated;

    private ArrayList<Double> pointMagnitudes;

    // (x, y) is the center of mass of the collider and points is a list of the vertices that make up the polygon,
    // relative to the center of mass. It must have a size of at least 3. Each point in points list should have a size of two.
    public PolygonCollider (double x, double y, LinkedList<double[]> points) {
        super(x, y);

        this.vertices = points;
        angleRotated = 0;

        pointMagnitudes = new ArrayList<Double>();

        for (int i = 0; i < points.size(); i++) {
            // Calculate point magnitude
            pointMagnitudes.add(Math.sqrt(points.get(i)[0]*points.get(i)[0] + points.get(i)[1]*points.get(i)[1]));
        }
    }

    public ArrayList<Double> getPointMagnitudes() {
        return pointMagnitudes;
    }

    boolean collidesWithPolygon(PolygonCollider collided) {
        for (int shape = 0; shape < 2; shape++) {

        
            // For each vertex check if the master line intersects with an edge from another line
            for (double[] vertex : vertices) {
                double[] diagStart = {this.x, this.y};
                double[] diagEnd = {this.x + vertex[0], this.y + vertex[1]};

                for (int i = 0; i  < collided.vertices.size(); i++) {
                    // If vertices[i] is the last element of the list, edgeEndPoint will be the first
                    double[] edgeStart = collided.vertices.get(i);
                    double[] edgeEnd = collided.vertices.get((i + 1) % vertices.size());

                    // Check if diagonal and edge intersect
                    double h = (edgeEnd[0] - edgeStart[0]) * (diagStart[1] - diagEnd[1]) - (diagStart[0] - diagEnd[0]) * (edgeEnd[1] - edgeStart[1]);
                    double t1 = ((edgeStart[1] - edgeEnd[1]) * (diagStart[0] - edgeStart[0]) + (edgeEnd[0] - edgeStart[0]) * (diagStart[1] - edgeStart[1])) / h;
                    double t2 = ((diagStart[1] - diagEnd[1]) * (diagStart[0] - edgeStart[0]) + (diagEnd[0] - diagStart[0]) * (diagStart[1] - edgeStart[1])) / h;

                    if (t1 >= 0 && t1 < 1 && t2 >= 0 && t2 < 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
}
