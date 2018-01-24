/*
    Manav Kulshrestha
    RGB.java
    1/18/18
*/

public class RGB {
    public static void main(String args[]) {
        double[] hsv = RGBtoHSV(8, 8, 8);
        System.out.printf("H:%s, S:%s, V:%s", hsv[0], hsv[1], hsv[2]);
    }

    public static double[] RGBtoHSV(double r, double g, double b) {

        double h, s, v;
        double min, max, delta;

        min = Math.min(Math.min(r, g), b);
        max = Math.max(Math.max(r, g), b);

        //V
        v = max;

        delta = max-min;

        //S
        if(max != 0)
            s = delta/max;
        else {
            s = 0;
            h = -1;
            return new double[] {h,s,v};
        }

        // H
        if( r == max )
            h = (g-b)/delta; //between yellow & magenta
        else if(g == max )
            h = 2+(b-r)/delta; //between cyan & yellow
        else
            h = 4+(r-g)/delta; //between magenta & cyan

        h *= 60; //degrees

        if(h < 0)
            h += 360;

        return new double[]{h,s,v};
    }
}