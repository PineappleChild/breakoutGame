import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Objects;

public class Game extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		int paddleKeyPressSpeed = 10;

		primaryStage.setTitle("Break Out Game");
		String pathTwo = getClass().getClassLoader().getResource("resources/tennisBall.png").toString();
		String paddleImg = getClass().getClassLoader().getResource("resources/testPaddle.png").toString();
		String redPath = Objects.requireNonNull(getClass().getClassLoader().getResource("resources/brick_10.png")).toString();
		String yellowPath = Objects.requireNonNull(getClass().getClassLoader().getResource("resources/brick_11.png")).toString();
		String bluePath = Objects.requireNonNull(getClass().getClassLoader().getResource("resources/brick_12.png")).toString();
		BorderPane root = new BorderPane();
		Pane layerPane = new Pane();
		layerPane.setBorder(new Border(new BorderStroke(Color.BLACK,
				BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		layerPane.setPrefSize(100, 100);

		Scene scene = new Scene(root, 500, 500);
		
		double brickWidth = scene.getWidth()/11;
		double amountOfBricksHorizontal = countBricksHoriz(brickWidth, scene.getWidth());
		double brickHeight = scene.getHeight()/amountOfBricksHorizontal;
		double brickSpacing = brickWidth/5;
		
		System.out.println(countBricksHoriz(brickWidth, scene.getWidth()));
		
		
		
		Image paddleOneImg = new Image(paddleImg, scene.getWidth()/5, scene.getWidth()/3, true, true);
		Image ballTwoImg = new Image(pathTwo, scene.getWidth()/20, scene.getWidth()/20, true, true);
		Image redImg = new Image(redPath, brickWidth, brickHeight, true, true);
		Image yellowImg = new Image(yellowPath, brickWidth, brickHeight, true, true);
		Image blueImg = new Image(bluePath, brickWidth, brickHeight, true, true);
		
		BallWorld ballWorld = new BallWorld();
		layerPane.getChildren().addAll(ballWorld);
		root.setBottom(layerPane);

		Ball ballTwo = new Ball(2, 2, ballTwoImg);
		ballTwo.setX(scene.getWidth()/2 - ballTwo.getWidth()/2);
		ballTwo.setY(-50);
		ballWorld.add(ballTwo);

		Paddle testPaddle = new Paddle(paddleOneImg);
		testPaddle.setX(scene.getWidth() / 2 - testPaddle.getWidth() / 2);

		for (int i = 0; i < amountOfBricksHorizontal; i++) {
			for (int j = 0; j < 15; j++) {
				Brick brick;
				if (j >= 0 && j < 3) {
					brick = new Brick(redImg);
				} else if (j >= 4 && j < 7) {
					brick = new Brick(yellowImg);
				} else {
					brick = new Brick(blueImg);
				}


				if (j % 2 == 0) {
					brick.setX(brickSpacing + (brickWidth * i));
				} else {
					brick.setX((brickWidth * i) - brickSpacing);
				}

				brick.setY((-(brickWidth/2) * j) - (brickWidth*3));
				ballWorld.add(brick);
			}

		}


		ballWorld.add(testPaddle);


		ballWorld.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				testPaddle.setX(event.getSceneX() - testPaddle.getWidth()/2);
			}
		});


		ballWorld.start();

		primaryStage.setScene(scene);
		primaryStage.setFullScreenExitHint("");
        primaryStage.show();

        ballWorld.requestFocus();

        ballWorld.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        public void handle(KeyEvent ke) {
	            if (ke.getCode() == KeyCode.LEFT) {
	            	ballWorld.addKeyToKeyDownTracker(KeyCode.LEFT);

	            }else if(ke.getCode() == KeyCode.RIGHT) {
	            	ballWorld.addKeyToKeyDownTracker(KeyCode.RIGHT);

	            }
	        }
	    });

        ballWorld.setOnKeyReleased(new EventHandler<KeyEvent>() {
	        public void handle(KeyEvent ke) {
	            if (ke.getCode() == KeyCode.LEFT) {
	            	ballWorld.removeKeyFromKeyDownTracker(KeyCode.LEFT);

	            }else if(ke.getCode() == KeyCode.RIGHT) {
	            	ballWorld.removeKeyFromKeyDownTracker(KeyCode.RIGHT);

	            }
	        }
	    });


	}
	
	public int countBricksHoriz(double width, double sceneWidth) {
		System.out.println(width + " " + sceneWidth);
		double newSize = width;
		int counter = 0;
		while(newSize < sceneWidth) {
			counter++;
			newSize = width*counter;
			System.out.println(newSize);
		}
		return counter;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
