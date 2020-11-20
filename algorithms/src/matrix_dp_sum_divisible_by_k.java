
//package cf;
import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.jar.JarOutputStream;

public class matrix_dp_sum_divisible_by_k {
    static int p=1000000007;
    static int max=(int)1e5;
    static boolean prime[]=new boolean[max];
    static  int dp[][][][]=new int[72][72][72][72];
    public static void main(String[] args) throws Exception{
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);
        FastReader sc=new FastReader();
        int t=1;
        StringBuilder sb2=new StringBuilder();
        //
        // 12654
        while(t-- >0) {
            int n = sc.nextInt();
            int m=sc.nextInt(); 
            int k=sc.nextInt();
            int mt[][]=new int[n][m];
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<m;j++)
                {
                    mt[i][j]=sc.nextInt();
                }
            }
            for(int i=0;i<72;i++) {
                for (int j = 0; j <72; j++) {
                    for (int k1=0;k1<72;k1++) {
                        for(int l=0;l<72;l++) {
                            dp[i][j][k1][l]=-1;
                        }
                    }
                }
            }System.out.println(ans(mt,0,0,n,m,m/2,0,k));
        }
        System.out.println(sb2.toString());
        out.flush();
    }
    public static int ans(int mt[][],int i,int j,int n,int m,int left,int rem,int k)
    {
        if(i==n)
        {
            if(rem==0)
                return 0;
            return Integer.MIN_VALUE;
        }/*
        if(left==0)
            return 0;*/
        if(dp[i][j][left][rem]!=-1)
            return dp[i][j][left][rem];
        int cur_ans=-1;
        if(j==m||left==0)
        {
            //    System.out.println(rem+" "+left);
            cur_ans=ans(mt,i+1,0,n,m,m/2,rem,k);
        }
        else
        {
            cur_ans=Math.max(mt[i][j]+ans(mt,i,j+1,n,m,left-1,(rem+mt[i][j])%k,k)
                    ,ans(mt,i,j+1,n,m,left,rem,k));
        }

        dp[i][j][left][rem]=cur_ans;
        return dp[i][j][left][rem];
    }

    static  int ht[]=new int[max];
    static class BIT {
        long[] tree;
        public BIT(int size) {
            tree = new long[size + 1];
        }
        public long sum(int i) {
            long ans = 0;
            while (i > 0) {
                ans += tree[i];
                i -= i & -i;
            }
            return ans;
        }

        public long query(int i, int j) {
            return sum(j) - sum(i - 1);
        }

        public void add(int i, long val) {
            while (i < tree.length) {
                tree[i] += val;
                i += i & -i;
            }
        }

        public void set(int i, long val) {
            add(i, val - query(i, i));
        }
    }
    static class  vert implements Comparator<vert>
    {
        int v;
        TreeSet<Integer> set;

        public vert(int v, TreeSet<Integer> set) {
            this.v = v;
            this.set = set;
        }

        @Override
        public int compare(vert o1, vert o2) {
            return -(o1.set.size()-o2.set.size());
        }
    }
    public static long pow(long a,long b,long m)
    {
        long r=1;
        while(b!=1)
        {
            if(b%2!=0)
                r=(r*a)%m;
            b=b>>1;
            a=(a*a)%m;
        }
        return (r*a)%m;
    }
    public static long gcd(long a,long b)
    {
        if(b>a)
        {
            a=a^b;
            b=a^b;
            a=a^b;
        }
        if(b==0)
            return a;
        return gcd(b,a%b);
    }
    public static void extgcd(long a,long b)
    {/*
            if(a==0)
            {
                x=0;
                y=1;
                d=b;
                // return ;
            }
            else
            {

                extgcd(b%a,a);

                long t=y;
                y=x;
                x=t-(b/a)*x;
            }*/
    }
    public static void dfs(List<Integer> adj[],int u,int p)
    {
        ht[u]=ht[p]==1?2:1;
        for(int v:adj[u])
        {
            if(v!=p)
            {
                dfs(adj,v,u);
            }
        }
    }
    public static void seive()
    {
        for(int i=0;i<max;i++)
            prime[i] = true;

        for(int p = 2; p*p <=max; p++)
        {
            // If prime[p] is not changed, then it is a prime
            if(prime[p] == true)
            {
                // Update all multiples of p
                for(int i = p*p; i <=max; i += p)
                    prime[i] = false;
            }
        }

        // Print all prime numbers
        for(int i = 2; i <= max; i++)
        {
            if(prime[i] == true)
            {
                // l1.add(i);
            }
        }
    }
    public static boolean union(int a, int b, int p[], int r[]){
        // add your code here
        int pa=find(a,p,r);
        int pb=find(b,p,r);
        if(pa==pb)
            return true;
        if(pa!=pb)
        {
            if(r[pa]<r[pb])
            {
                p[pa]=pb;
            }
            else if(r[pa]>r[pb])
            {
                p[pb]=pa;
            }
            else
            {
                p[pa]=pb;
                r[pb]++;
            }
        }
        return false;
    }
    public static int find(int x,int p[],int r[])
    {
        if(p[x]==x)
            return x;
        p[x]=find(p[x],p,r);
        return p[x];
    }
    public static int binary_Search_upper(Integer[] ar, Integer x)
    {
        int res=-1;
        int l=0;int r=ar.length-1;
        while(l<=r)
        {
            int mid=(l+r)>>1;
            if(ar[mid]==x)
            {
                res=mid;
                l=mid+1;
            }
            else if(ar[mid]>x)
            {
                //res=mid;
                r=mid-1;
            }
            else
            {
                l=mid+1;
            }
        }
        return res;
    }
    public static int binary_Search_lower(int[] ar, int x,int l,int r)
    {
        int res=-1;
        //int l=0;int r=ar.length-1;
        while(l<=r)
        {
            int mid=(l+r)>>1;
            if(ar[mid]==x)
            {
                res=mid;
                r=mid-1;
            }
            else if(ar[mid]>x)
            {
                r=mid-1;
            }
            else
            {
                //      res=mid;
                l=mid+1;
            }
        }
        return res;
    } public static int binary_Search_lower_suf(int[] ar, int x,int l,int r)
    {
        int res=-1;
        //int l=0;int r=ar.length-1;
        while(l<=r)
        {
            int mid=(l+r)>>1;
            if(ar[mid]==x)
            {
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
        return res;
    }
    int bit[]=new int[(int)1e6];
    public void update(int n,int val,int i)
    {
        i++;
        while(i<n)
        {
            bit[i]+=val;
            i+=(i)&(-i);
        }

    }
    public long query(int n,int i)
    {
        i++;
        long sum=0;
        while(i>0)
        {
            sum+=bit[i];
            i-=(i)&(-i);
        }
        return sum;
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
