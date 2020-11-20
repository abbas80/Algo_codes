import java.util.*;

class GFG {
    public static void main (String[] args) {
        Scanner sc=new Scanner (System.in);
        int n = 4;
        int m = 4;
        int mt[][]={{2, 2, 6,7}, {7, 6, 2,1}, {1, 7,1,6},{7,1,2,6} };
        List<Integer> fr[]=new ArrayList[5];

        HashMap<Integer,Integer> freq=new HashMap<>();
        for(int i=0;i<5;i++)
        {
            fr[i]=new ArrayList<>();
        }
           /*
Edge cases:

3 4
5 5 5 5
5 5 5 5
5 5 5 5

3 4
5 5 1 5
5 5 5 5
5 1 5 5

3 4
5 1 5
9 5 5
1 5 5
             */

        //counting the frequency of all elements
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                freq.put(mt[i][j],freq.getOrDefault(mt[i][j],0)+1);
            }
        }
        boolean no=false;
        //mapping the to frequency list of  rang 1 ,2-3  and 4 -INF , and adding them
        for(Map.Entry<Integer,Integer> i:freq.entrySet())
        {
            int k=i.getKey();
            int val=i.getValue();
            if(val>=4||val%4==0)
            {
                fr[4].add(k);
            }
            else if(val>=2)
            {
                fr[2].add(k);
            }
            else if(val==1&&fr[1].size()==0)
            {
                fr[1].add(k);
            }
            else
            {
                no=true;
                break;
            }
        }
        //putting add 4 elements at their positions
        for(int i=0;i<n/2&&no==false;i++)
        {
            for(int j=0;j<m/2&&no==false;j++)
            {
                if(fr[4].size()==0)
                {
                    no=true;
                break;
                }

                int cur_element=fr[4].get(0);
                freq.put(cur_element,freq.get(cur_element)-4);
                if(freq.get(cur_element)==0)
                    fr[4].remove(0);
                //if its frequency decreases to range of 2 them transfer
                if(freq.get(cur_element)==2)
                {
                    fr[2].add(cur_element);
                    fr[4].remove(0);
                }

                //if its frequency decreases to range of 1 them transfer
                else if(freq.get(cur_element)==1)
                {
                    fr[1].add(cur_element);
                    fr[4].remove(0);
                }
                mt[i][j]=cur_element;
                mt[i][m-1-j]=cur_element;
                mt[n-1-i][j]=cur_element;
                mt[n-1-i][m-1-j]=cur_element;
            }
        }

        //transfer elements in 4 frequency to 2 for further use
        fr[2].addAll(fr[4]);
        fr[4].clear();
        //m is odd
        if(m%2!=0)
        {
            for(int i=0;i<n/2&&no==false;i++)
            {
                if(fr[2].size()==0)
                {
                    no=true;
                break;
                }

                int cur_element=fr[2].get(0);
                freq.put(cur_element,freq.get(cur_element)-2);
                if(freq.get(cur_element)==0)
                    fr[2].remove(0);

                //if its frequency decreases to range of 1 them transfer
                if(freq.get(cur_element)==1)
                {
                    fr[1].add(cur_element);
                    fr[2].remove(0);
                }

                mt[i][m/2]=cur_element;
                mt[n-1-i][m/2]=cur_element;
            }
        }
        //n is odd
        if(n%2!=0)
        {

            for(int j=0;j<m/2&&no==false;j++)
            {
                if(fr[2].size()==0)
                {
                    no=true;
                break;
                }

                int cur_element=fr[2].get(0);
                freq.put(cur_element,freq.get(cur_element)-2);
                if(freq.get(cur_element)==0)
                    fr[2].remove(0);

                //if its frequency decreases to range of 2 them transfer
                if(freq.get(cur_element)==1)
                {
                    fr[1].add(cur_element);
                    fr[2].remove(0);
                }
                mt[n/2][j]=cur_element;
                mt[n/2][m-1-j]=cur_element;
            }
        }

        //transfer elements in 2 frequency to 1 for further use
        fr[1].addAll(fr[2]);
        fr[2].clear();
       //if both are odd
        if(n%2!=0&&m%2!=0)
        {
            mt[n/2][m/2]=fr[1].get(0);
        }
        if(no)
        {
            System.out.println("NO");
        }
        else
        {
            System.out.println("YES");
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<m;j++)
                {
                    System.out.print(mt[i][j]+" ");
                }
                System.out.println();
            }

        }
    }
}
