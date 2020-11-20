import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class test {
    private static int f=0;
    private static int ans=0;

    public static void main (String[]args){
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        sc.nextLine();
        char ar[]=sc.nextLine().toCharArray();

        int x[]=new int[m];
        int y[]=new int[m];
        for(int i=0;i<m;i++)
        {
            x[i]=sc.nextInt();

        }
        for(int i=0;i<m;i++)
        {
            y[i]=sc.nextInt();
        }
        List<Integer> adj[]=new ArrayList[n+1];
        for(int i=0;i<=n;i++)
        {
           adj[i]=new ArrayList<>();
        }
        for(int i=0;i<m;i++)
        {
            adj[x[i]].add(y[i]);
        }
        boolean vis[ ]=new boolean[n+1];
        int fr[]=new int[26];
        for(int i=1;i<=n;i++)
        {
            if(vis[i]==false)
            {
                dfs(vis,ar,adj,i,fr);
            }
        }
        if(f==1)
        {
            System.out.println(-f);
        }
        else
        {
            System.out.println(ans);
        }
    }

    private static void dfs(boolean[] vis, char ar[],List<Integer>[] adj, int i, int[] fr) {
        /*if(vis[i])*/
        vis[i]=true;
        System.out.println(i+" --->");
       // System.out.println(Arrays.toString(fr));
        if(f==1)
            return;
        fr[ar[i-1]-'a']=fr[ar[i-1]-'a']+1;
       // System.out.println(Arrays.toString(fr));
        for(int v:adj[i])
        {
            if(vis[v])
            {
                f=1;
                return;
            }
            dfs(vis,ar,adj,v,fr);
        }
        for(int it:fr)
        {
            ans=Math.max(ans,it);
        }
        fr[ar[i-1]-'a']=fr[ar[i-1]-'a']-1;
    }

}
