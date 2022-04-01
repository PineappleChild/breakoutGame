import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Paddle extends Actor {
    private int xSpeed = 5;

    public Paddle(Image img) {
        setImage(img);
    }

    @Override
    public void act(long now) {

        if (getWorld().isInKeyDownTracker(KeyCode.LEFT)) {
        	this.setX(this.getX() - xSpeed);
        } else if (getWorld().isInKeyDownTracker(KeyCode.RIGHT)) {
        	this.setX(this.getX() + xSpeed);
        }
    }
}
