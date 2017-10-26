/*	
	Manav Kulshrestha
 	H Block
 	9/12/2017
 	SimpleAlgorithms.java
 */

import java.util.Scanner;

public class SimpleAlgorithms {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int in;
        String menu="1. factors\n2. power\n3. GCD\n4. countDigits\n5. prime\n6. findDigit\n7. downDigits\n\n";
        do {
            System.out.print(menu);
            System.out.print("Choose option: ");
            in = input.nextInt();
            switch(in) {
                case -1:
                    break;
                case 1:
                    System.out.print("Enter number: ");
                    in = input.nextInt();
                    factors(in);
                    break;
                case 2:
                    System.out.print("Enter a double followed by an int: ");
                    double b = input.nextDouble();
                    int e = input.nextInt();
                    //%s for double so it doesn't round
                    System.out.printf("%s^%d = %s", b, e, power(b, e));
                    break;
                case 3:
                    System.out.print("Enter number: ");
                    int in1 = input.nextInt();
                    int in2 = input.nextInt();
                    System.out.printf("The GCD of %d and %d is %d", in1, in2, GCD(in1, in2));
                    break;
                case 4:
                    System.out.print("Enter number: ");
                    double number = input.nextDouble();
                    //%s for double so it doesn't round
                    System.out.printf("%s has %d digits left of the decimal", number, countDigits(number));
                    break;
                case 5:
                    System.out.print("Enter number: ");
                    in = input.nextInt();
                    if(prime(in))
                        System.out.printf("%d is prime", in);
                    else
                        System.out.printf("%d is composite", in);
                    break;
                case 6:
                    System.out.print("Enter an int(number) followed by another int(parameter): ");
                    int num = input.nextInt();
                    int n = input.nextInt();
                    if(n > 0 && n <= countDigits((double)num))
                        System.out.printf("Result = %d", findDigit(num, n));
                    else
                        System.out.printf("Invalid parameter");
                    break;
                case 7:
                    System.out.print("Enter number: ");
                    downDigits(input.nextInt());
                    break;
                default:
                    System.out.printf("Invalid Input: %d", in);
                    break;
            }
            System.out.print("\n\n");
        } while(in != -1);
        input.close();
    }

    //helper function
    public static int floorSqrt(int n) {
        //returns floor of the square root. eg: floorSqrt(3) will return 1
        if (n == 0 || n == 1)
            return n;

        int i=1;

        for(int result=i; result<=n; i++, result = i*i)
            if(result == n)
                return i;
        return i-1;
    }

    public static void factors(int n) {
        //iterates up to floorSqrt(n) as opposed to n.
        int step=1;

        //if number is odd, only check odd numbers
        if(n%2 != 0)
            step = 2;

        System.out.printf("The factors of %d are: 1, ", n);
        if(!prime(n)) {
            int sqrtN=floorSqrt(n);

            for(int i=2; i<sqrtN; i += step)
                if(n%i == 0)
                    System.out.printf("%d, %d, ", i, n/i);

            if(power(sqrtN, 2) == n)
                System.out.printf("%d, ", sqrtN);
        }
        System.out.printf("%d.", n);

    }

    public static double power(double b, int e) {
        //doesn't work for negative. slow.
//		double result=1;
//		
//		for(int i=0; i<e; i++)
//			result *= b;
//		
//		return result;

        //will work for negative, faster because less multiplication operations
        //eg: b^2 *b^2 *b as opposed to b*b*b*b*b for x^5
        if(e < 0)
            return power(1/b, -e);
        else if(e == 0)
            return 1;
        else if(e%2 == 0)
            return power(b*b, e/2);
        else
            return b*power(b*b, (e-1)/2);
    }

    public static int GCD(int a, int b) {
        //implementing euler's gcd algorithm
        if(a == 0 || b == 0)
            return a+b;
        if(b>a) {
            return GCD(a, b%a);
        }
        return GCD(b, a%b);
    }

    public static int countDigits(double num) {
//		return (int)(Math.log10((int)num)+1);
//		return Integer.toString((int)num).length();
        int result=0;

        for(; num>1; num /= 10) {
            result++;
        }

        if(result == 0)
            return 1;
        return result;
    }

    public static boolean prime(int n) {
        int checkLimit=floorSqrt(n);
        //returns 1 as prime, but I can't send a third value since it returns boolean

        if(n%2 == 0 && n != 2)
            return false;
        if(n%3 == 0 && n != 3)
            return false;

        //all other primes are of the form (6 * k - 1) OR (6 * k + 1)
        for(int divisor=5, step=2; divisor <= checkLimit; divisor += step, step = 6-step) {
            if(n%divisor == 0)
                return false;
        }
        return true;
    }

    public static int findDigit(int num, int n) {
        return (num/((int)power(10, n-1)))%10;
    }

    public static void downDigits(int n) {
//		Stack<Integer> temp = new Stack<Integer>();
//		
//		for(; n != 0; n /= 10)
//			temp.push(n%10);
//		
//		int resSize = temp.size();
//		int[] result = new int[resSize];
//		for(int i=0; i<resSize; i++) {
//			result[i]=temp.pop();
//		}
//
//		return result;
        int nSize=countDigits((double)n);

        System.out.printf("The digits of %d are: ", n);
        for(int i=0, finder=nSize ; i<nSize; i++, finder--) {
            System.out.printf("\n%d", findDigit(n, finder));
        }
    }
}