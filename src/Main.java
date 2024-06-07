import utils.FileUtils;

import java.awt.image.BufferedImage;

public class Main {

    private static double hitSphere(Vec3 center, double radius, Ray ray) {
        Vec3 oc = center.subtract(ray.origin());
        double a = ray.direction().lengthSquared();
        double h = ray.direction().dot(oc);
        double c = oc.lengthSquared() - radius * radius;
        double discriminant = h*h - a*c;

        if (discriminant < 0) {
            return -1.0;
        } else {
            return (h - Math.sqrt(discriminant)) / a;
        }
    }

    private static Color rayColor(Ray ray) {
        Vec3 sphereCenter = new Vec3(0, 0, -1);
        double t = hitSphere(sphereCenter, 0.5, ray);
        if (t > 0.0) {
            Vec3 n = ray.at(t).subtract(sphereCenter).unitVector();
            // 将法线控制在（0, 1）
            return new Color(n.x+1, n.y+1, n.z+1).multiply(0.5);
        }


        Vec3 unitDirection = ray.direction().unitVector();
        double a = 0.5 * (unitDirection.y() + 1.0);
        return new Color(1.0, 1.0, 1.0).multiply(1.0 - a).add(new Color(0.5, 0.7, 1.0).multiply(a));
    }


    public static void main(String[] args) {
        // Image

        double aspectRatio = 16.0 / 9.0;
        int imageWidth = 400;

        // 计算画面高度，确保高度至少为1
        int imageHeight = (int) (imageWidth / aspectRatio);
        imageHeight = Math.max(imageHeight, 1);

        // Camera
        double focalLength = 1.0;
        double viewportHeight = 2.0;
        double viewportWidth = viewportHeight * ((double) imageWidth / imageHeight);
        Vec3 cameraCenter = Vec3.zero;

        // 计算横向和竖向视口边缘的向量
        Vec3 viewportU = new Vec3(viewportWidth, 0,0);
        Vec3 viewportV = new Vec3(0, -viewportHeight, 0);

        // 计算水平方向和垂直视口边缘的向量
        Vec3 pixelDeltaU = viewportU.divide(imageWidth);
        Vec3 pixelDeltaV = viewportV.divide(imageHeight);

        // 计算左上角的像素
        Vec3 viewportUpperLeft = cameraCenter.subtract(new Vec3(0, 0, focalLength))
                                                .subtract(viewportU.divide(2))
                                                .subtract(viewportV.divide(2));
        Vec3 pixel00_loc = pixelDeltaU.add(pixelDeltaV).divide(2).add(viewportUpperLeft);

        // Renderx

        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

        for (int j = 0; j < imageHeight; j++) {

            System.out.print("\rScanlines remaining: " + (imageHeight - j) + " ");
            System.out.flush();

            for (int i = 0; i < imageWidth; i++) {
                Vec3 pixelCenter = pixel00_loc.add(pixelDeltaU.multiply(i))
                                            .add(pixelDeltaV.multiply(j));// 像素中心点
                Vec3 rayDirection = pixelCenter.subtract(cameraCenter);// 视线方向
                Ray r = new Ray(cameraCenter, rayDirection);

                Color color = rayColor(r);
                image.setRGB(i, j, color.getRgb());
            }
        }

        System.out.println("\rDone.                 ");
        FileUtils.saveFileToJPG(image);
    }
}