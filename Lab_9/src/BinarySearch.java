/*
    Name: Manav Kulshrestha
    Block: H
    File: BinarySearch
    Date: 1/2/18
 */

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BinarySearch {
    public static Random dice = new Random();
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int n = dice.nextInt(31)+20;
        int[] arushiSushi = createIntArrayRandom(n, 0, 99);
        int[] probe = {0};

        printf("Original Array[%d]:", n);
        printIntArray(arushiSushi, 10);

        Arrays.sort(arushiSushi);
        print("\n\n");

        printf("Sorted Array[%d]:", n);
        printIntArray(arushiSushi, 10);

        print("\n\n");

        print("Built-in Binary Search:\n");
        for(int i=0; i<2; i++) {
            print("Entry: ");
            int index = Arrays.binarySearch(arushiSushi, in.nextInt());
            printf("\tStatus: %s.\n", (index<0) ? "Not Found" : "Found at index "+index);
        }

        print("\n");

        print("My Binary Search:\n");
        for(int i=0; i<2; i++) {
            print("Entry: ");
            int index = intArrayBinarySearch(arushiSushi, in.nextInt(), probe);
            printf("\tStatus: %s.\n", (index < 0) ? "Not Found" : "Found at index "+index+" after "+probe[0]+" probes");
            probe[0] = 0;
        }

        in.close();
    }

    public static void printIntArray(int[] array, int lineBreakPara) {
        for(int i=0; i<array.length; i++) {
            if(i%lineBreakPara == 0)
                print("\n");
            printf("%4d", array[i]);
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

    public static int[] createIntArrayRandom(int n, int rangeStart, int rangeEnd) {
        int[] array = new int[n];

        for(int i=0; i<array.length; i++)
            array[i] = dice.nextInt(rangeEnd+1)+rangeStart;

        return array;
    }

    public static int intArrayBinarySearch(int[] array, int key, int probe[]) {
        return intArrayBinarySearch(array, 0, array.length-1, key, probe);
    }

    public static int intArrayBinarySearch(int[] array, int from, int to, int key, int[] probe) {
        if (to >= from) {
            int mid = from + (to-from)/2;
            probe[0]++;

            if (array[mid] == key) {
                return mid;
            } if(array[mid] > key) {
                return intArrayBinarySearch(array, from, mid-1, key, probe);
            }
            return intArrayBinarySearch(array, mid+1, to, key, probe);
        }

        return -1;
    }

}
