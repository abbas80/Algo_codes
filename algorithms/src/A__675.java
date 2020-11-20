
//package cf;
import java.io.*;
import java.util.*;
public class A__675 {
    static int p=1000000007;
    public static void main(String[] args) throws Exception{
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);
        FastReader sc=new FastReader();
        int t=sc.nextInt();
        StringBuilder sb=new StringBuilder();
        while(t-->0)
        {
            int a=sc.nextInt();
            int b=sc.nextInt();
            int c=sc.nextInt();
            Set<Integer> s=new HashSet<>();
            s.add(a);
            s.add(b);
            s.add(c);
            int d1=a+b-c;
            int d2=a+c-b;
            int d3=b+c-a;
            d1++;
            d2++;
            d3++;
            if(!s.contains(d1)&&d1>0)
            {

                sb.append(d1+"\n");
            continue;
            }
            if(!s.contains(d2)&&d2>0)
            {

                sb.append(d2+"\n");
                continue;
            }
            if(!s.contains(d3)&&d3>0)
            {

                sb.append(d3+"\n");
                continue;
            }
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

