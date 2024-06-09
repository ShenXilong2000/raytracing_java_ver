package entity.object;

import entity.HitRecord;
import entity.Hittable;
import entity.Ray;

import java.util.ArrayList;
import java.util.List;

public class HittableList extends Hittable {

    List<Hittable> objects = new ArrayList<>();

    public HittableList() {
    }

    public HittableList(Hittable object) {
        this.add(object);
    }

    public void clear() {
        objects.clear();
    }

    public void add(Hittable object) {
        objects.add(object);
    }

    @Override
    public boolean hit(Ray ray, double rayTMin, double rayTMax, HitRecord rec) {
        HitRecord tempRec = new HitRecord();
        Boolean hitAnything = false;
        double closestSoFar = rayTMax;

        for (Hittable object : objects) {
            if (object.hit(ray, rayTMin, closestSoFar, tempRec)) {
                hitAnything = true;
                closestSoFar = tempRec.t;
                rec = tempRec;
            }
        }

        return hitAnything;
    }
}
