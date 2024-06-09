package entity.object;

import entity.HitRecord;
import entity.Hittable;
import entity.Ray;
import entity.Vec3;

/**
 * @Author sxl
 * @Date 2024/6/7 16:52
 **/
public class Sphere extends Hittable {
    private final Vec3 center;
    private final double radius;

    public Sphere(Vec3 center, double radius) {
        this.center = center;
        this.radius = Math.max(radius, 0);
    }

    @Override
    public boolean hit(Ray ray, double rayTMin, double rayTMax, HitRecord rec) {
        Vec3 oc = center.subtract(ray.origin());
        double a = ray.direction().lengthSquared();
        double h = ray.direction().dot(oc);
        double c = oc.lengthSquared() - radius * radius;

        double discriminant = h*h - a*c;
        if (discriminant < 0) {
            return false;
        }

        double sqrtd = Math.sqrt(discriminant);

        // 查找可接受范围内的根
        double root = (h - sqrtd) / a;
        if (root <= rayTMin || root >= rayTMax) {
            root = (h + sqrtd) / a;
            if (root <= rayTMin || root >= rayTMax) {
                return false;
            }
        }

        rec.t = root;
        rec.p = ray.at(rec.t);
        Vec3 outwardNormal = rec.p.subtract(center).divide(radius);
        rec.setFaceNormal(ray, outwardNormal);

        return true;
    }
}
