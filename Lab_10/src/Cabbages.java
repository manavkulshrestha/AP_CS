/*
    Manav Kulshrestha
    Cabbages.java
    1/17/18
 */
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Cabbages {
    public static void main(String[] args) throws IOException {
        final String FILENAME = "cabbages.txt";
        String fileContent;
        String[] punctuations = {"\"", ".", ",", ";", ":", "-", "?", "!", "'"}, words, lines;
        int[] info = new int[] {0, 0};
        int newLength;

        print("Words found in text --\n");
        printf("The longest word in the text is <%s>\n\n", partOne(FILENAME, "%d %s\n", info));

        print("Words sorted alphabetically with duplicates removed --\n");
        words = partTwo(FILENAME, info[0], punctuations);
        Arrays.sort(words);

        newLength = makeStartUnique(words);
        for(int i=0; i<newLength; i++)
            printf("%d %s\n", i, words[i]);

        lines = new String[info[1]];
        fileContent = readFile(FILENAME, lines);

        Scanner in = new Scanner(System.in);
        for(int repeat=0; repeat<3; repeat++) {
            print("\nSomething to grep: ");
            grep(in.nextLine(), fileContent, lines, "\nLine %d: %s", "\nPhrase doesn't appear in any lines");
            print("\n");
        }
    }

    public static String partOne(String fileName, String format, int[] info) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line, word, longestWord = null;
        int temp, longestWordLength = 0;

        for(info[0]=0/*number of words*/, info[1]=0/*number of lines*/; (line = br.readLine()) != null; info[1]++) {
            StringTokenizer st = new StringTokenizer(line);
            for(int j=0; st.hasMoreTokens(); j++) {
                word = st.nextToken();
                if((temp = word.length()) > longestWordLength) {
                    longestWord = word;
                    longestWordLength = temp;
                }
                printf(format, info[0]++, word);
            }
        }

        return longestWord;
    }

    public static String[] partTwo(String fileName, int n, String[] punctuations) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String[] words = new String[n];

        for(int i=0; i<n;) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens())
                words[i++] = removePunctuations(st.nextToken(), punctuations).toLowerCase();
        }

        return words;
    }

    public static String readFile(String fileName, String[] lines) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String ret = "", line;

        for(int i=0; (line = br.readLine()) != null; i++) {
            ret += line;
            lines[i] = line;
        }

        return ret;
    }

    public static void grep(String phrase, String fileContent, String[] lines, String format, String nullPhrase) {
        int fileContentLength;
        String[] newLines = new String[lines.length];
        boolean noneFound = true;

        fileContent = fileContent.replace(phrase, "<"+phrase+">");
        fileContentLength = fileContent.length();

        for(int lIndex=0, lStartIndex=0, fCIndex=0; lIndex<lines.length; lIndex++) {
            for(int currLineIndex=0, upperBound = lines[lIndex].length(); currLineIndex<upperBound; currLineIndex++, fCIndex++) {
                char c = fileContent.charAt(fCIndex);
                if(c == '<' || c == '>')
                    upperBound++;
            }
            newLines[lIndex] = fileContent.substring(lStartIndex, fCIndex)+(((fCIndex == fileContentLength-1) && (fileContent.charAt(fCIndex) == '>')) ? ">" : "");
            lStartIndex = fCIndex;
        }

        for(int i=0; i<newLines.length; i++) {
            if(newLines[i].indexOf('<') >= 0) {
                noneFound = false;
                do
                    if(i < newLines.length)
                        printf(format, i, newLines[i]);
                while (i < newLines.length && newLines[i++].indexOf('>') == -1);
            }
        }

        if(noneFound)
            print(nullPhrase);
    }


    public static String removePunctuations(String s, String[] replaceArr) {
        for(String str: replaceArr)
            s = s.replace(str, "");
        return s;
    }

    public static int makeStartUnique(String arr[]) {
        if(arr.length < 2)
            return arr.length;

        int j = 0;
        for(int i=1; i<arr.length; i++)
            if (!arr[i].equals(arr[j]))
                arr[++j] = arr[i];

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