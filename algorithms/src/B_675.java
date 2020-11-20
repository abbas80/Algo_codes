
//package cf;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
public class B_675 {
    static int p=1000000007;
    public static void main(String[] args) throws Exception{
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);
        FastReader sc=new FastReader();
        int t=sc.nextInt();
        StringBuilder sb=new StringBuilder();
        while(t-->0)
        {
            int n=sc.nextInt();
            int m=sc.nextInt();
            long mt[][]=new long[n][m];
            for(int i=0;i<n;i++)
            {
                String in[]=sc.nextLine().split(" ");
                for(int j=0;j<m;j++)
                {
                    mt[i][j]=Long.parseLong(in[j]);
                }
                ///  System.out.println(Arrays.toString(mt[i]));
            }
            long ans=0;
            for(int i=0;i<n/2;i++)
            {
                for(int j=0;j<m/2;j++)
                {
                    List<Long> l1=new ArrayList<>();
                    l1.add(mt[i][j]);
                    l1.add(mt[n-i-1][j]);
                    l1.add(mt[i][m-1-j]);
                    l1.add(mt[n-1-i][m-1-j]);
                    Collections.sort(l1);
                    long d=l1.get(2);
                    //       System.out.println(i+"c1"+d1+" "+d);
                    ans+=Math.abs(mt[i][j]-d);
                    ans+=Math.abs(mt[n-1-i][m-1-j]-d);
                    ans+=Math.abs(mt[i][m-1-j]-d);
                    ans+=Math.abs(mt[n-1-i][j]-d);
                    //mt[i][j]=d;
                    // mt[n-i][m-j]=d;
                    //mt[i][m-j]=d;
                    //mt[n-i][j]=d;

                }
            }
            // System.out.println(ans);
            if((n)%2!=0)
            {
                for(int j=0;j<m/2;j++)
                {
                    long d=mt[n/2][m-1-j];

                    ans+=Math.abs(d-mt[n/2][j])+Math.abs(d-mt[n/2][m-1-j]);
                }

            }
            if((m)%2!=0)
            {
                for(int j=0;j<n/2;j++)
                {
                    long d=mt[n-1-j][m/2];
                    //d/=2;
                    ans+=Math.abs(d-mt[j][m/2])+Math.abs(d-mt[n-1-j][m/2]);
                }
            }
            //for(int i=0;i<=n;i++)
            ///  System.out.println(Arrays.toString(mt[i]));
            sb.append(ans+"\n");
        }
        System.out.println(sb.toString());
        out.flush();
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

