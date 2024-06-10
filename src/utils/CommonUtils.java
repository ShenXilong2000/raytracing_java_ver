package utils;

public class CommonUtils {
    public static final double INFINITY = Double.POSITIVE_INFINITY;
    public static final double PI = 3.14159265358979323846;

    public static double degreesToRadians(double degrees) {
        return degrees * PI / 180.0;
    }
}
