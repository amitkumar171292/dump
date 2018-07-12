
import java.util.Scanner;

public class palin 
{
    public static void main(String[] args) 
    {
        int n,r,x,a=0;
        System.out.println("Enter the Number=");
        Scanner s=new Scanner(System.in);
        n=s.nextInt();
        x=n;
        while(x>0)
        {
            r=x%10;
            x=x/10;
            a=a*10+r;
        }
        if(a==n)
        {
            System.out.println("Palindrome");
        }
        else
        {
            System.out.println("Not Palindrome");
        }
    }
}
