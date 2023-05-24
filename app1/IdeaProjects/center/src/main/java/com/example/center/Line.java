package com.example.center;

public class Line {
    double a, b, c;

    public Line(Point point, double angleRadians) {
        this.a = Math.sin(angleRadians);
        this.b = -Math.cos(angleRadians);
        this.c = this.a * point.x + this.b * point.y;
    }

    public static Point intersect(Line line1, Line line2) {
        double determinant = line1.a * line2.b - line2.a * line1.b;
        if (determinant == 0) {
            return null; // The lines are parallel
        }
        int x = (int)((line2.b * line1.c - line1.b * line2.c) / determinant);
        int y = (int)((line1.a * line2.c - line2.a * line1.c) / determinant);
        return new Point(x, y);
    }
}

