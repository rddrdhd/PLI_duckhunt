package DuckHunt;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class DuckHunt extends Application {

    public static final int gameWidth = 800;
    public static final int gameHeight = 600;

    private final int NUM_OF_AMMO = 5;
    private final int NUM_OF_DUCKS = 2; //*2

    private Pane root = new Pane();
    private List<FlyDuck> flyDucks = new ArrayList<>();
    private List<FallDuck> fallDucks = new ArrayList<>();

    private int ammo = NUM_OF_AMMO;
    private final int MAX_MISS_COUNT = NUM_OF_AMMO*5;
    private int missCount = 0;
    private int roundNum = 0;
    private int killedDucks = 0;
    private int missedDucks = 0;

    private Text scoreText = new Text();
    private Text ammoText = new Text();
    private Text gameOver = new Text();
    private Text roundText = new Text();
    private Text missText = new Text();

    @Override
    public void  start(Stage stage) {


        Scene scene = new Scene(createContent());
        Image cursor = new Image("DuckHunt/images/gunsight.png");
        scene.setCursor(new ImageCursor(cursor, cursor.getWidth()/2, cursor.getHeight()/2 ));

        scene.setOnMousePressed(event -> {
            for (FlyDuck flyDuck : flyDucks) {
                if (flyDuck.isPressed()) {
                    removeFlyDuck(flyDuck);
                    killedDucks++;
                    missCount--;
                    break;
                }
            }
            for (FallDuck fallDuck : fallDucks){
                if (fallDuck.isPressed()){
                    removeFallDuck(fallDuck);
                    killedDucks++;
                    missCount--;
                    break;
                }
            }
            ammo--;
            missCount++;
        });
        root.getChildren().add(scoreText);
        root.getChildren().add(ammoText);
        root.getChildren().add(roundText);
        root.getChildren().add(missText);
        root.getChildren().add(gameOver);

        stage.setScene(scene);
        stage.setTitle("DuckHunt");
        stage.show();
    }


    private void onUpdate(){
        List<FlyDuck> flyDucksToRemove = new ArrayList<>();
        List<FallDuck> fallDucksToRemove = new ArrayList<>();
        this.flyDucks.forEach(flyDuck -> {
            flyDuck.moveLikeDuck();
            if (flyDuck.getTranslateY() + flyDuck.getHeight() < 0 || ammo == 0) {
                flyDucksToRemove.add(flyDuck);
                missedDucks++;
            }
            flyDuck.setImage(flyDuck.getCurrentState());
        });
        this.fallDucks.forEach(fallDuck -> {
            fallDuck.moveDown();
            if (fallDuck.getTranslateY() + fallDuck.getHeight() > gameHeight  || ammo == 0) {
                fallDucksToRemove.add(fallDuck);
                missedDucks++;
            }
            fallDuck.setImage(fallDuck.getCurrentState());
        });
        flyDucksToRemove.forEach(this::removeFlyDuck);
        fallDucksToRemove.forEach(this::removeFallDuck);
        if(this.flyDucks.isEmpty() && this.fallDucks.isEmpty()){

            newRound();
        }
        setText();

        if(missCount >= MAX_MISS_COUNT){
            try { Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("GAME OVER\nYour score: " + killedDucks*100);
            Platform.exit();
        }

    }


    private Parent createContent(){
        root.setPrefSize(gameWidth,gameHeight);
        Image bgr = new Image("DuckHunt/images/gameBackground.png");
        BackgroundImage background = new BackgroundImage(bgr,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(background));
        root.setMinWidth(bgr.getWidth());
        root.setMinHeight(bgr.getHeight());
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

        for (int i = 0; i < NUM_OF_DUCKS; i++){
            FlyDuck flyDuck = new FlyDuck();
            FallDuck fallDuck = new FallDuck();

            if(killedDucks > 50){ flyDuck.setSpeed(6);fallDuck.setSpeed(6);}
            else if(killedDucks > 40){flyDuck.setSpeed(5);fallDuck.setSpeed(5);}
            else if(killedDucks > 30){flyDuck.setSpeed(4);fallDuck.setSpeed(4);}
            else if(killedDucks > 20){flyDuck.setSpeed(3);fallDuck.setSpeed(3);}
            else if(killedDucks > 10){flyDuck.setSpeed(2);fallDuck.setSpeed(2);}
            else{flyDuck.setSpeed(1);fallDuck.setSpeed(1);}

            flyDucks.add(flyDuck);
            if(killedDucks>15){
                fallDucks.add(fallDuck);
                root.getChildren().add(fallDuck);
            }

            root.getChildren().add(flyDuck);

            ammo = NUM_OF_AMMO;
        }
    }

    private void removeFlyDuck(FlyDuck flyDuck) {

        this.flyDucks.removeIf(d -> d == flyDuck); // from ArrayList
        this.root.getChildren().removeIf(d -> d == flyDuck); //from Pane
    }
    private void removeFallDuck(FallDuck fallDuck) {

        this.fallDucks.removeIf(d -> d == fallDuck); // from ArrayList
        this.root.getChildren().removeIf(d -> d == fallDuck); //from Pane
    }
    private void setText(){
        scoreText.setText("Score: " + killedDucks*100);
        scoreText.setTranslateX(gameWidth/16*6);
        scoreText.setTranslateY(32);
        scoreText.setFont(Font.font(40));
        ammoText.setText("Ammo: " + ammo);
        ammoText.setTranslateX(gameWidth/100);
        ammoText.setTranslateY(32);
        ammoText.setFont(Font.font(20));
        roundText.setText("Round " + roundNum);
        roundText.setTranslateX(gameWidth/16*14);
        roundText.setTranslateY(32);
        roundText.setFont(Font.font(20));
        missText.setText("Missed: " + missCount + "/" + MAX_MISS_COUNT);
        missText.setTranslateX(gameWidth/16*13);
        missText.setTranslateY(gameHeight/16*15);
        missText.setFont(Font.font(20));
        if(missCount >= MAX_MISS_COUNT){
            gameOver.setText("GAME\nOVER");
            gameOver.setFont(Font.font(70));
            gameOver.setTranslateX(gameWidth/2);
            gameOver.setTranslateY(gameHeight/2);}
    }
    public static void main(String[] args) {
        launch(args);
    }
}
