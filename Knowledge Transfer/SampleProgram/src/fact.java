
import java.util.Scanner;

public class fact 
{
    public static void main(String[] args) 
    {
        int n,f=1;
        System.out.print("Enter the Number=");
        Scanner s=new Scanner(System.in);
        n=s.nextInt();
        while(n>0)
        {
            f=f*n;
            n--;
        }
        System.out.println("Factorial is = "+f);
    }
}
