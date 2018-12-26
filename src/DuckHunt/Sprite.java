package DuckHunt;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.Random;
// Motherclass of ducks
class Sprite extends Rectangle {
    private static final double STEP = 0.3;
    final String type;
    Sprite duck;
    Sprite(int x, int y, int w, int h, String type, Color color){
        super(w,h,color);

        this.type = type;
        setTranslateX(x);
        setTranslateY(y);
    }
    public static int randomX() {
        Random random = new Random();
        int randomValue = random.nextInt((DuckHunt.gameWidth-80)) ;
        return randomValue;
    }
    void moveLeft(){
        setTranslateX(getTranslateX()-STEP);
    }
    void moveRight(){
        setTranslateX(getTranslateX()+STEP);
    }
    void moveDown(){ setTranslateY(getTranslateY()+STEP); }
    void moveUp(){
        setTranslateY(getTranslateY()-STEP);
    }

}
