/**
 * @Author sxl
 * @Date 2024/6/3 11:32
 **/
public class Color {

    public static void writeColor(Vec3 pixelColor) {
        System.out.println(pixelColor.r() + " " + pixelColor.g() + " " + pixelColor.b());
    }

    public static int getRgb(Vec3 color) {
        return ((0 & 0xFF) << 24) |
                ((color.r() & 0xFF) << 16) |
                ((color.g() & 0xFF) << 8) |
                ((color.b() & 0xFF) << 0);

    }
}
