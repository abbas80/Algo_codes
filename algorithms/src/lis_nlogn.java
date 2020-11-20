
import java.io.*;
import java.math.BigInteger;
import java.util.*;

 class Main {
    static int p=1000000007;
    static int max=(int)1e5;
    static  int my_val=0;
    static boolean prime[]=new boolean[max+5];
    static  int dp[][]=new int[(int)200][410];
    static  List<Integer> total_prime=new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);
        FastReader sc=new FastReader();
        int t=1;
        StringBuilder sb2=new StringBuilder();

        while(t-- >0) {
            int n=sc.nextInt();
            int ar[]=new int[n];
            for(int i=0;i<n;i++)
            {
                ar[i]=sc.nextInt();
            }
            int l[]=new int[n];
            int in[]=new int[n+1];
            int len=1;
            for(int i=0;i<n;i++)
            {
                if(len==1)
                {
                    l[i]=len;
                    in[len]=ar[i];
                    len++;
                    //8 1 9 8 3 4 6 1 5 2
                }
                else if(in[len-1]<ar[i])
                {
                    l[i]=len;
                    in[len]=ar[i];
                    len++;
                }
                else
                {
                    int pos=binary_Search_lower(in,ar[i],1,len-1);
                    //     System.out.println(pos);2 5 3 7 11 8 10 13 6
                    l[i]=pos;
                    in[pos]=ar[i];
                }
            }
            sb2.append((len-1)+"\n");
            /*
            int max=0;
            for(int i=0;i<n;i++)
            {
                if(l[i]>=l[max])
                    max=i;
            }
            System.out.println(Arrays.toString(in));
            System.out.println(Arrays.toString(ar));
            System.out.println(Arrays.toString(l));
            sb2.append("size is "+l[max]+"\n"+ar[max]+" ");
            for(int i=n-1;i>=0;i--)
            {
                if(ar[i]<ar[max]&&l[i]==l[max]-1)
                {
                    max=i;
                    sb2.append(ar[i]+" ");
                }
            }*/
        }
        System.out.println(sb2.toString());
        out.flush();
    }
    public static int binary_Search_upper(Integer[] ar, Integer x,int l,int r)
    {
        int res=-1,f=0;
        while(l<=r)
        {
            int mid=(l+r)>>1;
            if(ar[mid]==x)
            {
                f=1;
                res=mid;
                l=mid+1;
            }
            else if(ar[mid]>x)
            {
                res=res!=-1&&ar[res]==x?res:mid;
                r=mid-1;
            }
            else
            {
                l=mid+1;
            }
        }
        return f==1?res:res+1;
    }
    public static int binary_Search_lower(int[] ar, long x, int l, int r)
    {
        int res=0,f=0;
        //int l=0;int r=ar.length-1;
        /*public static int binary(Long[] ar, int l, int r, long v)
        {
            int n=r;
            int m=(l+r)/2;
            int re=-1;
            while(l<=r)
            {
                if(ar[m]>=v)
                {

                    re=m;
                    r=m-1;
                }
                else if(ar[m]<v)
                {
                    l=m+1;
                }
                m=(l+r)/2;
            }
            if(l<=n&&ar[l]==v)
                return l;
            return re;
        }*/
        //   System.out.println(Arrays.toString(ar)+" "+x);
        while(l<=r)
        {
            int mid=(l+r)>>1;
            //   System.out.println(l+" "+r+" "+mid+" = "+ar[mid]);
            if(ar[mid]==x)
            {
                f=1;
                res=mid;
                r=mid-1;
            }
            else if(ar[mid]>x)
            {
                r=mid-1;
            }
            else
            {
                res=res!=0&&ar[res]==x?res:mid;
                l=mid+1;
            }
        }

        //  System.out.println(res);
        return f==1?res:res+1;
    } public static int binary_Search_lower_suf(int[] ar, int x,int l,int r)
    {
        int res=-1,f=0;
        //int l=0;int r=ar.length-1;
        while(l<=r)
        {
            int mid=(l+r)>>1;
            if(ar[mid]==x)
            {
                f=1;
                res=mid;
                r=mid-1;
            }
            else if(ar[mid]<x)
            {
                r=mid-1;
            }
            else
            {
                //      res=mid;
                l=mid+1;
            }
        }
        return f==0?res+1:res;
    }

    ///////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////
    static class FastReader {

        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }


}
