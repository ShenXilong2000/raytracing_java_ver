package entity;

import utils.CommonUtils;

/**
 * @Author sxl
 * @Date 2024/5/30 17:41
 **/
public class Vec3 {
    protected final double x;
    protected final double y;
    protected final double z;

    public static final Vec3 zero = new Vec3(0.0, 0.0, 0.0);

    public Vec3() {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }

    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double x() {
        return this.x;
    }

    public double y() {
        return this.y;
    }

    public double z() {
        return this.z;
    }

    public Vec3 add(Vec3 v) {
        return new Vec3(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    public Vec3 subtract(Vec3 v) {
        return new Vec3(this.x - v.x, this.y - v.y, this.z - v.z);
    }

    public Vec3 multiply(double t) {
        return new Vec3(this.x * t, this.y * t, this.z * t);
    }

    public Vec3 multiply(Vec3 v) {
        return new Vec3(this.x * v.x, this.y * v.y, this.z * v.z);
    }

    public Vec3 divide(double t) {
        return this.multiply(1.0/t);
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public double lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    public boolean nearZero() {
        double s = 1e-8;
        return (Math.abs(this.x) < s) && (Math.abs(this.y) < s) && (Math.abs(this.z) < s);
    }

    public double dot(Vec3 v) {
        return this.x * v.x + this.y * v.y + this.z * v.z;
    }

    public Vec3 cross(Vec3 v) {
        return new Vec3(
                this.y * v.z - this.z * v.y,
                this.z * v.x - this.x * v.z,
                this.x * v.y - this.y * v.x
        );
    }

    // 归一化
    public Vec3 unitVector() {
        return this.divide(this.length());
    }

    public Vec3 oppositeVector () {
        return zero.subtract(this);
    }


    public static Vec3 random() {
        return new Vec3(CommonUtils.randomDouble(), CommonUtils.randomDouble(), CommonUtils.randomDouble());
    }

    public static Vec3 random(double min, double max) {
        return new Vec3(CommonUtils.randomDouble(min, max), CommonUtils.randomDouble(min, max), CommonUtils.randomDouble(min, max));
    }


    public static Vec3 randomInUnitSphere() {
        while (true) {
            Vec3 p = Vec3.random(-1, 1);
            if (p.lengthSquared() < 1) {
                return p;
            }
        }
    }

    public static Vec3 randomUnitVector() {
        return randomInUnitSphere().unitVector();
    }

    public static Vec3 randomOnHemisphere(Vec3 normal) {
        Vec3 onUnitSphere = randomUnitVector();
        if (onUnitSphere.dot(normal) > 0.0) {
            return onUnitSphere;
        } else {
            return onUnitSphere.oppositeVector();
        }
    }
}
