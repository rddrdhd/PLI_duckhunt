package DuckHunt;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import java.util.Random;

public class FallDuck extends Sprite implements IMovable{
    private Image imgA;
    private Image imgB;
    private Image imgC;
    private Image imgD;
    private double speedX ; //We dont need that, the duck just falls
    private double speedY ;
    public static final int A = 0; //for currentstate -> images
    public static final int B = 1;
    public static final int C = 2;
    public static final int D = 3;

    private long time = System.currentTimeMillis();


    private int currentState;

    FallDuck(){
        super(Sprite.randomX(), 0,43,73,"duck",Color.BLANCHEDALMOND);
        loadImages();
        Random random = new Random();
        this.currentState = random.nextInt(4) ; //Random image from 4 falling images
    }

    int getCurrentState(){
        return this.currentState;
    }
    void setCurrentState(){

        long currTime = System.currentTimeMillis();
        if(time + 50 >= currTime){
            this.currentState = this.getCurrentState()+1;
        }
    }

    private void loadImages(){
        imgA = new Image("DuckHunt/images/duckPrecipitate0.png");
        imgB = new Image("DuckHunt/images/duckPrecipitate1.png");
        imgC = new Image("DuckHunt/images/duckPrecipitate2.png");
        imgD = new Image("DuckHunt/images/duckPrecipitate3.png");
    }

    public void setImage(int state){
        switch(state){
            case A:
                this.setFill(new ImagePattern(imgA));
                break;
            case B:
                this.setFill(new ImagePattern(imgB));
                break;
            case C:
                this.setFill(new ImagePattern(imgC));
                break;
            case D:
                this.setFill(new ImagePattern(imgD));
            default:
                this.setFill(new ImagePattern(imgA));
        }
    }
    @Override
    public String toString() {
        return "FlyDuck (x=" + this.getTranslateX() + ", y=" + this.getTranslateY() + ", width=" + this.getWidth() + ", height="
                + this.getHeight() + ", color=" + this.getFill()+ ")";
    }

    public void setSpeed(int level){
        switch(level){
            case 1:
                this.speedY = 0.0;
            case 2:
                this.speedY = 0.3;
            case 3:
                this.speedY = 0.4;
            case 4:
                this.speedY = 0.5;
            case 5:
                this.speedY = 0.6;
            case 6:
                this.speedY = 0.7;
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




}
