/*
	Manav Kulshrestha
 	H Block
 	11/16/2017
 	Recursion.java
 */
import java.util.Scanner;

public class Recursion {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int option;
        //I see what you did here, Mr. Harris
        String menu = "1. letters\n2. twos\n3. powerOfThree\n4. reverse\n5. base5\n6. printWithCommas\nSELECT: ";


        do {
            print(menu);

            option = in.nextInt();
            print("\n");

            switch (option) {
                case -1:
                    break;
                case 1:
                    print("Enter letter: ");
                    letters(Character.toLowerCase(in.next().charAt(0)));
                    break;
                case 2:
                    print("Enter number: ");
                    print(twos(in.nextInt()));
                    break;
                case 3:
                    print("Enter number: ");
                    if (powerOf3(in.nextInt()))
                        print("IS a power of 3");
                    else
                        print("Is NOT a power of 3");
                    break;
                case 4:
                    print("Enter number: ");
                    print(reverse(in.nextInt()));
                    break;
                case 5:
                    print("Enter number: ");
                    base5(in.nextInt());
                    break;
                case 6:
                    print("Enter number: ");
                    printWithCommas(in.nextInt());
                    break;
                default:
                    print("-_-");
                    break;
            }

            if(option != -1)
                print("\n\n");

        } while(option != -1);
    }

    //because writing System.out is tedious
    public static void printf(String s, Object... o) {
        System.out.printf(s, o);
    }

    public static void print(Object... o) {
        for(Object object: o)
            System.out.print(object);
    }

    public static void letters(char finalLetter) {
        if(finalLetter != 'a')
            letters((char)(finalLetter-1));
        print(finalLetter);
    }

    public static int twos(int num) {
        return ((num&1) == 1) ? 0 : 1+twos(num/2);
    }

    public static boolean powerOf3(int n) {
        //return (n==1) ? true : (n<=0) ?
        if(n == 1)
            return true;
        else if(n <= 0)
            return false;
        else if(n%3 == 0)
            return powerOf3(n/3);
        else
            return false;
    }

    public static int floorPowerOf10(int num) {
        return (((num<0) ? -num : num)<10) ? 1 : 10*floorPowerOf10(num/10);
    }

    //Leading zeros not preserved. eg: 10000
    public static int reverse(int num) {
        return (((num<0) ? -num : num)<10) ? num : (num%10)*floorPowerOf10(num)+reverse(num/10);
    }

    public static void base5(int num) {
        if(num != 0) {
            base5(num/5);
            print(num%5);
        }
    }

    public static void printWithCommas(int num) {
        if(num<0) {
            num = -num;
            print('-');
        }
        if(num>999) {
            printWithCommas(num/1000);
            printf(",%03d", num%1000);
        } else
            print(num);
    }
}
