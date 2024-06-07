package entity;

/**
 * @Author sxl
 * @Date 2024/6/7 16:46
 **/
public abstract class Hittable {
    public Hittable() {
    }

    public boolean hit(Ray ray, double rayTMin, double rayTMax, HitRecord rec) {
        return false;
    }
}
