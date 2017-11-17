import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        double x1, x2, y1;
        double magnitude, angle;

        for(int i = 0; i < 100; i++){
            System.out.print("Driver Angle: ");
            angle = in.nextDouble() * (Math.PI/180);
            System.out.print("Magnitude: ");
            magnitude = in.nextDouble();
            System.out.print("x2: ");
            x2 = in.nextDouble();

            x1 = magnitude * Math.cos(angle);
            y1 = magnitude * Math.sin(angle);

            System.out.printf("x1: %s   y1: %s\n", x1, y1);

            angle -= Math.PI/4;

            double LFP, RFP, LRP, RRP;
            LFP = Math.cos(angle) + x2;
            RFP = Math.sin(angle) - x2;
            LRP = Math.sin(angle) + x2;
            RRP = Math.cos(angle) - x2;

//            double scaleFactor = maxAbsolute(LFP, RFP, LRP, RRP);
//            LFP /= scaleFactor;
//            RFP /= scaleFactor;
//            LRP /= scaleFactor;
//            RRP /= scaleFactor;

            LFP *= magnitude;
            RFP *= magnitude;
            LRP *= magnitude;
            RRP *= magnitude;

            System.out.printf("Left Front: %s\t\tRight Front: %s\nLeft Rear: %s\t\tRight Rear: %s\n\n", LFP, RFP, LRP, RRP);
        }
    }

    public static double maxAbsolute(double... powers) {
        double maximumAbs = 0, temp;

        for(double power: powers) {
            temp = Math.abs(power);
            if(temp > maximumAbs) {
                maximumAbs = temp;
            }

            if(maximumAbs == 0) {
                return 1;
            }
        }

        return maximumAbs;
    }
}
