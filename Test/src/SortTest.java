import java.util.Arrays;

public class SortTest {
    public static void main(String[] args) {
        char[] lawrence = {'h', 'e', 'g', 'f', 'd', 'b', 'a', 'c'};
        System.out.print(Arrays.toString(lawrence)+"\n");

        selection(lawrence);

        System.out.print(Arrays.toString(lawrence));
    }

    public static int selection(char[] list) {
        for(int n=list.length; n>1; n--) {
            // Find the index iMax of the largest element
            //   among list[0], ..., list[n-1]:

            int iMax = 0;
            for(int i=1; i<n; i++)
                if(list[i] > list[iMax])
                    iMax = i;

            swap(list, iMax, n-1);
            System.out.print(Arrays.toString(list)+"\n");
            // int listTemp = list[iMax];
            // list[iMax] = list[n-1];
            // list[n-1] = listTemp;

            // Decrement n (accomplished by n-- in the for loop).
        }
        return 0;
    }

    public static void swap(char[] list, int a, int b) {
        char temp = list[a];
        list[a] = list[b];
        list[b] = temp;
    }

    public static int merge(char[] list, int from, int middle, int to) {
        char[] temp = new char[list.length];
        int i = from, j = middle+1, k = from;

        // While both arrays have elements left unprocessed:
        while(i <= middle && j <= to) {
            if(list[i]<list[j])
                temp[k++] = list[i++];
            else
                temp[k++] = list[j++];
        }

        // Copy the tail of the first half, if any, into temp:
        while(i <= middle)
            temp[k++] = list[i++];

        // Copy the tail of the second half, if any, into temp:
        while(j <= to)
            temp[k++] = list[j++];

        // Copy temp back into a
        for(k=from; k<=to; k++)
            list[k] = temp[k];
        return 0;
    }

    public static int mergeSort(char[] list, int from, int to) {
        if(to-from < 2) { // Base case: 1 or 2 elements
            if(to>from && list[to]<list[from]) {
                swap(list, to, from);
                System.out.print("swapped: "+Arrays.toString(list)+"\n");
            }

        } else { // Recursive case
            int middle = (from + to) / 2;
            mergeSort(list, from, middle);
            System.out.print("1: "+Arrays.toString(list)+"\n");
            mergeSort(list, middle+1, to);
            System.out.print("2: "+Arrays.toString(list)+"\n");
            merge(list, from, middle, to);
            System.out.print("merged: "+Arrays.toString(list)+"\n");
        }
        return 0;
    }


    public static int quickSort(char[] list, int from, int to) {
        if(from >= to)
            return 0;

        // Choose pivot list[p]:
        int p = from;
        // The choice of the pivot location may vary:
        //   you can also use p = from or p = to or use
        //   a fancier method, say, the median of the above three.

        // Partition:

        int i = from;
        int j = to;
        while(i <= j) {
            if(list[i] <= list[p])
                i++;
            else if (list[j] >= list[p])
                j--;
            else
            {
                swap(list, i++, j--);
                System.out.print("pSort: "+Arrays.toString(list)+"\n");
            }
        }

        // Finish partitioning:

        if(p<j) {// place the pivot in its correct position
            swap(list, j, p);
            p = j;
            System.out.print("pSwap: "+Arrays.toString(list)+"\n");
        }
        else if(p>i) {
            swap(list, i, p);
            p = i;
            System.out.print("pSwap: "+Arrays.toString(list)+"\n");
        }

        // Sort recursively:
        quickSort(list, from, p-1);
        quickSort(list, p+1, to);

        return 0;
    }
}
