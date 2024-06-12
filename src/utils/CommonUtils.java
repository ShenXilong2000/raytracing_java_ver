package utils;

import java.util.Random;

public class CommonUtils {
    public static final double INFINITY = Double.POSITIVE_INFINITY;
    public static final double PI = 3.14159265358979323846;

    public static double degreesToRadians(double degrees) {
        return degrees * PI / 180.0;
    }

    public static double randomDouble() {
        // [0, 1)
        return new Random().nextDouble();
    }

    public static double randomDouble(double min, double max) {
        // [min, max)
        return min + (max-min)*randomDouble();
    }
}
