/*	
	Manav Kulshrestha
 	H Block
 	10/17/2017
 	MiddleEarthBaseMath.java
 */
import java.util.Scanner;

public class MiddleEarthBaseMath {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int base=0;
        String line="--";

        for(int i=1; i<=10; i++, line="--") {
            System.out.printf("Input %d:\n", i);

            switch(input.next()) {
                case "wizard":
                    base = 2;
                    break;
                case "dwarf":
                    base = 5;
                    break;
                case "elf":
                    base = 8;
                    break;
                case "human":
                    base = 10;
                    break;
            }

            int n0 = input.nextInt();
            int n1 = input.nextInt();

            for(int j=n1; j!=0; j/=10)
                line += "-";

            if(input.next().contains("+")) {
                printf("\n%d\n+ %d\n%s\n%d", n0, n1, line, add(n0, n1, base));
            } else {
                printf("\n%d\n* %d\n%s\n", n0, n1, line);
                print(multiply(n0, n1, base));
            }
            print("\n\n");

        }
    }

    //because writing System.out is tedious
    public static void printf(String s, Object... o) {
        System.out.printf(s, o);
    }

    public static void print(Object... o) {
        for(Object object: o)
            System.out.print(object);
    }

    public static int add(int n0, int n1, int base) {
        int answer=0;

        //loops as long as one of them is non-zero
        for(int i=1, temp; n0!=0 || n1!=0; n0/=10, n1/=10, i*=10) {
            temp=n0%10+n1%10;
            if(temp >= base) {
                temp -= base;
                //[one of the numbers(n0)] += [carry over value(1)]*10
                n0 += 10;
            }
            answer += temp*i;
        }

        return answer;
    }

    public static int multiply(int n0, int n1, int base) {
        int answer=0, carry=0, count=0;

        //n0 and n1 swapped in loops to emulate how people do it
        for(int i=1, j=1, set=0; n1!=0; n1/=10, i*=10, carry=0, j=1, set=0, count++) {
            for(int digit=n0; digit!=0; digit/=10, j*=10) {
                int temp=(n1%10)*(digit%10)+carry;
                carry=0;
                if(temp >= base) {
                    carry=temp/base;
                    temp %= base;
                }
                set += temp*j;
            }
            set += carry*j;
            answer = add(answer, set*i, base);
            printf("%d\n", set);
        }
        for(int i=answer; i!=0; i/=10)
            print("=");
        print("\n");
        return answer;
    }
}