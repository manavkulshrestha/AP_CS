/*
Manav Kulshrestha
BlackBoxChallengeV2.java
Block H

CHEAT CODE: 42
Enter as choice to see TOGGLE seeing all mirrors
 */

import java.util.Random;
import java.util.Scanner;

public class BlackBoxChallengeV2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char[][] box = new char[10][10];
        int[] stats = {1, 0, 0, 0};
        String statString = "\n[Game Statistics: Round %d]\nNumber of shots:\t\t\t%d\nNumber of correct guesses:\t%d\nNumber of incorrect guesses:%d\n\n";
        int option;
        boolean cheat = false;

        for(int i=0; i<box.length; i++)
            for(int j=0; j<box[i].length; j++)
                box[i][j] = '.';

        placeMirrors(box, 10);

        do {
            displayBox(box, cheat);
            print("\n\nChoose:\n\t(1) Shoot a Laser\n\t(2) Guess at a mirror location\n\t(0) Quit the game\n\nEnter choice: ");
            option = input.nextInt();

            if(option == 42)
                cheat = !cheat;

            switch(option) {
                case 1:
                    stats[0]++;
                    stats[1]++;
                    print("Select shooting position(0-39): ");
                    printf("End position is of the laser is %d\n\n", shootLaser(box, new Laser(input.nextInt())));
                    break;
                case 2:
                    stats[0]++;
                    print("Guess Mirror coordinates(x, y): ");
                    int[] coords = getBoxCoordinate(input.nextInt(), input.nextInt());
                    if(box[coords[0]][coords[1]] != '.' && ((box[coords[0]][coords[1]]-'0')&1) == 0) {
                        print("You FOUND a previously hidden mirror\n");
                        stats[2]++;
                        box[coords[0]][coords[1]] = (char)(box[coords[0]][coords[1]]+1);
                    } else {
                        print("You NOT FIND a previously hidden mirror\n");
                        stats[3]++;
                    }
                    break;
            }
            printf(statString, stats[0], stats[1], stats[2], stats[3]);
        } while(option != 0);
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

    public static int shootLaser(char[][] box, Laser laser) {
        if(laser.wallCollision(box[0].length, box.length))
            return (laser.i == 0) ? ((laser.j < 0) ? laser.x : 20+laser.x) : ((laser.i < 0) ? 10+laser.y : 39-laser.y);
        else if(box[laser.y][laser.x] == '.')
            return shootLaser(box, new Laser(laser.x+laser.i, laser.y+laser.j, laser.i, laser.j));
        else if((box[laser.y][laser.x]-'0') <= 1)
            return shootLaser(box, new Laser(laser.x-laser.j, laser.y-laser.i, -laser.j, -laser.i));
        else
            return shootLaser(box, new Laser(laser.x+laser.j, laser.y+laser.i, laser.j, laser.i));
    }

    public static void displayBox(char[][] box, boolean cheat) {
        print("   20212223242526272829\n");
        for(int i=box.length-1; i>=0; i--) {
            printf("%d ", 10+i);
            for(int colItem: box[i]) {
                if(colItem == '1' || (colItem == '0' && cheat))
                    print("\\ ");
                else if(colItem == '3' || (colItem == '2' && cheat))
                    print("/ ");
                else
                    print(". ");
            }
            printf("%d \n", 39-i);
        }
        print("   0 1 2 3 4 5 6 7 8 9");
    }

    public static int[] getBoxCoordinate(int... coordsF) {
        int[] coordsR = coordsF;

        if(coordsF[1] >= 30 || (coordsF[1] <= 19 && coordsF[1] >= 10)) {
            coordsR[0] = coordsF[1];
            coordsR[1] = coordsF[0];
        }
        for(int i=0; i<2; i++) {
            if(coordsR[i] >= 30)
                coordsR[i] = 49-coordsR[i];
            else if(coordsR[i] >= 20 && coordsR[i] <= 29)
                coordsR[i] -= 20;
            coordsR[i] = (coordsR[i] <= 9) ? coordsR[i] : coordsR[i]-10;
        }
        print(coordsR[0], coordsR[1]);
        return coordsR;
    }

    //because writing System.out. is tedious
    public static void printf(String s, Object... o) {
        System.out.printf(s, o);
    }

    public static void print(Object... o) {
        for(Object object : o)
            System.out.print(object);
    }
}

class Laser {
    int x;
    int y;
    int i;
    int j;

    public Laser(int startPos) {
        if(startPos >= 0 && startPos <= 9) {
            x = startPos;
            y = 0;
            i = 0;
            j = 1;
        } else if(startPos >= 10 && startPos <= 19) {
            x = 0;
            y = startPos-10;
            i = 1;
            j = 0;
        } else if(startPos >= 20 && startPos <= 29) {
            x = 20-startPos;
            y = 9;
            i = 0;
            j = -1;
        } else if(startPos >= 30) {
            x = 9;
            y = 39-startPos;
            i = -1;
            j = 0;
        } else
            throw new IllegalArgumentException("Start position must be between 0 (inclusive) and 39 (inclusive)");
    }

    public Laser(int x, int y, int i, int j) {
        this.x = x;
        this.y = y;
        this.i = i;
        this.j = j;
    }

    public boolean wallCollision(int boxWidth, int boxHeight) {
        return (i == -1 && x <= 0) || (i == 1 && x >= boxWidth-1) || (j == -1 && y <= 0) || (j == 1 && y >= boxHeight-1);
    }
}