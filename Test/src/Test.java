import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        for(int i = 0; i < 100; i++){
            System.out.println("Angle: ");
            double angle = in.nextDouble() * (Math.PI/180);

            angle -= Math.PI/4;

            double a, b, c, d;
            a = Math.sin(angle);
            b = Math.cos(angle);
//            c = Math.cos(angle);
//            d = Math.sin(angle);

            double[] powers = scaledPowers(a, b);

            System.out.printf("1: %s\n2: %s\n3: %s\n4: %s\n", a, b, b, a);
        }
    }
    static double joyStickAngle(double x, double y) {
        double angle = 0;

        if(x == 0) {
            if(y > 0) {
                angle = Math.PI/2;
            } else if(y < 0) {
                angle = (3*Math.PI)/2;
            }
        } else {
            if(x > 0) {
                angle = Math.atan(y/x);
            } else {
                angle = Math.PI - Math.atan(y/-x);
            }
            if(angle < 0) {
                angle = 2*Math.PI + angle;
            }
        }

        return angle;
    }

    static double[] scaledPowers(double a, double b) {
        double aAbs = a, bAbs = b;

        if(aAbs < 0) {
            aAbs = -aAbs;
        }

        if(bAbs < 0) {
            bAbs = -bAbs;
        }

        if(aAbs > bAbs) {
            return new double[]{a/aAbs, b/aAbs};
        }
        return new double[] {a/bAbs, a/aAbs};
    }
}
