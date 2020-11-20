
//package cf;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class digit_substring_sum {
    static int p=1000000007;
    public static void main(String[] args) throws Exception{
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileDescriptor.out), "ASCII"), 512);
        FastReader sc=new FastReader();
        int t=1;

        while(t-->0)
        {
            String s=sc.nextLine();
        char ar[]=s.toCharArray();
        int l=ar.length;
        if(l>=15&&s.substring(0,15).equals("214792656582415"))
        {
//            System.out.println(l);
        }
           // System.out.println(l);
        long pow[]=new long[100006];
        long pow1[]=new long[100006];
            pow[0]=1;
            pow1[0]=0;
            long res=0;

            for(int i=1;i<pow.length;i++) {
                pow[i] =( pow[i - 1]*10)%p;
                pow[i] %= p;
            }

            //2147926565824157674783184371823367137546153913814747924368692935531415624862789623859347541324476325812933161936238188785264672941836793936129645175366266627561398129828932198538513946835148492565242189371133393541934556641358722697122639971845673235514749964114378279893293992447587846848788885821392986529211597458649694698939888331168584939711665631756578884622883988761682749263633849645856759189363295634647536784795338474345226844633667483365159156578741893421865953998334843585578136212244717469343879487
            for(int i=1;i<pow1.length;i++) {
                pow1[i]=(pow[i-1]*i)%p;
            pow1[i]%=p;
            pow1[i]= (pow1[i]+pow1[i-1])%p;
            pow1[i]%=p;
        }
/*100500100500
[1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000, 999999937, 999999307, 999993007, 999930007, 999300007, 993000007, 930000007, 300000007, 49, 490, 4900, 49000]
[0, 1, 21, 321, 4321, 54321, 654321, 7654321, 87654321, 987654321, 987654251, 987653481, 987645081, 987554081, 986574081, 976074081, 864074081, 674074088, 74074172, 74075103, 74084903, 74187803]
             */
            for(int i=0;i<l;i++)
        {
            /*int after = l-i-1;
            int val = ar[i]-'0';

            long prev = i;
            prev = (prev * (prev + 1) / 2) % MOD;
            int add = mult(prev, mult(val, pow[l-i-1]));
            res = add(res, add);

            add = mult(pow1[after], val);
            res = add(res, add);
            */
            long left=i;
            left=(((left+1)*left)/2)%p;
            left=((left*pow[l-1-i])%p);

            left=(left*(ar[i]-'0'))%p;
            res=(res+left)%p;
            long right = ((pow1[l-1-i]*(ar[i]-'0'))%p);
            res=(res+right)%p;
            //ans=add((int)ans,(int)left)%p;
        //    ans+=(ar[i]-'0')*(pow[l-1-i]*(i+1));428101984
        }

            System.out.println(res);
        }
        out.flush();
    }
    static int MOD = (int)1e9+7;
    static int mult(long a, long b) {
        a *= b;
        if(a >= MOD) a %= MOD;
        return (int)a;
    }
    static int add(int a, int b) {
        a += b;
        if(a >= MOD) a -= MOD;
        return a;
    }
    static int sub(int a, int b) {
        a -= b;
        if(a < 0) a += MOD;
        return a;
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
