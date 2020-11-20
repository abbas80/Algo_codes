
//package cf;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

class LCA_dp {
    /*
    8
1 2
1 3
2 4
2 5
2 6
3 7
3 8
Enter 2 child
4 8
lca =1
4

                        1
                    2       3
            /     /  \     |  \
           4     5    6     7   8
     */
    //static int p=1000000007;
    static int p[]=new int[(int)1e5+5];
    static int dp[][]=new int[(int)1e5+5][64];
    static int level[]=new int[(int)1e5+5];
    //   static BigInteger wt[]=new BigInteger[(int)1e5+5];
    // static BigInteger wt_sq[]=new BigInteger[(int)1e5+5];
    static int maxn=0;
    static long mod=1l<<32;
    static HashMap<String,Long> h1=new HashMap<>();
    public static void main(String[] args) throws Exception{
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileDescriptor.out), "ASCII"), 512);
        FastReader sc=new FastReader();
        int t=1;
        BigInteger wt[]=new BigInteger[(int)1e5+5];
        BigInteger wt_sq[]=new BigInteger[(int)1e5+5];

        StringBuilder sb=new StringBuilder();
        for(int i=0;i<wt.length;i++)
        {
            wt[i]=new BigInteger("0");
        }
        for(int i=0;i<wt.length;i++)
        {
            wt_sq[i]=new BigInteger("0");
        }
        while(t-->0) {
          /* Arrays.fill(p,0);
           Arrays.fill(level,0);
           for(int i=0;i<dp.length;i++)
           {
               Arrays.fill(dp[0],0);
           }
h1.clear();
5 2
500000000 400000000 300000000 200000000 100000000
1 2
1 3
2 4
2 5
2 3
4 5
*/
            // System.out.println(mod);
            int n = sc.nextInt();
            int q=sc.nextInt();
            List<Integer> adj[] = new ArrayList[n + 1];
            for (int i = 0; i <= n; i++) {
                adj[i] = new ArrayList<>();
            }
            BigInteger weight[]=new BigInteger[n+1];

            for (int i = 1; i <= n; i++) {
                weight[i]=new BigInteger(String.valueOf(sc.nextLong()));
            }
            // System.out.println(Arrays.toString(weight));
            for (int i = 0; i < n - 1; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                adj[u].add(v);
                adj[v].add(u);
            }
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp[0].length; j++)
                    dp[i][j] = -1;
            }
            maxn = (int) (Math.log10(n) / Math.log10(2));

            for (int i = 1; i <= maxn; i++) {
                for (int j = 0; j < n; j++) {
                    if (dp[j][i - 1] != -1) {
                        int par = dp[j][i - 1];
                        dp[i][j] = dp[par][i - 1];
                    }
                }
            }
         /*  for (int i = 1; i <= n; i++) {
               for (int j = i + 1; j <= n; j++) {
                   int lca = lca(i, j);
               }
           }
         */
            // System.out.println("Enter 2 child");
            List<Integer> u=new ArrayList<>();
            List<Integer> v=new ArrayList<>();

            for(int i=0;i<q;i++) {
                int u1 = sc.nextInt();
                int v1 = sc.nextInt();
                u.add(u1);
                v.add(v1);
            }
            //System.out.println(Arrays.toString(wt));
           /*for(int i=0;i<q;i++)
           {
               int uu=u.get(i);
               int vv=v.get(i);
               int lca = lca(uu, vv);
               long cst[]=new long[2];
               dfs_cst(cst,adj,uu,-1,level[uu],lca);
               long cost=cst[0];
               Arrays.fill(cst,0);
               dfs_cst(cst,adj,vv,-1,level[vv],lca);
               cost+=cst[0];
                sb.append(cost+"\n");
           }*/
            dfs(adj,wt,wt_sq,weight, 1, 0, 1);
            for(int i=0;i<q;i++) {
                int a =u.get(i) ;
                int b =v.get(i);
                int lca = lca(a, b);
                //    System.out.println("lca =" + lca);

                BigInteger b_wta=new BigInteger(String.valueOf(wt[a]));
                BigInteger b_lca=new BigInteger(String.valueOf(wt[lca]));
                BigInteger b_wtb=new BigInteger(String.valueOf(wt[b]));
                b_wta=b_wta.add(b_wtb);
                b_wta=b_wta.subtract(b_lca);
                b_wta=b_wta.subtract(b_lca);
                b_wta=b_wta.pow(2);
                // System.out.println("btw "+b_wta);
                BigInteger b_sqa=new BigInteger(String.valueOf(wt_sq[a]));
                BigInteger b_sqlca=new BigInteger(String.valueOf(wt_sq[lca]));
                BigInteger b_sqb=new BigInteger(String.valueOf(wt_sq[b]));
                b_sqa=b_sqa.subtract(b_sqlca);//(wt_sq[a]-wt_sq[lca]
                b_sqb=b_sqb.subtract(b_sqlca);//(wt_sq[b]-wt_sq[lca]
                b_sqa=b_sqa.add(b_sqb);
                b_wta=b_wta.subtract(b_sqa);
                b_wta=b_wta.divide(new BigInteger("2"));
                //  System.out.println("a*b "+b_wta);
                //   System.out.println(b_wta.mod(new BigInteger(""+mod)));
                b_wta=b_wta.add(wt_sq[lca]);
                b_wta=b_wta.mod(new BigInteger(String.valueOf(mod)));
                sb.append(b_wta.toString()+"\n");
                // System.out.println(b_wta.toString());
             /*   long ab_sq=((wt[a]-wt[lca]+wt[b]-wt[lca])%mod)*((wt[a]-wt[lca]+wt[b]-wt[lca])%mod) ;
                long fac=(ab_sq-(((wt_sq[a]-wt_sq[lca])%mod+(wt_sq[b]-wt_sq[lca])%mod)%mod)+mod)%mod;
                //fac=(fac+mod)%mod;
                fac/=2;
               // System.out.println(" in sq"+fac);
                //fac%=mod;

                System.out.println(fac+"innn "+wt_sq[lca]);
                fac=(fac+wt_sq[lca])%mod;
             */  // int dis = level[b] + level[b] - 2 * level[lca];
                //sb.append(fac+"\n");
                // System.out.println(fac);
                // System.out.println(dis);
                // 829685760
                //499908608
            }
        }
        System.out.println(sb.toString());
        out.flush();
    }
    public static void dfs_cst(long cst[],List<Integer> adj[],int u,int pr,int le,int dst)
    {
        if(u==dst)
        {
            cst[1]=1;
            return;
        }
        for(int i:adj[u])
        {
            if(cst[1]==0&&i!=pr&&level[i]<le)
            {
                cst[0]+=h1.get(u+" "+i);
                dfs_cst(cst,adj,i,u,le,dst);
            }
        }
    }
    public static int lca(int a,int b)
    {
        if(level[a]>level[b ])
        {
            a=a^b;
            b=a^b;
            a=a^b;
        }
        int d=level[b]-level[a];
        while(d>0)
        {
            int dg=(int)(Math.log10(d)/Math.log10(2));
            b=dp[b][dg];
            d-=1<<dg;
        }
        if(a==b)
            return a;
        for(int i=maxn;i>=0;i--)
        {
            if(dp[a][i]!=-1&&(dp[a][i]!=dp[b][i]))
            {
                a=dp[a][i];
                b=dp[b][i];
            }
        }
        return dp[a][0];
    }

    public static void dfs(List<Integer> adj[],BigInteger wt[],BigInteger wt_sq[],BigInteger weigtht[],int u,int pr,int le)
    {
        level[u]=le;
        dp[u][0]=pr;
        //wt[u]=(weigtht[u]%mod+wt[pr]%mod)%mod;
        wt[u]=wt[u].add(weigtht[u]);
        //  System.out.println(wt[u]+" "+weigtht[u]);
        wt[u]=wt[u].add(wt[pr]);
        //wt_sq[u]=((wt_sq[pr]%mod+ (weigtht[u]*weigtht[u]) % mod))%mod;
        wt_sq[u]=wt_sq[u].add(wt_sq[pr]);
        BigInteger tn=new BigInteger(weigtht[u].toString());
        tn=tn.pow(2);
        wt_sq[u]=wt_sq[u].add(tn);

        for(int i:adj[u])
        {
            if(i!=pr)
            {
                dfs(adj,wt,wt_sq,weigtht,i,u,le+1);
            }
        }
    }
    public static int genrate_lps(char ar[],int lps[])
    {
        int i=0;
        int j=1;
        int n=ar.length;
        while(j<n)
        {
            if(ar[i]==ar[j])
            {
                lps[j]=i+1;
                i++;
                j++;
            }
            else if(i!=0)
            {
                i--;
            }
            else
            {
                lps[j]=0;
                j++;
                i=0;
            }
        }
        return 1;
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
