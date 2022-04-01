import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Ball extends Actor {
    private double xSpeed;
    private double ySpeed;

    public Ball(double dxSpeed, double dySpeed, Image img) {
        xSpeed = dxSpeed;
        ySpeed = dySpeed;
        setImage(img);
    }

    @Override
    public void act(long now) {
        Point2D coords = localToScene(getX(), getY());
//    	System.out.println(coords);
   
        boolean rightSide = coords.getX() >= getScene().getWidth() - getWidth();
        boolean leftSide = coords.getX() <= 0;
        
        boolean bottomSide = coords.getY() >= getScene().getHeight() - getHeight();
        boolean topSide = coords.getY() <= 0;

        if (rightSide || leftSide) {
        	xSpeed *= -1;
        }
        if (bottomSide || topSide) {
        	ySpeed *= -1;
        	if(bottomSide)
        	    ((BallWorld)getWorld()).setScore(((BallWorld) getWorld()).getScore()-1000); //Decrease score by 1000 if it hits the bottom
        }
        this.move(xSpeed, ySpeed);
        if(getOneIntersectingObject(Paddle.class) != null
        		&& getOneIntersectingObject(Paddle.class).getClass() == Paddle.class) {
        	ySpeed *= -1;
        }
        //ball gets stuck in bricks and causes the speed to increase to a game breaking level, adding a period of time before more speed can be added to the 
        //ball could help, or increasing speed when the ball removes bricks(like a counter to keep track of removed bricks) could also be a solution
        if(getOneIntersectingObject(Brick.class) != null
        		&& getOneIntersectingObject(Brick.class).getClass() == Brick.class) {
            Brick brickHit = getOneIntersectingObject(Brick.class);
            ((BallWorld) getWorld()).setScore(((BallWorld) getWorld()).getScore() + 100); //Decrease score by 1000 if it hits the bottom

            if (this.getX() <= (getOneIntersectingObject(Brick.class).getX() + getOneIntersectingObject(Brick.class).getWidth() / 2)
                    && this.getX() >= (getOneIntersectingObject(Brick.class).getX() - getOneIntersectingObject(Brick.class).getWidth() / 2)) {
                ySpeed *= -1.01;
            } else if (this.getY() <= (getOneIntersectingObject(Brick.class).getY() - getOneIntersectingObject(Brick.class).getHeight() / 2)
                    && this.getY() >= (getOneIntersectingObject(Brick.class).getY() + getOneIntersectingObject(Brick.class).getHeight() / 2)) {
                xSpeed *= -1.01;
            } else {
                xSpeed *= -1.01;
                ySpeed *= -1.01;
            }
            //make the brick class do the animation so if the ball hits more than one brick in a short amount of time the other bricks will also be removed
            transitionEffectFade(getOneIntersectingObject(Brick.class)).setOnFinished(new EventHandler<ActionEvent>() {

	            @Override
	            public void handle(ActionEvent actionEvent) {
	            	getWorld().remove(brickHit);
	            }           
            });
            
            
        }
    }
    
    public FadeTransition transitionEffectFade(Node n) {
    	FadeTransition ft = new FadeTransition(Duration.millis(1000), n);
		ft.setFromValue(1.0);
		ft.setToValue(0);
		ft.setCycleCount(1);
		ft.setAutoReverse(true);
		ft.play();
		return ft;
    }
}
