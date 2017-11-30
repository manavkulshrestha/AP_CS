import java.util.Arrays;
import java.util.Random;

public class BlackBoxChallenge {

    public static void main(String[] args) {
//        char[][] box = new char[10][10];
        int[] startState;
        int endPos;

//        for(int i=0; i<box.length; i++)
//            for(int j=0; j<box.length; j++)
//                box[i][j] = '.';

        char[][] box = {
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '2', '.', '.', '.', '.', '.', '.', '.'},
        };

        //placeMirrors(box, 10);
        displayBox(box);
        startState = getStartState(19);
        endPos = shootLaser(box, startState[0], startState[1], startState[2], startState[3]);
        printf("\n%d\n", endPos);
    }

    public static void placeMirrors(char[][] box, int n) {
        //1 and 2 is /. 3 and 4 is \
        Random rand = new Random();
        int x, y, cMax=box[0].length, rMax=box.length;

        for(int i=0; i<n; i++) {
            do {
                x = rand.nextInt(cMax);
                y = rand.nextInt(rMax);
            } while(box[x][y] != '.');

            if(rand.nextBoolean())
                box[x][y] = '0';// \
            else
                box[x][y] = '2';// /
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
     *Recursive function to shoot laser. Outputs the end position using the end state of the laser.
     *
     * @param box   2-D character array containing '.', '0', '1', '2', '3' chars.
     *              '.' represents a no mirror (blank .). Laser proceeeds normally as per direction vectors
     *              '0' and '1' represent mirror \. Laser colliding with these switches direction vectors and changes direction
     *              '2' and '3' represent mirror /.  Laser colliding with these only switches direction vectors
     * @param x     X coordinate of the laser
     * @param y     Y coordinate of the laser
     * @param i     Horizontal direction vector of movement
     * @param j     Vertical direction vector of movement
     * @return      Modified state of the laser. x and y change as per direction vector. Direction vectors change as per mirror.
     */
//    public static int shootLaser(char[][] box, int x, int y, int i, int j) {
//        printf("\nX:%d;Y:%d;I:%d;J:%d; B:%s\n", x, y, i, j, box[x][y]);
//        if(wallCollision(x, y, i, j))
//            return getEndPos(x, y, i, j);
//        else if(box[x][y] == '.')
//            return shootLaser(box, x+i, y+j, i, j);
//        else if((int)box[x][y] <= 1)//Mirror is \
//            return shootLaser(box, x-j, y-i, -j, -i);
//        else
//            return shootLaser(box, x+j, y+i, j, i);
//    }
        public static int shootLaser(char[][] box, int x, int y, int i, int j) {
        printf("\nX:%d;Y:%d;I:%d;J:%d; B:%s\n", x, y, i, j, box[y][x]);
        if(wallCollision(x, y, i, j))
            return getEndPos(x, y, i, j);
        else if(box[y][x] == '.')
            return shootLaser(box, x+i, y+j, i, j);
        else if((box[y][x]-48) <= 1)//Mirror is \
            return shootLaser(box, x-j, y-i, -j, -i);
        else//Mirror is /
            return shootLaser(box, x+j, y+i, j, i);
    }

    public static int[] getStartState(int startPos) {
        int[] start = new int[4];
        //0, 1 are positions it starts on in box[][]. 2, 3 are direction vectors (i, j)

        if(startPos <= 9) {
            start[0] = startPos;
            start[1] = 0;
            start[2] = 0;
            start[3] = 1;
        } else if(startPos >= 10 && startPos <= 19) {
            start[0] = 0;
            start[1] = startPos-10;
            start[2] = 1;
            start[3] = 0;
        } else if(startPos >= 20 && startPos <= 29) {
            start[0] = 20-startPos;
            start[1] = 9;
            start[2] = 0;
            start[3] = -1;
        } else if(startPos >= 30) {
            start[0] = 9;
            start[1] = 39-startPos;
            start[2] = -1;
            start[3] = 0;
        }
        return start;
    }

    public static int getEndPos(int x, int y, int i, int j) {
        return (i == 0) ? ((j < 0) ? x : 20+x) : ((i < 0) ? 10+y : 39-y);
    }

    public static boolean wallCollision(int x, int y, int i, int j) {
        return (i == -1 && x <= 0) || (i == 1 && x >= 9) || (j == -1 && y <= 0) || (j == 1 && y >= 9);
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
                    case '0':
                    case '1':
                        print("\\ ");
                        break;
                    case '2':
                    case '3':
                        print("/ ");
                        break;
                }
            }
            printf("%d \n", 39-i);
        }
        print("   0 1 2 3 4 5 6 7 8 9");
    }
}