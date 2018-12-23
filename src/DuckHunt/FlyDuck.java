package DuckHunt;

import com.sun.xml.internal.bind.v2.TODO;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import java.util.Random;

public class FlyDuck extends Sprite implements IMovable{
    private Image GUR0;
    private Image GUL0;
    private Image D;
    public static final int GOING_UP_RIGHT = 0;
    public static final int GOING_UP_LEFT = 1;
    private double speedX = 0.6;
    private double speedY = 0.4;
    private int currentState;

    FlyDuck(){
        super(Sprite.randomX(), 391,80,80,"duck",Color.BLANCHEDALMOND);
        loadImages();
        Random random = new Random();
        this.currentState = random.nextInt(2) ;
    }

    int getCurrentState(){
        return this.currentState;
    }
    void setCurrentState(int state){
        this.currentState = state;
    }
    private void loadImages(){
        GUR0 = new Image("DuckHunt/images/duckUpRight1.png");
        GUL0 = new Image("DuckHunt/images/duckUpLeft1.png");
        D = new Image("DuckHunt/images/duckDead.png");
    }

    public void setImage(int state){
        switch(state){
            case GOING_UP_RIGHT:
                this.setFill(new ImagePattern(GUR0));
                break;
            case GOING_UP_LEFT:
                this.setFill(new ImagePattern(GUL0));
                break;
            default:
                this.setFill(new ImagePattern(D));
        }
    }
    @Override
    public String toString() {
        return "FlyDuck (x=" + this.getTranslateX() + ", y=" + this.getTranslateY() + ", width=" + this.getWidth() + ", height="
                + this.getHeight() + ", color=" + this.getFill()+ ")";
    }

    public void setSpeed(int level){
        if(level == 1){
            this.speedX = 0.6;
            this.speedY = 0.2;
        }if(level == 2){
            this.speedX = 0.7;
            this.speedY = 0.3;
        }if(level == 3){
            this.speedX = 0.8;
            this.speedY = 0.4;
        }if(level == 4){
            this.speedX = 0.9;
            this.speedY = 0.5;
        }if(level == 5){
            this.speedX = 1.0;
            this.speedY = 0.6;
        }if(level == 6){
            this.speedX = 1.2;
            this.speedY = 0.8;
        }
    }

    @Override
    public void moveLeft(){
        setTranslateX(getTranslateX()- speedX);
    }
    @Override
    public void moveRight(){
        setTranslateX(getTranslateX()+ speedX);
    }
    @Override
    public void moveDown(){
        setTranslateY(getTranslateY()+ speedY);
    }
    @Override
    public void moveUp(){
        setTranslateY(getTranslateY()- speedY);
    }

    public void moveLikeDuck() {
        this.moveUp();

        if (this.currentState == GOING_UP_LEFT) {
            this.moveLeft();

            if (this.getTranslateX() < 0) {
                this.currentState = GOING_UP_RIGHT;
            }
        } else if (this.currentState == GOING_UP_RIGHT){
            this.moveRight();

            if (this.getTranslateX() + this.getWidth() > DuckHunt.gameWidth) {
                this.currentState = GOING_UP_LEFT;
            }
        }
    }
}
