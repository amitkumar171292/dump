
import java.util.Scanner;

public class insertsort 
{
    public static void main(String[] args) 
    {
        int i,n;
        System.out.print("Enter the Value of N=");
        Scanner s=new Scanner(System.in);
        n=s.nextInt();
        int a[]=new int[n];
        for(i=0;i<n;i++)
        {
            a[i]=s.nextInt();
        }
        inssort(a,n);
    }
    static void inssort(int a[],int n)
    {
        int i,j,temp;
        System.out.println("Insertion Sort=");
        for(i=1;i<n;i++)
        {
            temp=a[i];
            j=i-1;
            while((j>=0)&&(temp<a[j]))
            {
                a[j+1]=a[j];
                j--;
            }
            a[j+1]=temp;
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
