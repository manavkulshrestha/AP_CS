/*
    Manav Kulshrestha
    test.java
    1/18/18
*/

public class test {
    public static void main(String args[]) {
        System.out.print(works("CATBAGE", "BAG"));
//        System.out.print("CABBAGE".indexOf("CABB"));
    }

    public static int fO(String s, String t) {
        int sIndex = 0, tIndex = 0, startIndex = 0;
        boolean lastCharComparision = false;

        for(; sIndex<s.length(); ) {
            if(tIndex < t.length() && (lastCharComparision = (s.charAt(sIndex) == t.charAt(tIndex)))) {
//                startIndex = tIndex == 0 ? tIndex++ : startIndex;
                tIndex++;
                sIndex++;
                if (tIndex == t.length()-1)
                    return startIndex;
            } else {
                if(lastCharComparision)
                    return startIndex;
                else {
                    startIndex = sIndex;
                    sIndex += (tIndex == 0) ? 1 : 0;
                    tIndex = 0;
                }
            }
        }

        return -1;
    }

    public static int works(String s, String t) {
        int sIndex = 0, tIndex = 0, startIndex = 0;

        for(; sIndex<s.length();) {
            if (s.charAt(sIndex) == t.charAt(tIndex)) {
                if (tIndex == 0)
                    startIndex = sIndex;
                if (tIndex == t.length() - 1)
                    return startIndex;
                tIndex++;
                sIndex++;
            }
            else {
                startIndex = -1;
                if (tIndex == 0)
                    sIndex++;
                tIndex = 0;
            }
        }

        return -1;
    }

    public static int getIndex(String s, String t) {
        int tLen = t.length(), sLen = s.length();

//        if(s)
        return 0;
    }

//    int getIndex(String s, String t, int sLen, int tLen, int index) {
//        if(s.length() < t.length())
//            return -1;
//
//        if (s.substring(0, tLen).equals(t.substring(0, tLen)))
//            return index;
//
//        return getIndex(mystring+1, len1-1, substring, len2, index+1);
//    }

}
