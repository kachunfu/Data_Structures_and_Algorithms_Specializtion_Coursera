package fibonacciNumberAgainWeek2Q8;

import java.util.Scanner;
import java.util.Vector;

public class App2BestSolution {
	
	 static final int MOD = 1000000007;
	    static long n;
	    static long f = 0, s = 1, t, sum = 1, i, j = 0, k;

	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        n = sc.nextLong();
	        Vector<Long> v = new Vector<>();
	        v.add(0L);
	        v.add(1L);
	        if (n <= 1) {
	            System.out.println(n);
	        } else {
	            for (i = 2;; i++) 
	            {
	                t = (f + s) % 10;
	                sum = (sum + t * t) % 10;
	                f = s;
	                s = t;
	                v.add(sum % 10);
	                
	                //Help to understand
	                System.out.println("At increment i : " + i + " , j = " + j + " , sum%10 = " + sum %10);
	                
	                if (v.get((int) i).equals(v.get((int) j))) 
	                {
	                    j++;
	                    if ((i + 1) / 2 == j && i % 2 == 1) break;
	                } 
	                else 
	                {
	                    j = 0;
	                    if (v.get((int) i).equals(v.get((int) j))) j++;
	                }
	                
	            }
	            //Help to illustrate
	            System.out.println("j = " + j);
	            System.out.println("i = " + i);
	            
	            /*!!!!!!!!!!!SUPER IMPORTANT!!!! BECAUSE F2015 mod 3 = F2015%8 mod 3 !!!!
	            Thus, Fn mod 10 = F (n % 60/2) mod 10*/
	            n %= j;
	            System.out.println("n = " + n);
	            System.out.println("ans = " + v.get((int) n));
	            System.out.println(v);
	        }
	        sc.close();
	    }

}
