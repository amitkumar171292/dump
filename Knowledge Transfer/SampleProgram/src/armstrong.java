
import java.util.Scanner;


public class armstrong 
{
    public static void main(String[] args) 
    {
        int n,x,r,a=0;
        System.out.print("Enter the Number=");
        Scanner s=new Scanner(System.in);
        n=s.nextInt();
        x=n;
        while(x>0)
        {
            r=x%10;
            x=x/10;
            a=a+r*r*r;
        }
        if(n==a)
        {
            System.out.println("Armstrong Number");
        }
        else
        {
            System.out.println("Not Armstrong Number");
        }
    }
}
