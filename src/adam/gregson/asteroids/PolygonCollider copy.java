package adam.gregson.asteroids;

import java.util.LinkedList;
import java.util.ArrayList;

class CircleCollider extends Collider {
    public LinkedList<double[]> vertices;
    private double radius;


    public CircleCollider (double x, double y, double radius) {
        super(x, y);

        this.radius = radius;
    }

    public boolean collidesWithCircle(CircleCollider c) {
        return (c.x - this.x)*(c.x - this.x) + (c.y - this.y)*(c.y - this.y) >= (radius + c.radius)*(radius + c.radius);
    }
}
