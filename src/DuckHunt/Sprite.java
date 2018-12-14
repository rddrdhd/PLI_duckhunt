package DuckHunt;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;


public class Sprite extends Rectangle {
    private static final double STEP = 0.3;
    final String type;
    Sprite duck;
//    Image img = new Image("/duckDead.png");
    Sprite(int x, int y, int w, int h, String type, Color color){
        super(w,h,color);

 //       duck.setFill(new ImagePattern(img));

        this.type = type;
        setTranslateX(x);
        setTranslateY(y);
    }
    public static int randomX() {
        Random random = new Random();
        int randomValue = random.nextInt(DuckHunt.gameWidth) ;
        return randomValue;
    }
    void moveLeft(){
        setTranslateX(getTranslateX()-STEP);
    }
    void moveRight(){
        setTranslateX(getTranslateX()+STEP);
    }
    //void moveDown(){ setTranslateY(getTranslateY()+STEP); }
    void moveUp(){
        setTranslateY(getTranslateY()-STEP);
    }

    public boolean isInBound(double x, double y) {
        return (duck.getTranslateX() <= x) &&
                (x <= (duck.getTranslateX() + duck.getWidth())) &&
                (duck.getTranslateY() <= y) &&
                (y <= (duck.getTranslateY() + duck.getHeight()));
    }
}
