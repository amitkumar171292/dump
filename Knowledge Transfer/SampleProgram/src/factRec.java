
import java.util.Scanner;


public class factRec 
{
    static int f=1;
    static int fac(int n)
    {
        if(n==0 || n==1)
        {
            return 1;
        }
        else
        {
            f=n*fac(n-1);
        }
        return f;
    }
    public static void main(String[] args) 
    {
        int n,fact;
        System.out.print("Enter the Number=");
        Scanner s=new Scanner(System.in);
        n=s.nextInt();
        if(n<0)
        {
            System.out.println("Not Possible");
        }
        else
        {
            fact=fac(n);
            System.out.println("Factorial is = "+fact);
        }
        
    }
}
