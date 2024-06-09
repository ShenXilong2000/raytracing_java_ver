package entity;

import entity.Vec3;

/**
 * @Author sxl
 * @Date 2024/6/7 16:45
 **/
public class HitRecord {
    public Vec3 p;
    public Vec3 normal;
    public double t;
    public boolean frontFace;

    // outwardNormal 法线已经归一化
    public void setFaceNormal(Ray r, Vec3 outwardNormal) {
        frontFace = r.direction().dot(outwardNormal) < 0;
        normal = frontFace ? outwardNormal : outwardNormal.oppositeVector();

    }
}
