package modules;

import entity.*;
import utils.CommonUtils;
import utils.FileUtils;

import java.awt.image.BufferedImage;

/**
 * @Author sxl
 * @Date 2024/6/12 15:20
 **/
public class Camera {
    private int imageHeight;            // Rendered image height
    private double pixelSampleScale;    // Color scale factor for a sum of pixel samples
    private Vec3 center;                // Camera center
    private Vec3 pixel00_loc;           // Location of pixel 0, 0
    private Vec3 pixelDeltaU;           // Offset of pixel to the right
    private Vec3 pixelDeltaV;           // Offset of pixel below

    public double aspectRatio = 1.0;    // Ratio of image width over height
    public int imageWidth = 100;        // Rendered image width in pixel count
    public int samplesPerPixel = 10;    // Count of random samples for each pixel

    public Camera() {
    }

    private void initialize() {
        imageHeight = (int) (imageWidth / aspectRatio);
        imageHeight = Math.max(1, imageHeight);

        pixelSampleScale = 1.0 / samplesPerPixel;

        center = Vec3.zero;

        // Determine viewport dimensions
        double focalLength = 1.0;
        double viewportHeight = 2.0;
        double viewportWidth = viewportHeight * ((double) imageWidth / imageHeight);

        // 计算横向和竖向视口边缘的向量
        Vec3 viewportU = new Vec3(viewportWidth, 0,0);
        Vec3 viewportV = new Vec3(0, -viewportHeight, 0);

        // 计算水平方向和垂直视口边缘的向量
        pixelDeltaU = viewportU.divide(imageWidth);
        pixelDeltaV = viewportV.divide(imageHeight);

        // 计算左上角的像素
        Vec3 viewportUpperLeft = center.subtract(new Vec3(0, 0, focalLength))
                .subtract(viewportU.divide(2))
                .subtract(viewportV.divide(2));
        pixel00_loc = pixelDeltaU.add(pixelDeltaV).divide(2).add(viewportUpperLeft);
    }

    private static Color rayColor(Ray ray, Hittable world) {
        // 世界物体
        HitRecord hitRecord = world.hit(ray, new Interval(0, CommonUtils.INFINITY));
        if (hitRecord != null) {
            Vec3 direction = Vec3.randomOnHemisphere(hitRecord.normal);
            return rayColor(new Ray(hitRecord.p, direction), world).multiply(0.5);
        }

        // 背景
        Vec3 unitDirection = ray.direction().unitVector();
        double a = 0.5 * (unitDirection.y() + 1.0);
        return new Color(1.0, 1.0, 1.0).multiply(1.0 - a).add(new Color(0.5, 0.7, 1.0).multiply(a));
    }

    private Ray getRay(int i, int j) {
        // 构造一个相机光线，从原点出发，随机采样
        // 像素位置i, j周围的点
        Vec3 offset = sampleSquare();
        Vec3 pixelSample = pixel00_loc
                .add(pixelDeltaU.multiply(i+offset.x()))
                .add(pixelDeltaV.multiply(j+offset.y()));
        Vec3 rayOrigin = center;
        Vec3 rayDirection = pixelSample.subtract(rayOrigin);

        return new Ray(rayOrigin, rayDirection);
    }

    private static Vec3 sampleSquare() {
        // Returns the vector to a random point in the [-.5,-.5]-[+.5,+.5] unit square.
        return new Vec3(CommonUtils.randomDouble() - 0.5, CommonUtils.randomDouble() - 0.5, 0);
    }




    public void render(Hittable world) {
        initialize();

        // Render
        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        for (int j = 0; j < imageHeight; j++) {
            CommonUtils.progressBar(j, imageHeight);
            for (int i = 0; i < imageWidth; i++) {
                Color pixelColor = Color.WHITE;
                for (int sample = 0; sample < samplesPerPixel; sample++) {
                    Ray r = getRay(i, j);
                    pixelColor = pixelColor.add(rayColor(r, world));
                }
                image.setRGB(i, j, pixelColor.multiply(pixelSampleScale).getRgb());
            }
        }

        FileUtils.saveFileToJPG(image);
    }



}
