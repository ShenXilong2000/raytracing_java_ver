package entity.materials;

import entity.*;

/**
 * 透明材质
 * @Author sxl
 * @Date 2024/6/20 16:30
 **/
public class Dielectric extends Material {
    // 真空或者空气中的折射率，或者材料的折射率比
    // 封闭介质的折射率

    private final double refractionIndex;

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

        if (cannotRefract) {
            direction = Vec3.reflect(unitDirection, record.normal);
        } else {
            direction = Vec3.refract(unitDirection, record.normal, ri);
        }

        return new Ray(record.p, direction);
    }

}
