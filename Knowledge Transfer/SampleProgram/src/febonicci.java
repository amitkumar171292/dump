
import java.util.Scanner;

public class febonicci
{
    public static void main(String[] args) 
    {
        int a=0,b=1,c,n;
        System.out.print("Enter the value of n=");
        Scanner s= new Scanner(System.in);
        n=s.nextInt();
        System.out.print(a+" "+b);
        for(int i=2;i<n;i++)
        {
            c=a+b;
            System.out.print(" "+c);
            a=b;
            b=c;
        }
    }
}
