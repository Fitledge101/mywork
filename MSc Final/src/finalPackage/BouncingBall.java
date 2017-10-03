package finalPackage;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Random;

//to store a direction
enum Direction {
    NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST;
}

/**
 * Class BouncingBall - creates a ball that can collide and play music
 *
 *
 * @author Ed Taylor based on original version by:
 * @author Michael Kölling (mik)
 * @author David J. Barnes
 * @author Bruce Quig
 *
 * @version 2017.09.07
 */
public class BouncingBall {

    private final Random random;
    private final Display canvas;
    private Ellipse2D.Double circle;
    private Color color;
    private final int id;
    private final int diameter;
    private int xPosition;
    private int yPosition;
    private int speedX;
    private int speedY;
    private final float radius;
    private Direction currentDirection;
    private final int xLimit;
    private final int yLimit;
    private final int baseline;
    private final int whichNote;

    /**
     * Constructor for objects of class BouncingBall
     *
     * @param xPos the horizontal coordinate of the ball
     * @param yPos the vertical coordinate of the ball
     * @param ballDiameter the diameter (in pixels) of the ball
     * @param drawingCanvas the canvas to draw this ball on
     * @param ID unique ID
     * @param xLimit rightmost ball can go
     * @param yLimit highest ball can go
     * @param stringColour specify ball colour
     *
     */
    public BouncingBall(int xPos, int yPos, int ballDiameter, Display drawingCanvas, int ID, int xLimit, int yLimit, String stringColour) {
        random = new Random();
        xPosition = xPos;
        yPosition = yPos;

        //lower limit of x and y
        baseline = 30;
        //default bounds are x 50-900 and y 50-600
        this.xLimit = xLimit - 45;//safe limit 
        this.yLimit = yLimit - 100;

        //select initial  speed at random
        speedX = random.nextInt(5) + 1;
        speedY = random.nextInt(5) + 1;

        //set tone of the ball
        whichNote = 105 - ballDiameter + speedX + speedY;//make the default tone a bit higher

        //load up some variables from constructor
        diameter = ballDiameter;
        radius = diameter / 2;
        canvas = drawingCanvas;

        //give each ball a unique ID from 1 to size of list+1
        id = ID + 1;

        //select an initial direction at random
        switch (random.nextInt(4) + 1) {
            case 1:
                currentDirection = Direction.NORTHEAST;
                break;
            case 2:
                currentDirection = Direction.SOUTHEAST;
                break;
            case 3:
                currentDirection = Direction.NORTHWEST;
                break;
            case 4:
                currentDirection = Direction.SOUTHWEST;
                break;
            default:
                System.out.println("constructor error");
                break;
        }

        //make pastel colour:
        float hue = random.nextFloat();
        final float saturation = (random.nextInt(2000) + 1000) / 10000f;
        final float luminance = 0.9f;

        // assign colours 
        switch (stringColour) {
            case "red":
                color = Color.RED;
                break;
            case "green":
                color = Color.green;
                break;
            case "blue":
                color = Color.blue;
                break;
            case "magenta":
                color = Color.MAGENTA;
                break;
            case "white":
                color = Color.WHITE;
                break;
            case "yellow":
                color = Color.YELLOW;
                break;
            default:
                //pastel colour
                color = Color.getHSBColor(hue, saturation, luminance);
                break;
        }

    }

    /**
     * Move this ball according to its position and speed and redraw.
     *
     * @param list of BouncingBalls
     */
    public void move(ArrayList<BouncingBall> list) {

        // remove from canvas at the current position
        erase();

        if (Physics.Collision(this, list)) {
            ballCollision();
            draw();
            return;

        }

        switch (currentDirection) {

            case SOUTHEAST:

                if (xMaxCollision()) {

                    goSW();
                    currentDirection = Direction.SOUTHWEST;

                } else if (yMinCollision()) {

                    goNE();

                    currentDirection = Direction.NORTHEAST;
                    //no collision
                } else {

                    goSE();
                }

                break;

            case SOUTHWEST:

                if (xMinCollision()) {

                    goSE();
                    currentDirection = Direction.SOUTHEAST;

                } else if (yMinCollision()) {

                    goNW();

                    currentDirection = Direction.NORTHWEST;
                } else {

                    goSW();
                }

                break;

            case NORTHWEST:

                if (xMinCollision()) {

                    goNE();
                    currentDirection = Direction.NORTHEAST;

                } else if (yMaxCollision()) {

                    goSW();

                    currentDirection = Direction.SOUTHWEST;

                } else {

                    goNW();
                }

                break;

            case NORTHEAST:

                if (xMaxCollision()) {

                    goNW();
                    currentDirection = Direction.NORTHWEST;

                } else if (yMaxCollision()) {

                    goSE();
                    currentDirection = Direction.SOUTHEAST;

                } else {

                    goNE();
                }

                break;

            default:
                System.out.println("move() error");
                break;
        }

        draw();

    }

    /**
     * Move Ball North East on Canvas
     */
    public void goNE() {
        xPosition += speedX;
        yPosition -= speedY;
    }

