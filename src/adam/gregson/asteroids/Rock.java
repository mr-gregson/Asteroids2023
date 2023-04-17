package adam.gregson.asteroids;

import java.awt.image.BufferedImage;

public class Rock extends Sprite {

    private RockSize rockSize;
    private boolean rotateLeft;

    public Rock(BufferedImage[] images, RockSize rockSize, double x, double y, int maxX, int maxY, double deltaTime) {
        super(images, x, y, maxX, maxY, deltaTime);
        this.rockSize = rockSize;
        this.rotateLeft = Math.random() < 0.5;
    }

    public RockSize getRockSize() {
        return rockSize;
    }

    public void rotate() {
        if (rotateLeft) rotateLeft();
        else rotateRight();
    }
}
