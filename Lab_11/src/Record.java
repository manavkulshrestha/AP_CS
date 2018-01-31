/*
    Manav Kulshrestha
    Record.java
    1/30/18
*/

import com.sun.org.apache.regexp.internal.RE;

public class Record implements Comparable<Record>{
    private char originalLetter;
    private int frequency;

    public Record(char letter, int freq) {
        this.originalLetter = letter;
        this.frequency = freq;
    }

    public Record(char letter) {
        this(letter, 0);
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

    public String toString() {
        return originalLetter+":"+frequency;
    }

//    public Record difference(Record a, Record b) {
//        return new Record(a, b)
//    }

    @Override
    public int compareTo(Record other) {
        return this.frequency - other.frequency;
    }
}