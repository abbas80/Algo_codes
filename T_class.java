//package cf;
import com.sun.source.tree.Tree;

import java.awt.event.MouseAdapter;
import java.io.*;
import java.sql.Array;
import java.text.CollationElementIterator;
import java.util.*;
import java.util.jar.JarOutputStream;

public class T_class {
    static int p=1000000007;
    static int max=(int)1e6+1;
    static ArrayDeque<Integer> l1=new ArrayDeque<>();
    static boolean prime[]=new boolean[max];
    static long fact[]=new long[3_000_00];
    static class  pr_int
    {
        int first;
        int second;
        int ind;

        public pr_int(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
    static class  pr
    {
        int first;
        ArrayDeque<Integer> pq;

        public pr(int first, ArrayDeque<Integer> pq) {
            this.first = first;
            this.pq = pq;
        }
    }
    static  class edge
    {
        int v,w,lst;
        edge(int v,int w)
        {
            this.v=v;
            this.w=w;
        }
    }
    static  class wt
    {
        int n,d,s;

        public wt(int n, int d, int s) {
            this.n = n;
            this.d = d;
            this.s = s;
        }
    }
    static  long anss=0;
    static long dp[];
    public static void main(String[] args) throws Exception {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);
        FastReader sc = new FastReader();
        int t =sc.nextInt();
        StringBuilder sb2 = new StringBuilder();
            outer: while (t-- >0) {
                String draw=sc.nextLine();
                int l=draw.length();
                int n=sc.nextInt();
                int tol=sc.nextInt();
                String ar[]=new String[n];
                char ar_draw[]=draw.toCharArray();
                draw=draw.replace('a','@');
                draw=draw.replace('o','@');
                draw=draw.replace('l','#');
                draw=draw.replace('t','#');

                System.out.println(draw);
                for(int i=0;i<n;i++)
                {
                    ar[i]=sc.nextLine();
                    ar[i]=ar[i].replace('a','@');
                    ar[i]=ar[i].replace('o','@');
                   ar[i]= ar[i].replace('l','#');
                    ar[i]=ar[i].replace('t','#');

                }
                List<String> sub[]=new ArrayList[l+1];
                for(int len=1;len<=l;len++) {
                    sub[len]=new ArrayList<>();
                    for (int i = 0; i+len <=l; i++) {
                        sub[len].add(draw.substring(i,i+len));
                    }
                  ///  System.out.println(sub[len]);
                }
                int c=0;
                for(String i:ar)//10^3
                {
                    int lenar=i.length();
                    int newlen=lenar+tol;

                    len: for(int len=lenar;len<=newlen;len++)//200
                    {
                        for(String j:sub[len])//200
                        {
                            if(match(i,j))//200
                            {
                            c++;
                            break len;
                            }
                        }
                    }
                }
                System.out.println("total match is "+c);
           }
        System.out.println(sb2.toString());
        out.flush();

    }

    private static boolean match(String i, String j) {
    char a[]=i.toCharArray();
    char b[]=j.toCharArray();
    int l1=a.length;
    int l2=b.length;
    int x=0,y=0;
    while(x<l1&&y<l2)
    {
        if(a[x]==b[y])
        {
            x++;
            y++;
        }
        else
            y++;
    }
    return  x==l1||x==l1-1;
    }

    public static long ans(long ar[],int n,long in)
    {
        if(in>=n)
            return 0;
        int i=(int)in;
        if(dp[i]!=-1)
            return dp[i];
        long ans=Math.max(ar[i]+ans(ar,n,(i+ar[i])),ans(ar,n,i+1));
        return dp[i]=ans;
    }
    static void sortMyMapusingValues(HashMap<Integer, Integer> hm) {
        List<Map.Entry<Integer, Integer>> capitalList = new LinkedList<>(hm.entrySet());

        Collections.sort(capitalList, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        HashMap<Integer, Integer> result = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : capitalList) {
            result.put(entry.getKey(), entry.getValue());
        }
    }
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

    static long lcm(long a,long b)
    {
        return  a*b/gcd(a,b);
    }
    static boolean done=false;
    static long ret;
    public  static long tolong(char ar[])
    {
        long ans=0;
        for(char i:ar)
            ans=ans*10+ (i-'0');

        return ans;
    }
    public  static  void recur(char ar[],int n,int cur,long lcm,boolean con)
    {
        if(cur==n)
        {
            System.out.println(tolong(ar)+" "+lcm);
            done=tolong(ar)%lcm==0;
            if(done)
                ret=tolong(ar);
            return;
        }
        int lst=ar[cur]-'0';
        for(int i=con?ar[cur]-'0':0;i<=9;i++)
        {
            char prev=ar[cur];
            ar[cur]=(char) (i+'0');
            recur(ar, n, cur + 1, i==0?lcm:lcm(i,lcm),lst==i);
            ar[cur]=prev;
            if(done)
                return ;
        }
    }
    static long ansa(long l,long r,long s,long ss,int n,int ar[])
    {

        while(l<=r)
        {
            long mid=(l+r)/2;
            long tot=0;
            for(int i=0;i<n;i++)
            {
                tot+=Math.abs(ar[i]-mid);
            }

            if(tot*2<=s)
            {
                // System.out.println(s+" - "+(mid*n)+" <= "+ss);
                return mid;
            }
            else if(tot*2>s)
            {
                r=mid-1;
            }
            else
            {
                l=mid+1;
            }
        }
        return 1;
    }
    static void ruffle_sort(long[] a) {
        //shandom_ruffle
        Random r=new Random();
        int n=a.length;
        for (int i=0; i<n; i++) {
            int oi=Math.abs(r.nextInt(i+1));
            long temp=a[i];
            a[i]=a[oi];
            a[oi]=temp;
        }

        //sort
        Arrays.sort(a);
    }
    static void ruffle_sort(int[] a) {
        //shandom_ruffle
        Random r=new Random();
        int n=a.length;
        for (int i=0; i<n; i++) {
            int oi=Math.abs(r.nextInt(i+1));
            int temp=a[i];
            a[i]=a[oi];
            a[oi]=temp;
        }

        //sort
        Arrays.sort(a);
    }
    static long mod=(long) 1e9+7;
    private static long nC2(long n) {
        return n*(n-1)/2;
    }
    static long mul(long a, long b) {
        return a*b%mod;
    }

