/*
    Manav Kulshrestha
    Record.java
    1/30/18
*/

import java.util.Comparator;

public class Record implements Comparable<Record> {
//public class Record {
    private char originalLetter;
    private int frequency;
    private char replaceLetter;

    public Record(char letter, int freq, char replace) {
        this.originalLetter = letter;
        this.frequency = freq;
        this.replaceLetter = replace;
    }

    public Record(char letter) {
        this(letter, 0, letter);
    }

    public char getOriginalLetter() {
        return this.originalLetter;
    }

    public void setOriginalLetter(char newLetter) {
        this.originalLetter = newLetter;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public void setFrequency(int newFrequency) {
        this.frequency = newFrequency;
    }

    public void changeFrequency(int delta) {
        this.frequency += delta;
    }

    public void setReplaceLetter(char replace) {
        this.replaceLetter = replace;
    }

    public char getReplaceLetter() {
        return this.replaceLetter;
    }

    public void swapReplace(Record other) {
        char temp = this.replaceLetter;
        this.replaceLetter = other.replaceLetter;
        other.setReplaceLetter(temp);
    }

    public String toString() {
        return this.originalLetter+":"+this.frequency+":"+this.replaceLetter;
    }

    @Override
    public int compareTo(Record other) {
        return this.frequency-other.frequency;
    }
}

class originalLetterComparator implements Comparator {
    public int compare(Object a, Object b){
        Record rA = (Record)a;
        Record rB = (Record)b;
		return rA.getOriginalLetter() - rB.getOriginalLetter();
    }
}

class frequencyComparator implements Comparator {
    public int compare(Object a, Object b){
        Record rA = (Record)a;
        Record rB = (Record)b;
        return rA.getFrequency() - rB.getFrequency();
    }
}

class replaceLetterComparator implements Comparator {
    public int compare(Object a, Object b){
        Record rA = (Record)a;
        Record rB = (Record)b;
        return rA.getReplaceLetter() - rB.getReplaceLetter();
    }
}