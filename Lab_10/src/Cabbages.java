import java.io.*;
import java.util.Scanner;

public class Cabbages {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("cabbages.txt"));
        int longestWordLen = 0;
        String longestWord;

        print("Words found in text --\n");

        for(int counter=1; in.hasNext(); counter++) {
            Scanner in2 = new Scanner(in.nextLine());
            while(in2.hasNext()) {
                String word = in.next();
                int temp = word.length();

                if(word.length() > longestWordLen) {
                    longestWord = word;
                    longestWordLen = temp;
                }
                printf("%d \n", counter, word);
            }
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
}
