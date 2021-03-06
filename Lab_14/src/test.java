/*
    Manav Kulshrestha
    test.java
    2/27/18
*/

import java.util.ArrayList;

public class test {
    static ArrayList<String> list = new ArrayList<>();

    public static void main(String args[]) {
        list.add("Arushi");
        list.add("Jack");
        list.add("Manav");
        list.add("Yuval");

        p(modifiedBinarySearch("Arushi"));
        p(modifiedBinarySearch("Jack"));
        p(modifiedBinarySearch("Manav"));
        p(modifiedBinarySearch("Yuval"));

        p(modifiedBinarySearch("Aa"));
        p(modifiedBinarySearch("Ia"));
        p(modifiedBinarySearch("La"));
        p(modifiedBinarySearch("Xa"));
        p(modifiedBinarySearch("Z"));
    }

    public static int modifiedBinarySearch(String key) {
        int low  = 0, size = list.size(), high = size-1;

        while(low <= high) {
            int mid = low + ((high-low)/2);

            if(list.get(mid).compareTo(key) < 0)
                low = mid + 1;
            else if(list.get(mid).compareTo(key) > 0)
                high = mid - 1;
            else
                return mid + 1;
        }

        if(high < 0)
            return 0;

        if(low > (size-1))
            return size;
        else
            return (low < high) ? low+1 : high+1;
    }

    public static void p(Object o) {
        System.out.print(o+"\n");
    }
}