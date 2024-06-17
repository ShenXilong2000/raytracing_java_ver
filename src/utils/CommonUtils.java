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

    // 进度条
    public static void progressBar(int i, int total) {
        int per = (int)(1.0 * i / total * 100 / 5) ;
        StringBuilder bar = new StringBuilder();
        for (int j = 0; j < per; j++) {
            bar.append("@");
        }
        for (int j = 0; j < 20 - per - 1; j++) {
            bar.append("-");
        }
        StringBuilder remainNumber = new StringBuilder();
        for (int j = 0; j <  5 - String.valueOf(total - i - 1).length(); j++) {
            remainNumber.append(" ");
        }
        remainNumber.append(total - i - 1);
        System.out.print("\rScanLines remaining: " + remainNumber + " ");
        System.out.print("[" + bar + "]  ");
        System.out.flush();
    }
}
