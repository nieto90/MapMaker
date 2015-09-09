package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 *
 * @author nieto
 */
public class CustomFunctions {
    
    public static int randInt(int min, int max) {
        Random rand = new Random();

        int random = min + (int)(Math.random() * (max-min + 1));
        
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return random;
    }
    
    public static double round(double value, int decimals) {
        BigDecimal bd = new BigDecimal(value).setScale(decimals, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }
    
    public static boolean isAtDistance(double x1, double z1, 
            double x2, double z2, double distance){
        double X = Math.abs(x1-x2);
        double Z = Math.abs(z1-z2);
        double dist = Math.sqrt(X*X + Z*Z);
        
        return (Math.abs(dist - distance) < 1);
    }
    
    public static boolean isAtDistance(double x1, double y1, double z1, 
            double x2, double y2, double z2, double distance){
        double X = Math.abs(x1-x2);
        double Y = Math.abs(y1-y2);
        double Z = Math.abs(z1-z2);
        double dist = Math.sqrt(X*X + Y*Y + Z*Z);
        
        return (Math.abs(dist - distance) < 1);
    }
    /**
     * 
     * @param x1 First X coord
     * @param z1 First Z coord
     * @param x2 Second X coord
     * @param z2 Second Z coord
     * @param distance Distance to compare
     * @return a negative integer, zero, or a positive integer as the calculated distance 
     * is less than, equal to, or greater than the given distance.
     */
    public static int compareDistances(double x1, double z1, 
            double x2, double z2, double distance){
        double X = Math.abs(x1-x2);
        double Z = Math.abs(z1-z2);
        double dist = Math.sqrt(X*X + Z*Z);
        
        if(Math.abs(dist - distance) < 1) return 0;
        return (int) (dist - distance);
    }
}
