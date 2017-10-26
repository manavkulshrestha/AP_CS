/*
	Manav Kulshrestha
 	H Block
 	9/20/2017
 	StatisticsPacket.java
 */
//NOTE:	i know how to do for-each loops, but since I'm already taking the length of the data array,
//		i might as well use it to make things slightly faster
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StatisticsPacket {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] data = readFile("./data.txt", "File doesn't exist: \"%s\"");
        int in, len=data.length;
        String menu="1. Mean\n2. Median\n3. Mode\n4. Range\n5. Standard Deviation\n\nChoose option: ";

        System.out.print("Data:\n\n");
        displayArr(data, 10, len);

        System.out.print("\n\n");
        data=sort1(data, len);

        System.out.print("Sorted:\n\n");
        displayArr(data, 10, len);

        System.out.print("\n\n");

        do {
            System.out.print(menu);
            in=input.nextInt();
            System.out.print("\n");
            switch(in) {
                case -1:
                    break;
                case 1:
                    System.out.printf("The Mean is %s", mean(data, len));
                    break;
                case 2:
                    System.out.printf("The Median is %s", median(data, len));
                    break;
                case 3:
                    int[] mode = mode(data, len);
                    switch(mode[0]) {
                        case 0:
                            //no modes
                            System.out.print("Mode doesn't exist");
                            break;
                        case 1:
                            //one mode
                            System.out.printf("The Mode is %d", mode[1]);
                            break;
                        case 2:
                            //bi-modal
                            System.out.printf("The Modes are %d and %d", mode[1], mode[2]);
                            break;
                    }
                    break;
                case 4:
                    System.out.printf("The Range is %s", range(data, len));
                    break;
                case 5:
                    System.out.printf("The Standard Deviation is %s", standardDeviation(data, mean(data, len), len));
                    break;
                default:
                    System.out.printf("Invalid input: \"%d\"", in);
                    break;
            }
            System.out.print("\n\n");
        } while(in != -1);

        input.close();
    }

    public static int[] readFile(String pathname, String err_msg) {
        File file = new File(pathname);
        Scanner file_input=null;

        try {
            file_input = new Scanner(file);
        }
        catch(FileNotFoundException ex) {
            System.out.printf(err_msg, pathname);
            System.exit(-1);
        }

        int[] data = new int[file_input.nextInt()];

        for(int i=0; file_input.hasNextInt(); i++)
            data[i]=file_input.nextInt();

        return data;
    }

    public static void displayArr(int[] arr, int rowlim, int len) {
        for(int i=0; i<len; i++) {
            System.out.print(arr[i]);
            if((i+1)%rowlim == 0)
                System.out.print("\n");
            else
                System.out.print(" ");
        }
    }

    public static int swapInts(int x, int y) {
        //b=swapInts(a, a=b); don't have to use a temp variable this way.
        return x;
    }

    public static int[] sort1(int[] arr, int len) {
        //compares two adjacent elements, swaps if in wrong order
        //after one iteration, last element will be the largest
        //repeats for array excluding last element from previous iteration
        //slow. worst case, it's gonna run through len^2 times
        //base case
        if(len == 1)
            return arr;

        //I was using varargs before, but removed that since data.length was being calculated in each function
        for(int i=0; i<len-1; i++) {
            if(arr[i]>arr[i+1])
                arr[i+1]=swapInts(arr[i], arr[i]=arr[i+1]);
        }

        return sort1(arr, len-1);
    }

//	public static int[] sort2(int[] arr) {
//		//
//
//		return arr;
//	}

    public static double mean(int[] data, int len) {
        double mean=0;

        for(int i=0; i<len; i++)
            mean += data[i];

        return mean/len;
    }

    public static double median(int[] data, int len) {
        int mid=len/2;

        if(len%2 == 0)
            return (data[mid-1]+data[mid])/2;
        return data[mid];
    }

    public static int range(int[] data, int len) {
        return data[len-1]-data[0];
    }

    public static double standardDeviation(int[] data, double mean, int len) {
        double sd=0;

        for(int i=0; i<len; i++)
            sd += Math.pow(data[i]-mean, 2);

        return Math.sqrt(sd/(len-1));
    }

    public static int[] mode(int[] data, int len) {
//		//creates HashMap of form {number: frequency}. Doesn't account for multiple numbers of same frequency.
//		HashMap<Integer, Integer> temp = new HashMap<Integer, Integer>();
//		int maxFreq=1, mode=data[0];
//
//		for(int number: data) {
//			if(temp.containsKey(number)) {
//				int freq=temp.get(number);
//				freq++;
//				if(freq>maxFreq) {
//					maxFreq=freq;
//					mode=number;
//				}
//			}
//			else
//				temp.put(number, 1);
//		}
        //oh wait, it's already sorted.
        //counts occurances of numbers and sees when next number changes. goes through n times
        //doesn't account for bimodal data.
//		int mode=data[0], modeFreq=1;
//
//		for(int i=1, freq=1, num=data[0]; i<len; i++) {
//			if(data[i] == num)
//				freq++;
//			else {
//				if(freq>modeFreq) {
//					mode=num;
//					modeFreq=freq;
//				}
//				num=data[i];
//				freq=1;
//			}
//		}
        //returns array of form {number of modes(0, 1, 2), modes...}
        int[] freqs = new int[len], mode = new int[3];
        int modeCount=0, maxFreq=1;
        freqs[0]=1;
        //Populates freqs with frequencies of numbers in data.
        //freqs won't be fully populated, but since I can't use lists.
        for(int i=1, j=0, num=data[0]; i<len; i++) {
            if(data[i] != num) {
                num=data[i];
                j++;
            }
            freqs[j]++;
        }

        //Gets the largest number in freqs
        for(int i=0; i<len; i++)
            if(freqs[i]>maxFreq)
                maxFreq=freqs[i];

        //Counts the occurances of said number
        for(int i=0, modeIndex =- 1; i<len; i++) {
            modeIndex += freqs[i];
            if(freqs[i] == maxFreq) {
                modeCount++;
                if(modeCount>2)
                    return new int[] {0};
                mode[modeCount-1]=data[modeIndex];
            }
        }

        if(modeCount == 1)
            return new int[] {1, mode[0]};
        return new int[] {2, mode[0], mode[1]};
    }
}