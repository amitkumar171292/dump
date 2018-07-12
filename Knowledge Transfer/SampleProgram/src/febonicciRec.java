import java.util.Scanner;

public class febonicciRec 
{
    static int n,a=0,b=1,c;
    public static void rec(int n)
    {
        if(n>0)
        {
            c=a+b;
            System.out.print(" "+c);
            a=b;
            b=c;
            rec(n-1);
        }
    }
    public static void main(String[] args) 
    {
        System.out.print("Enter the value of N=");
        Scanner s= new Scanner(System.in);
        n=s.nextInt();
        System.out.print(a+" "+b);
        rec(n-2);
    }
}