    /**
     * Move Ball North West on Canvas
     */
    public void goNW() {
        xPosition -= speedX;
        yPosition -= speedY;
    }

    /**
     * Move Ball South East on Canvas
     */
    public void goSE() {
        xPosition += speedX;
        yPosition += speedY;
    }

    /**
     * Move Ball South West on Canvas
     *
     */
    public void goSW() {
        xPosition -= speedX;
        yPosition += speedY;
    }

    /**
     * Detects collision on xMax
     *
     * @return true if collision
     *
     */
    public boolean xMaxCollision() {
        return (xPosition + speedX + radius) >= xLimit;
    }

    /**
     * Detects collision on yMin
     *
     * @return true if collision
     */
    public boolean yMinCollision() {
        return (yPosition + speedY + radius) >= yLimit;
    }

    /**
     * Detects collision on xMin
     *
     * @return true if collision
     */
    public boolean xMinCollision() {
        return (xPosition - speedX + radius) <= baseline;
    }

    /**
     * Detects collision on yMax
     *
     * @return true if collision
     *
     */
    public boolean yMaxCollision() {
        return (yPosition - speedY + radius) <= baseline;
    }

    /**
     * Get the current note
     *
     * @return int note
     *
     */
    public int getNote() {
        return whichNote;
    }

    /**
     * Overall manager of ball collision response
     */
    public void ballCollision() {
        collision();

    }

    /**
     * Manages collision on X Axis
     */
    public void collision() {

        switch (currentDirection) {

            case NORTHEAST:
                goSW();
                currentDirection = Direction.SOUTHWEST;

                break;

            case NORTHWEST:
                goSE();
                currentDirection = Direction.SOUTHEAST;

                break;

            case SOUTHEAST:
                goNW();
                currentDirection = Direction.NORTHWEST;

                break;

            case SOUTHWEST:
                goNE();
                currentDirection = Direction.NORTHEAST;

                break;

            default:
                System.out.println("error in collision()");
                break;
        }
    }

    /**
     * Dictates ball's reaction to a yCollision can be called to ballCollision()
     * but causes issues currently unused
     */
    public void yCollision() {
        switch (currentDirection) {

            case SOUTHEAST:

                currentDirection = Direction.NORTHEAST;
                break;

            case SOUTHWEST:

                currentDirection = Direction.NORTHWEST;
                break;

            case NORTHEAST:

                currentDirection = Direction.SOUTHEAST;
                break;

            case NORTHWEST:

                currentDirection = Direction.SOUTHWEST;
                break;

            default:
                System.out.println("error in yCollision()");
                break;
        }
    }

    /**
     *
     * * @return String of ball's current direction
     */
    public String reportDirection() {

        switch (currentDirection) {
            case SOUTHWEST:
                return "SW";

            case NORTHWEST:
                return "NW";

            case SOUTHEAST:
                return "SE";

            case NORTHEAST:
                return "NE";

            default:
                return "fail";

        }

    }

    /**
     * return the horizontal position of this ball
     *
     * @return
     */
    public int getXPosition() {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     *
     * @return
     */
    public int getYPosition() {
        return yPosition;
    }

    /**
     * Sets new colour for ball
     *
     * @param newColour choice of colour to assign to ball
     */
    public void setColor(Color newColour) {

        color = newColour;

    }

    /**
     *
     * @return current colour if specified
     */
    public String checkColour() {
        switch (id) {
            case 0:
                return "orange";
            case 1:
                return "green";
            case 2:
                return "blue";
            case 3:
                return "magenta";
            default:
                return "no colour found";
        }
    }

    /**
     *
     * @return ID of Ball
     */
    public int getID() {
        return id;
    }

    /**
     *
     * @return radius of ball
     */
    public float getRadius() {

        return radius;
    }

    /**
     *
     * @return diameter of ball
     */
    public int getDiameter() {
        return diameter;
    }

    /**
     *
     * @return speedX of ball
     */
    public int getXSpeed() {
        return speedX;
    }

    /**
     *
     * @return speedY of ball
     *
     */
    public int getYSpeed() {
        return speedY;
    }

    /**
     *
     * Set new speedX for ball
     *
     * @param xSpeed desired speedX
     */
    public void setXSpeed(int xSpeed) {
        if (speedX <= 1) {
            speedX = 1;
        } else if (speedX >= 6) {
            speedX = 6;
        }
        speedX = xSpeed;
    }

    /**
     *
     * Set new speedY for ball
     *
     * @param ySpeed desired speedX
     */
    public void setYSpeed(int ySpeed) {
        speedY = ySpeed;
    }

    /**
     * Draw this ball at its current position onto the canvas.
     *
     */
    public void draw() {
        canvas.setForegroundColor(color);
        circle = new Ellipse2D.Double(xPosition, yPosition, diameter, diameter);
        canvas.fill(circle);
    }

    /**
     * Erase this ball at its current position.
     *
     */
    public void erase() {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }

}
