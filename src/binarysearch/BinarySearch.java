/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarysearch;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author ^_^
 */
public class BinarySearch {

    /**
     * This is my recursive re-write of the books version of iterative binary
     * search
     *
     * @param data the sorted array to be searched
     * @param searchKey the value to be looked for
     * @param startingIndex the current starting position of the search
     * @param endingIndex the current ending position
     * @return the position in the array or -1 if not found
     */
    public static int recursiveSearchBradford(int[] data, int searchKey, int startingIndex, int endingIndex) {
        //calculate new middle with every function call
        int middle = (startingIndex + endingIndex) / 2;

        // print remaining elements of array
        System.out.print(remainingElements(data, startingIndex, endingIndex));

        //if the key is less than the start it is not in the array
        if (endingIndex < startingIndex) {
            return -1;
        }

        // if the element is found at the middle 
        if (searchKey == data[middle]) {
            return middle; // key is the current middle
        } else if (searchKey < data[middle]) {// middle element is too high 
            // eliminate the higher half and try again
            return recursiveSearchBradford(data, searchKey, startingIndex, middle - 1);
        } else {// middle element is too low
            // eliminate the lower half and try again
            return recursiveSearchBradford(data, searchKey, middle + 1, endingIndex);
        }
    }

    /**
     * This is the books version of iterative binary search
     *
     * @param data the sorted array to be searched
     * @param key the value to be looked for
     * @return the position in the array or -1 if not found
     */
    public static int binarySearch(int[] data, int key) {

        int low = 0;// low end of the search area 
        int high = data.length - 1;// high end of the search area 
        int middle = (low + high + 1) / 2;// Middle
        int location = -1;// return value; -1 if not found

        do {
            // print remaining elements of array
            System.out.print(remainingElements(data, low, high));

            // output spaces for alignment
            for (int i = 0; i < middle; i++) {
                System.out.print("  ");
            }
            System.out.println(" * ");// indicate current middle

            // if the element is found at the middle 
            if (key == data[middle]) {
                location = middle;// location is the current middle 
            } else if (key < data[middle])// middle element is too high 
            {
                high = middle - 1;// eliminate the higher half 
            } else// middle element is too low
            {
                low = middle + 1;// eliminate the lower half
            }

            middle = (low + high + 1) / 2;// re-calc mid val
        } while ((low <= high) && (location == -1));

        return location;
    }

    /**
     *  my modification of the book method to format string output
     * I did not think it was good to have console output happening all
     * over when it could be done cleaner in one place
     *
     * @param data the sorted array to be searched
     * @param low low end of the search area
     * @param high high end of the search area
     * @return a formatted string
     */
    private static String remainingElements(int[] data, int low, int high) {
        StringBuilder temporary = new StringBuilder();

        // append spaces for alignment 
        for (int i = 0; i < low; i++) {
            temporary.append(" ");
        }

        // append elements left in array 
        for (int i = low; i <= high; i++) {
            temporary.append(data[i]).append(" ");
        }
        int temp = temporary.length();
        temporary.append("\n");

        for (int i = 0; i < (temp/2) + (low/2); i++) {
            temporary.append(" ");
        }
        
        temporary.append("*");

        return String.format("%s%n", temporary);
    }// end method remainingElements

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //user input of search key
        Scanner input = new Scanner(System.in);
        SecureRandom generator = new SecureRandom();
        int[] data = new int[15];// create array 
        for (int i = 0; i < data.length; i++)// populate array 
        {
            data[i] = 10 + generator.nextInt(90);
        }

        Arrays.sort(data);// binarySearch requires sorted array

        System.out.printf("%s%n%n", Arrays.toString(data));// display array 
        // get input from user 
        System.out.print("Please enter an integer value (-1 to quit):");
        int searchInt = input.nextInt();

        // repeatedly input an integer; -1 terminates the program 
        while (searchInt != -1) {
            // perform search 
            int location = recursiveSearchBradford(data, searchInt, 0, data.length - 1);//binarySearch(data, searchInt);
            if (location == -1)// not found 
            {
                System.out.printf("%d was not found%n%n", searchInt);
            } else// found 
            {
                System.out.printf("%d was found in position %d%n%n",
                        searchInt, location);
            }
            // get input from user 
            System.out.print("Please enter an integer value (-1 to quit):");
            searchInt = input.nextInt();
        }
    }
}
