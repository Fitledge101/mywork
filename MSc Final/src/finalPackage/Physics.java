/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalPackage;

import java.util.*;

/**
 * Physics class for Display and BouncingBall
 *
 * @author Ed 7/9/17
 */
public class Physics {

    /**
     * Check ball vs other balls for collisions
     *
     * @param a BouncingBall Object to check for collisions
     * @param list List of BouncingBalls to check against
     * @return true if collision
     */
    public static boolean Collision(BouncingBall a, ArrayList<BouncingBall> list) {

        for (int i = 0; i < list.size(); i++) {

            if (a == list.get(i)) {
                continue;
            }

            if (Calculate(a, list.get(i))) {

                return true;
            }

        }
        return false;
    }

    /**
     * Do the actual collision calculations based on the radius and position of
     * two balls
     *
     * @param a BouncingBall Object to check for collisions
     * @param b BouncingBall to check against
     * @return true if collision
     */
    public static boolean Calculate(BouncingBall a, BouncingBall b) {
        //initialise variables
        int aX = a.getXPosition();
        int bX = b.getXPosition();
        int aY = a.getYPosition();
        int bY = b.getYPosition();
        float aR = a.getRadius();
        float bR = b.getRadius();
        //do maths
        return Math.abs((aX - bX) * (aX - bX) + (aY - bY) * (aY - bY)) < (aR + bR) * (aR + bR);

    }

}
