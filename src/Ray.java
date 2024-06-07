/**
 * @Author sxl
 * @Date 2024/6/3 12:39
 **/
public class Ray {
    private Vec3 orig;
    private Vec3 dir;

    public Ray() {
    }

    public Ray(Vec3 orig, Vec3 dir) {
        this.orig = orig;
        this.dir = dir;
    }

    public Vec3 origin() {
        return orig;
    }

    public Vec3 direction() {
        return dir;
    }

    // orig + dir * t
    public Vec3 at(double t) {
        return this.orig.add(this.dir.multiply(t));
    }
}
