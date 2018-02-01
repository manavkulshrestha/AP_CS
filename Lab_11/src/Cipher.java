/*
    Manav Kulshrestha
    Main.java
    1/30/18
*/

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Cipher {
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("cipher.txt"));
        BufferedWriter out;
        String lineString, poem = "", newPoem, fileName;
        char[] frequencyChart = getUpperAlphabets(new BufferedReader(new FileReader("replace.txt")).readLine());
        Record[] textRecord = new Record[26];
        Scanner in = new Scanner(System.in);

        // initializes Record array
        for(int i=0; i<textRecord.length; i++)
            textRecord[i] = new Record((char)(i+'A'));

        // records frequencies and stores poem
        for(int i=0; (lineString = br.readLine()) != null; i++) {
            if(i == 0)
                poem += lineString;
            else
                poem += '\n'+lineString;
            for(char c: getUpperAlphabets(lineString)) {
                textRecord[c-'A'].changeFrequency(1);
            }
        }
        // sorting based on frequency in reverse order
        Arrays.sort(textRecord, Comparator.reverseOrder());

        //put in replace chars
        for(int i=0; i<textRecord.length; i++)
            textRecord[i].setReplaceLetter(frequencyChart[i]);

        // assuming duplicate runs of max length 2. stores duplicates' indexes
        int duplicates = 0;
        for(int i=1; i<textRecord.length; i++)
            if(textRecord[i-1].compareTo(textRecord[i]) == 0)
                duplicates++;
        int[] duplicateStartIndexes = new int[duplicates];
        for(int i=1, j=0; i<textRecord.length; i++)
            if(textRecord[i-1].compareTo(textRecord[i]) == 0)
                duplicateStartIndexes[j++] = i-1;

        p("Old Poem:\n"+poem);
        p("\nNew Poem:\n");

        //user interaction to fix poem
        for(int i=0; i<duplicates; i++) {
            if(textRecord[duplicateStartIndexes[i]].getFrequency() > 0) {
                p(newPoem = decryptPoem(poem, textRecord));
                pf("\nSwitch %c and %c? (Y/N): ", textRecord[duplicateStartIndexes[i]].getReplaceLetter(), textRecord[duplicateStartIndexes[i]+1].getReplaceLetter());
                if(in.next().toUpperCase().charAt(0) == 'Y')
                    textRecord[duplicateStartIndexes[i]].swapReplace(textRecord[duplicateStartIndexes[i]+1]);
            }
        }
        p(newPoem = decryptPoem(poem, textRecord));
        p("\nEnter name of output file (with extention): ");

        out = new BufferedWriter(new FileWriter(fileName = in.next()));
        pf("Writing to file %s", fileName);
        out.write(newPoem);
        out.close();
    }

    public static char[] getUpperAlphabets(String s) {
        return s.replaceAll("[^A-Z]", "").toCharArray();
    }

    public static String decryptPoem(String originalPoem, Record[] record) {
        String newPoem = "";

        for(int i=0; i<originalPoem.length(); i++) {
            char c = originalPoem.charAt(i);
            if(c >= 'A' && c <= 'Z') {
                for(int j=0; j<record.length; j++) {
                    if(record[j].getOriginalLetter() == c) {
                        newPoem += record[j].getReplaceLetter();
                        break;
                    }
                }
            }
            else
                newPoem += c;
        }

        return newPoem;
    }

    public static void p(Object o) {
        System.out.print(o);
    }
    public static void pf(String s, Object... o) {
        System.out.printf(s, o);
    }
    public static void pa(Object[] a) {
        p(Arrays.toString(a));
    }
}