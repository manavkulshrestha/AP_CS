/*
	Manav Kulshrestha
 	H Block
 	10/2/2017
 	ArrayManipulation.java
 */
import java.util.Scanner;

public class ArrayManipulation {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int len, in;
        String menu="1. Display array\n2. Check item's existence in array\n3. Bring lowest item to front\n4. Rotate array\n5. Purge zeros";

        print("Enter size of int array: ");
        len=input.nextInt();

        int[] arr = new int[len];

        print("Enter int array entries: ");
        for(int i=0; i<len; i++)
            arr[i]=input.nextInt();

        do {
            printf("\n%s\n\nEnter appropriate option to choose one of the vaguely specific functions: ", menu);
            in=input.nextInt();
            print("\n");

            switch(in) {
                case -1:
                    break;
                case 1:
                    displayIntArray(arr, len);
                    print("\n");
                    break;
                case 2:
                    isInIntArray(arr, len);
                    break;
                case 3:
                    bringLowestIntToFront(arr, len);
                    print("\n");
                    break;
                case 4:
                    rotateArray(arr, len);
                    break;
                case 5:
                    arr=purgeZeros(arr, len);
                    len=arr.length;
                    print("\n");
                    break;
                default:
                    print("Invalid option\n");
                    break;
            }
        } while(in != -1);
    }

    //Helper Method
    public static int swapInts(int a, int b) {
        //y=swapInts(x, x=y); don't have to use a temp variable this way.
        return a;
    }

    public static int gcd(int a, int b) {
        //implementing euler's gcd algorithm
        if(a == 0 || b == 0)
            return a+b;
        if(b>a) {
            return gcd(a, b%a);
        }
        return gcd(b, a%b);
    }

    //because writing System.out is tedious
    public static void printf(String s, Object... o) {
        System.out.printf(s, o);
    }

    public static void print(Object... o) {
        for(Object object: o)
            System.out.print(object);
    }

    //METHOD 1
    public static void displayIntArray(int[] arr, int len) {
        printf("Size of array:\t%d\nArray positions:", len);
        for(int i=0; i<len; i++)
            printf("\t%d", i);

        print("\nArray entries:");
        for(int i=0; i<len; i++)
            printf("\t%d", arr[i]);
    }

    //METHOD 2
    public static void isInIntArray(int[] arr, int len) {
        int pos=-1, item;
        String option;

        do {
            print("Enter entry to check existence of: ");
            item=input.nextInt();

            for(int i=0; i<len; i++)
                if(arr[i] == item) {
                    pos=i;
                    break;
                }

            if(pos != -1) {
                printf("Found at index %d", pos);
                pos=-1;
            }
            else
                printf("Not found");

            print("\nAgain? (yes/no): ");
            option=input.next();
        } while(option.toLowerCase().equals("yes"));
    }

    //METHOD 3
    public static void bringLowestIntToFront(int[] arr, int len) {
        int smallestPos=0;

        for(int i=1; i<len; i++)
            if(arr[i]<arr[smallestPos])
                smallestPos=i;

        if(smallestPos != 0)
            arr[smallestPos]=swapInts(arr[0], arr[0]=arr[smallestPos]);

        displayIntArray(arr, len);
    }

    //METHOD 4
    public static void rotateArray(int[] arr, int len) {
        String option;

        do {
            print("Enter steps to rotate by: ");
            int rotate=input.nextInt();

            if(rotate>len)
                rotate=rotate%len;

//			int[] result = new int[len];
//
//			for(int i=0; i<len; i++)
//				result[(i+rotate)%len] = arr[i];

            if(rotate != 0) {
                if(rotate>0)
                    rotate=len-rotate;
                else if(rotate<0)
                    rotate *= -1;

                for(int i=0, temp=arr[i], j=i; i<gcd(rotate, len); arr[j]=temp, i++, temp=arr[i], j=i)
                    for(int k=j+rotate; true; arr[j]=arr[k], j=k, k=j+rotate) {
                        if (k >= len)
                            k=k-len;
                        if(k == i)
                            break;
                    }

            }

            print("\n");
            displayIntArray(arr, len);

            print("\nAgain? (yes/no): ");
            option=input.next();
        } while(option.toLowerCase().equals("yes"));
    }

    //METHOD 5
    public static int[] purgeZeros(int[] arr, int len) {
        int zeroCount=0, newSize;

        for(int i=0; i<len; i++)
            if(arr[i] == 0)
                zeroCount++;

        newSize=len-zeroCount;

        int[] result = new int[newSize];

        for(int i=0, j=0; i<len; i++)
            if(arr[i] != 0) {
                result[j]=arr[i];
                j++;
            }

        displayIntArray(result, newSize);

        return result;
    }
}