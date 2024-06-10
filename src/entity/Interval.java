package entity;

import utils.CommonUtils;

// 范围
public class Interval {
    public double min;
    public double max;

    public static final Interval EMPTY = new Interval(+CommonUtils.INFINITY, -CommonUtils.INFINITY);
    public static final Interval UNIVERSE = new Interval(-CommonUtils.INFINITY, +CommonUtils.INFINITY);

    public Interval() {
        this.min = CommonUtils.INFINITY;
        this.max = -CommonUtils.INFINITY;
    }

    public Interval(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public double size() {
        return max - min;
    }

    public boolean contains(double x) {
        return x >= min && x <= max;
    }

    public boolean surrounds(double x) {
        return x > min && x < max;
    }
}
