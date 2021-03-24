import java.util.*;
import java.util.stream.IntStream;

class WeiRand
{

    private static int[] nums;
    private static int[] weights;

    public WeiRand(int[] nums, int[] weights)
    {
        this.nums=nums;
        this.weights=weights;
    }

    public int RandNum()
    {
        int tot=0;
        int n=0;
        Random rn=new Random();

        for(int i=0; i<weights.length; i++)
        {
            tot+=weights[i];

        }

        int randWeight=rn.nextInt(tot);
        for(int i=0; i<nums.length; i++)
        {
            n+=weights[i];
            if(n>=randWeight)
            {
                return nums[i];
            }
        }

        return nums[0];//nums[i];


    }
}

class Main {

    public static boolean ArrFind(int[] arr,int num)
    {
        for(int i=0; i<arr.length; i++)
        {
            if(arr[i]==num)
            {
                return true;
            }
        }
        return false;

    }


    public static void main(String[] args) {
        int[]nums=new int[20];
        int[]weights=new int[20];
        int new_num=0;
        Random rn=new Random();
        for(int i=0; i<10; i++)
        {
            new_num=rn.nextInt(50);
            while (ArrFind(nums,new_num)==true)
            {
                new_num=rn.nextInt(50);
            }
            nums[i]=new_num;


            weights[i]=rn.nextInt(10);
            System.out.println(i+1+": Число: "+nums[i]+", вес: "+weights[i]);

        }


        System.out.println("----------------------------");
        WeiRand weirand=new WeiRand(nums, weights);
        for(int i=0; i<nums.length; i++)
        {
            System.out.println(weirand.RandNum());
        }
    }
}