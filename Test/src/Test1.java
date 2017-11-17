import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        double angle, magnitude;
        double x1, x2, y1, divisor;
        double pLeftFrontRightRear, pLeftRearRightFront;
        double[] scaledPowers = new double[2];

        Scanner in = new Scanner(System.in);

        for(int i=0; i < 100; i++) {
            System.out.print("Angle: ");
            angle = (in.nextDouble()) * (Math.PI/180);

            System.out.print("Magnitude: ");
            magnitude = in.nextDouble();

            x1 = magnitude/Math.cos(angle);
            y1 = magnitude/Math.sin(angle);

            System.out.printf("X1: %s, Y1: %s\n", x1, y1);

            System.out.print("X2 (Turn factor): ");
            x2 = in.nextDouble();

            angle -= Math.PI/4;

            if (x1 != 0 && y1 != 0) {
                //Power Values
                if (Math.abs(y1) > Math.abs(x1)) {
                    pLeftFrontRightRear = 1;
                    pLeftRearRightFront = x1 / y1;
                    divisor = y1;
                } else {
                    pLeftFrontRightRear = y1 / x1;
                    pLeftRearRightFront = 1;
                    divisor = x1;
                }

                //Power Directions (May be unnecesary)
                if (x1 > 0) {
                    if (y1 < 0) {
                        pLeftFrontRightRear = -pLeftFrontRightRear;
                    }
                } else if (x1 < 0) {
                    pLeftFrontRightRear = -pLeftFrontRightRear;
                    if (y1 > 0) {
                        pLeftRearRightFront = -pLeftRearRightFront;
                    } else {
                        pLeftRearRightFront = -pLeftRearRightFront;
                    }
                } else if (x1 == 0) {
                    //special cases for for angle lying on the y-axis
                    if (y1 < 0) {
                        //270
                        pLeftFrontRightRear = -1;
                        pLeftRearRightFront = -1;
                    } else {
                        //90
                        pLeftFrontRightRear = 1;
                        pLeftRearRightFront = 1;
                    }
                    //y1 == 0
                } else if (y1 == 0) {
                    //special cases for angle lying on the x-axis
                    if (x1 < 0) {
                        //180
                        pLeftFrontRightRear = -1;
                        pLeftRearRightFront = 1;
                    } else {
                        //0
                        pLeftFrontRightRear = 1;
                        pLeftRearRightFront = -1;
                    }
                }

                scaledPowers = scalePowers((pLeftFrontRightRear + (x2 / divisor)), (pLeftRearRightFront + (x2 / divisor)));

            } else {
                scaledPowers[0] = 0;
                scaledPowers[1] = 0;
            }

            System.out.printf("1: %s\n2: %s\n3: %s\n4: %s\n\n", scaledPowers[0], scaledPowers[1], scaledPowers[1], scaledPowers[0]);
        }
    }

    static double[] scalePowers(double a, double b) {
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
