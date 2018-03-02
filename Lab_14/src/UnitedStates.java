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
        final String FILENAME = "states.txt";
        ArrayList<String> states = readFile(FILENAME);
        PrintWriter out = null;
        menu input;
        Scanner in = new Scanner(System.in);

        do {
            pMenu();

            switch(input = menu.values()[in.nextInt()]) {
                case displayList:
                    displayStates(states);
                    break;
                case insertItem:
                    in.nextLine(); // because apparently nextLine means sameLine
                    insertState(states, in.nextLine());
                    break;
                case removeItem:
                    in.nextLine(); // because apparently nextLine means sameLine
                    removeState(states, in.nextLine());
                    break;
                case quit:
                case saveList:
                    saveStates(states, out, FILENAME);
                    break;
            }
        } while(input != menu.quit);
    }

    public static ArrayList<String> readFile(String fileName) throws IOException{
        ArrayList<String> states = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String state;

        while((state = br.readLine()) != null) {
            if(states.isEmpty())
                states.add(state);
            else
                states.add(modifiedBinarySearch(states, state), state);
        }

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

    public static void insertState(ArrayList<String> states, String insertState) {
        if(!states.isEmpty()) {
            if(!states.contains(insertState))
                states.add(modifiedBinarySearch(states, insertState), insertState);
        } else
            states.add(insertState);
    }

    public static void removeState(ArrayList<String> states, String removeState) {
        states.remove(removeState);
    }

    public static void displayStates(ArrayList<String> states) {
        for(String state: states)
            pf("%n%s", state);
        if(!states.isEmpty())
            p("\n");
        p("\n");
    }

    public static void saveStates(ArrayList<String> states, PrintWriter out, String fileName) throws IOException {
        out = new PrintWriter(new File(fileName));
        for(String state: states)
            out.println(state);
        out.close();
    }

    public static void pMenu() {
        for(menu option: menu.values())
            pf("%d. %s%n", option.ordinal(), option);
        p(':');
    }

    public static void p(Object o) {
        System.out.print(o);
    }
    public static void pf(String s, Object... o) {
        System.out.printf(s, o);
    }
}

enum menu {displayList, insertItem, removeItem, saveList, quit};