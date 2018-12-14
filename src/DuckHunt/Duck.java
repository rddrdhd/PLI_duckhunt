package DuckHunt;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import java.util.Random;

public class Duck extends Sprite implements IMovable{
    private  static Sprite duck;
    private Image GUR0;
    private Image GUR1;
    private Image GUL0;
    private Image GUL1;
    private Image GR;
    private Image GL;
    private Image GU;
    private Image D;
    private Image F0;
    private Image F1;
    private Image F2;
    private Image F3;
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
        loadImages();
        Random random = new Random();
        this.currentState = random.nextInt(2) ;
        //Image img = new Image("DuckHunt/images/duckDead.png");
        //this.setFill(new ImagePattern(img));
    }
    public int getCurrentState(){
        return this.currentState;
    }
    public void setCurrentState(int state){
        this.currentState = state;
    }
    private void loadImages(){
        GUR0 = new Image("DuckHunt/images/duckUpRight1.png");
        GUR1 = new Image("DuckHunt/images/duckUpRight0.png");
        GUL0 = new Image("DuckHunt/images/duckUpLeft1.png");
        GUL1 = new Image("DuckHunt/images/duckUpLeft0.png");
        F0 = new Image("DuckHunt/images/duckPrecipitate0.png");
        F1 = new Image("DuckHunt/images/duckPrecipitate1.png");
        F2 = new Image("DuckHunt/images/duckPrecipitate2.png");
        F3 = new Image("DuckHunt/images/duckPrecipitate3.png");
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
            case DYING:
                this.setFill(new ImagePattern(D));
                break;
            case FALLING:
                this.setFill(new ImagePattern(F0));
        }
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
                this.currentState = GOING_UP_RIGHT;
            }
        } else if (this.currentState == GOING_UP_RIGHT){
            this.moveRight();

            if (this.getTranslateX() + this.getWidth() > DuckHunt.gameWidth) {
                this.currentState = GOING_UP_LEFT;
            }/*
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
        }*/

    }


}}
