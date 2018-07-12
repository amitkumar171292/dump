
import java.util.Scanner;

public class bubble 
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
        bub(a,n);
//        sel(a,n);
    }
    static void bub(int a[],int n)
    {
        System.out.print("Bubble Sort=");
        int temp;
        for(int i=0;i<n;i++)
        {
            for(int j=1;j<(n-i);j++)
            {
                if(a[j-1]>a[j])
                {
                    temp=a[j-1];
                    a[j-1]=a[j];
                    a[j]=temp;
                }
            }
            System.out.print("\n");
            for(int k=0;k<n;k++)
            {
                System.out.print(" "+a[k]);
            }  
        }
        System.out.print("\n");
        for(int i=0;i<n;i++)
        {
            System.out.print(" "+a[i]);
        }
    }            
}