    static long add(long a, long b) {
        return (a+b)%mod;
    }
    static void precomp() {
        fact[0]=1;
        for (int i=1; i<fact.length; i++) fact[i]=mul(fact[i-1], i);
    }
    static long modInv(long x) {
        return pow(x, mod-2,mod);
    }
    static long nCk(int n, int k) {
        return mul(fact[n], mul(modInv(fact[k]), modInv(fact[n-k])));
    }


    private static List<Long> getdivisors(long n) {
        List<Long> l1=new ArrayList<>();
        for(long i=1;i<=Math.sqrt(n);i++)
        {
            if(n%i==0){
                l1.add(i);
                if(n/i!=i)
                    l1.add(n/i);
            }
        }
        // System.out.println(l1);
        Collections.sort(l1);
        return l1;
    }

    private static int count(int k, int l, String cur) {
        char ar[]=cur.toCharArray();
        int c=0;
        for(int i=k;i<=l;i++)
        {
            if(i=='1')
                c++;
        }
        return c;
    }

    private static String revese(String substring) {
        StringBuilder sb=new StringBuilder();
        sb.append(substring);
        sb=sb.reverse();
        return sb.toString();
    }


    static  int ht[]=new int[max];
    /*static class BIT {
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
    */public static long pow(long a,long b,long m)
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
    /*public static long gcd(long a,long b)
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
    }*/
    static long gcd(long a, long b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
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
    static  Set<Integer > used=new HashSet<>();
    public static void dfs(List<Integer>[] adj, int u, int p, boolean[] vis,long ind[],long n)
    {
        // anss-=((ind[u]-1)*(ind[u]-2))/2>0&&!used.contains(u)?((ind[u]-1)*(ind[u]-2))/2:0;

        if(((ind[u]-1)*(ind[u]-2))/2>0)
        {
            //   System.out.println(u+" -- "+((ind[u]-1)*(ind[u]))/2);
            //   used.add(u);
        }
        vis[u]=true;
        int c=0;
        for(int v:adj[u])
        {
            if(v!=p&&vis[v])
            {
                //   System.out.println(u+" c--c"+v+" "+((ind[v]-1)*(ind[v]))/2);
                if(((ind[v]-1)*(ind[v]-2))/2>0) {
                    anss -= ((ind[v] - 1) * (ind[v] - 2)) / 2 > 0 && !used.contains(v) ? ((ind[v] - 1) * (ind[v] - 2)) / 2 : 0;
                    used.add(v);
                }
                if(((ind[u]-1)*(ind[u]-2))/2>0) {
                    anss -= ((ind[u] - 1) * (ind[u] - 2)) / 2 > 0 && !used.contains(u) ? ((ind[u] - 1) * (ind[u] - 2)) / 2 : 0;
                    used.add(u);
                }
                continue;
            }
            if(!vis[v])
            {
                c++;
                dfs(adj,v,u, vis,ind,n+1);
            }
        }
        if(c==0)
        {
            anss+=(n)*(n+1l)/2;
        }
        vis[u]=false;
    }
    public static void seive()
    {
        for(int i=0;i<max;i++)
            prime[i] = true;

        for(int p = 2; p*p <max; p++)
        {
            // If prime[p] is not changed, then it is a prime
            if(prime[p] == true)
            {
                // Update all multiples of p
                for(int i = p*p; i <max; i += p)
                    prime[i] = false;
            }
        }

        // Print all prime numbers
        for(int i = 2; i < max; i++)
        {
            if(prime[i] == true)
            {
                l1.addLast(i);
            }
        }
    }
    static int max_abv=Integer.MIN_VALUE,min_abv=Integer.MAX_VALUE,ans=0;
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
//                p[pb]=pa;

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
    public static int binary_Search_upper(int[] ar,int l, int x)
    {
        int res=-1;
        int r=ar.length-1;
        while(l<=r)
        {
            int mid=(l+r)>>1;
            if(ar[mid]<=x)
            {
                res=mid;
                l=mid+1;
            }
            else if(ar[mid]>x)
            {
                //res=mid;
                r=mid-1;
            }
        }
        return res;
    }
    public static int binary_Search_lower(long[] ar, long x)
    {
        int res=ar.length;
        int l=0;int r=ar.length-1;
        while(l<=r)
        {
            int mid=(l+r)>>1;
            if(ar[mid]>=x)
            {
                res=mid;
                r=mid-1;
            }
            else
            {
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