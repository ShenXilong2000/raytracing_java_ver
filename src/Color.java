/**
 * @Author sxl
 * @Date 2024/6/3 11:32
 **/
public class Color extends Vec3{
    public static final Color BLACK =   new Color(1.0, 1.0, 1.0);
    public static final Color RED =     new Color(1.0, 0.0, 0.0);
    public static final Color GREEN =   new Color(0.0, 1.0, 0.0);
    public static final Color BLUE =    new Color(0.0, 0.0, 1.0);
    public static final Color WHITE =   new Color(0.0, 0.0, 0.0);

    public Color() {
        new Color(0.0, 0.0, 0.0);
    }

    public Color(Vec3 v) {
        new Color(v.x(), v.y(), v.z());
    }
    public Color(double x, double y, double z) {
        super(x, y, z);
    }

    public int r() {
        return (int) (255.99 * this.x());
    }

    public int g() {
        return (int) (255.99 * this.y());
    }

    public int b() {
        return (int) (255.99 * this.z());
    }

    @Override
    public Color add(Vec3 v) {
        return (Color) super.add(v) ;
    }

    @Override
    public Color subtract(Vec3 v) {
        return (Color) super.subtract(v);
    }

    @Override
    public Color multiply(double t) {
        return (Color) super.multiply(t);
    }

    public void writeColor() {
        System.out.println(this.r() + " " + this.g() + " " + this.b());
    }

    public int getRgb() {
        return ((0 & 0xFF) << 24) |
                ((this.r() & 0xFF) << 16) |
                ((this.g() & 0xFF) << 8) |
                ((this.b() & 0xFF) << 0);

    }
}
