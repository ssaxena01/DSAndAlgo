package DynamicProgramming;


import java.util.Scanner;

/**
 * Given the total sum and coin denominations, find the minimum number of coins that can be used to get to this sum.
 * http://www.ideserve.co.in/learn/minimum-number-of-coins-to-make-change
 *
 * Extra credit :
 * Given the total sum and the coin denominations, print all the minimum ways the sum be
 * formed with the given coin denominations.
 */


public class MinChange {

    /* Recursive - Define recurrence relation, if you can.
     */
    public static int getMinCoins(int sum, int val[]) {
        if (sum == 0){
            return 0;
        }

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < val.length; i++) {
            if (sum >= val[i]) {
                min = Math.min(min, getMinCoins((sum - val[i]), val));
            }
        }

        if(min != Integer.MAX_VALUE){
            return min + 1;
        }

        return min;
    }

    public static int getMinCoinsDP(int sum, int [] coin){
        int min = 0;
        int minChangeCache[] = new int[sum +1];

        minChangeCache[0] = 0;

        for(int iSum = 1; iSum <= sum; iSum++){
            min = Integer.MAX_VALUE;
            for (int j = 0; j < coin.length; j++){
                if(iSum >= coin[j]){
                    min = Math.min(min, minChangeCache[iSum - coin[j]]);
                }

                if(min != Integer.MAX_VALUE){
                    minChangeCache[iSum] = min + 1;
                } else {
                    minChangeCache[iSum] = Integer.MAX_VALUE;
                }
            }
        }

        return minChangeCache[sum];
    }

    public  static void main(String [] args){
       /* int coins [] = { 2, 3, 5};
        int sum = 7;

        int minChange = getMinCoins(sum, coins);

        System.out.println(minChange);

        int change = getMinCoinsDP(sum, coins);

        System.out.println(change);*/

        Scanner in = new Scanner(System.in);

        int targetSum = 0;
        int denomination [];
        System.out.println("Enter sum ");
        try{
            targetSum = in.nextInt();
        } catch (Exception e){
            e.printStackTrace();
        }

        int total = 0;
        System.out.println("total coins");
        try {
            total = in.nextInt();
        }catch(Exception e){
            e.printStackTrace();
        }

        denomination = new int [total];
       for(int i = 0 ; i < total; i++){

            denomination[i] = in.nextInt();

        }

        int change = getMinCoinsDP(targetSum, denomination);

        System.out.println("total Change " + change);

    }
}
