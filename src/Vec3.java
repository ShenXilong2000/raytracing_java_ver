/**
 * @Author sxl
 * @Date 2024/5/30 17:41
 **/
public class Vec3 {
    private final double x;
    private final double y;
    private final double z;

    public static final Vec3 zero = new Vec3(0.0, 0.0, 0.0);

    public Vec3() {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }

    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double x() {
        return this.x;
    }

    public double y() {
        return this.y;
    }

    public double z() {
        return this.z;
    }

    public int r() {
        return (int) (255.99 * this.x);
    }

    public int g() {
        return (int) (255.99 * this.y);
    }

    public int b() {
        return (int) (255.99 * this.z);
    }

    public Vec3 add(Vec3 v) {
        return new Vec3(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    public Vec3 subtract(Vec3 v) {
        return new Vec3(this.x - v.x, this.y - v.y, this.z - v.z);
    }

    public Vec3 multiply(double t) {
        return new Vec3(this.x * t, this.y * t, this.z * t);
    }

    public Vec3 multiply(Vec3 v) {
        return new Vec3(this.x * v.x, this.y * v.y, this.z * v.z);
    }

    public Vec3 divide(double t) {
        return this.multiply(1.0/t);
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public double lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    public double dot(Vec3 v) {
        return this.x * v.x + this.y * v.y + this.z * v.z;
    }

    public Vec3 cross(Vec3 v) {
        return new Vec3(
                this.y * v.z - this.z * v.y,
                this.z * v.x - this.x * v.z,
                this.x * v.y - this.y * v.x
        );
    }

    // 归一化
    public Vec3 unitVector() {
        return this.divide(this.length());
    }
}
