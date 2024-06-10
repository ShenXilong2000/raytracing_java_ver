package entity.object;

import entity.HitRecord;
import entity.Hittable;
import entity.Interval;
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
    public HitRecord hit(Ray ray, Interval rayT) {
        HitRecord hitRecord = null;
        double closestSoFar = rayT.max;

        for (Hittable object : objects) {
            HitRecord tempRec = object.hit(ray, new Interval(rayT.min, closestSoFar));
            if (tempRec != null) {
                closestSoFar = tempRec.t;
                hitRecord = tempRec;
            }
        }

        return hitRecord;
    }
}