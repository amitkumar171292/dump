
//import static bubble.bub;
import java.util.Scanner;


public class selsort 
{
    public static void main(String[] args) 
    {
        int i,n;
        //int a[]=new int[50];
        System.out.print("Enter the Number=");
        Scanner s=new Scanner(System.in);
        n=s.nextInt();
        int a[]=new int[n];
        for(i=0;i<n;i++)
        {
            a[i]=s.nextInt();
        }
        sel(a,n);
    }
    static void sel(int a[],int n)
    {
        System.out.print("\nSelection Sort=");
        int temp,i,j,temp1;
        for(i=0;i<n;i++)
        {
            temp=i;
            for(j=i+1;j<n;j++)
            {
                if(a[temp]>a[j])
                {
                    temp=j;
                }
            }
            temp1=a[temp];
            a[temp]=a[i];
            a[i]=temp1;
            System.out.print("\n");
            for(int k=0;k<n;k++)
            {
                System.out.print(" "+a[k]);
            }    
        }
        
        System.out.print("\n");
        for(i=0;i<n;i++)
        {
            System.out.print(" "+a[i]);
        }
    }
}
