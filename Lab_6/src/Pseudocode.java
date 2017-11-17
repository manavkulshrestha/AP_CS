public class Pseudocode {
    public static void main(String[] args) {

    }

    public static boolean powerOf3(int n) {
        if(n == 1)
            return true;
        else if(n <= 0)
            return false;
        else if(n%3 == 0)
            return powerOf3(n/3);
        else
            return false;
    }

//    public static int reverse(int input) {
//        return reverse(input, 0);
//    }
//
//    private static int reverse(int original, int reversed) {
//        int rightMost = original % 10;
//        original -= rightMost;
//        original /= 10;
//9
//        reversed += rightMost * Math.pow(10, numDigits(original));
//
//        return (original == 0) ? reversed: reverse(original, reversed);
//    }
//
//    public static int numDigits(int number) {
//        number = Math.abs(number);
//
//        if (number >= 10) {
//            return 1 + numDigits(number /= 10);
//        } else if (number > 0) {
//            return 1;
//        } else {
//            return 0;
//        }
//    }

//    public static int reverse(int n) {
//        return (n<10) ? n : (n%10 * (int)Math.pow(10, (int)Math.log10((double)n)) + reverse(n/10));
//    }

//    public static int reverse(int n) {
//        return reverse(n, 0);
//    }
//
//    private static int reverse(int n, int m) {
//        if(n == 0)
//            return m;
//        else
//            return reverse(n / 10, m * 10 +  n % 10);
//    }

    public static int revHelper(int num) {
        if(num/10 == 0)
            return 1;
        else
            //System.out.print(10*);
            return 10*revHelper(num/10);
    }

    public static int reverse(int num) {
        if(num < 10) {
            return num;
        } else {
            return (num%10)*revHelper(num)+reverse(num/10);
        }
    }

    public static void printWithCommas(int num) {
        if (num>999) {
            printWithCommas(num/1000);
            System.out.print(',');

            num %= 1000;

            if (num < 100)
                System.out.print('0');
            if (num < 10)
                System.out.print('0');

            System.out.print(num);
        }
        else
            System.out.print(num);
    }
}
