import java.util.Scanner;

public class prime 
{
    public static void main(String[] args) 
    {
        int n,flag=0;
        System.out.println("Enter the Number=");
        Scanner s=new Scanner(System.in);
        n=s.nextInt();
        for(int i=2;i<n;i++)
        {
            if(n%i==0)
            {
                flag=1;
            }
        }    
        if(flag==0)
        {
            System.out.println("Prime Number");
        }
        else
        {
            System.out.println("Not a Prime Number");
        }
    }
}
