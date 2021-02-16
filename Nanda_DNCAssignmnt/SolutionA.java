import java.util.Scanner;

public class SolutionA {
    public static int searchElement(int[] a){
        //Binary search logic modified
        int start = 0;
        int end = a.length - 1;
        while(start<=end){
            int mid = start + (end-start) / 2;
            //Initialise the index
            int key = mid + 1;
            //Check if the index and the element are
            //same and also, check if the prev element
            //and index are not same (checking for
            // first occurrence where element and index
            // are same)
            if(key == a[mid] && (mid == 0 || key-1 != a[mid-1])){
                return mid+1;
            //if prev element and index are same
            //proceed to search to the left half of
            //the array
            }else if (key == a[mid]){
                end = mid - 1;
            //if element and index are not same
            //proceed to right half of the array
            }else{
                start = mid + 1;
            }
        }
        return  -1;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //Length of the array
        int n = input.nextInt();
        //Elements of the array
        int[] arr = new int[n];
        for(int i = 0; i<n ; i++){
            arr[i] = input.nextInt();
        }
        //Calling the function
        int res = searchElement(arr);
        //Check if the element with
        //same index is found
        if(res < 0){
            System.out.println("NOT_FOUND");
        }else{
            System.out.println(res);
        }
    }
}
