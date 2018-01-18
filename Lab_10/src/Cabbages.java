/*
    Manav Kulshrestha
    Cabbages.java
    1/17/18
 */
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Cabbages {
    public static void main(String[] args) throws IOException {
        final String FILENAME = "cabbages.txt";
        Scanner in = new Scanner(new File(FILENAME));
        int longestWordLen = 0, wordCount = 1, newLen;
        String longestWord = "";
        String[] punctuations = {"\"", ".", ",", ";", ":", "--", "?", "!", " '", "' "};

        print("Words found in text --\n");
        while(in.hasNext()) {
            Scanner in2 = new Scanner(in.nextLine());
            for(;in2.hasNext();) {
                String word = in2.next();
                int temp = word.length();

                if(word.length() > longestWordLen) {
                    longestWord = word;
                    longestWordLen = temp;
                }
                printf("%d %s\n", wordCount++, word);
            }
        }
        wordCount--;
        printf("The longest word in the text is <%s>", longestWord);

        print("\n\nWords sorted alphabetically with duplicates removed --\n");
        in = new Scanner(new File(FILENAME));
        String[] words = new String[wordCount];
        for(int i=0; in.hasNext();) {
            Scanner in2 = new Scanner(in.nextLine());
            for(;in2.hasNext(); i++)
                words[i] = removePunctuations(in2.next(), punctuations).toLowerCase();
        }

        Arrays.sort(words);
        newLen = makeStartUnique(words);//so it isn't evaluated every loop
        for(int i=0; i<newLen; i++) {
            printf("\n%d %s", i, words[i]);
        }


        in = new Scanner(System.in);
        for(int repeat=0; repeat<3; repeat++) {
            print("\n\nSomething to grep: ");
            print(grep(FILENAME, in.nextLine(), "\nLine %d: %s", "\nPhrase doesn't appear in any lines"));
        }

    }

    public static String grep(String fileName, String inp, String format, String nullPhrase) throws IOException{
        Scanner in1 = new Scanner(new File(fileName));
        String ret = "";

        for(int counter=1; in1.hasNext(); counter++) {
            String line = in1.nextLine();

            if(line.contains(inp))
                ret += String.format(format, counter, line.replace(inp, "<"+inp+">"));
        }

        return (ret.equals("")) ? nullPhrase : ret;
    }

    public static String removePunctuations(String s, String[] replaceArr) {
        for(String str: replaceArr) {
            s = s.replace(str, "");
        }

        //removing ' at the end or beginning of the string
        if(s.charAt(0) == '\'')
            s = s.substring(1);
        int len = s.length();
        if(s.charAt(len-1) == '\'')
            s = s.substring(0, len-1);

        return s;
    }

    public static int makeStartUnique(String arr[]) {
        if (arr.length < 2)
            return arr.length;

        int j = 0;

        for(int i=1; i < arr.length; i++) {
            if (!arr[i].equals(arr[j])) {
                j++;
                arr[j] = arr[i];
            }
        }

        return j+1;
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