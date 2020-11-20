import java.util.Scanner;

public class palindrome {
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        String input=sc.nextLine();
        char inp_ar[]=input.toCharArray();
        int length=inp_ar.length;
        int count_pal=0;
        for(int i=0;i<length;i++)
        {
            for(int j=i+1;j<length;j++)
            {
                if(ispal(i,j,inp_ar))
                {
                    count_pal++;
                }
            }
        }
        System.out.println(count_pal);
    }
    public static boolean ispal(int l,int r,char inp_ar[])
    {
        for(int i=l;i<=(l+r)/2;i++)
        {
            if(inp_ar[i]!=inp_ar[r-(i-l)])
            {
                return false;
            }
        }
        return true;
    }
}
