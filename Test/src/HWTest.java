public class HWTest {
    public static void main(String[] args) {
        show1(123);
        nl();
        show2(123);
        nl();
        show3(123);
        nl();
        System.out.print(puzzle(4, 7));
        nl();
        System.out.print(f1(3));
        nl();
        System.out.print(f2(6, 5));
        nl();
        System.out.print(f3(f3(f3(f3(f3(f3(3)))))));
        nl();
        System.out.print(f3v2(f3v2(f3v2(f3v2(f3v2(f3v2(3)))))));
    }

    public static int f3(int x) {
        if(x<5)
            return x*x+1;
        if(x == 5)
            return x*x-3;
        return f3(x-2);
    }

    public static int f3v2(int x) {
        if(x>3)
            return ((x&1) == 0) ? 17 : 22;
        else
            return x*x+1;
    }

    public static int f2(int x, int y) {
        if(x <= 0)
            return 0;
        return (y >= x) ? 1+f2(y, x) : 2+f2(x-3, y-1);
    }

    public static int f1(int x) {
        return (x == 0) ? 1 : 1+f1(x-1);
    }

    public static void nl() {
        System.out.print("\n");
    }

    public static int puzzle(int base, int limit) {
        if(base>limit)
            return -1;
        else if(base == limit)
            return 1;
        else
            return base*puzzle(base+1, limit);
    }

    public static void show3(int n) {
        System.out.print(n%10);
        if(n != 0)
            show3(n/10);
        System.out.print(n%10);
    }

    public static void show1(int n) {
        if(n != 0)
            show1(n/10);
        System.out.print(n%10);
    }

    public static void show2(int n) {
        System.out.print(n%10);
        if(n != 0)
            show2(n/10);
    }
}
