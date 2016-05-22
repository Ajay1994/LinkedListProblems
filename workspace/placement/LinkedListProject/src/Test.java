import java.io.*;
import java.util.*;
import java.math.*;

public class Test{

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int t, n, i, j, k;
        List<Integer> rating = new ArrayList<Integer>();
        int days = 0;
        int prob;
        t = scan.nextInt();
        for(i = 0; i < t; i++){
            n = scan.nextInt();
            k = scan.nextInt();
            for(j = 0; j<n; j++){
                rating.add(scan.nextInt());
            }
            days = 0;
            int lastSolved;
            while(!rating.isEmpty()){
                 if(rating.isEmpty())
                    break;
                 lastSolved = rating.remove(0);
                 days++;
               
                j = 0;
                while(j < rating.size() && !rating.isEmpty()){
                prob = rating.get(j);
                if(Math.abs(lastSolved - prob) >= k){
                    lastSolved = prob;
                    rating.remove(j);
                }
                    else{
                    j++;
                    }    
                }
            }
            System.out.println(days);
        }
    }
}
