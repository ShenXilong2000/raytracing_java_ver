package entity;

/**
 * @Author sxl
 * @Date 2024/6/19 11:37
 **/
public abstract class Material {
    public Material() {}

    public Ray scatter (Ray rayIn, HitRecord record) {
        return null;
    }

    public Color getAttenuationColor() {
        return Color.WHITE;
    }
}
