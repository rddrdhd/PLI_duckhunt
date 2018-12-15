package DuckHunt;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class DuckHunt extends Application {

    public static final int gameWidth = 800;
    public static final int gameHeight = 600;

    private Pane root = new Pane();
    private List<Duck> ducks = new ArrayList<>();

    private final int NUM_OF_AMMO = 3;
    private int ammo = NUM_OF_AMMO;
    private int roundNum = 0;
    private int killedDucks = 0;
    private int missedDucks = 0;
    private int missedAmmo = 0;

    @Override
    public void  start(Stage stage) {

        Scene scene = new Scene(createContent());
        Image cursor = new Image("DuckHunt/images/gunsight.png");
        scene.setCursor(new ImageCursor(cursor, cursor.getWidth()/2, cursor.getHeight()/2 ));

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                for (Duck duck : ducks) {
                    if (duck.isPressed()) {
                        duck.setCurrentState(Duck.DYING);
                        removeDuck(duck);
                        killedDucks++;
                        missedAmmo--;
                        System.out.println(" You killed " + killedDucks + ". duck.");
                        break;
                    }
                }
                ammo--;
                missedAmmo++;
            }
        });
        stage.setScene(scene);
        stage.setTitle("DuckHunt");
        stage.show();
    }


    private void onUpdate(){
        List<Duck> ducksToRemove = new ArrayList<>();
        this.ducks.forEach(duck -> {
            duck.moveLikeDuck();
            if (duck.getTranslateY() + duck.getHeight() < 0 || ammo == 0) {
                ducksToRemove.add(duck);
                missedDucks++;
            }
            duck.setImage(duck.getCurrentState());
        });
        ducksToRemove.forEach(this::removeDuck);
        if(this.ducks.isEmpty()){
            System.out.println(" You missed " + missedAmmo + " bullets in this round.");
            System.out.println(" You missed " + missedDucks + " ducks.");
            newRound();
        }
    }

    private Parent createContent(){
        root.setPrefSize(gameWidth,gameHeight);
        Image img = new Image("DuckHunt/images/gameBackground.png");
        BackgroundImage background = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(background));
        root.setMinWidth(img.getWidth());
        root.setMinHeight(img.getHeight());
        newRound();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();
        return root;
    }

    private void newRound(){
        System.out.println("Round number " + roundNum++);
        int NUM_OF_DUCKS = 2;
        for (int i = 0; i < NUM_OF_DUCKS; i++){
            Duck duck = new Duck();
            if(roundNum > 50){duck.setSpeed(6);}
            else if(roundNum > 40){duck.setSpeed(5);}
            else if(roundNum > 30){duck.setSpeed(4);}
            else if(roundNum > 20){duck.setSpeed(3);}
            else if(roundNum > 10){duck.setSpeed(2);} //default speed is 1
            ducks.add(duck);
            root.getChildren().add(duck);
            ammo = NUM_OF_AMMO;
            missedAmmo = 0;
        }
    }

    private void removeDuck(Duck duck) {
        duck.setCurrentState(Duck.DYING);
        this.ducks.removeIf(d -> d == duck); // from ArrayList
        this.root.getChildren().removeIf(d -> d == duck); //from Pane
    }
    public static void main(String[] args) {
        launch(args);
    }
}
