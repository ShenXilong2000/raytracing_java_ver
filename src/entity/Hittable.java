package entity;

/**
 * @Author sxl
 * @Date 2024/6/7 16:46
 **/
public abstract class Hittable {
    public Hittable() {
    }

    public HitRecord hit(Ray ray, Interval rayT) {
        return null;
    }
}
