package entity;

/**
 * @Author sxl
 * @Date 2024/6/19 11:37
 **/
public abstract class Material {
    public Material() {}

    public boolean scatter (Ray rayIn, HitRecord record, Color attenuation, Ray scattered) {
        return false;
    }
}
