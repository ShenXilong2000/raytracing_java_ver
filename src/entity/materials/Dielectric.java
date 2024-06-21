package entity.materials;

import entity.*;
import utils.CommonUtils;

/**
 * 透明材质
 * @Author sxl
 * @Date 2024/6/20 16:30
 **/
public class Dielectric extends Material {
    // 真空或者空气中的折射率，或者材料的折射率比
    // 封闭介质的折射率

    private final double refractionIndex;

    private static double reflectance(double cosine, double refractionIndex) {
        // Use Schlick's approximation for reflectance
        double r0 = (1 - refractionIndex) / (1 + refractionIndex);
        r0 = r0*r0;
        return r0 + (1 - r0) * Math.pow((1 - cosine), 5);
    }

    public Dielectric(double refractionIndex) {
        this.refractionIndex = refractionIndex;
    }

    @Override
    public Ray scatter(Ray rayIn, HitRecord record) {
        double ri = record.frontFace ? (1.0/refractionIndex) : refractionIndex;

        Vec3 unitDirection = rayIn.direction().unitVector();
        double cosTheta = Math.min(unitDirection.oppositeVector().dot(record.normal), 1.0);
        double sinTheta = Math.sqrt(1.0 - cosTheta*cosTheta);

        boolean cannotRefract = ri * sinTheta > 1.0;
        Vec3 direction;

        if (cannotRefract || reflectance(cosTheta, ri) > CommonUtils.randomDouble()) {
            direction = Vec3.reflect(unitDirection, record.normal);
        } else {
            direction = Vec3.refract(unitDirection, record.normal, ri);
        }

        return new Ray(record.p, direction);
    }

}
