//********************************************************************
//  Sorts.java       Author: Manav Kulshrestha
//
//  A collection of methods for sorting Arrays.
//********************************************************************

import java.util.List;
import java.util.Random;

public class Sorts
{
    
	private static int[] temp;
	
	public static Group Insertion(int[] list)
    {
        Group icount = new Group();
		for (int n = 1; n < list.length; n++)
		{
			// Save the next element to be inserted:
			
			int listTemp = list[n];
			icount.move++;
			
			// Going backward from list[n-1], shift elements to the
			//   right until you find an element list[i] <= aTemp:
			
			int i = n;
			icount.compare++;
			while (i > 0 && listTemp < list[i-1])
			{
                list[i] = list[i-1];
                icount.move++;
				i--;
				icount.compare++;
			}
			
			// Insert the saved element after a[i]:
			list[i] = listTemp;
			icount.move++;
			
			// Increment n (accomplished by n++ in the for loop).
		}
		
        return icount;
    }
	
    
    public static Group Selection(int[] list)
    {
    	Group scount = new Group(0, 0);
		for (int n = list.length; n > 1; n--)
		{
			// Find the index iMax of the largest element
			//   among list[0], ..., list[n-1]:
			
			int iMax = 0;
			for (int i = 1; i < n; i++)
			{
				if (list[i] > list[iMax])
					iMax = i;
			}
			scount.compare += n-1;
			
			swap(list, iMax, n-1);
            scount.move += 3;
			
			// Decrement n (accomplished by n-- in the for loop).
		}
        return scount;
    }

    public static Group Merge(int[] list, int from, int middle, int to)
    {
        Group mcount = new Group();
		temp = new int[list.length];
		int i = from, j = middle+1, k = from;
		
		// While both arrays have elements left unprocessed:
		while (i <= middle && j <= to)
		{
            mcount.move++;
            mcount.compare++;
			if (list[i] < list[j])
				temp[k++] = list[i++];
			else
				temp[k++] = list[j++];
		}
		
		// Copy the tail of the first half, if any, into temp:
        mcount.move += (middle-i)+1;
		while (i <= middle)
			temp[k++] = list[i++];
		
		// Copy the tail of the second half, if any, into temp:
        mcount.move += (to-j)+1;
		while (j <= to)
			temp[k++] = list[j++];
		
		// Copy temp back into a
        mcount.move += (to-from)+1;
		for (k = from; k <= to; k++)
            list[k] = temp[k];

		return mcount;
    }
	
	public static Group mergeSort(int[] list, int from, int to)
	{
	    Group mcount = new Group();
		if (to-from < 2)       // Base case: 1 or 2 elements
		{
			mcount.compare++;
			if (to > from && list[to] < list[from])
			{
			    swap(list, to, from);
                mcount.move += 3;
			}
		}
		else                     // Recursive case
		{
			int middle = (from+to)/2;
			mcount.add(mergeSort(list, from, middle));
			mcount.add(mergeSort(list, middle+1, to));
			mcount.add(Merge(list, from, middle, to));
		}
		return mcount;
	}
	
    public static Group QuickSort(int[] list, int from, int to)
    {
        Group qcount = new Group();

		if (from >= to)
			return qcount;
		
		// Choose pivot list[p]:
		int p = from;
		// The choice of the pivot location may vary:
		// you can also use p = from or p = to or use
		// a fancier method, say, the median of the above three.
		
		// Partition:
		
		int i = from;
		int j = to;
		while (i <= j)
		{
			if (list[i] <= list[p])
			{
				i++;
                qcount.compare++;
			}
			else if (list[j] >= list[p])
			{
                j--;
                qcount.compare += 2;
            }
			else
			{
				swap(list, i++, j--);
                qcount.move += 3;
                qcount.compare += 2;
			}
		}
		
		// Finish partitioning:
		
		if (p < j)    // place the pivot in its correct position
		{
			swap(list, j, p);
			p=j;
            qcount.move += 3;
		}
		else if (p > i)
		{
			swap(list, i, p);
			p=i;
            qcount.move += 3;
		}
		
		// Sort recursively:
		qcount.add(QuickSort(list, from, p - 1));
		qcount.add(QuickSort(list, p + 1, to));
		
		return qcount;
	}

    public static Group QuickMid(int[] list, int from, int to)
	{
        Group qcount = new Group();

		if (from >= to)
			return qcount;

		// Choose pivot list[p]:
		int p = (from+to)/2;
		// The choice of the pivot location may vary:
		//   you can also use p = from or p = to or use
		//   a fancier method, say, the median of the above three.

		// Partition:

		int i = from;
		int j = to;
		while (i <= j)
		{
			if (list[i] <= list[p])
			{
				i++;
				qcount.compare++;
			}
			else if (list[j] >= list[p])
			{
				j--;
                qcount.compare += 2;
			}
			else
			{
				swap(list, i++, j--);
                qcount.move += 3;
                qcount.compare += 2;
			}
		}

		// Finish partitioning:

		if (p < j)    // place the pivot in its correct position
		{
			swap(list, j, p);
			p=j;
            qcount.move += 3;
		}
		else if (p > i)
		{
			swap(list, i, p);
			p=i;
            qcount.move += 3;
		}

		// Sort recursively:
		qcount.add(QuickMid(list, from, p-1));
		qcount.add(QuickMid(list, p+1, to));

		return qcount;
    }
    
	public static Group QuickRandom(int[] list, int from, int to)
    {
		Group qcount = new Group();

		if (from >= to)
			return qcount;

		// Choose pivot list[p]:
		int p = ListSetup.rand.nextInt(to-from)+from;
		//   The choice of the pivot location may vary:
		//   you can also use p = from or p = to or use
		//   a fancier method, say, the median of the above three.

		// Partition:

		int i = from;
		int j = to;
		while (i <= j)
		{
			if (list[i] <= list[p])
			{
				i++;
				qcount.compare++;
            }
			else if (list[j] >= list[p])
			{
				j--;
                qcount.compare += 2;
			}
			else
			{
				swap(list, i++, j--);
                qcount.move += 3;
                qcount.compare += 2;
			}
		}

		// Finish partitioning:

		if (p < j)    // place the pivot in its correct position
		{
			swap(list, j, p);
            p = j;
            qcount.move += 3;
		}
		else if (p > i)
		{
			swap(list, i, p);
            p = i;
            qcount.move += 3;
		}

		// Sort recursively:
		qcount.add(QuickRandom(list, from, p - 1));
		qcount.add(QuickRandom(list, p + 1, to));

		return qcount;
    }
    
    private static void swap (int[] list, int a, int b)
    {
        int temp = list[a];
		list[a] = list[b];
		list[b] = temp;
    }
}

class Group {
    int compare;
    int move;

    public Group(int compare, int move) {
        this.compare = compare;
        this.move = move;
    }

    public Group() {
        this(0, 0);
    }

    public void add(Group a) {
        this.compare += a.compare;
        this.move += a.move;
    }

    public void print(String formatLabel) {
        System.out.printf(formatLabel, this.compare, this.move);
    }
}