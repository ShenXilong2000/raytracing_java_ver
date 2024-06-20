package entity.materials;

import entity.*;

/**
 * @Author sxl
 * @Date 2024/6/19 17:00
 **/
public class Metal extends Material {
    private final Color albedo;
    private final double fuzz;      // 模糊反射 球

    public Metal(Color albedo, double fuzz) {
        this.albedo = albedo;
        this.fuzz = fuzz < 1 ? fuzz : 1;
    }

    @Override
    public Ray scatter(Ray rayIn, HitRecord record) {
        Vec3 reflected = Vec3.reflect(rayIn.direction(), record.normal);
        reflected = reflected.unitVector().add(Vec3.randomUnitVector().multiply(fuzz));
        Ray scattered = new Ray(record.p, reflected);
        if (scattered.direction().dot(record.normal) > 0) {
            return scattered;
        } else {
            return null;
        }
    }

    @Override
    public Color getAttenuationColor() {
        return albedo;
    }
}
