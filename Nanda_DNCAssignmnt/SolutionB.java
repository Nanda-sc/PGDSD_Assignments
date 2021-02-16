import java.util.Scanner;

public class SolutionB {
    public static int findUpperBound(int [] ar, int key){
        int start;
        int end = 1;
        int val = ar[0];
        int res ;
        // loop through the elements of the
        // given array to find the upper
        // bound of the array
        while(key > val){
            start = end;
            //Check for index out of bound exception
            if(2*end < ar.length - 1) {
                end = 2 * end;
                //Function call
                res = searchElement(ar, key, start, end);
                if(res > 0){
                    return res;
                }
            //if the calculated end (2*end) has
            //crossed the upper bound of the array
            //initialize end to the length of array
            }else{
                end = ar.length - 1;
                //Function call
                res = searchElement(ar, key, start, end);
                return res;
            }
            val = ar[end];
        }
        return -1;
    }
    //function which implements binary search logic
    private static int searchElement(int[] ar, int key, int start, int end){
        while(start <= end){
            int mid = start + (end - start)/ 2;
            if(key == ar[mid]){
                return mid;
            }else if (key < ar[mid]){
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //Length of the array
        int n = input.nextInt();
        //Element to be searched
        int key = input.nextInt();
        //Elements of the array
        int[] arr = new int[n];
        arr[0] = 0;
        for(int i = 1; i<n ; i++){
            arr[i] = input.nextInt();
        }
        //Calling the function
        int res = findUpperBound(arr, key);
        //Check if the element was found
        if(res < 0){
            System.out.println("NOT_FOUND");
        }else{
            System.out.println(res);
        }
    }
}
