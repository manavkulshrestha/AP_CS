/*
    Manav Kulshrestha
    Activity3_questions.java
    3/21/18
*/

import java.util.Random;

public class Activity3Questions {
    public static void main(String[] args) {
        for(int i=0; i<3; i++)
            System.out.println(flip());
        System.out.println(arePermutations(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[] {2, 5, 3, 1, 4, 6, 7, 8, 9}));
    }

    public static String flip() {
        return (new Random().nextInt(3) > 0) ? "heads" : "tails";
    }

    public static boolean arePermutations(int[] a1, int[] a2) {
        for(int item: a1)
            if(!contains(item, a2))
                return false;
        return true;
    }

    private static boolean contains(int item, int[] array) {
        for(int aItem: array)
            if(item == aItem)
                return true;
        return false;
    }
}