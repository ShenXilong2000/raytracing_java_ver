package entity.materials;

import entity.*;

/**
 * @Author sxl
 * @Date 2024/6/19 17:00
 **/
public class Metal extends Material {
    private final Color albedo;

    public Metal(Color albedo) {
        this.albedo = albedo;
    }

    @Override
    public Ray scatter(Ray rayIn, HitRecord record) {
        Vec3 reflected = Vec3.reflect(rayIn.direction(), record.normal);
        return new Ray(record.p, reflected);
    }

    @Override
    public Color getAttenuationColor() {
        return albedo;
    }
}
