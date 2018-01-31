/*
    Manav Kulshrestha
    Main.java
    1/30/18
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("cipher.txt"));
        String lineString, poem = "", newPoem = "";
        char[] frequencyChart = getUpperAlphabets("E T A O N R I S H D L F C M U G Y P W B V K X J Q Z");
        Record[] textRecord = new Record[26];

        for(int i=0; i<textRecord.length; i++)
            textRecord[i] = new Record((char)(i+'A'));

        pa(textRecord); p('\n');

        for(int i=0; (lineString = br.readLine()) != null; i++) {
            if(i == 0)
                poem += lineString;
            else
                poem += '\n'+lineString;
            for(char c: getUpperAlphabets(lineString)) {
                textRecord[c-'A'].changeFrequency(1);
            }
        }
        Arrays.sort(textRecord, Comparator.reverseOrder());
        pa(textRecord);

        for(int i=0; i<poem.length(); i++) {
            char c = poem.charAt(i);
            if(c >= 'A' && c <= 'Z') {
                for(int j=0; j<textRecord.length; j++) {
                    if(textRecord[j].getOriginalLetter() == c) {
                        newPoem += frequencyChart[j];
                        break;
                    }
                }
            } else
                newPoem += c;
        }

        p("\n\n"+poem);
        p("\n\nNew Poem:");
        p("\n\n"+newPoem);
    }

    public static char[] getUpperAlphabets(String s) {
        return s.replaceAll("[^A-Z]", "").toCharArray();
    }

    public static Object p(Object o) {
        System.out.print(o);
        return o;
    }
    public static void pf(String s, Object... o) {
        System.out.printf(s, o);
    }
    public static void pa(Object[] a) {
        p(Arrays.toString(a));
    }
}