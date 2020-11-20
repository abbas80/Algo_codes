// Java program to find modular inverse
// of a under modulo m
import java.io.*;

class GFGg {

    // A naive method to find modulor
    // multiplicative inverse of 'a'
    // under modulo 'm'
    static long modInverse(long a, long m)
    {
        a = a % m;
        for (long x = 1; x < m; x++)
            if ((a * x) % m == 1)
                return x;
        return 1;
    }

    // Driver Code
    public static void main(String args[])
    {
        long a = 2, m = 1l<<31;

        // Function call
        System.out.println(modInverse(a, m));
    }
}

/*This code is contributed by Nikita Tiwari.*/
