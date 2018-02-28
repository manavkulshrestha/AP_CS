/*
    Manav Kulshrestha
    States.java
    2/26/18
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UnitedStates {
    public static void main(String args[]) throws IOException {
        ArrayList<String> states = readFile("states.txt");
        PrintWriter out;
        menu input;
        Scanner in = new Scanner(System.in);

        do {
            for(menu option: menu.values())
                pf("%d. %s%n", option.ordinal(), option);
            p(':');

            switch(input = menu.values()[in.nextInt()]) {
                case displayList:
                    for(String state: states)
                        pf("%n%s", state);
                    if(!states.isEmpty())
                        p("\n");
                    p("\n");
                    break;
                case insertItem:
                    in.nextLine(); // because apparently nextLine means sameLine
                    String insertState = in.nextLine();

                    if(!states.isEmpty()) {
                        states.add(modifiedBinarySearch(states, insertState), insertState);
                    } else
                        states.add(insertState);
                    break;
                case removeItem:
                    in.nextLine(); // because apparently nextLine means sameLine
                    states.remove(in.nextLine());
                    break;
                case quit:
                case saveList:
                    out = new PrintWriter(new File("states.txt"));
                    for(String state: states)
                        out.println(state);
                    out.close();
                    break;
            }
        } while(input != menu.quit);
    }

    public static ArrayList<String> readFile(String fileName) throws IOException{
        ArrayList<String> states = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String state;

        while((state = br.readLine()) != null)
            states.add(state);

        br.close();
        return states;
    }

    public static int modifiedBinarySearch(ArrayList<String> list, String key) {
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
        System.out.print(o);
    }
    public static void pf(String s, Object... o) {
        System.out.printf(s, o);
    }
}

enum menu {displayList, insertItem, removeItem, saveList, quit};