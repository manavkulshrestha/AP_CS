//********************************************************************
//  Sortlab.java       Author: Manav Kulshrestha
//
//  Driver to exercise the use of several Sorting Arrays methods.
//********************************************************************


public class SortsLab {
    static final int MAX = 1000;

    public SortsLab() {
        int[] list = new int[MAX], list2 = new int[MAX], list3 = new int[MAX], list4 = new int[MAX], list5 = new int[MAX], list6 = new int[MAX], list0 = new int[MAX];
        Group qcount, icount, scount, mcount;

        Group test;

        //RANDOM:
        System.out.println("Random:");

        ListSetup.MakeRandom(list);
        ListSetup.Copy(list, list0);
        ListSetup.Copy(list, list2);
        ListSetup.Copy(list, list3);
        ListSetup.Copy(list, list4);
        ListSetup.Copy(list, list5);
        ListSetup.Copy(list, list6);

        System.out.println("List before sorting:");
//        ListSetup.Print(list);
//        System.out.println();

        qcount = Sorts.QuickSort(list, 0, list.length-1);
//        System.out.println("Here is the list after the Quicksort (split first). ");
//	      ListSetup.Print(list);
        qcount.print("[qcount] compareCount: %d, moveCount: %d\n\n");

        scount = Sorts.Selection(list2);
//        System.out.println("Here is the list after the Selection Sort. ");
//		  ListSetup.Print(list2);
        scount.print("[scount] compareCount: %d, moveCount: %d\n\n");

        icount = Sorts.Insertion(list3);
//        System.out.println("Here is the list after the Insertion Sort. ");
//		  ListSetup.Print(list3);
        icount.print("[icount] compareCount: %d, moveCount: %d\n\n");

        mcount = Sorts.mergeSort(list4, 0, list.length-1);
//        System.out.println("Here is the list after the Merge Sort. ");
//        ListSetup.Print(list4);
        mcount.print("[mcount] compareCount: %d, moveCount: %d\n\n");

        qcount = Sorts.QuickRandom(list5, 0, list.length - 1);
//        System.out.println("Here is the list after the QuickRandom. ");
//        ListSetup.Print(list5);
        qcount.print("[qcount]-RANDOM compareCount: %d, moveCount: %d\n\n");

        qcount = Sorts.QuickMid(list6, 0, list.length - 1);
//        System.out.println("Here is the list after the QuickMid. ");
//        ListSetup.Print(list6);
        qcount.print("[qcount]-MID compareCount: %d, moveCount: %d\n\n");

        //ASCENDING:
        System.out.println("Ascending:");

        ListSetup.MakeInOrder(list);
        ListSetup.Copy(list, list0);
        ListSetup.Copy(list, list2);
        ListSetup.Copy(list, list3);
        ListSetup.Copy(list, list4);
        ListSetup.Copy(list, list5);
        ListSetup.Copy(list, list6);

        System.out.println("List before sorting:");
//        ListSetup.Print(list);
//        System.out.println();

        qcount = Sorts.QuickSort(list, 0, list.length-1);
//        System.out.println("Here is the list after the Quicksort (split first). ");
//	      ListSetup.Print(list);
        qcount.print("[qcount] compareCount: %d, moveCount: %d\n\n");

        scount = Sorts.Selection(list2);
//        System.out.println("Here is the list after the Selection Sort. ");
//		  ListSetup.Print(list2);
        scount.print("[scount] compareCount: %d, moveCount: %d\n\n");

        icount = Sorts.Insertion(list3);
//        System.out.println("Here is the list after the Insertion Sort. ");
//		  ListSetup.Print(list3);
        icount.print("[icount] compareCount: %d, moveCount: %d\n\n");

        mcount = Sorts.mergeSort(list4, 0, list.length-1);
//        System.out.println("Here is the list after the Merge Sort. ");
//        ListSetup.Print(list4);
        mcount.print("[mcount] compareCount: %d, moveCount: %d\n\n");

        qcount = Sorts.QuickRandom(list5, 0, list.length - 1);
//        System.out.println("Here is the list after the QuickRandom. ");
//        ListSetup.Print(list5);
        qcount.print("[qcount]-RANDOM compareCount: %d, moveCount: %d\n\n");

        qcount = Sorts.QuickMid(list6, 0, list.length - 1);
//        System.out.println("Here is the list after the QuickMid. ");
//        ListSetup.Print(list6);
        qcount.print("[qcount]-MID compareCount: %d, moveCount: %d\n\n");

        //DESCENDING:
        System.out.println("Descending:");

        ListSetup.MakeReverse(list);
        ListSetup.Copy(list, list0);
        ListSetup.Copy(list, list2);
        ListSetup.Copy(list, list3);
        ListSetup.Copy(list, list4);
        ListSetup.Copy(list, list5);
        ListSetup.Copy(list, list6);

        System.out.println("List before sorting:");
//        ListSetup.Print(list);
//        System.out.println();

        qcount = Sorts.QuickSort(list, 0, list.length-1);
//        System.out.println("Here is the list after the Quicksort (split first). ");
//	      ListSetup.Print(list);
        qcount.print("[qcount] compareCount: %d, moveCount: %d\n\n");

        scount = Sorts.Selection(list2);
//        System.out.println("Here is the list after the Selection Sort. ");
//		  ListSetup.Print(list2);
        scount.print("[scount] compareCount: %d, moveCount: %d\n\n");

        icount = Sorts.Insertion(list3);
//        System.out.println("Here is the list after the Insertion Sort. ");
//		  ListSetup.Print(list3);
        icount.print("[icount] compareCount: %d, moveCount: %d\n\n");

        mcount = Sorts.mergeSort(list4, 0, list.length-1);
//        System.out.println("Here is the list after the Merge Sort. ");
//        ListSetup.Print(list4);
        mcount.print("[mcount] compareCount: %d, moveCount: %d\n\n");

        qcount = Sorts.QuickRandom(list5, 0, list.length-1);
//        System.out.println("Here is the list after the QuickRandom. ");
//        ListSetup.Print(list5);
        qcount.print("[qcount]-RANDOM compareCount: %d, moveCount: %d\n\n");

        qcount = Sorts.QuickMid(list6, 0, list.length-1);
//        System.out.println("Here is the list after the QuickMid. ");
//        ListSetup.Print(list6);
        qcount.print("[qcount]-MID compareCount: %d, moveCount: %d\n\n");
    }

    public static void main(String args[])
    {
        new SortsLab();
    }
}
