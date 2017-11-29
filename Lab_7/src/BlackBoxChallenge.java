import java.util.Random;

public class BlackBoxChallenge {

    public static void main(String[] args) {
        char[][] box = new char[10][10];

        for(int i=0; i<box.length; i++)
            for(int j=0; j<box.length; j++)
                box[i][j] = '.';

        displayBox(box);
    }

    public static void placeMirrors(char[][] box, int n) {
        //1 and 2 is /. 3 and 4 is \
        Random rand = new Random();
        int x, y, cMax=box[0].length-1, rMax=box.length-1;

        for(int i=0; i<n; i++) {
            do {
                x = rand.nextInt(cMax);
                y = rand.nextInt(rMax);
            } while(box[x][y] != '.');

            switch(rand.nextInt(1)) {
                case 0:
                    box[x][y] = 1;
                    break;
                case 1:
                    box[x][y] = 3;
                    break;
            }
        }
    }

    //because writing System.out is tedious
    public static void printf(String s, Object... o) {
        System.out.printf(s, o);
    }

    public static void print(Object... o) {
        for(Object object : o)
            System.out.print(object);

    }

        /**
         *Recursive function to shoot laser. Outputs the end state of the laser in the box.
         *
         * @param box   2-D character array containing '.', '0', '1', '2', '3' chars.
         *              '0' and '1' represent mirror \. Laser colliding with these switches direction vectors and changes direction
         *              '2' and '3' represent mirror /.  Laser colliding with these only switches direction vectors
         * @param start start state of the laser. Form {x, y, i, j}
         *              Indexes 0 and 1 refer to x and y positions. These are modified as per direction vectors.
         *              Indexes 2 and 3 refer to i and j direction vectors
         * @param d0    whether the laser just started
         * @return      modified start state. Indexes 0 and 1 change as per direction vector. Direction vectors change as per mirror
         */
    public static int[] shootLaser(char[][] box, int[] start, boolean d0) {
        if(inIntArray(0, start) || inIntArray(9, start) && !d0)
            return start;

        start[0] += start[3];
        start[1] += start[2];

        if(box[start[0]][start[1]] != '.') {
            int temp = start[2];
            start[2] = start[3];
            start[3] = temp;
            if((int)(box[start[0]][start[1]]) <= 1)//is \
                for(int i = 0; i <= 1; i++)
                    start[i] = -start[i];
        }
        return shootLaser(box, start, false);
    }

    public static int[] getStart(int startPos) {
        int[] start = new int[4];
        //0, 1 are positions it starts on in box[][]. 2, 3 are direction vectors (i, j)

        if(startPos <= 9) {
            start[0] = 0;
            start[1] = startPos;
            start[2] = 0;
            start[4] = 1;
        } else if(startPos >= 10 && startPos <= 19) {
            start[0] = 10-startPos;
            start[1] = 0;
            start[2] = 1;
            start[4] = 0;
        } else if(startPos >= 20 && startPos <= 29) {
            start[0] = 9;
            start[1] = 20-startPos;
            start[2] = 0;
            start[4] = -1;
        } else if(startPos >= 30) {
            start[0] = 39-startPos;
            start[1] = 9;
            start[2] = -1;
            start[4] = 0;
        }
        return start;
    }

    public static int getEndPos(int[] end) {
        return (end[2] == 0) ? ((end[3] < 0) ? end[0] : 20+end[0]) : ((end[2] < 0) ? 10+end[1] : 39-end[1]);
    }

    //helper
    public static boolean inIntArray(int in, int[] arr) {
        for(int val: arr)
            if(val == in)
                return true;
        return false;
    }

    //display
    public static void displayBox(char[][] box) {
        print("   20212223242526272829\n");
        for(int i=box.length-1; i>=0; i--) {
            printf("%d ", 10+i);
            for(int colItem: box[i]) {
                switch(colItem) {
                    case '.':
                        print(". ");
                        break;
                    //case '0':
                    case '1':
                        print("/ ");
                        break;
                    //case '2':
                    case '3':
                        print("\\ ");
                        break;
                }
            }
            printf("%d \n", 39-i);
        }
        print("   0 1 2 3 4 5 6 7 8 9");
    }
}