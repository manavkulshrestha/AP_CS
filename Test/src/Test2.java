public class Test2 {
    static int count=0;
    public static void main(String[] args) {
//        double[] scaleArray = {0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
//                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00};
//
//        double[] exponentArray = {1, 1.1, 1.2, 1.3, 1.4, 1.5};
//
//        for(double val: scaleArray) {
//            for(double exponent: exponentArray) {
//                System.out.printf("%s ", Math.pow(val, exponent));
//            }
//            System.out.print("\n");
//        }

        star(40);
        System.out.print(count);
    }

    public static void star(int n) {
        count++;
        if(n > 2) {
            star(n - 1);
            star(n - 2);
        }
    }
}
