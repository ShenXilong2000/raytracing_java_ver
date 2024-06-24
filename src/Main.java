import entity.*;
import entity.materials.Dielectric;
import entity.materials.Lambertian;
import entity.materials.Metal;
import entity.object.HittableList;
import entity.object.Sphere;
import modules.Camera;
import utils.CommonUtils;

public class Main {


    public static void main(String[] args) {
        HittableList world = new HittableList();

        Material materialGround = new Lambertian(new Color(0.8, 0.8, 0.0));
        Material materialCenter = new Lambertian(new Color(0.1, 0.2, 0.5));
        Material materialLeft = new Dielectric(1.50);
        Material materialBubble = new Dielectric(1.00 / 1.50);
        Material materialRight = new Metal(new Color(0.8, 0.6, 0.2), 1.0);

        world.add(new Sphere(new Vec3(0.0, -100.5, -1.0), 100.0, materialGround));
        world.add(new Sphere(new Vec3(0.0, 0.0, -1.2), 0.5, materialCenter));
        world.add(new Sphere(new Vec3(-1.0, 0.0, -1.0), 0.5, materialLeft));
        world.add(new Sphere(new Vec3(-1.0, 0.0, -1.0), 0.4, materialBubble));
        world.add(new Sphere(new Vec3(1.0, 0.0, -1.0), 0.5, materialRight));


        Camera camera = new Camera();
        camera.aspectRatio = 16.0 / 9.0;
        camera.imageWidth = 400;
        camera.samplesPerPixel = 10;
        camera.maxDepth = 100;

        camera.vFov = 20;
        camera.lookFrom = new Vec3(-2, 2, 1);
        camera.lookAt = new Vec3(0, 0, -1);
        camera.vUp = new Vec3(0, 1, 0);

        camera.render(world);
    }
}