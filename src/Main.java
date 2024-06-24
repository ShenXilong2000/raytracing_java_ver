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

        double R = Math.cos(CommonUtils.PI / 4);

        Material materialLeft = new Lambertian(Color.BLUE);
        Material materialRight = new Lambertian(Color.RED);

        world.add(new Sphere(new Vec3(-R, 0, -1), R, materialLeft));
        world.add(new Sphere(new Vec3(R, 0, -1), R, materialRight));


        Camera camera = new Camera();
        camera.aspectRatio = 16.0 / 9.0;
        camera.imageWidth = 400;
        camera.samplesPerPixel = 10;
        camera.maxDepth = 100;

        camera.vfov = 90;
        camera.render(world);
    }
}