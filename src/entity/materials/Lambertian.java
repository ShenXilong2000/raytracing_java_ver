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
    public boolean scatter(Ray rayIn, HitRecord record, Color attenuation, Ray scattered) {
        Vec3 scatterDirection = record.normal.add(Vec3.randomUnitVector());

        if (scatterDirection.nearZero()) {
            scatterDirection = record.normal;
        }

        scattered = new Ray(record.p, scatterDirection);
        attenuation = albedo;
        return true;
    }
}
