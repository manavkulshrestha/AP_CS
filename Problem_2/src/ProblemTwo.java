//import java.math.BigInteger;
import java.util.Scanner;

public class ProblemTwo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int in;

        do {
            System.out.print("Number: ");
            in = input.nextInt();
            long t0 = System.currentTimeMillis();
            int prime = nthPrime(in);

            if(in>0) {
                System.out.print("Result: "+prime+", "+factorial(prime));
                System.out.print("\nTime Taken: "+(double)(System.currentTimeMillis()-t0)/1000+" s");
                System.out.print("\n\n");
            }
        } while(in>0);
    }

    public static long factorial(int n) {
        long result = 1;
        //not using BigInteger because it takes too much time to calculate factorial of big numbers (eg: 99999999)
        //whereas the prime can be done in a couple seconds
//		BigInteger result = new BigInteger("1");

        for(int i=1; i<=n; i++) {
            result *= i;
//			result = result.multiply(result.valueOf(i));
        }

        return result;
    }

//	public static boolean isPrime(int n) {		
//	    for(int i=2; i < (n+1)/2; i++) {
//	        if(n%i == 0) {
//	        		return false;
//	        }
//	    }
//		
//		return true;
//	}

    public static int nthPrime(int n) {
        //iterates through to the limit and removes all multiples of number. remaining numbers are prime. get's nth prime.
        //better way to do this would be to get an approximation (fermat's little theorum) and then work from there, but this was more fun

        //exceptions to algorithm
        if (n < 2)
            return 2;
        if (n == 2)
            return 3;

        int checkLimit, checker, counter=1, checkCounter;

        checkLimit = (int)(n*(Math.log(n)+Math.log(Math.log(n))))+3;
        checker = (int)Math.sqrt(checkLimit)+1;
        checkLimit = (checkLimit-1)/2;
        checker = checker/2-1;

        boolean[] primes = new boolean[checkLimit];

        for(int i=0; i<checker; i++) {
            if(!primes[i]) {
                counter++;
                //since x in the array is really 2x+3
                for(int j=2*i*(i+3)+3, step=2*i+3; j<checkLimit; j+=step) {
                    primes[j] = true;
                }
            }
        }

        for(checkCounter=checker; counter<n; checkCounter++) {
            if (!primes[checkCounter]) {
                counter++;
            }
        }

        return 2*checkCounter+1;
    }
}