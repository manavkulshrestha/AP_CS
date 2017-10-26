import java.util.Scanner;

public class ProblemSix {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int checksum;

        do {
            checksum=input.nextInt();

            if(checksum != -1) {
                for(int i=0; i<2; i++)
                    checksum += input.nextInt();
                if(checksum >= 256)
                    checksum -= 256;
                checksum = 255-checksum;
                //checksum = 255-(checksum%256);

                if(checksum == input.nextInt())
                    System.out.print("valid\n");
                else
                    System.out.print("invalid\n");
            }
        } while(checksum != -1);

        input.close();
    }
}