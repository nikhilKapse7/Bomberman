import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.media.AudioClip;
import java.net.URL;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
public class Bomberman extends Application implements EventHandler<InputEvent> {
	Image brick;
	Image steel;
	Image background;
	int level;
	GraphicsContext gc;
	int x;
	AnimateObjects animate;
	Image trooper;
	AudioClip clip;
	public static void main(String[] args){
		launch();
	}
	public void start(Stage stage) {
		stage.setTitle("Bomberman");
		Group root = new Group();
		Canvas canvas = new Canvas(676, 676);
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		scene.addEventHandler(KeyEvent.KEY_PRESSED, this);
		scene.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		stage.setScene(scene);

		gc = canvas.getGraphicsContext2D();
		trooper = new Image("trooper.png");
		steel = new Image("steel.png");
		gc.drawImage( trooper, 180, 100 );
		background = new Image("EmptyScreen.png");
		animate = new AnimateObjects();
		animate.start();


		//URL resource = getClass().getResource("test.wav");
	//	clip = new AudioClip(resource.toString());
		//clip.play();
		stage.show();
	}
	public class AnimateObjects extends AnimationTimer {
		public void handle(long now) {
			gc.drawImage(background, 0, 0);
			if(level == 0) { //setup opening screen
				gc.fillText("Bomberman", 300, 80) ;
				gc.fillText("Press any key to start!", 300, 570) ;
			}
			else if(level == 1) {// setup level 1
				gc.drawImage(steel, 50, 50);
				Rectangle2D rect1 = new Rectangle2D(50, 50, 50, 50);
			}

			if (x > -50) {
				//x += 1;
				gc.drawImage( trooper, 180 + x, 100);
/*
				Rectangle2D rect1 = new Rectangle2D(400, 100, 100, 100);
				gc.fillRect(400, 100, 100, 100);
				Rectangle2D rect2 = new Rectangle2D( 180 + x, 100, trooper.getWidth(), trooper.getHeight() );

				if(rect1.intersects(rect2)) //when contact is made
					System.out.println("HIT");
*/
				//all the code for drawing your image on the screen goes here
			}

			else{
				// we are going to display Game over if the user moves 50 pixels to the left
				gc.setFill( Color.YELLOW); //Fills the text in yellow
				gc.setStroke( Color.BLACK ); //Changes the outline the black
				gc.setLineWidth(1); //How big the black lines will be
				Font font = Font.font( "Arial", FontWeight.NORMAL, 48 );
				gc.setFont( font );
				gc.fillText( "Game Over", 100, 50 ); //draws the yellow part of the text
				gc.strokeText( "Game Over", 100, 50 ); //draws the outline part of the text
			}

		}

	}
	public void handle(final InputEvent event) {
		if(event instanceof KeyEvent) {
			if(level == 0) {
				level++;
			}
		//ARROW MOVEMENT
			if(((KeyEvent)event).getCode() == KeyCode.LEFT) {
				x -= 10;
			}
			else if(((KeyEvent)event).getCode() == KeyCode.RIGHT) {
				x += 10;
			}
			// else if(((KeyEvent)event).getCode() == KeyCode.DOWN) {
			// 	y--;
			// }
			// else if(((KeyEvent)event).getCode() == KeyCode.UP) {
			// 	y++;
			// }
		}
		/*if (event instanceof MouseEvent) {
			System.out.println( ((MouseEvent)event).getX() );
			System.out.println( ((MouseEvent)event).getY() );
		}*/
	}
}
