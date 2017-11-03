import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        for(int i = 0; i < 100; i++){
            System.out.println(Math.toDegrees(joyStickAngle(in.nextDouble(),in.nextDouble())));
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
}
