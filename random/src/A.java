import java.io.*;
import java.util.*;

public class A {
    public static void main (String[] args) { new A(); }

    public A() {
        FastScanner fs = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        System.err.println("");

        char[] num = fs.next().toCharArray();
        int n = num.length;
        System.out.println(n);
        int[] mult = new int[n + 10];
        int[] pow10 = new int[n + 10];
        pow10[0] = 1;
        for(int i = 1; i < pow10.length; i++)
            pow10[i] = mult(pow10[i-1], 10);

        mult[1] = 1;
        for(int i = 2; i < mult.length; i++) {

            int term = mult(pow10[i-1], i);

            mult[i] = add(term, mult[i-1]);
        }

        System.out.println(Arrays.toString(pow10));
        System.out.println(Arrays.toString(mult));
        int res = 0;
        for(int i = 0; i < n; i++) {
            int after = n-i-1;
            int val = num[i]-'0';

            long prev = i;
            prev = (prev * (prev + 1) / 2) % MOD;
            int add = mult(prev, mult(val, pow10[n-i-1]));
            res = add(res, add);

            add = mult(mult[after], val);
            res = add(res, add);
        }

        out.println(res);

        out.close();
    }

    int MOD = (int)1e9+7;
    int mult(long a, long b) {
        a *= b;
        if(a >= MOD) a %= MOD;
        return (int)a;
    }
    int add(int a, int b) {
        a += b;
        if(a >= MOD) a -= MOD;
        return a;
    }
    int sub(int a, int b) {
        a -= b;
        if(a < 0) a += MOD;
        return a;
    }


    class FastScanner {
        public int BS = 1<<16;
        public char NC = (char)0;
        byte[] buf = new byte[BS];
        int bId = 0, size = 0;
        char c = NC;
        double num = 1;
        BufferedInputStream in;

        public FastScanner() {
            in = new BufferedInputStream(System.in, BS);
        }

        public FastScanner(String s) {
            try {
                in = new BufferedInputStream(new FileInputStream(new File(s)), BS);
            }
            catch (Exception e) {
                in = new BufferedInputStream(System.in, BS);
            }
        }

        public char nextChar(){
            while(bId==size) {
                try {
                    size = in.read(buf);
                }catch(Exception e) {
                    return NC;
                }
                if(size==-1)return NC;
                bId=0;
            }
            return (char)buf[bId++];
        }

        public int nextInt() {
            return (int)nextLong();
        }

        public long nextLong() {
            num=1;
            boolean neg = false;
            if(c==NC)c=nextChar();
            for(;(c<'0' || c>'9'); c = nextChar()) {
                if(c=='-')neg=true;
            }
            long res = 0;
            for(; c>='0' && c <='9'; c=nextChar()) {
                res = (res<<3)+(res<<1)+c-'0';
                num*=10;
            }
            return neg?-res:res;
        }

        public double nextDouble() {
            double cur = nextLong();
            return c!='.' ? cur:cur+nextLong()/num;
        }

        public String next() {
            StringBuilder res = new StringBuilder();
            while(c<=32)c=nextChar();
            while(c>32) {
                res.append(c);
                c=nextChar();
            }
            return res.toString();
        }

        public String nextLine() {
            StringBuilder res = new StringBuilder();
            while(c<=32)c=nextChar();
            while(c!='\n') {
                res.append(c);
                c=nextChar();
            }
            return res.toString();
        }

        public boolean hasNext() {
            if(c>32)return true;
            while(true) {
                c=nextChar();
                if(c==NC)return false;
                else if(c>32)return true;
            }
        }

        public int[] nextIntArray(int n) {
            int[] res = new int[n];
            for(int i = 0; i < n; i++) res[i] = nextInt();
            return res;
        }

    }

}