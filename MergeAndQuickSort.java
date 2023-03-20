import java.util.Random;
import java.lang.Math;

public class MergeAndQuickSort {

    static long counter;

    //@https://www.geeksforgeeks.org/shuffle-a-given-array-using-fisher-yates-shuffle-algorithm/
    public static void shuffle(int[] array) { // Fisher-Yates Shuffle Algorithm
        Random r = new Random();
        int j;
        int temp;
        for (int i = array.length - 1; i > 0; i--) {
            j = r.nextInt(i+1);
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static int[] mergeSort(int[] list, int first, int last) {
        counter = 0;
        
        if (first < last) {
            int middle = (first + last) / 2;
            mergeSort(list, first, middle);
            mergeSort(list, middle+1, last);
            return mergeLists(list, first, middle, middle + 1, last);
        }
        return list;
    }

    public static int[] mergeLists(int[] list, int start1, int end1, int start2, int end2) {
        int finalStart = start1;
        int finalEnd = end2;
        int indexC = 0;
        int[] result = new int[list.length];

        counter++;
        while (start1 <= end1 && start2 <= end2) {
            counter++;
            if (list[start1] < list[start2]) {
                result[indexC++] = list[start1++];
            } else {
                result[indexC++] = list[start2++];
            }
        }

        //move the part of the list that is left over

        counter++;
        if (start1 <= end1) {
            for (int i = start1; i <= end1; i++) {
                result[indexC++] = list[i];
            }
        } else {
            for (int i = start2; i <= end2; i++) {
                result[indexC++] = list[i];
            }
        }

        // now put the result back into the list

        indexC = 0;
        for (int i = finalStart; i <= finalEnd; i++) {
            list[i] = result[indexC++];
        }
        return list;
    }

    public static int[] quickSort(int[] list, int first, int last) {

        if (first < last) {
            int pivot = pivotList(list, first, last);
            quickSort(list, first, pivot - 1);
            quickSort(list, pivot + 1, last);
        }
        return list;
    }

    // public static int pivotList(int[] list, int first, int last) { // pivot = first index of list
        
    //     int pivotValue = list[first];
    //     int pivotPoint = first;
        
    //     for (int i = first; i < last; i++) {
    //         counter++;
    //         if (list[i] < pivotValue) {
    //             pivotPoint++;
    //             //swap
    //             int temp = list[pivotPoint];
    //             list[pivotPoint] = list[i];
    //             list[i] = temp;
    //         }
    //     }
    //     //swap first and pivotPoint to complete the sorting
    //     int swapTemp = list[first];
    //     list[first] = list[pivotPoint];
    //     list[pivotPoint] = swapTemp;
    //     return pivotPoint;
    // }

    public static int pivotList(int[] list, int first, int last) { // pivot = random index of list
        
        int range = last - first;
        int random = (int) (Math.random() * range) + first;
        int pivotValue = list[random];
        int pivotPoint = first;
        
        for (int i = first; i < last; i++) {
            counter++;
            if (list[i] < pivotValue) {
                pivotPoint++;
                //swap
                int temp = list[pivotPoint];
                list[pivotPoint] = list[i];
                list[i] = temp;
                
            }
        }
        //swap random and pivotPoint to complete the sorting
        int swapTemp = list[random];
        list[random] = list[pivotPoint];
        list[pivotPoint] = swapTemp;
        return pivotPoint;
    }

    // public static int pivotList(int[] list, int first, int last) { // pivot = last index of list
    
    //     int pivotValue = list[last];
    //     int pivotPoint = last;
        
    //     for (int i = first; i < last; i++) {
    //         counter++;
    //         if (list[i] < pivotValue) {
    //             pivotPoint--;
    //             //swap
    //             int temp = list[pivotPoint];
    //             list[pivotPoint] = list[i];
    //             list[i] = temp;
    //         }
    //     }
    //     //swap last and pivotPoint to complete the sorting
    //     int swapTemp = list[last];
    //     list[last] = list[pivotPoint];
    //     list[pivotPoint] = swapTemp;
    //     return pivotPoint;
    // }

    public static void main(String args[]) {
        // int[] list = {6,2,4,7,1,3,8,5};

        // for (int i = 0; i < list.length; i++) {
        //     System.out.println(list[i]);
        // }

        // System.out.println("unsorted vs sorted");

        // quickSort(list, 0, list.length - 1);

        // for (int i = 0; i < list.length; i++) {
        //     System.out.println(list[i]);
        // }

        // System.out.println("counter = " + counter);

        int maxExp = 14;
        // Merge Sort w/ Proper Sample Size
        System.out.println("Merge Sort\n");
        System.out.println("Sample Size\tAscending\tRandom\t\tDescending");

        for (int i = 0; i <= maxExp; i++) {
            int size = (int) Math.pow(2, i);
            int[] list = new int[size];

            // Ascending
            for (int j = 0; j < size; j++) {
                list[j] = j;
            }
            counter = 0;
            quickSort(list, 0, list.length - 1);
            if (i < 13) {
                System.out.print(size + "\t\t" + counter + "\t\t");
            } else {
                System.out.print(size + "\t\t" + counter + "\t");
            }

            //Random
            shuffle(list);
            counter = 0;
            quickSort(list, 0, list.length - 1);
            if (i < 13) {
                System.out.print(counter + "\t\t");
            } else {
                System.out.print(counter + "\t\t");
            }


            // Descending
            for (int j = size - 1; j >= 0; j--) {
                list[j] = j;
            }
            counter = 0;
            quickSort(list, 0, list.length - 1);
            System.out.println(counter);
        }
    }
}