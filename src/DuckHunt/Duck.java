package DuckHunt;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import java.util.Random;

public class Duck extends Sprite implements IMovable{
    private  static Sprite duck;

    public static final int GOING_UP_RIGHT = 0;
    public static final int GOING_UP_LEFT = 1;
    public static final int GOING_RIGHT = 2;
    public static final int GOING_LEFT = 3;
    public static final int GOING_UP = 4;
    public static final int DYING = 5;
    public static final int FALLING = 6;
    public static final int DEAD = 7;
    private double speedX = 0.6;
    private double speedY = 0.4;//new Random().nextFloat(1.0);
    private int currentState;
    Duck(){
        super(Sprite.randomX(), 391,80,80,"duck",Color.BLANCHEDALMOND);

        Random random = new Random();
        this.currentState = random.nextInt(2) ;
        Image img = new Image("DuckHunt/duckDead.png");
        this.setFill(new ImagePattern(img));
        //Image img = new Image("/home/leja/Desktop/PJI/MyJavaProject/src/DuckHunt/duckDead.png");
        //duck.setFill(new ImagePattern(img));
    }
    public int getCurrentState(){
        return this.currentState;
    }
    public void setCurrentState(int state){
        this.currentState = state;
    }

    @Override
    public String toString() {
        return "Duck (x=" + this.getTranslateX() + ", y=" + this.getTranslateY() + ", width=" + this.getWidth() + ", height="
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
                this.currentState = 0;
            }
        } else if (this.currentState == GOING_UP_RIGHT){
            this.moveRight();

            if (this.getTranslateX() + this.getWidth() > DuckHunt.gameWidth) {
                this.currentState = 1;
            }
        } else if(this.currentState == GOING_UP){
            this.moveUp();
        } else if(this.currentState == GOING_LEFT){
            this.moveLeft();
        }else if (this.currentState == GOING_RIGHT){
            this.moveRight();
        }else if(this.currentState == DYING){
            System.out.println("BOOM");
            //wait
            this.setCurrentState(FALLING);
        }
        if (this.currentState == FALLING){
            this.moveDown();
        }
        if(this.currentState == DEAD){
            //this.setDisabled(true);
            System.out.println("DEAD" + this);
        }

    }

    public boolean isInBound(double x, double y) {
        return (duck.getTranslateX() <= x) &&
                (x <= (duck.getTranslateX() + duck.getWidth())) &&
                (duck.getTranslateY() <= y) &&
                (y <= (duck.getTranslateY() + duck.getHeight()));
    }
}
