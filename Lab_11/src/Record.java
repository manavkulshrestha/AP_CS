/*
    Manav Kulshrestha
    Record.java
    1/30/18
*/

public class Record implements Comparable<Record> {
    private char originalLetter;
    private int frequency;
    private char replace;

    public Record(char letter, int freq, char replace) {
        this.originalLetter = letter;
        this.frequency = freq;
        this.replace = replace;
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
        this.replace = replace;
    }

    public char getReplaceLetter() {
        return this.replace;
    }

    public void swapReplace(Record other) {
        char temp = this.replace;
        this.replace = other.replace;
        other.setReplaceLetter(temp);
    }

    public String toString() {
        return this.originalLetter+":"+this.frequency+":"+this.replace;
    }

    @Override
    public int compareTo(Record other) {
        return this.frequency-other.frequency;
    }
}