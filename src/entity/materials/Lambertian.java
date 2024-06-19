package entity.materials;

import entity.*;


/**
 * @Description 漫反射
 * @Author sxl
 * @Date 2024/6/19 14:22
 **/
public class Lambertian extends Material {
    private final Color albedo;
    public Lambertian(Color albedo) {
        this.albedo = albedo;
    }

    @Override
    public Ray scatter(Ray rayIn, HitRecord record) {
        Vec3 scatterDirection = record.normal.add(Vec3.randomUnitVector());

        if (scatterDirection.nearZero()) {
            scatterDirection = record.normal;
        }

        return new Ray(record.p, scatterDirection);
    }

    @Override
    public Color getAttenuationColor() {
        return albedo;
    }
}
