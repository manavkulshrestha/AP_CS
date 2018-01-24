/*
    Manav Kulshrestha
    Cabbages.java
    1/17/18
 */
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Cabbages2 {
    public static void main(String[] args) throws IOException {
        final String FILENAME = "cabbages.txt";
        String fileContent;
        String[] punctuations = {"\"", ".", ",", ";", ":", "--", "?", "!", " '", "' "}, words;
        int[] info = new int[] {0};
        int newLength;

        print("Words found in text --\n");
        printf("The longest word in the text is <%s>\n\n", partOne(FILENAME, "%d %s\n", info));

        print("Words sorted alphabetically with duplicates removed --\n");
        words = partTwo(FILENAME, info[0], punctuations);
        Arrays.sort(words);

        newLength = makeStartUnique(words);
        for(int i=0; i<newLength; i++)
            printf("%d %s\n", i, words[i]);
        print("\n");

        fileContent = readFileToString(FILENAME);

    }

    public static String partOne(String fileName, String format, int[] info) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line, word, longestWord = null;
        int temp, longestWordLength = 0;

        for(info[0]=0/*number of words*/; (line = br.readLine()) != null;) {
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

    public static String readFileToString(String fileName) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String ret = br.readLine(), line;

        while((line = br.readLine()) != null)
            ret += "\n"+line;

        return ret;
    }

    public static String removePunctuations(String s, String[] replaceArr) {
        for(String str: replaceArr)
            s = s.replace(str, "");

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
        for(int i=1; i < arr.length; i++)
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